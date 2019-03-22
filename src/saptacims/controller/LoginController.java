package saptacims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pub.util.DateUtil;
import saptacims.service.ILoginService;
import saptacims.vo.base.LoginMsg;
import saptacims.exception.AppException;
import saptacims.model.TbUser;

/**
 * 登录
 * @author Fjj
 *
 */
@Controller
public class LoginController {
	
	@Resource
	private ILoginService loginService;
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
//	@RequestMapping(value="/")
//	public String getloginDefault()
//	{
//		return "/login";
//	}
	
	@RequestMapping(value="/login")
	public String getlogin()
	{
		return "/login";
	}
	
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public @ResponseBody JSONObject loginSystem(HttpServletRequest request,HttpSession session)
	{
		
		LoginMsg loginMsg = (LoginMsg)session.getAttribute("loginMsg");
		JSONObject saveJosn = new JSONObject();
		if(loginMsg ==null){
			/*logger.info("系统登录:signin");*/
			String ACCOUNT=request.getParameter("ACCOUNT");
			String password=request.getParameter("password");
			String code=request.getParameter("code");
			try{
				TbUser currentUser = loginService.signIn(ACCOUNT, password,code);
				if(currentUser !=null){
					session.setAttribute("currentUser", currentUser);
					saveJosn.put("sgin", "登录成功");
				}
			}catch(AppException e){
				logger.info("登录失败："+e.getMessage());
				if(e.getErrorCode() ==10010){
					LoginMsg msg =new LoginMsg();
					msg.setCount(1);
					session.setAttribute("loginMsg", msg);
				}
				saveJosn.put("msg", e.getMessage());
			}
			}else{
				int count = loginMsg.getCount();
				Properties p =new Properties();
				try {
					p.load(LoginController.class.getResourceAsStream("/properties/login.properties"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int maxCount =Integer.valueOf(p.getProperty("count"));
				if(count <= maxCount){
					String ACCOUNT=request.getParameter("ACCOUNT");
					String password=request.getParameter("password");
					String code=request.getParameter("code");
					try{
						TbUser currentUser = loginService.signIn(ACCOUNT, password,code);
						if(currentUser !=null){
							session.removeAttribute("loginMsg");
							session.setAttribute("currentUser", currentUser);
							saveJosn.put("sgin", "登录成功");
						}
					}catch(AppException e){
						if(e.getErrorCode() ==10010){
							count =count+1;
							if(count <=maxCount){
								loginMsg.setCount(count);
								session.setAttribute("loginMsg", loginMsg);
								saveJosn.put("msg", e.getMessage());
							}else{
								loginMsg.setCount(count);
								loginMsg.setDate(new Date());
								session.setAttribute("loginMsg", loginMsg);
								long timelimit =Long.parseLong(p.getProperty("timeLimit"));
								int timeLimit =Math.round(timelimit/60/1000);
								saveJosn.put("msg", "登录错误超过"+maxCount+"次,需在"+timeLimit+"分钟之后登录");
							}
						}else{
							saveJosn.put("msg", e.getMessage());
						}
						
					}
				}else{
					long oldTime = loginMsg.getDate().getTime();
					long currentTime = new Date().getTime();
					long passTime =currentTime-oldTime;
					long timelimit =Long.parseLong(p.getProperty("timeLimit"));
					if(passTime >timelimit){
						session.removeAttribute("loginMsg");
						String ACCOUNT=request.getParameter("ACCOUNT");
						String password=request.getParameter("password");
						String code=request.getParameter("code");
						try{
						TbUser currentUser = loginService.signIn(ACCOUNT, password,code);
						if(currentUser !=null){
							session.setAttribute("currentUser", currentUser);
							saveJosn.put("sgin", "登录成功");
						}
						}catch(AppException e){
							logger.info("登录失败："+e.getMessage());
							if(e.getErrorCode() ==10010){
								LoginMsg msg =new LoginMsg();
								msg.setCount(1);
								session.setAttribute("loginMsg", msg);
							}
							saveJosn.put("msg", e.getMessage());
						}
				}else{
					
					long remainTime =timelimit -passTime;
					String time = DateUtil.formatTime(remainTime);
					saveJosn.put("msg", "剩余时间:"+time);
				}
			}
			}
			return saveJosn;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession();
//		request.getSession().setAttribute("currentUser", null);
		session.removeAttribute("currentUser");
		session.invalidate();
		logger.debug("logout");
		return "login";
	}
	
	@RequestMapping(value="/menulist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> menulist(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			int id=currentUser.getUserId();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(id !=0 ){
				if(currentUser.getAccount().equals("admin"))
				{
					list = loginService.getAllMenu();
				}else{
					list = loginService.getRoleMenuByUserId(id);
				}
			}
			map.put("menulist", list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return map;
	}
	
}

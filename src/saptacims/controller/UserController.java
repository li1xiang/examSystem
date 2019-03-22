package saptacims.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.util.ComboboxVO;
import pub.util.EncryptUtil;
import saptacims.cst.Gender;
import saptacims.cst.RoleNames;
import saptacims.cst.Status;
import saptacims.cst.StudClass;
import saptacims.model.TbUser;
import saptacims.model.TbUserRole;
import saptacims.service.IRoleService;
import saptacims.service.IUserService;
import saptacims.vo.base.UserVo;
import saptacims.vo.page.Pager;
import saptacims.vo.page.TbUserVo;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	private TbUser tbUser;


	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/newUser")
	public String newUser()
	{
		return "user/newUser";
	}
	
	@RequestMapping(value="/sexList")
	@ResponseBody
	public Object sexList()
	{
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Gender.class,String.valueOf(Gender.MALE));
		return volist;
	}

	@RequestMapping(value="/classList")
	@ResponseBody
	public Object classList()
	{
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(StudClass.class,String.valueOf(StudClass.LEVEL1));
		return volist;
	}
	@RequestMapping(value="roleNameList")
	@ResponseBody
	public  Object roleNames(){
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(RoleNames.class,String.valueOf(RoleNames.STUDENT));
		return volist;
	}
	
	@RequestMapping(value="/statusList")
	@ResponseBody
	public Object statusList()
	{
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Status.class,String.valueOf(Status.ENABLE));
		return volist;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject save(TbUser user, HttpSession session, TbUserRole tbUserRole)
	{
		JSONObject saveJson = new JSONObject();
		try {
			logger.info("--------保存用户开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			user.setCreateUser(currentUser.getUserId());
			boolean tag = userService.newAndSaveUser(user,tbUserRole);
			if(tag)
			{
				saveJson.put("msg", "保存成功");
			}else{
				saveJson.put("msg", "保存失败");
			}
			saveJson.put("saveFlag", tag);
		} catch (Exception e) {
			
			logger.info("保存失败");
			logger.error(e.getMessage(),e);
			saveJson.put("msg", e.getMessage());
			saveJson.put("saveFlag", false);
		}
		return saveJson;
	}
	
	
	@RequestMapping(value="/userManage")
	public String userManage(){
		return "user/userManage";
	}
	
	@RequestMapping(value="/userList")
	@ResponseBody
	public Map<String, Object> userList(Pager page,TbUser user)
	{
		return userService.userListPage(page, user);
	}
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public JSONObject deleteUser(HttpSession session,TbUser user)
	{
		JSONObject saveJson = new JSONObject();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			int tag = userService.userDelete(currentUser, user);
			if(tag == 0)
			{
				saveJson.put("msg", "失败");
			}else{
				saveJson.put("msg", "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return saveJson;
	}
	
	@RequestMapping(value="/updateUser")
	public String updateUser(Model model,int userId)
	{
		tbUser = userService.qryUserByID(userId);
		TbUserVo tbUserVo=new TbUserVo();
		String t=roleService.getStuRoleName(userId);
		tbUserVo.setRoleName(t);
		tbUserVo.setTbUser(tbUser);

		model.addAttribute("tbUserVo",tbUserVo);
		return "user/modUser";
	}
	
	@RequestMapping(value="/doUpdateUser")
	@ResponseBody
	public JSONObject doUpdateUser(TbUser user,HttpSession session)
	{
		JSONObject saveJson = new JSONObject();
		try {
			logger.info("------修改用户开始------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			user.setUpdateUser(currentUser.getUserId());
			user.setUpdateTime(new Date());
			int tag = userService.saveInModUser(user);
			if(tag==0)
			{
				saveJson.put("msg", "修改失败");
				saveJson.put("modFlag", false);
			}else{
				saveJson.put("msg", "修改成功");
				saveJson.put("modFlag", true);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			saveJson.put("msg", e.getMessage());
			saveJson.put("modFlag", false);
		}
		return saveJson;
	}
	
	@RequestMapping(value="/rsetPasswd")
	@ResponseBody
	public JSONObject rsetPasswd(TbUser user,HttpSession session)
	{
		JSONObject saveJson = new JSONObject();
		try {
			logger.info("------重置开始------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			user.setUserPassword(EncryptUtil.getHash("123456", EncryptUtil.HASHTYPE_MD5));
			user.setUpdateUser(currentUser.getUserId());
			user.setUpdateTime(new Date());
			int tag = userService.saveInModUser(user);
			if(tag==0)
			{
				saveJson.put("msg", "重置失败");
			}else{
				saveJson.put("msg", "重置成功");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			saveJson.put("msg", "重置失败");
		}
		return saveJson;
	}

	public TbUser getTbUser() {
		return tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("changePassword")
	public String tochangePassword(){
		return "user/modPwd";
	}
	
	@RequestMapping("doChangePwd")
	@ResponseBody
	public JSONObject doChangePwd(UserVo userVo,HttpSession session){
		JSONObject result = new JSONObject();
		try {
			TbUser currentUser = (TbUser)session.getAttribute("currentUser");
			int modCount = userService.modifyUserPwd(userVo, currentUser);
			if(modCount > 0){
				result.put("modFlag", true);
				result.put("msg", "密码修改成功");
			} else{
				result.put("modFlag", false);
				result.put("msg", "密码修改失败");
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put("modFlag", false);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@RequestMapping("/getQueryUser")
	public String queryUser(){
		logger.info("=============用户查询==============");
		return "user/queryUser";
	}
	
	/**
	 * @param page
	 * @param user
	 * @return
	 * 用户查询
	 */
	@RequestMapping(value="/queryUserList")
	@ResponseBody
	public Map<String, Object> queryUserList(Pager page,TbUser user)
	{
		return userService.queryUserListPage(page, user);
	}
	
	@RequestMapping("/queryMyMessage")
	public String queryMyMsg(HttpSession session,Model model){
		logger.info("===========我的信息查看============");
		TbUser currentUser = (TbUser) session.getAttribute("currentUser");
		TbUser tbUser = userService.qryUserByID(currentUser.getUserId());
		model.addAttribute("tbUser",tbUser);
		return "user/queryMyMessage";
	}
	
	/**
	 * 我的信息修改
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateMyMessage")
	@ResponseBody
	public JSONObject updateMyMessage(TbUser user,HttpSession session)
	{
		JSONObject saveJson = new JSONObject();
		try {
			logger.info("------我的信息修改------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			user.setUpdateUser(currentUser.getUserId());
			user.setUpdateTime(new Date());
			int tag = userService.saveInModUser(user);
			if(tag==0)
			{
				saveJson.put("msg", "修改失败");
				saveJson.put("modFlag", false);
			}else{
				saveJson.put("msg", "修改成功");
				saveJson.put("modFlag", true);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			saveJson.put("msg", e.getMessage());
			saveJson.put("modFlag", false);
		}
		return saveJson;
	}
	@RequestMapping("/getUserDetail")
	public String getUserDetail(Model model,Integer userId){
		logger.info("============明细操作:" + userId);
		TbUser tbUser = userService.qryUserByID(userId);
		model.addAttribute("tbUser", tbUser);
		return "user/userDetail";
	}
}

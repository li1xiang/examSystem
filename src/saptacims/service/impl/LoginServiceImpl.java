package saptacims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saptacims.dao.base.TbMenuMapper;
import saptacims.dao.base.TbUserMapper;
import saptacims.service.ILoginService;
import pub.util.EncryptUtil;
import saptacims.cst.ActiveStatus;
import saptacims.exception.Err;
import saptacims.exception.error;
import saptacims.model.TbMenu;
import saptacims.model.TbMenuExample;
import saptacims.model.TbUser;

@Service
public class LoginServiceImpl implements ILoginService{

	private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private TbUserMapper usermapper;
	
	@Autowired
	private TbMenuMapper menuMapper; 
	

/*	@Override
	public String loginOut(String userId) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	public TbUser signIn(String ACCOUNT,String password,String code) {
		logger.info("查找用户");
		//判断用户是否存在
		List<TbUser> users=usermapper.selectByAccount(ACCOUNT);
		if(users.size()==0){
			logger.info("失败的用户:"+ACCOUNT);
			Err.checkError(true, error.USER_NOT_EXIT);
		}else{
			//在保存用户时回去判断account这个字段是否是唯一
			TbUser user = users.get(0);
			//判断用户是否有效
			if(user.getUserStatus().equals(ActiveStatus.CANCEL)){
				Err.checkError(true, error.USER_STATUS);
				logger.info("失败的用户:"+ACCOUNT+",原因:用户失效");
			}else{
				password = EncryptUtil.getHash(password, EncryptUtil.HASHTYPE_MD5);
				if(!password.equals(user.getUserPassword())){
					logger.info("登陆密码错误");
					Err.checkError(true, error.LOGINUSERID_PWD);
				}else{
					//List<Function> funcLists=getFunc(user.getUserid());
					return user;
					//session.setAttribute("currentUser", user);//保存当前登录用户
					//session.setAttribute("Function",funcLists);//保存用户权限
				}
			}
		}
		return null;
	}

	
	@Override
	public String loginOut(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getAllMenu() {
		TbMenuExample example = new TbMenuExample();
		saptacims.model.TbMenuExample.Criteria c = example.createCriteria();
		c.andMenuParentidEqualTo(0);
		example.setOrderByClause("MENU_PARENTID,SORT");
		List<TbMenu> list = menuMapper.selectByExample(example);
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			TbMenu topMenu = list.get(i);
			TbMenuExample exampleFoeSecond = new TbMenuExample();
			saptacims.model.TbMenuExample.Criteria cForSencond = exampleFoeSecond.createCriteria();
			cForSencond.andMenuParentidEqualTo(topMenu.getMenuId());
			exampleFoeSecond.setOrderByClause("MENU_PARENTID,SORT");
			List<TbMenu> secondMenus = menuMapper.selectByExample(exampleFoeSecond);
			map.put("topMenu", topMenu.getMenuName());
			map.put("secondMenu", secondMenus);
			listmap.add(map);
		}
		return listmap;
	}
	
	@Override
	public List<Map<String, Object>> getRoleMenuByUserId(int userId) {
		List<TbMenu> topMenus = menuMapper.getMenuByUserId(userId);
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < topMenus.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			TbMenu topMenu = topMenus.get(i);
			List<TbMenu> secondMenus = menuMapper.getSecondMenuByUserId(topMenu.getMenuId(),userId);
			map.put("topMenu", topMenu.getMenuName());
			map.put("secondMenu", secondMenus);
			listmap.add(map);
		}
		return listmap;
	}
	

	public static void main(String[] args) {
		System.out.println(EncryptUtil.getHash("admin", "MD5"));
	}
//	private List<Function> getFunc(String userId){
//		List<UserPrvl> prvlLists=userPrvlMapper.getFunById(userId);
//		List<Function> funcLists=new ArrayList<Function>();
//		if(prvlLists!=null&&prvlLists.size()>0){
//			for(UserPrvl prvl:prvlLists){
//				Function func=new Function();
//				func=funcmapper.selectByPrimaryKey(prvl.getPrvlId());
//				if(func!=null)
//					funcLists.add(func);
//			}
//		}
//		if(funcLists==null||funcLists.size()==0)
//			Err.checkError(true, error.NOPRVL);
//		return funcLists;
//	}


/*	@Override
	public void CheckLogin(String code, HttpSession session) {
		// TODO Auto-generated method stub
		
	}*/
}

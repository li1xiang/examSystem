package saptacims.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pub.util.TreeCVO;
import saptacims.model.TbRole;
import saptacims.model.TbRoleMenu;
import saptacims.model.TbUser;
import saptacims.model.TbUserRole;
import saptacims.service.IRoleService;
import saptacims.service.IUserService;
import saptacims.vo.page.Pager;

@Controller
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService; 
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	
	@RequestMapping(value="/roleManage")
	public String roleManage(){
		return "role/roleManage";
	}
	
	@RequestMapping(value="/roleList")
	@ResponseBody
	public Map<String, Object> roleList(Pager page,TbRole role)
	{
		return roleService.roleListPage(page, role);
	}
	
	@RequestMapping(value="/addRole")
	public String addRole()
	{
		return "role/modRole";
	}
	
	@RequestMapping(value="/updateRole")
	public String updateRole(Model model,int roleId)
	{
		TbRole role = roleService.getRole(roleId);
		model.addAttribute("tbRole", role);
		return "role/modRole";
	}
	
	@RequestMapping(value="/roleUserByRoleId")
	@ResponseBody
	public JSONObject roleUserByRoleId(int roleId)
	{
		JSONObject saveJson = new JSONObject();
		try {
			List<TbUserRole> list = roleService.getUserRole(roleId);
			saveJson.put("userRoleList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	@RequestMapping(value="/allUserList")
	@ResponseBody
	public List<TbUser> allUserList() throws Exception{
		List<TbUser> userList =  userService.qryUsersByCondition(new TbUser());
		
		for (TbUser tbUser : userList) {
			tbUser.setUserCname(tbUser.getAccount() + "(" + tbUser.getUserCname() + ")");
		}
		
		return userList;
	}
	
	@RequestMapping(value="/saveOrUpdateRole")
	@ResponseBody
	public JSONObject saveOrUpdateRole(TbRole role,HttpSession session)
	{
		JSONObject saveJson = new JSONObject();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			boolean flag = roleService.saveOrUpdateRole(role,currentUser.getUserId());
			if(flag)
			{
				saveJson.put("msg", "操作成功");
			}else{
				saveJson.put("msg", "操作失败,权限名称重复");
			}
			saveJson.put("modFlag",flag);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			saveJson.put("modFlag",false);
		}
		return saveJson;
	}
	
	@RequestMapping(value="/saveOrUpdateRoleMenu")
	public String saveOrUpdateRoleMenu(Model model,int roleId) throws Exception{
		model.addAttribute("roleId", roleId);
		return "role/saveOrUpdateRoleMenu";
	}
	
	@RequestMapping(value="/allMenuList")
	@ResponseBody
	public List<TreeCVO> allMenuList(Model model) throws Exception{
		List<TreeCVO> tcList = roleService.getAllMenuForRole();
		return tcList;
	}
	
	@RequestMapping(value="/roleMenu")
	@ResponseBody
	public List<TbRoleMenu> roleMenu(int roleId) throws Exception
	{
		List<TbRoleMenu> list = roleService.getMenuByRoleId(roleId);
		return list;
	}
	
	@RequestMapping(value="/addOrUpdateRoleMenu")
	@ResponseBody
	public JSONObject addOrUpdateRoleMenu(int roleId, String menuId)
	{
		JSONObject saveJson = new JSONObject();
		try {
			boolean flag = roleService.addOrUpdateRoleMenu(roleId, menuId);
			if(flag)
			{
				saveJson.put("msg", "权限菜单保存成功");
			}else{
				saveJson.put("msg", "权限菜单保存成功");
			}
			saveJson.put("modFlag", flag);
		} catch (Exception e) {
			saveJson.put("msg", "权限菜单保存成功");
			saveJson.put("modFlag", false);
		}
		return saveJson;
	}
}

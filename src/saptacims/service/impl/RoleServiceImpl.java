package saptacims.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pub.util.TreeCVO;
import pub.util.TreeVO;
import saptacims.dao.base.TbMenuMapper;
import saptacims.dao.base.TbRoleMapper;
import saptacims.dao.base.TbRoleMenuMapper;
import saptacims.dao.base.TbUserRoleMapper;
import saptacims.model.*;
import saptacims.model.TbUserRoleExample.Criteria;
import saptacims.service.IRoleService;
import saptacims.vo.page.Pager;
import saptacims.vo.page.RoleListVO;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleServiceImpl implements IRoleService{
	
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Resource
	private TbRoleMapper roleMapper;
	
	@Resource
	private TbUserRoleMapper userRoleMapper;
	
	@Resource
	private TbMenuMapper menuMapper;
	
	@Resource
	private TbRoleMenuMapper roleMenuMapper;
	
	@Override
	public TbRole getRole(int roleId)
	{
		return roleMapper.selectByPrimaryKey(roleId);
	}
	
	@Override
	public List<TbUserRole> getUserRole(int roleId)
	{
		TbUserRoleExample example = new TbUserRoleExample();
		Criteria c = example.createCriteria();
		c.andRoleIdEqualTo(roleId);
		return userRoleMapper.selectByExample(example);
	}
	
	@Override
	public List<TbRole> getAllRole() {
		TbRoleExample tue = new TbRoleExample();
		List<TbRole> allRoles = roleMapper.selectByExample(tue);
		return allRoles;
	}
	
	@Override
	public Map<String, Object> roleListPage(Pager page,TbRole role)
	{
		Map<String, Object> result = new HashMap<String, Object>(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(role.getRoleName() != null && !role.getRoleName().equals(""))
			{
				map.put("roleName", role.getRoleName());
			}
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<RoleListVO> roleMap = roleMapper.getPageList(map);
            int count = roleMapper.getPageCount(map);
            result.put("rows", roleMap);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean saveOrUpdateRole(TbRole role,int userId)
	{
		boolean flag; 
		try {
			if(role.getRoleId() == null)
			{
				role.setCreateUser(userId);
				role.setCreateTime(new Date());
				if(!(roleMapper.selectroleName(role.getRoleName())>0)) {
					roleMapper.insertSelective(role);
					/*for (TbUserRole tur:role.getRoleUsers()) {
						tur.setRoleId(role.getRoleId());
						userRoleMapper.insert(tur);
					}*/
					flag=true;
				}else{
					flag=false;
				}
			}else{
				role.setUpdateTime(new Date());
				role.setUpdateUser(userId);
				roleMapper.updateByPrimaryKeySelective(role);
				
				TbUserRoleExample example = new TbUserRoleExample();
				Criteria c = example.createCriteria();
				c.andRoleIdEqualTo(role.getRoleId());
				userRoleMapper.deleteByExample(example);
				for (int i = 0; i < role.getRoleUsers().size(); i++) {
					TbUserRole tur = role.getRoleUsers().get(i);
					tur.setRoleId(role.getRoleId());
					userRoleMapper.insert(tur);
				}
				flag=true;
			}

		} catch (Exception e) {
			flag=false;
			logger.error(e.getMessage(),e);
		}
		return flag;
	}
	
	@Override
	public List<TreeCVO> getAllMenuForRole() {
		TbMenuExample example = new TbMenuExample();
		saptacims.model.TbMenuExample.Criteria c = example.createCriteria();
		c.andMenuParentidEqualTo(0);
		example.setOrderByClause("MENU_PARENTID,SORT");
		List<TbMenu> list = menuMapper.selectByExample(example);
		List<TreeCVO> tcList = new ArrayList<TreeCVO>();
		for (int i = 0; i < list.size(); i++) {
			TbMenu topMenu = list.get(i);
			TreeCVO tc = new TreeCVO();
			tc.setId(topMenu.getMenuId().toString());
			tc.setText(topMenu.getMenuName());
			TbMenuExample exampleFoeSecond = new TbMenuExample();
			saptacims.model.TbMenuExample.Criteria cForSencond = exampleFoeSecond.createCriteria();
			cForSencond.andMenuParentidEqualTo(topMenu.getMenuId());
			exampleFoeSecond.setOrderByClause("MENU_PARENTID,SORT");
			List<TbMenu> secondMenus = menuMapper.selectByExample(exampleFoeSecond);
			if(secondMenus.size() >0)
			{
				tc.setState("open");
				List<TreeVO> tlist = new ArrayList<TreeVO>();
				for (int j = 0; j < secondMenus.size(); j++) {
					TbMenu secondMenu = secondMenus.get(j);
					TreeVO t = new TreeVO();
					t.setId(secondMenu.getMenuId().toString());
					t.setText(secondMenu.getMenuName());
					tlist.add(t);
				}
				tc.setChildren(tlist);
			}
			tcList.add(tc);
		}
		return tcList;
	}
	
	@Override
	public List<TbRoleMenu> getMenuByRoleId(int roleId) {
		List<TbRoleMenu> list = new ArrayList<TbRoleMenu>();
		try {
			TbRoleMenuExample example = new TbRoleMenuExample();
			saptacims.model.TbRoleMenuExample.Criteria c = example.createCriteria();
			c.andRoleIdEqualTo(roleId);
			list  = roleMenuMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean addOrUpdateRoleMenu(int roleId,String menuId)
	{
		boolean flag;
		try {
			TbRoleMenuExample example = new TbRoleMenuExample();
			saptacims.model.TbRoleMenuExample.Criteria c = example.createCriteria();
			c.andRoleIdEqualTo(roleId);
			roleMenuMapper.deleteByExample(example);
			if(menuId != null && !menuId.equals(""))
			{
				String [] tag = menuId.split(",");
				for (String str : tag) {
					TbRoleMenu vo = new TbRoleMenu();
					vo.setMenuId(Integer.valueOf(str));
					vo.setRoleId(roleId);
					roleMenuMapper.insertSelective(vo);
				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			logger.error(e.getMessage(),e);
		}
		return flag;
	}

	@Override
	public String getStuRoleName(int userId) {
		String t=userRoleMapper.getStuRoleName(userId);
		return t;
	}

}

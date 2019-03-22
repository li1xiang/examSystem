package saptacims.service;

import pub.util.TreeCVO;
import saptacims.model.TbRole;
import saptacims.model.TbRoleMenu;
import saptacims.model.TbUserRole;
import saptacims.vo.page.Pager;

import java.util.List;
import java.util.Map;

public interface IRoleService {
	public List<TbRole> getAllRole();

	public Map<String, Object> roleListPage(Pager page, TbRole role);

	public boolean saveOrUpdateRole(TbRole role, int userId);

	public TbRole getRole(int roleId);

	public List<TbUserRole> getUserRole(int roleId);

	public List<TreeCVO> getAllMenuForRole();

	public List<TbRoleMenu> getMenuByRoleId(int roleId);

	public boolean addOrUpdateRoleMenu(int roleId, String menuId);

	public String getStuRoleName(int userId);
}

package saptacims.service;

import saptacims.model.TbUser;
import saptacims.model.TbUserRole;
import saptacims.vo.base.UserVo;
import saptacims.vo.page.Pager;

import java.util.List;
import java.util.Map;

public interface IUserService {
	

	/**
	 * 按条件查询用户列表
	 * @return
	 */
	public List<TbUser> qryUsersByCondition(TbUser user);
	
	/**
	 * 新建并保存用户
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean newAndSaveUser(TbUser user,TbUserRole  tbUserRole) throws Exception;
	
	/**
	 * 根据UserId查询用户信息
	 * @param id
	 * @return
	 */
	public TbUser qryUserByID(Integer id);
	
	/**
	 * 保存修改的用户
	 * @param user
	 * @return
	 */
	public int saveInModUser(TbUser user) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param userVo
	 * @return
	 */
	public int modifyUserPwd(UserVo userVo, TbUser currentUser) throws Exception;

	/**
	 * 用户列表查询
	 * @param page
	 * @param user
	 * @return
	 */
	public Map<String, Object> userListPage(Pager page, TbUser user);

	
	
	/**
	 * 用户查询
	 * @param page
	 * @param user
	 * @return
	 */
	public Map<String, Object> queryUserListPage(Pager page, TbUser user);
	
	/**
	 * 用户删除
	 * @param currentUser
	 * @param deleteUser
	 * @return
	 */
	public int userDelete(TbUser currentUser, TbUser deleteUser);
}

package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbUser;

/**
 * 登入登出
 *@author qianwei
 * 
 */
public interface ILoginService {

	/**
	 * 登入
	 * @param User
	 * @return josn数据
	 */
	public TbUser signIn(String ACCOUNT,String password,String code);
	
	/**
	 * 登出
	 * @param User
	 * @return josn数据
	 */
	public String loginOut(Integer userId);

	public List<Map<String, Object>> getAllMenu();

	public List<Map<String, Object>> getRoleMenuByUserId(int userId);
	
	/**
	 * 检查登录信息
	 * @param User
	 * @return josn数据
	 */
//	public void CheckLogin(String code,HttpSession session);
}

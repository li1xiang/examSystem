package saptacims.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.util.EncryptUtil;
import pub.util.StringUtil;
import saptacims.cst.Status;
import saptacims.dao.base.TbUserMapper;
import saptacims.dao.base.TbUserRoleMapper;
import saptacims.model.TbUser;
import saptacims.model.TbUserExample;
import saptacims.model.TbUserExample.Criteria;
import saptacims.model.TbUserRole;
import saptacims.service.IUserService;
import saptacims.vo.base.UserVo;
import saptacims.vo.page.Pager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private TbUserRoleMapper tbUserRoleMapper;


	@Override
	public List<TbUser> qryUsersByCondition(TbUser user) {
		return userMapper.selectByUser(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean newAndSaveUser(TbUser user,TbUserRole tbUserRole) throws Exception{
		
		//查询用户登录名是否已经存在

		List<TbUser> users = userMapper.selectByAccount(user.getAccount());
		if(users.size() != 0){
			throw new Exception("登录名已存在");
		}
		user.setCreateTime(new Date());
		user.setUserPassword(EncryptUtil.getHash(user.getUserPassword(), EncryptUtil.HASHTYPE_MD5));
		//默认是禁用
		user.setUserStatus(Status.ENABLE);
		int insertCount = userMapper.insertSelective(user);
		tbUserRole.setUserId(user.getUserId());
		int insertrole=tbUserRoleMapper.insertSelective(tbUserRole);
		if(insertCount > 0&&insertrole>0)
			return true;
		else
			return false;
	}

	@Override
	public TbUser qryUserByID(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int saveInModUser(TbUser user) throws Exception{
		//先查询登录名是否存在
		int result = userMapper.queryAccountById(user);
		if(result != 0){
			throw new Exception("修改失败：登录名已存在");
		}
		if(StringUtil.isNotEmpty(user.getStuNo())){
			int falg=userMapper.getCountStuNo(user.getStuNo());
			if(falg!=0){
				throw new Exception("学号重复");
			}
		}

		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int modifyUserPwd(UserVo userVo, TbUser currentUser) throws Exception{
		String newPwd = userVo.getNewPwd();
		String confirmNewPwd = userVo.getConfirmNewPwd();
		String oldPwdEncry = EncryptUtil.getHash(userVo.getOldPassword(), EncryptUtil.HASHTYPE_MD5);
		
		//1.对比输入的密码加密后是否与数据库中密码
		TbUser oldUser = userMapper.selectByPrimaryKey(currentUser.getUserId());
		if(!oldPwdEncry.equals(oldUser.getUserPassword())){
			throw new Exception("原密码输入错误");
		}
		//2.对比新密码和确认密码是否相同
		if(!newPwd.equals(confirmNewPwd))
			throw new Exception("新密码与确认密码不一致！");
		//3.修改密码
		oldUser.setUserPassword(EncryptUtil.getHash(newPwd, EncryptUtil.HASHTYPE_MD5));
		return userMapper.updateByPrimaryKey(oldUser);
	}
	
	@Override
	public Map<String, Object> userListPage(Pager page,TbUser user)
	{
		Map<String, Object> result = new HashMap<String, Object>(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(user.getAccount() != null && !user.getAccount().equals(""))
			{
				map.put("account", user.getAccount());
			}
			if(user.getStuNo() != null && !user.getStuNo().equals(""))
			{
				map.put("stuNo", user.getStuNo());
			}
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<TbUser> list = userMapper.getPageList(map);
            int count = userMapper.getPageCount(map);
            result.put("rows", list);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int userDelete(TbUser currentUser,TbUser deleteUser)
	{
		int tag = 0;
		try {
			deleteUser.setUpdateTime(new Date());
			deleteUser.setUpdateUser(currentUser.getUserId());
			TbUserExample te = new TbUserExample();
			Criteria criteria = te.createCriteria();
			criteria.andUseridEqualTo(deleteUser.getUserId());
			tag = userMapper.updateByExampleSelective(deleteUser,te);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return tag;
	}

	/**
	 * 用户查询
	 * @param page
	 * @param user
	 * @return
	 * 
	 */
	@Override
	public Map<String, Object> queryUserListPage(Pager page, TbUser user) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(user.getAccount() != null && !user.getAccount().equals(""))
			{
				map.put("account", user.getAccount());
			}
			if(user.getUserCname() != null && !user.getUserCname().equals(""))
			{
				map.put("userCname", user.getUserCname());
			}
			//设置用户状态为启用
			map.put("userStatus", Status.ENABLE);
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<TbUser> list = userMapper.getUserList(map);
            int count = userMapper.getUserCount(map);
            result.put("rows", list);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
}

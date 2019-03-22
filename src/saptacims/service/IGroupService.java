package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbGroup;
import saptacims.model.TbUpdateGroup;
import saptacims.vo.page.Pager;

public interface IGroupService {

	/**
	 * 新增群组
	 * @param group
	 * @return
	 */
	public boolean saveGroup(TbGroup group) throws Exception;
	
	
	/**
	 * 删除群组
	 * @param id
	 * @return
	 */
	public int deleteGroup(TbGroup group,Integer id);
	
	
	/**
	 * 修改群组
	 * @param group
	 * @return
	 */
	public boolean updateGroup(TbGroup group,Integer userId) throws Exception;
	
	
	/**
	 * 群组列表查询
	 * @param page
	 * @param group
	 * @return
	 */
	public Map<String, Object> groupListPage(Pager page,TbGroup group);
	
	
	/**
	 * 根据ID查询群组信息
	 * @param groupId
	 * @return
	 */
	public TbGroup queryGroupById(Integer groupId);
	
	
	/**
	 * 根据name查询群组是否存在
	 * @param groupName
	 * @return
	 */
	public boolean queryGroupByName(String groupName);
	
	
	public TbUpdateGroup showInfoGroup(Integer groupId);
}

package saptacims.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saptacims.cst.Status;
import saptacims.dao.base.TbGroupMapper;
import saptacims.dao.base.TbUserMapper;
import saptacims.model.TbGroup;
import saptacims.model.TbGroupExample;
import saptacims.model.TbUpdateGroup;
import saptacims.model.TbGroupExample.Criteria;
import saptacims.service.IGroupService;
import saptacims.vo.page.Pager;

@Service
public class GroupServiceImpl implements IGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);
	
	@Autowired
	private TbGroupMapper groupMapper;
	
	@Autowired
	private TbUserMapper userMapper;
	
	@Override
	public boolean saveGroup(TbGroup group) throws Exception{
		LOGGER.info("---------群组新增-------------");
		
		group.setCreateTime(new Date());
		group.setUpdateTime(new Date());
		group.setGroupStatus(Status.ENABLE);
		
		int saveCount = groupMapper.insertSelective(group);
		if(saveCount > 0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int deleteGroup(TbGroup deleteGroup,Integer userId) {
		LOGGER.info("--------群组删除-------------");
		int tag = 0;
		try {
			deleteGroup.setUpdateTime(new Date());
			deleteGroup.setUpdateUser(userId);
			
			TbGroupExample te = new TbGroupExample();
			Criteria criteria = te.createCriteria();
			criteria.andGroupIdEqualTo(deleteGroup.getGroupId());
			tag = groupMapper.updateByExampleSelective(deleteGroup, te);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return tag;
	}



	@Override
	public boolean updateGroup(TbGroup group,Integer userId) throws Exception{
		LOGGER.info("--------群组更新-----------");
		
		//1.根据输入id和输入name校验数据库中是否群组重名，
		if(groupMapper.queryGroupCount(group) != 0){
			//群组名称已存在
			throw new Exception("群组名称已存在");
		}
		
		//2.根据输入ID加载群组
		TbGroup tbGroup = groupMapper.selectByPrimaryKey(group.getGroupId());
		if(tbGroup == null){
			//该群组已经不存在
			throw new Exception("该群组已经不存在");
		}
		//将修改的内容封装到保存的实体中
		tbGroup.setGroupName(group.getGroupName());
		tbGroup.setGroupStatus(group.getGroupStatus());
		tbGroup.setRemark(group.getRemark());
		tbGroup.setUpdateTime(new Date());
		tbGroup.setUpdateUser(userId);
		
		//更新数据库
		int count = groupMapper.updateByPrimaryKey(tbGroup);
		if(count != 1){
			//更新失败
			throw new Exception("更新失败");
		}
		return true;
	}

	@Override
	public boolean queryGroupByName(String groupName) {
		int selectCount = groupMapper.selectGroupByName(groupName);
		LOGGER.info("----------根据名称查询--------------" + selectCount);
		
		if(selectCount == 0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Map<String, Object> groupListPage(Pager page, TbGroup group) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(group.getGroupName() != null && !group.getGroupName().equals("")){
				map.put("groupName", group.getGroupName());
			}
			
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            
           List<TbGroup> list = groupMapper.getPageList(map);
           int count = groupMapper.getPageCount(map);
           result.put("rows", list);
		   result.put("total", count);
		} catch (Exception e) {
			
			LOGGER.error(e.getMessage(),e);
		}
		return result;
	}

	public TbGroup queryGroupById(Integer groupId){
		
		return groupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public TbUpdateGroup showInfoGroup(Integer groupId) {
		TbGroup group = groupMapper.selectByPrimaryKey(groupId);
		TbUpdateGroup updateGroup = new TbUpdateGroup();
		
		String createrName = userMapper.queryUsernameById(group.getCreateUser());
		String updaterName = userMapper.queryUsernameById(group.getUpdateUser());
		updateGroup.setGroupId(groupId);
		updateGroup.setGroupName(group.getGroupName());
		updateGroup.setGroupStatus(group.getGroupStatus());
		updateGroup.setCreateTime(group.getCreateTime());
		updateGroup.setUpdateTime(group.getUpdateTime());
		updateGroup.setRemark(group.getRemark());
		updateGroup.setCreaterName(createrName);
		updateGroup.setUpdaterName(updaterName);
		
		return updateGroup;
	}
	
}

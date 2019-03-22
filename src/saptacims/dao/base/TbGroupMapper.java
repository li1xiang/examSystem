package saptacims.dao.base;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import saptacims.model.TbGroup;
import saptacims.model.TbGroupExample;

public interface TbGroupMapper {
    int countByExample(TbGroupExample example);

    int deleteByExample(TbGroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(TbGroup record);

    int insertSelective(TbGroup record);

    List<TbGroup> selectByExample(TbGroupExample example);

    TbGroup selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") TbGroup record, @Param("example") TbGroupExample example);

    int updateByExample(@Param("record") TbGroup record, @Param("example") TbGroupExample example);

    int updateByPrimaryKeySelective(TbGroup record);

    int updateByPrimaryKey(TbGroup record);
    
    List<TbGroup> selectAll(TbGroupExample groupExample);
    
    int selectGroupByName(@Param("groupName") String groupName);
    
    List<TbGroup> getPageList(Map<String, Object> map);
    
    int getPageCount(Map<String, Object> map);
 
	
	List<TbGroup> selectByGroup(TbGroup group);
	
	int queryGroupCount(TbGroup group);
}
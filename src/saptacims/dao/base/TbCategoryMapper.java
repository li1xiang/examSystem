package saptacims.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbCategory;
import saptacims.model.TbCategoryExample;

public interface TbCategoryMapper {
    int countByExample(TbCategoryExample example);

    int deleteByExample(TbCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(TbCategory record);

    int insertSelective(TbCategory record);

    List<TbCategory> selectByExample(TbCategoryExample example);
    
    List<TbCategory> selectAll(TbCategoryExample example);

    TbCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") TbCategory record, @Param("example") TbCategoryExample example);

    int updateByExample(@Param("record") TbCategory record, @Param("example") TbCategoryExample example);

    int updateByPrimaryKeySelective(TbCategory record);

    int updateByPrimaryKey(TbCategory record);
    
    List<TbCategory> selectByCategory(TbCategory category);
    
    int selectCategoryByName(@Param("categoryName") String categoryName);
    
    List<TbCategory> getPageList(Map<String, Object> map);
    
    int getPageCount(Map<String, Object> map);
    
    int queryCategoryCount(TbCategory record);
}
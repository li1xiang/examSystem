package saptacims.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import saptacims.model.TbMenu;
import saptacims.model.TbMenuExample;

public interface TbMenuMapper {
    int countByExample(TbMenuExample example);

    int deleteByExample(TbMenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(TbMenu record);

    int insertSelective(TbMenu record);

    List<TbMenu> selectByExample(TbMenuExample example);

    TbMenu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") TbMenu record, @Param("example") TbMenuExample example);

    int updateByExample(@Param("record") TbMenu record, @Param("example") TbMenuExample example);

    int updateByPrimaryKeySelective(TbMenu record);

    int updateByPrimaryKey(TbMenu record);
    
    List<TbMenu> getMenuByUserId(Integer userId);
    
    List<TbMenu> getSecondMenuByUserId(@Param("menuId")Integer menuId,@Param("userId")Integer userId);
}
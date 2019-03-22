package saptacims.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import saptacims.model.TbRoleMenu;
import saptacims.model.TbRoleMenuExample;

public interface TbRoleMenuMapper {
    int countByExample(TbRoleMenuExample example);

    int deleteByExample(TbRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRoleMenu record);

    int insertSelective(TbRoleMenu record);

    List<TbRoleMenu> selectByExample(TbRoleMenuExample example);

    TbRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRoleMenu record, @Param("example") TbRoleMenuExample example);

    int updateByExample(@Param("record") TbRoleMenu record, @Param("example") TbRoleMenuExample example);

    int updateByPrimaryKeySelective(TbRoleMenu record);

    int updateByPrimaryKey(TbRoleMenu record);
}
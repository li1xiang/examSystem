package saptacims.dao.base;

import org.apache.ibatis.annotations.Param;
import saptacims.model.TbRole;
import saptacims.model.TbRoleExample;
import saptacims.vo.page.RoleListVO;

import java.util.List;
import java.util.Map;

public interface TbRoleMapper {
    int countByExample(TbRoleExample example);

    int deleteByExample(TbRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    List<TbRole> selectByExample(TbRoleExample example);

    TbRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);
    
    List<RoleListVO> getPageList(Map<String, Object> map);
    
    int getPageCount(Map<String, Object> map);

    int  selectroleName (String  roleName);
}
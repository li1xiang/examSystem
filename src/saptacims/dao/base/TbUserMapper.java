package saptacims.dao.base;

import org.apache.ibatis.annotations.Param;
import saptacims.model.TbUser;
import saptacims.model.TbUserExample;

import java.util.List;
import java.util.Map;

public interface TbUserMapper {
    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    TbUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
    
    int qryUserByIdPwd(TbUser user);
    
    List<TbUser> selectByUser(TbUser user);
    
    List<TbUser> selectByAccount(@Param("account") String account);
    
    List<TbUser> getPageList(Map<String, Object> map);
    
    int getPageCount(Map<String, Object> map);
    
    String queryUsernameById(@Param("userId") Integer userId);
    
    int queryAccountById(TbUser user);
    //用户查询
    List<TbUser> getUserList(Map<String, Object> map);
    //用户查询
    int getUserCount(Map<String, Object> map);

    //查询学号重复
    int getCountStuNo(@Param("stuNo") String stuNo );
}
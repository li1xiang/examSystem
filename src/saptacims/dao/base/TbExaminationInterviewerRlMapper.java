package saptacims.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import saptacims.model.TbExaminationInterviewerRl;
import saptacims.model.TbExaminationInterviewerRlExample;

public interface TbExaminationInterviewerRlMapper {
    long countByExample(TbExaminationInterviewerRlExample example);

    int deleteByExample(TbExaminationInterviewerRlExample example);

    int deleteByPrimaryKey(Integer rlId);

    int insert(TbExaminationInterviewerRl record);

    int insertSelective(TbExaminationInterviewerRl record);

    List<TbExaminationInterviewerRl> selectByExample(TbExaminationInterviewerRlExample example);

    TbExaminationInterviewerRl selectByPrimaryKey(Integer rlId);

    int updateByExampleSelective(@Param("record") TbExaminationInterviewerRl record, @Param("example") TbExaminationInterviewerRlExample example);

    int updateByExample(@Param("record") TbExaminationInterviewerRl record, @Param("example") TbExaminationInterviewerRlExample example);

    int updateByPrimaryKeySelective(TbExaminationInterviewerRl record);

    int updateByPrimaryKey(TbExaminationInterviewerRl record);
    
    /**
     * 根据面试者Id查询关联记录
     * @param interviewerId
     * @return
     */
    public List<TbExaminationInterviewerRl> selectByInterviewerId(int interviewerId);
    
    /**
     * 根据试卷Id查询关联记录
     * @param examinationId
     * @return
     */
    public List<TbExaminationInterviewerRl> selectByExaminationId(int examinationId);
}
package saptacims.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbInterviewer;
import saptacims.model.TbInterviewerExample;

public interface TbInterviewerMapper {
    long countByExample(TbInterviewerExample example);

    int deleteByExample(TbInterviewerExample example);

    int deleteByPrimaryKey(Integer interviewerId);

    int insert(TbInterviewer record);

    int insertSelective(TbInterviewer record);

    List<TbInterviewer> selectByExample(TbInterviewerExample example);

    TbInterviewer selectByPrimaryKey(Integer interviewerId);

    int updateByExampleSelective(@Param("record") TbInterviewer record, @Param("example") TbInterviewerExample example);

    int updateByExample(@Param("record") TbInterviewer record, @Param("example") TbInterviewerExample example);

    int updateByPrimaryKeySelective(TbInterviewer record);

    int updateByPrimaryKey(TbInterviewer record);
    
    List<TbInterviewer> getPageList(Map<String, Object> map);
    
    int getCount(TbInterviewer qryCondition);
    
    int clearAttach(@Param("interviewerId")int interviewerId);
    
    /**
     * 根据名字和手机查询面试者信息
     * @param interviewer
     * @return
     */
    int selectByNameAndPhone(TbInterviewer interviewer);
    
    /**
     * 根据手机查面试者信息
     * @param phone 手机
     * @return
     */
    List<TbInterviewer> selectByphone(String phone);
    
    /**
     * 根据面试者Id查询关联试卷数量
     * @param interviewerId
     * @return
     */
    int selectRelatedExaminationCount(@Param("interviewerId")int interviewerId);
}
package saptacims.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationAnswerExample;

public interface TbExaminationAnswerMapper {
    int countByExample(TbExaminationAnswerExample example);

    int deleteByExample(TbExaminationAnswerExample example);

    int deleteByPrimaryKey(Integer answerId);

    int insert(TbExaminationAnswer record);

    int insertSelective(TbExaminationAnswer record);

    List<TbExaminationAnswer> selectByExample(TbExaminationAnswerExample example);

    TbExaminationAnswer selectByPrimaryKey(Integer answerId);

    int updateByExampleSelective(@Param("record") TbExaminationAnswer record,
    		@Param("example") TbExaminationAnswerExample example);

    int updateByExample(@Param("record") TbExaminationAnswer record, 
    		@Param("example") TbExaminationAnswerExample example);

    int updateByPrimaryKeySelective(TbExaminationAnswer record);

    int updateByPrimaryKey(TbExaminationAnswer record);
    
    /**
     * 根据题目id查询该题目的所有答案
     * @param questionId
     * @return
     */
    List<TbExaminationAnswer> selectByQuestionId(int questionId);

	int deleteByQuestionIds(List<Integer> examinationQuestionIds);
    
    /**
     * 根据题目id查询该题目的正确答案
     * @param questionId
     * @return
     */
    TbExaminationAnswer selectCorrectAnswerByQuestionId(int questionId);
}
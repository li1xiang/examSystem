package saptacims.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbAnswer;
import saptacims.model.TbAnswerExample;

public interface TbAnswerMapper {
    int countByExample(TbAnswerExample example);

    int deleteByExample(TbAnswerExample example);

    int deleteByPrimaryKey(Integer answerId);

    int insert(TbAnswer record);

    int insertSelective(TbAnswer record);

    List<TbAnswer> selectByExample(TbAnswerExample example);

    TbAnswer selectByPrimaryKey(Integer answerId);

    int updateByExampleSelective(@Param("record") TbAnswer record, @Param("example") TbAnswerExample example);

    int updateByExample(@Param("record") TbAnswer record, @Param("example") TbAnswerExample example);

    int updateByPrimaryKeySelective(TbAnswer record);

    int updateByPrimaryKey(TbAnswer record);

	TbAnswer getSubjectAnswer(Integer questionId);

	List<TbAnswer> answerList(Integer questionId);

	int deleteAnswer(Integer questionId);
}
package saptacims.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbQuestion;
import saptacims.model.TbQuestionExample;
import saptacims.model.TbQuestionView;

public interface TbQuestionMapper {
    int countByExample(TbQuestionExample example);

    int deleteByExample(TbQuestionExample example);

    int deleteByPrimaryKey(Integer questionId);

    int insert(TbQuestion record);

    int insertSelective(TbQuestion record);

    List<TbQuestion> selectByExample(TbQuestionExample example);

    TbQuestion selectByPrimaryKey(Integer questionId);

    int updateByExampleSelective(@Param("record") TbQuestion record, @Param("example") TbQuestionExample example);

    int updateByExample(@Param("record") TbQuestion record, @Param("example") TbQuestionExample example);

    int updateByPrimaryKeySelective(TbQuestion record);

    int updateByPrimaryKey(TbQuestion record);

	int getPageCount(Map<String, Object> map);

	List<TbQuestionView> getPageList(Map<String, Object> map);

	int batchUpdateStatus(Map<String, Object> record);

	TbQuestionView getQuestionView(@Param("questionId")Integer questionId);

	int singleUpdateStatus(Map<String, Object> result);
	
}
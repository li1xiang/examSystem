package saptacims.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbExaminationQuestionExample;
import saptacims.model.TbQuestion;

public interface TbExaminationQuestionMapper {
    int countByExample(TbExaminationQuestionExample example);

	int deleteByExample(TbExaminationQuestionExample example);

	int deleteByPrimaryKey(Integer examinationQuestionId);

	int insert(TbExaminationQuestion record);

	int insertSelective(TbExaminationQuestion record);

	List<TbExaminationQuestion> selectByExample(TbExaminationQuestionExample example);

	TbExaminationQuestion selectByPrimaryKey(Integer examinationQuestionId);

	int updateByExampleSelective(@Param("record") TbExaminationQuestion record,
			@Param("example") TbExaminationQuestionExample example);

	int updateByExample(@Param("record") TbExaminationQuestion record,
			@Param("example") TbExaminationQuestionExample example);

	int updateByPrimaryKeySelective(TbExaminationQuestion record);

	int updateByPrimaryKey(TbExaminationQuestion record);


    /**
     * 根据试卷Id查询试题
     * @param examinationId
     * @return
     */
    List<TbExaminationQuestion> selectByExaminationId(int examinationId);
    
    /**
     * 根据试卷Id查询所有试题Id
     * @param examinationId
     * @return
     */
    List<TbExaminationQuestion> getAllQuestionIdByExaminationId(int examinationId);
    
	/**
	 * 根据答卷Id查询问题
	 * @param paperId
	 * @return
	 */
	List<TbExaminationQuestion> selectByPaperId(int paperId);

	List<TbExaminationQuestion> getQuestionList(Integer examinationId);

	int delteByExaminationId(Integer examinationId);
	
    
}
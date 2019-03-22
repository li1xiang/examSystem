package saptacims.service;

import java.util.List;

import saptacims.model.TbAnswer;

public interface IAnswerService {

	/**
	 * 保存主观题答案
	 * @param answer
	 * @return
	 */
	Boolean addAnswer(TbAnswer answer);
	
	/**
	 * 保存客观题答案
	 * @param answer
	 * @return
	 */
	Boolean addAnswer(List<TbAnswer> answers);

	/**
	 * 获取主观题答案
	 * @param questionId
	 * @return
	 */
	TbAnswer getSubjectAnswer(Integer questionId);

	/**
	 * 获取答案列表(客观题)
	 * @param questionId
	 * @return
	 */
	List<TbAnswer> answerList(Integer questionId);

	/**
	 * 删除答案
	 * @param questionId
	 * @return
	 */
	int deleteAnswer(Integer questionId);

	/**
	 * 更新主观答案
	 * @param tbAnswer
	 * @return
	 */
	Boolean updateAnswer(TbAnswer tbAnswer);

}

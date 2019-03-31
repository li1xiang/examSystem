package saptacims.service;

import java.util.List;
import java.util.Map;

import pub.util.ComboboxVO;
import saptacims.model.TbAnswer;
import saptacims.model.TbQuestion;
import saptacims.model.TbQuestionView;
import saptacims.vo.page.Pager;


public interface IQuestionService {
	
	/**
	 * 新增考题
	 * @param filePath 
	 * @param question 
	 * @param file 
	 * @param question
	 * @return
	 */
	public TbQuestion addQuestion(TbQuestion question);

	/**
	 * 获取类别下拉框
	 * @param all 是否全部
	 * @return
	 */
	public List<ComboboxVO> getCategoryList(Boolean all);

	/**
	 * 获取群组下拉框
	 * @param all 是否全部
	 * @return
	 */
	public List<ComboboxVO> getGroupList(Boolean all);
	
	/**
	 * 查询题目
	 * @param questionId
	 * @return
	 */
	public TbQuestion getQuestion(int questionId);

	/**
	 * 获取创建人下拉框
	 * @param all
	 * @return
	 */
	public List<ComboboxVO> getcreateUserList(boolean all);

	/**
	 * 获取题目分页列表
	 * @param question
	 * @param page
	 * @return
	 */
	public Map<String, Object> questionListPage(TbQuestion question, Pager page);


	/**
	 * 启用/禁用试题
	 * @param questionIds
	 * @param status
	 * @return
	 */
	public int batchUpdateStatus(List<Integer> questionIds, Integer status);

	/**
	 * 查询题目明细
	 * @param questionId
	 * @return
	 */
	public TbQuestionView getQuestionView(Integer questionId);

	/**
	 * 更新试题
	 * @param question
	 * @param oldQuestionType 
	 * @return
	 */
	public TbQuestion updateQuestion(TbQuestion question);

	/**
	 * 更改试题状态
	 * @param questionId
	 */
	public int updateQuestionStatus(Integer questionId);

	/**
	 * 新增主观题
	 * @param question
	 * @param tbAnswer
	 * @return
	 */
	public Boolean saveSubjecQuestion(TbQuestionView question, TbAnswer tbAnswer);

	/**
	 * 新增客观题
	 * @param question
	 * @param answerTable
	 * @return
	 */
	public Boolean saveObjecQuestion(TbQuestionView question, String answerTable);

	/**
	 * 更新主观题
	 * @param question
	 * @param tbAnswer
	 * @return
	 */
	public Boolean updateSubQuestion(TbQuestion question, TbAnswer tbAnswer);

	/**
	 * 更新客观题
	 * @param question
	 * @param answerTable
	 * @return
	 */
	public Boolean updateObQuestion(TbQuestion question, String answerTable);


	/**
	 * 新增判断题
	 * @param question
	 * @param isright
	 * @return
	 */
	public Boolean  saveRejumentQuestion(TbQuestionView question ,String isright);

}

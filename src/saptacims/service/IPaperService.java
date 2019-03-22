package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbInterviewer;
import saptacims.model.TbPaper;
import saptacims.model.TbPaperDetail;
import saptacims.model.TbUser;
import saptacims.vo.base.PaperVo;
import saptacims.vo.page.Pager;

public interface IPaperService {

	/**
	 * 查询与该面试者有关的试卷信息
	 * @param interviewer
	 * @return
	 */
	public Map<String, Object> qryInterviewerExamination(Pager page, TbInterviewer interviewer);
	
	/**
	 * 列举题目与答案
	 * @param examinationId
	 * @return map中存放key："question","answers"
	 */
	public List<Map<String, Object>> examinationList(int examinationId);
	
	/**
	 * 根据试卷Id查询所有题目Id
	 * @param examinationId
	 * @return
	 */
	public List<TbExaminationQuestion> getAllQuestionIdByExaminationId(int examinationId);
	
	/**
	 * 显示试题的题目和答案
	 * @param questionId
	 * @return
	 */
	public Map<String,Object> showQuestion(int questionId,int paperId);
	
	/**
	 * 获取答卷信息,若有则更新状态,否则新增
	 * @param interviewer
	 * @param examinationId
	 * @return 对应答卷信息的Id
	 */
	public int savePaper(TbInterviewer interviewer,int examinationId,TbUser currentUser);
	
	/**
	 * 保存答题详细信息
	 * @param paperDetail
	 * @return
	 */
	public boolean savePaperDetail(TbPaperDetail paperDetail, int currentUserId);
	
	/**
	 * 交卷
	 * @param paperId
	 * @return
	 */
	public boolean submitPaper(int paperId, int currentUserId);
	
	/**
	 * 根据答卷Id查询试卷
	 * @param paperId
	 * @return
	 */
	public TbPaper qryExaminationIdByPaperId(int paperId);
	
	/**
	 * 自动评分 
	 * 根据paperId查询对应paperDetail
	 * 根据paperDetail中的exmination
	 * @param paperId
	 * @return
	 */
	public boolean autoMarking(int paperId, int currentUserId);
	
	/**
	 * 根据答卷和试卷Id获取PaperVo
	 * @param paperId
	 * @param examination
	 * @return
	 */
	public PaperVo getFullPaper(int paperId);
	
	/**
	 * 为答卷评分(记录阅卷人,总分和修改人修改时间)
	 * @param papaer
	 * @return
	 */
	public boolean markingPaper(TbPaper paper);
	
	/**
	 * 根据试卷Id获取PaperVo
	 * @param paperId
	 * @param examination
	 * @return
	 */
	public PaperVo getFullExamination(int examinationId);
}

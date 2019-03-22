package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbExamination;
import saptacims.model.TbQuestion;
import saptacims.vo.page.Pager;

public interface IExaminationService {

	public List<TbQuestion> questionList(TbQuestion question);

	public List<TbExamination> qryAllExamination();

	boolean saveExamination(TbExamination te, String examinatioInfoTable, String queFilePath);

	public Map<String, Object> examinationListPage(TbExamination examination,
			Pager page);

	public int batchUpdateStatus(List<Integer> examinationIds, Integer status);

	public TbExamination getExamination(Integer examinationId);

	public List<TbQuestion> questionList(Integer examinationId);

	public boolean updateExamination(TbExamination te,String examinatioInfoTable, String queFilePath);
	
	/**
	 * 查询是否有关联面试者 
	 * @param interviewerId
	 * @return
	 */
	public boolean hasRelatedInterviewer(Integer examinationId);
	
	public Object deleteExamination(Integer examinationId);
	
}

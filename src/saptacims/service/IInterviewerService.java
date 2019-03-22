package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbExamination;
import saptacims.model.TbExaminationInterviewerRl;
import saptacims.model.TbInterviewer;
import saptacims.model.TbUser;
import saptacims.vo.page.Pager;

public interface IInterviewerService {
	
	/**
	 * 列出面试者列表
	 * @param page
	 * @param interviewer
	 * @return
	 */
	public Map<String,Object> interviewerList(Pager page, TbInterviewer interviewer);
	
	/**
	 * 新增面试者
	 * @param interviewer
	 * @return
	 */
	public boolean newInterviewer(TbInterviewer interviewer);
	
	
	/**
	 * 根据Id查询面试者
	 * @param interviewerId
	 * @return
	 */
	public TbInterviewer qryInterviewerById(int interviewerId);
	
	/**
	 * 修改面试者
	 * @param interviewer
	 * @return
	 */
	public int modInterviewer(TbInterviewer interviewer);
	
	/**
	 * 删除附件
	 * @param interviewerId
	 * @return
	 */
	public int deleteAttach(int interviewerId);
	
	/**
	 * 查询所有试卷
	 * @return
	 */
	public List<TbExamination> qryAllExamination();
	
	/**
	 * 面试者与试卷关联
	 * @param interviewerId
	 * @param examinationId
	 * @return
	 */
	public List<TbExaminationInterviewerRl> doInterviewerRelatedExamination(int interviewerId, List<TbExaminationInterviewerRl> rls);
	
	/**
	 * 根据interviewerId查询该面试者已存在的关联
	 * @param interviewerId
	 * @return
	 */
	public List<TbExaminationInterviewerRl> qryRelatedByInterviewer(int interviewerId);
	
	/**
	 * 删除面试者
	 * @param currentUser
	 * @param interviewer
	 * @return
	 */
	public int deleteInterviewer(TbUser currentUser, TbInterviewer interviewer);
	
	/**
	 * 查询试卷列表
	 * @return
	 */
	public Map<String, Object> examinationList();
	
	
	/**
	 * 根据试卷Id查询试卷
	 * @param examinationId
	 * @return
	 */
	public TbExamination qryByExaminationId(int examinationId);
	
	/**
	 * 查询是否存在相同手机号的面试者
	 * @param phone
	 * @return
	 */
	public boolean existSamePhoneInterviewer(String phone);
	
	/**
	 * 是否存在绑定试卷
	 * @param interviewer
	 * @return
	 */
	public boolean hasRelatedExamination(int interviewerId);
}

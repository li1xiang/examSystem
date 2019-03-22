package saptacims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saptacims.cst.Status;
import saptacims.dao.base.TbExaminationInterviewerRlMapper;
import saptacims.dao.base.TbExaminationMapper;
import saptacims.dao.base.TbInterviewerMapper;
import saptacims.dao.base.TbPaperMapper;
import saptacims.model.TbExamination;
import saptacims.model.TbExaminationExample;
import saptacims.model.TbExaminationInterviewerRl;
import saptacims.model.TbInterviewer;
import saptacims.model.TbInterviewerExample;
import saptacims.model.TbPaper;
import saptacims.model.TbUser;
import saptacims.model.TbInterviewerExample.Criteria;
import saptacims.service.IInterviewerService;
import saptacims.vo.page.Pager;

@Service
public class InterviewerServiceImpl implements IInterviewerService {

	private static Logger logger = LoggerFactory
			.getLogger(InterviewerServiceImpl.class);

	@Resource
	private TbInterviewerMapper interviewerMapper;

	@Resource
	private TbExaminationMapper examinationMapper;
	@Resource
	private TbPaperMapper paperMapper;

	@Resource
	private TbExaminationInterviewerRlMapper examinationInterviewerRlMapper;
	
	@Override
	public Map<String, Object> interviewerList(Pager page,
			TbInterviewer interviewer) {
		Map<String,Object> result = new HashMap<>();
		try {
			Map<String,Object> params = new HashMap<>();
			params.put("name", interviewer.getName());
			params.put("phone", interviewer.getPhone());
			params.put("source", interviewer.getSource());
			params.put("pageOffset", (page.getPage()-1)*page.getRows());
			params.put("pageSize", page.getRows());
			params.put("order", page.getOrder());
			params.put("sort", page.getSort());
			List<TbInterviewer> pageList = interviewerMapper.getPageList(params);
			int count = interviewerMapper.getCount(interviewer);
			result.put("rows", pageList);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean newInterviewer(TbInterviewer interviewer) {
		interviewer.setCreateTime(new Date());
		interviewer.setActive(Status.ENABLE);
		
		int insertCount = interviewerMapper.insertSelective(interviewer);
		if(insertCount > 0)
			return true;
		else
			return false;
	}

	@Override
	public TbInterviewer qryInterviewerById(int interviewerId) {
		return interviewerMapper.selectByPrimaryKey(interviewerId);
	}

	@Override
	public int modInterviewer(TbInterviewer interviewer) {
		return interviewerMapper.updateByPrimaryKeySelective(interviewer);
	}

	@Override
	public int deleteAttach(int interviewerId) {
		return interviewerMapper.clearAttach(interviewerId);
	}


	@Override
	public List<TbExamination> qryAllExamination() {
		return examinationMapper.selectAll();
	}

	@Override
	@Transactional
	public List<TbExaminationInterviewerRl> doInterviewerRelatedExamination(int interviewerId, List<TbExaminationInterviewerRl> rls) {
		//查询该面试者原先有哪些关联
		List<TbExaminationInterviewerRl> oldRls = examinationInterviewerRlMapper.selectByInterviewerId(interviewerId);
		if(rls == null)
			rls = new ArrayList<>();
		List<TbExaminationInterviewerRl> cantDeleteList = checkDeleteExams(oldRls, rls);
		if(cantDeleteList.size()>0)
			return cantDeleteList;
		//删除原先关联
		for(TbExaminationInterviewerRl tbExaminationInterviewerRl : oldRls){
			examinationInterviewerRlMapper.deleteByPrimaryKey(tbExaminationInterviewerRl.getRlId());
		}
		//添加新关联
		for (TbExaminationInterviewerRl tbExaminationInterviewerRl : rls) {
			examinationInterviewerRlMapper.insertSelective(tbExaminationInterviewerRl);
		}
		return null;
	}
	/**
	 *  检查已有答卷的试卷
	 * @param oldRls
	 * @param newRls
	 * @return 不能删除的试卷关联
	 */
	private List<TbExaminationInterviewerRl> checkDeleteExams(List<TbExaminationInterviewerRl> oldRls,List<TbExaminationInterviewerRl> newRls){
		if(oldRls==null)
			return null;
		List<TbExaminationInterviewerRl> cantDeleteList = new ArrayList<>();
		//循环试卷与面试者原来的关系，如果新关系中也有对应关系，则跳过这个
		for (TbExaminationInterviewerRl oldRl : oldRls) {
			boolean flag = false;//原关联在新关联中是否存在
			for (TbExaminationInterviewerRl newRl : newRls) {
				logger.debug(oldRl.getExaminationId()+"");
				logger.debug(newRl.getExaminationId()+"");
				if(newRl.getExaminationId().equals(oldRl.getExaminationId())){
					flag = true;
					break;
				}
			}
			//原关联不存在于新关联中,需要查询是否已经答卷,若已经答卷,则不能删除
			if(!flag){
				TbPaper paperParam = new TbPaper();
				paperParam.setExaminationId(oldRl.getExaminationId());
				paperParam.setPaperUser(oldRl.getInterviewerId());
				List<TbPaper> relatedPapers = paperMapper.selectByPaperUser(paperParam);
				if(relatedPapers != null && relatedPapers.size() > 0)
					cantDeleteList.add(oldRl);
			}
		}
		return cantDeleteList;
	}

	@Override
	public List<TbExaminationInterviewerRl> qryRelatedByInterviewer(
			int interviewerId) {
		return examinationInterviewerRlMapper.selectByInterviewerId(interviewerId);
	}

	@Override
	public int deleteInterviewer(TbUser currentUser,
			TbInterviewer interviewer) {
		int tag = 0;
		try {
			interviewer.setUpdateTime(new Date());
			interviewer.setUpdateUser(currentUser.getUserId());
			TbInterviewerExample te = new TbInterviewerExample();
			Criteria criteria = te.createCriteria();
			criteria.andInterviewerIdEqualTo(interviewer.getInterviewerId());
			tag = interviewerMapper.updateByExampleSelective(interviewer,te);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public Map<String, Object> examinationList() {
		Map<String, Object> result = new HashMap<>();
		try {
			TbExaminationExample example = new TbExaminationExample();
			saptacims.model.TbExaminationExample.Criteria c = example.createCriteria();
			c.andExaminationStatusEqualTo(Status.ENABLE);
			List<TbExamination> pageList = examinationMapper.selectByExample(example);
			result.put("rows", pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public TbExamination qryByExaminationId(int examinationId) {
		return examinationMapper.selectByPrimaryKey(examinationId);
	}

	@Override
	public boolean existSamePhoneInterviewer(String phone) {
		List<TbInterviewer> samePhoneInterviewers = interviewerMapper.selectByphone(phone);
		if(samePhoneInterviewers==null || samePhoneInterviewers.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean hasRelatedExamination(int interviewerId) {
		int relatedCount = interviewerMapper.selectRelatedExaminationCount(interviewerId);
		if(relatedCount>0)
			return true;
		else
			return false;
	}
	
	


}

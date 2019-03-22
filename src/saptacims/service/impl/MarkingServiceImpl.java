package saptacims.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.apache.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import saptacims.cst.Constants;
import saptacims.dao.base.TbExaminationAnswerMapper;
import saptacims.dao.base.TbExaminationInterviewerRlMapper;
import saptacims.dao.base.TbExaminationMapper;
import saptacims.dao.base.TbExaminationQuestionMapper;
import saptacims.dao.base.TbPaperDetailMapper;
import saptacims.dao.base.TbPaperMapper;
import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbPaperDetail;
import saptacims.service.IMarkingService;
import saptacims.vo.base.TbPaperVo;
import saptacims.vo.page.Pager;

@Service
public class MarkingServiceImpl implements IMarkingService {

	private static Logger logger = LoggerFactory
			.getLogger(MarkingServiceImpl.class);

	@Resource
	private TbPaperMapper paperMapper;
	
	@Resource
	private TbPaperDetailMapper paperDetailMapper;
	
	@Resource
	private TbExaminationQuestionMapper examinationQuestionMapper;

	@Resource
	private TbExaminationAnswerMapper examinationAnswerMapper;
	
	@Resource
	private TbExaminationMapper examinationMapper;

	@Resource
	private TbExaminationInterviewerRlMapper examinationInterviewerRlMapper;
	
	@Override
	public Map<String, Object> paperList(Pager page,
			TbPaperVo paper){
		Map<String,Object> result = new HashMap<>();
		try {
			Map<String,Object> params = new HashMap<>();
			params.put("name", paper.getName());
			params.put("phone", paper.getPhone());
			params.put("examinationName", paper.getExaminationName());
			params.put("submitStatus", paper.getSubmitStatus());
			params.put("pageOffset", (page.getPage()-1)*page.getRows());
			params.put("pageSize", page.getRows());
			params.put("order", page.getOrder());
			params.put("sort", page.getSort());
			List<TbPaperVo> pageList = paperMapper.getPaperList(params);
			int count = paperMapper.getPaperListCount(params);
			result.put("rows", pageList);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> qryPaperDetails(int paperId) {
		//答卷详细信息(题目,答案,参考答案,得分)
		List<Map<String, Object>> paper = new ArrayList<>();
		try {
			List<TbExaminationQuestion> questions = examinationQuestionMapper.selectByPaperId(paperId);
			for (TbExaminationQuestion tbQuestion : questions) {
				Map<String, Object> question = new HashMap<>();
				tbQuestion.setQuestionImgPath(Constants.QUESTIONUPLOADRESS + File.separator + tbQuestion.getQuestionImgPath());
				question.put("question", tbQuestion);//题目
				int questionId = tbQuestion.getQuestionId();
				//根据题目Id查询答案列表
				List<TbExaminationAnswer> answers = examinationAnswerMapper.selectByQuestionId(questionId);
				question.put("answers", answers);//答案
				//根据答卷Id和题目Id查询答卷者选择的答案
				TbPaperDetail paperDetail = paperDetailMapper.selectByPaperIdAndQuestionId(paperId, questionId);
				question.put("selectedAnswer", paperDetail);//主观题客观题显示不同
				paper.add(question);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return paper;
	}


}

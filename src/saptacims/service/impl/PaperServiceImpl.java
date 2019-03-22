package saptacims.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import saptacims.cst.Constants;
import saptacims.cst.QuestionType;
import saptacims.cst.SubmitStatus;
import saptacims.dao.base.TbExaminationAnswerMapper;
import saptacims.dao.base.TbExaminationMapper;
import saptacims.dao.base.TbExaminationQuestionMapper;
import saptacims.dao.base.TbInterviewerMapper;
import saptacims.dao.base.TbPaperDetailMapper;
import saptacims.dao.base.TbPaperMapper;
import saptacims.model.TbExamination;
import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbInterviewer;
import saptacims.model.TbPaper;
import saptacims.model.TbPaperDetail;
import saptacims.model.TbPaperDetailExample;
import saptacims.model.TbUser;
import saptacims.model.TbPaperDetailExample.Criteria;
import saptacims.service.IPaperService;
import saptacims.vo.base.PaperVo;
import saptacims.vo.base.QuestionVo;
import saptacims.vo.base.TbExaminationVo;
import saptacims.vo.page.Pager;

@Service
public class PaperServiceImpl implements IPaperService {

	private static Logger logger = LoggerFactory
			.getLogger(PaperServiceImpl.class);

	@Resource
	private TbExaminationMapper examinationMapper;
	@Resource
	private TbExaminationQuestionMapper examinationQuestionMapper;
	@Resource
	private TbExaminationAnswerMapper examinationAnswerMapper;
	@Resource
	private TbInterviewerMapper interviewerMapper;
	@Resource
	private TbPaperMapper paperMapper;
	@Resource
	private TbPaperDetailMapper paperDetailMapper;

	@Override
	public Map<String, Object> qryInterviewerExamination(Pager page,
			TbInterviewer interviewer) {
		logger.info("查询与该面试者有关的试卷信息Service：qryInterviewerExamination......");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (interviewer.getName() == null || interviewer.getPhone() == null)
				return null;
			Map<String, Object> params = new HashMap<>();
			params.put("name", interviewer.getName());
			params.put("phone", interviewer.getPhone());
			params.put("pageOffset", (page.getPage()-1)*page.getRows());
			params.put("pageSize", page.getRows());
			params.put("order", page.getOrder());
			params.put("sort", page.getSort());

			List<TbExaminationVo> list = examinationMapper
					.selectExaminationByInterviewer(params);
			int count = examinationMapper
					.getExaminationByInterviewerCount(interviewer);
			result.put("rows", list);
			result.put("total", count);
			logger.info(result + "");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> examinationList(int examinationId) {
		List<Map<String, Object>> examination = new ArrayList<Map<String, Object>>();
		// 查询出对应试卷的所有题目
		List<TbExaminationQuestion> questionList = examinationQuestionMapper
				.selectByExaminationId(examinationId);
		// 遍历试卷中所有的试题
		for (TbExaminationQuestion tbExaminationQuestion : questionList) {
			int questionId = tbExaminationQuestion.getQuestionId();
			Map<String, Object> questionAndAnswers = new HashMap<String, Object>();
			// 查询该试题对应的答案
			List<TbExaminationAnswer> answers = examinationAnswerMapper
					.selectByQuestionId(questionId);

			questionAndAnswers.put("question", tbExaminationQuestion);
			questionAndAnswers.put("answers", answers);
			examination.add(questionAndAnswers);
		}

		return examination;
	}

	@Override
	public List<TbExaminationQuestion> getAllQuestionIdByExaminationId(
			int examinationId) {
		return examinationQuestionMapper
				.getAllQuestionIdByExaminationId(examinationId);
	}

	@Override
	public Map<String, Object> showQuestion(int questionId, int paperId) {
		Map<String, Object> result = new HashMap<String, Object>();
		TbExaminationQuestion question = examinationQuestionMapper
				.selectByPrimaryKey(questionId);
		if (!"".equals(question.getQuestionImgPath())
				&& question.getQuestionImgPath() != null)
			question.setQuestionImgPath(Constants.QUESTIONUPLOADRESS
					+ File.separator + question.getQuestionImgPath());
		// 若客观题
//		if (question.getQuestionType() == QuestionType.OBJECTIVE) {
			//查询此客观题的选项
			List<TbExaminationAnswer> answers = examinationAnswerMapper
					.selectByQuestionId(questionId);
			result.put("answers", answers);
//		} else{
//			//主观题，查询出主观题答案
//			List<TbExaminationAnswer> answers= examinationAnswerMapper.selectByQuestionId(questionId);
//			result.put("answers", answers);
//		}

		result.put("question", question);
		TbPaperDetailExample example = new TbPaperDetailExample();
		Criteria c = example.createCriteria();
		c.andQuestionIdEqualTo(questionId);
		c.andPaperIdEqualTo(paperId);
		List<TbPaperDetail> answered = paperDetailMapper
				.selectByExample(example);// 已答题目的详情
		if (answered != null && answered.size() != 0) {
			if (question.getQuestionType() == QuestionType.OBJECTIVE)
				result.put("selected", answered.get(0).getAnswerId());
			else
				result.put("selected", answered.get(0).getSubjectiveAnswer());
			result.put("score", answered.get(0).getScore());
		}
		return result;
	}

	@Override
	public int savePaper(TbInterviewer interviewer,
			int examinationId, TbUser currentUser) {

		// 查询名字和手机对应的面试者Id
		int interviewerId = interviewerMapper.selectByNameAndPhone(interviewer);

		// 查询是否已答题,若已答题,穿已答的paperId到前台
		TbPaper qryCondition = new TbPaper();
		qryCondition.setPaperUser(interviewerId);
		qryCondition.setExaminationId(examinationId);
		TbPaper oldPaper = paperMapper
				.selectByExaminationIdAndPaperUser(qryCondition);
		if (oldPaper != null) {
			oldPaper.setSubmitStatus(SubmitStatus.HASNOTSUBMIT);
			oldPaper.setUpdateUser(currentUser.getUserId());
			oldPaper.setUpdateTime(new Date());
			paperMapper.updateByPrimaryKey(oldPaper);
			return oldPaper.getPaperId();
		}

		TbPaper paper = new TbPaper();
		paper.setPaperStartTime(new Date());
		paper.setExaminationId(examinationId);
		paper.setPaperUser(interviewerId);
		// 插入时默认未提交
		paper.setSubmitStatus(SubmitStatus.HASNOTSUBMIT);
		paper.setCreateTime(new Date());
		paper.setCreateUser(currentUser.getUserId());
		paperMapper.insertReturnKey(paper);
		return paper.getPaperId();
	}

	@Override
	public boolean savePaperDetail(TbPaperDetail paperDetail,
			int currentUserId) {
		TbPaperDetailExample example = new TbPaperDetailExample();
		Criteria c = example.createCriteria();
		c.andPaperIdEqualTo(paperDetail.getPaperId());
		c.andQuestionIdEqualTo(paperDetail.getQuestionId());
		List<TbPaperDetail> result = paperDetailMapper.selectByExample(example);
		int insertCount = 0;

		// 如果没答过,则新增
		if (result.size() == 0)
			insertCount = paperDetailMapper.insertSelective(paperDetail);

		// 如果答过,则修改成新答案
		else {
			paperDetail.setPaperDetailId(result.get(0).getPaperDetailId());
			insertCount = paperDetailMapper
					.updateByPrimaryKeySelective(paperDetail);
		}

		TbPaper paper = new TbPaper();
		paper.setUpdateTime(new Date());
		paper.setUpdateUser(currentUserId);

		if (insertCount > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean submitPaper(int paperId, int currentUserId) {
		TbPaper paper = new TbPaper();
		paper.setPaperId(paperId);
		paper.setUpdateUser(currentUserId);
		paper.setUpdateTime(new Date());
		paper.setSubmitStatus(SubmitStatus.SUBMITTED);
		paper.setPaperEndTime(new Date());
		int updateCount = paperMapper.updateByPrimaryKeySelective(paper);
		if (updateCount > 0)
			return true;
		else
			return false;
	}

	@Override
	public TbPaper qryExaminationIdByPaperId(int paperId) {
		return paperMapper.selectByPrimaryKey(paperId);
	}

	@Override
	public boolean autoMarking(int paperId, int currentUserId) {
		// 查询这份答卷是否有评分
		TbPaper paper = paperMapper.selectByPrimaryKey(paperId);
		if (paper.getScore() == null) {
			//int paperScore = 0;
			try {
				// 根据paperId查询对应paperDetail
				List<TbPaperDetail> result = paperDetailMapper.selectByPaperId(paperId);// 回答结果
				// 根据paperDetail中的exminationId查询所有的题目对应的TbExaminationAnswer正确答案
				for (TbPaperDetail tbPaperDetail : result) {
					if(tbPaperDetail.getAnswerId()==null)
						continue;
					int questionId = tbPaperDetail.getQuestionId();
					TbExaminationQuestion question = examinationQuestionMapper.selectByPrimaryKey(questionId);// 用于获取题目分数
					TbExaminationAnswer answer = examinationAnswerMapper.selectCorrectAnswerByQuestionId(questionId);// 参考答案
					// 如果回答结果与参考答案相同
					if (answer.getAnswerId() == tbPaperDetail.getAnswerId()) {
						int detailScore = question.getTotalScore();
						tbPaperDetail.setScore(detailScore);
						//paperScore += detailScore;
						//修改detail分数
						paperDetailMapper.updateByPrimaryKeySelective(tbPaperDetail);
					} else{
						tbPaperDetail.setScore(0);
						paperDetailMapper.updateByPrimaryKeySelective(tbPaperDetail);
					}
				}
				//paper.setScore(paperScore);
				//修改paper分数
				paperMapper.updateByPrimaryKeySelective(paper);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		}
		return true;
	}

	@Override
	public PaperVo getFullPaper(int paperId) {
		PaperVo paperVo = new PaperVo();
		TbPaper paper = paperMapper.selectByPrimaryKey(paperId);
		paperVo.setScore(paper.getScore());
		paperVo.setScoreRemarks(paper.getScoreRemarks());
		paperVo.setMarkingMan(paper.getMarkingMan());
		TbInterviewer paperUser = interviewerMapper.selectByPrimaryKey(paper.getPaperUser());
		paperVo.setPaperUserName(paperUser.getName());
		TbExamination examination = examinationMapper.selectByPrimaryKey(paper.getExaminationId());
		if(examination != null)
			paperVo.setExaminationName(examination.getExaminationName());
		List<QuestionVo> questionVos = new ArrayList<>();
		//查处答卷对应的所有题目
		List<TbExaminationQuestion> questions =  examinationQuestionMapper.selectByPaperId(paperId);
		if(questions != null && questions.size() > 0){
			for (TbExaminationQuestion tbExaminationQuestion : questions) {
				if(tbExaminationQuestion!=null){
					int questionId = tbExaminationQuestion.getExaminationQuestionId();
					QuestionVo questionVo = new QuestionVo();
					questionVo.setQuestionTitle(tbExaminationQuestion.getQuestionTitle());
					questionVo.setQuestionType(tbExaminationQuestion.getQuestionType());
					questionVo.setExaminationQuestionId(questionId);
					if(tbExaminationQuestion.getQuestionImgPath()!=null&&!"".equals(tbExaminationQuestion.getQuestionImgPath()))
						questionVo.setQuestionImgPath(Constants.QUESTIONUPLOADRESS + File.separator + tbExaminationQuestion.getQuestionImgPath());
					//通过paperId和questionId查询PaperDetail
					TbPaperDetail paperDetail = paperDetailMapper.selectByPaperIdAndQuestionId(paperId, tbExaminationQuestion.getExaminationQuestionId());
					questionVo.setPaperDetail(paperDetail);
					//通过questionId查询答案
					List<TbExaminationAnswer> options = examinationAnswerMapper.selectByQuestionId(questionId);
					questionVo.setOptions(options);
					questionVos.add(questionVo);
				}
			}
		}
		paperVo.setQuestionList(questionVos);
		return paperVo;
	}

	@Override
	public boolean markingPaper(TbPaper paper) {
		int totalScore = 0;
		//记录阅卷人,总分和修改人修改时间
		//根据paperId查询所有的小题分数
		List<TbPaperDetail> details = paperDetailMapper.selectByPaperId(paper.getPaperId());
		for (TbPaperDetail tbPaperDetail : details) {
			totalScore += tbPaperDetail.getScore();
		}
		paper.setScore(totalScore);
		int updateCount = paperMapper.updateByPrimaryKeySelective(paper);
		if(updateCount>0)
			return true;
		else
			return false;
	}

	@Override
	public PaperVo getFullExamination(int examinationId) {
		PaperVo paperVo = new PaperVo();
		TbExamination examination = examinationMapper.selectByPrimaryKey(examinationId);
		if(examination != null){
			paperVo.setExaminationName(examination.getExaminationName());
			if(examination.getUpdateTime() != null)
				paperVo.setExaminationUpdateDate(examination.getUpdateTime());
			else
				paperVo.setExaminationUpdateDate(examination.getCreateTime());
				
		}
		List<QuestionVo> questionVos = new ArrayList<>();
		//查处答卷对应的所有题目
		List<TbExaminationQuestion> questions =  examinationQuestionMapper.selectByExaminationId(examinationId);
		if(questions != null && questions.size() > 0){
			for (TbExaminationQuestion tbExaminationQuestion : questions) {
				if(tbExaminationQuestion!=null){
					int questionId = tbExaminationQuestion.getExaminationQuestionId();
					QuestionVo questionVo = new QuestionVo();
					questionVo.setQuestionTitle(tbExaminationQuestion.getQuestionTitle());
					questionVo.setQuestionType(tbExaminationQuestion.getQuestionType());
					questionVo.setExaminationQuestionId(questionId);
					if(tbExaminationQuestion.getQuestionImgPath()!=null&&!"".equals(tbExaminationQuestion.getQuestionImgPath()))
						questionVo.setQuestionImgPath(Constants.QUESTIONUPLOADRESS + File.separator + tbExaminationQuestion.getQuestionImgPath());
					//通过paperId和questionId查询PaperDetail
					//通过questionId查询答案
					List<TbExaminationAnswer> options = examinationAnswerMapper.selectByQuestionId(questionId);
					questionVo.setOptions(options);
					questionVos.add(questionVo);
				}
			}
		}
		paperVo.setQuestionList(questionVos);
		return paperVo;
	}
	

}

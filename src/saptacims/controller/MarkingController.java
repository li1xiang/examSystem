package saptacims.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pub.util.ComboboxVO;
import saptacims.cst.SubmitStatus;
import saptacims.model.TbPaper;
import saptacims.model.TbPaperDetail;
import saptacims.model.TbUser;
import saptacims.service.IMarkingService;
import saptacims.service.IPaperService;
import saptacims.vo.base.PaperVo;
import saptacims.vo.base.TbPaperVo;
import saptacims.vo.page.Pager;

/**
 * 阅卷管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/marking")
public class MarkingController {
	@Autowired
	private IPaperService paperService;
	@Autowired
	private IMarkingService markingService;
	

	private static Logger logger = LoggerFactory
			.getLogger(MarkingController.class);

	/**
	 * 跳转到答卷列表页面
	 * @return
	 */
	@RequestMapping(value = "/toPaperList")
	public String toPaperList() {
		return "marking/paperList";
	}

	
	/**
	 * 加载答卷状态下拉框
	 * @return
	 */
	@RequestMapping(value = "/submitStatusList")
	@ResponseBody
	public Object sexList() {
		List<ComboboxVO> volist = ComboboxVO.getCstAllCombobox(SubmitStatus.class);
		return volist;
	}
	
	/**
	 * 列出该面试者的关联试卷
	 * 
	 * @return
	 */
	@RequestMapping("paperList")
	@ResponseBody
	public Map<String, Object> paperList(Pager page,  
			TbPaperVo paper) {
		logger.info("列出该面试者的关联试卷:paperList......");
		return markingService.paperList(page, paper);
	}


	/**
	 * 显示面试题目
	 * 
	 * @param examinationId
	 * @return
	 */
	@RequestMapping(value = "/markingPage")
	public String markingPage(HttpSession session, Model model, int paperId) {
		logger.info("显示面试题目：showQuestion......");
		TbUser currentUser = (TbUser)session.getAttribute("currentUser");
		//自动评分
		paperService.autoMarking(paperId, currentUser.getUserId());
		//根据答卷Id获取试卷Id
		TbPaper paper = paperService.qryExaminationIdByPaperId(paperId);
		//再根据试卷Id查询所有题目Id
		//List<TbExaminationQuestion> questions = paperService.getAllQuestionIdByExaminationId(paper.getExaminationId());
		//获取整个PaperVo
		PaperVo paperVo = paperService.getFullPaper(paperId);
		model.addAttribute("paperId",paperId);
		model.addAttribute("paper", paperVo);
		model.addAttribute("examinationId",paper.getExaminationId());
		return "marking/markingPaper";
	}

	/**
	 * 查询出对应答卷的题目,答案,参考答案和得分
	 * @param model
	 * @param paperId
	 * @return
	 */
	@RequestMapping("showPaperDetails")
	public String showPaperDetails(Model model,int paperId){
		List<Map<String, Object>> paper = markingService.qryPaperDetails(paperId);
		model.addAttribute("paper", JSONArray.toJSON(paper));
		model.addAttribute("paperId", paperId);
		return "marking/paperDetail";
	}
	
	/**
	 * 显示面试题目
	 * 
	 * @param examinationId
	 * @return
	 */
	@RequestMapping(value = "/showQuestion", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showQuestion(int questionId,int paperId) {
		logger.info("显示面试题目：showQuestion......");
		
		return paperService.showQuestion(questionId, paperId);
	}

	/**
	 * 题目评分
	 * @param paperDetail
	 * @return
	 */
	@RequestMapping(value="/nextQuestion", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject nextQuestion(TbPaperDetail paperDetail, HttpSession session){
		logger.info("题目评分：marking......");
		JSONObject result = new JSONObject();
		try {
			TbUser currentUser = (TbUser)session.getAttribute("currentUser");
			paperDetail.setCreateUser(currentUser.getUserId());
			paperDetail.setCreateTime(new Date());
			
			boolean saveFlag = paperService.savePaperDetail(paperDetail,currentUser.getUserId());
			if(saveFlag)
			{
				result.put("msg", "保存成功");
			}else{
				result.put("msg", "保存失败");
			}
			result.put("saveFlag", saveFlag);
			result.put("score", paperDetail.getScore());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("msg", "保存失败");
			result.put("saveFlag", false);
		}
		return result;
	}

	@RequestMapping(value="/markPaper", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject markPaper(HttpSession session, int paperId, String scoreRemarks){
		JSONObject result = new JSONObject();
		try {
			TbUser currentUser = (TbUser)session.getAttribute("currentUser");
			TbPaper paper = new TbPaper();
			paper.setPaperId(paperId);
			paper.setScoreRemarks(scoreRemarks);
			paper.setMarkingMan(currentUser.getUserId());
			boolean markingFlag = paperService.markingPaper(paper);
			if(markingFlag)
			{
				result.put("msg", "评分提交成功");
			}else{
				result.put("msg", "评分提交失败");
			}
			result.put("markingFlag", markingFlag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("msg", "评分提交失败");
			result.put("markingFlag", false);
		}
		return result;
	}
	
	/**
	 * 展示答卷
	 * @param model
	 * @param paperId
	 * @return
	 */
	@RequestMapping("showPaper")
	public String showPaper(Model model,int paperId){
		PaperVo paperVo = paperService.getFullPaper(paperId);
		model.addAttribute("paper", paperVo);
		model.addAttribute("paperId", paperId);
		return "marking/showPaper";
	}
}

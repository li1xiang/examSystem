package saptacims.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import saptacims.cst.Constants;
import saptacims.model.TbExamination;
import saptacims.model.TbQuestion;
import saptacims.model.TbUser;
import saptacims.service.IExaminationService;
import saptacims.vo.page.Pager;

import com.alibaba.fastjson.JSONObject;



@Controller
@RequestMapping(value="/examination")
public class ExaminationController {

	private static Logger logger = LoggerFactory.getLogger(ExaminationController.class);
	
	@Autowired
	private IExaminationService examinationService;
	
	@RequestMapping(value="/addExamination")
	public String addExamination(Model model,Integer examinationId)
	{
		if(examinationId!=null){	
			model.addAttribute("examinationId", examinationId);
		}
		return "examination/addExamination";
	}
	
	@RequestMapping(value="/allQuestionList")
	@ResponseBody
	public List<TbQuestion> allQuestionList(TbQuestion question)
	{
		return examinationService.questionList(question);
	}
	
	@RequestMapping(value="/examinationInfo")
	public String examinationInfo()
	{
		return "examination/examinationInfo";
	}
	
	@RequestMapping(value="/examinationManage")
	public String examinationManage()
	{
		return "examination/examinationManage";
	}
	
	@RequestMapping(value="/questionList")
	@ResponseBody
	public List<TbQuestion> questionList(Integer examinationId)
	{
		return examinationService.questionList(examinationId);
	}
	
	@RequestMapping(value="/examinationList")
	@ResponseBody
	public Map<String, Object> examinationList(TbExamination examination,Pager page)
	{
		logger.info("试卷列表 开始----");
		Map<String, Object> result = examinationService.examinationListPage(examination,page);
		logger.info("试卷列表 结束----");
		return result;
	}
	
	@RequestMapping(value="/saveExaminationInfo")
	@ResponseBody
	public JSONObject saveExaminationInfo(String examinationName,int examinationStatus,String examinationRemark,Integer totalScore,String examinatioInfoTable,HttpSession session)
	{

		String queFilePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);//题目图片文件夹
		
		JSONObject saveJson = new JSONObject();
		TbUser currentUser = (TbUser) session.getAttribute("currentUser");
		TbExamination te = new TbExamination();
		te.setCreateTime(new Date());
		te.setCreateUser(currentUser.getUserId());
		te.setExaminationName(examinationName);
		te.setExaminationRemark(examinationRemark);
		te.setExaminationStatus(examinationStatus);
		te.setTotalScore(totalScore);
		boolean tag =  examinationService.saveExamination(te, examinatioInfoTable,queFilePath);
		if(!tag)
		{
			saveJson.put("msg", "新增失败");
			saveJson.put("saveFlag", false);
		}else{
			saveJson.put("msg", "新增成功");
			saveJson.put("saveFlag", true);
		}
		return saveJson;
	}
	
	/**
	 * 修改试卷状态
	 * @param questionIds
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateStatus(@RequestParam("examinationIds[]")List<Integer> examinationIds,@RequestParam("status")Integer status,HttpSession session)
	{ 
		logger.info("--------批量禁用/启用试题开始--------");
		JSONObject saveJson = new JSONObject(); 
		try {
			int num = examinationService.batchUpdateStatus(examinationIds,status);
			if(num!=0){
				saveJson.put("msg", "操作成功");
				saveJson.put("saveFlag", true);
			}else{
				saveJson.put("msg", "操作失败");
				saveJson.put("saveFlag", false);
				logger.info("操作题目失败");		           
			}
		} catch (Exception e) {
			saveJson.put("msg", "操作失败");
			saveJson.put("saveFlag", false);
			logger.error("操作失败", e);
		}
		logger.info("--------批量禁用/启用试题结束 --------");
		return saveJson;
	}
	
	@RequestMapping(value="/getExaminationDetail")
	public String getexaminationDetail(Model model,Integer examinationId,String action) throws ParseException
	{
		TbExamination tbExamination = examinationService.getExamination(examinationId);		
		model.addAttribute("examinationDetail", tbExamination);
		if(action!=null){
			model.addAttribute("action", action);
		}
		String returnUrl = "examination/updateExamination";
		return returnUrl;
	}
	
	@RequestMapping(value="/updateExamination")
	@ResponseBody
	public JSONObject updateExamination(TbExamination examination,String examinatioInfoTable,HttpSession session)
	{

		String queFilePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);//题目图片文件夹
		
		JSONObject saveJson = new JSONObject();
		TbUser currentUser = (TbUser) session.getAttribute("currentUser");
		examination.setUpdateTime(new Date());
		examination.setUpdateUser(currentUser.getUserId());
		boolean tag =  examinationService.updateExamination(examination, examinatioInfoTable, queFilePath);
		if(!tag)
		{
			saveJson.put("msg", "更新失败");
			saveJson.put("saveFlag", false);
		}else{
			saveJson.put("msg", "更新成功");
			saveJson.put("saveFlag", true);
		}
		return saveJson;
	}
	
	/**
	 * 检查 
	 * @return
	 */
	@RequestMapping(value="/hasRelatedInterviewer",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject hasRelatedInterviewer(Integer examinationId){
		JSONObject result = new JSONObject();
		boolean flag = examinationService.hasRelatedInterviewer(examinationId);//是否有关联的面试者信息
		if(flag)
			result.put("flag", true);
		else
			result.put("flag", false);
		return result;
	}
	
	/*
	 * 删除试卷
	 */
	@RequestMapping("/deleteExamination")
	@ResponseBody
	public Object deleteExamination(Integer examinationId){
		return examinationService.deleteExamination(examinationId);
	}
}

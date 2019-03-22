package saptacims.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import saptacims.cst.Constants;
import saptacims.model.TbAnswer;
import saptacims.model.TbQuestion;
import saptacims.model.TbUser;
import saptacims.service.IAnswerService;
import saptacims.service.IQuestionService;
import saptacims.vo.page.Pager;

import com.alibaba.fastjson.JSONObject;

/**
 * 答案管理
 *
 */
@Controller
@RequestMapping("answer")
public class AnswerController {

	@Autowired
	private IAnswerService answerService;
	@Autowired
	private IQuestionService questionService;
	
	private static Logger logger = LoggerFactory.getLogger(AnswerController.class);
	
	/**
	 * 添加主观题答案
	 * @param file
	 * @param tbAnswer
	 * @param session
	 * @return
	 */
	 
	@RequestMapping(value="/addSubjecAnswer",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addSubjecAnswer(@RequestParam(value="answerFile",required = false) MultipartFile file,TbAnswer tbAnswer,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String realName = "";
		String filePath = "";
		try {
			logger.info("--------新增主观答案开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			//判重
			TbAnswer answer = answerService.getSubjectAnswer(tbAnswer.getQuestionId());
			if(answer!=null){
				saveJson.put("msg", "保存失败,已存在唯一答案!");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");
				return saveJson;
			}			
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();  
				filePath = session.getServletContext().getRealPath(Constants.ANSWERUPLOADRESS);
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				realName = String.valueOf(System.currentTimeMillis())+suffix;
				tbAnswer.setAnswerImg(realName);
				tbAnswer.setAnswerImgName(file.getOriginalFilename());
				try {  
	                file.transferTo(new File(filePath+File.separator+realName));  
	            } catch (Exception e) {  
	                e.printStackTrace(); 
	                logger.error("保存文件失败", e);
	            } 
			}
			tbAnswer.setIsright(1);
			tbAnswer.setCreateUser(currentUser.getUserId());
			tbAnswer.setCreateTime(new Date());
			tbAnswer.setUpdateUser(currentUser.getUserId());
			tbAnswer.setUpdateTime(new Date());
			Boolean tag = answerService.addAnswer(tbAnswer);
			if(tag){
				//更改题目状态为启用
				questionService.updateQuestionStatus(tbAnswer.getQuestionId());
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}else{
				File tempfile = new File(filePath+File.separator+realName);
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }
		        saveJson.put("msg", "保存失败");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");
			}
		} catch (Exception e) {
			File tempfile = new File(filePath+File.separator+realName);
	        if (tempfile.exists() && tempfile.isFile()) {
	            tempfile.delete();
	        }
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	/**
	 * 添加客观题答案
	 * @param answers
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addObjecAnswer",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addObjecAnswer(@RequestBody List<TbAnswer> answers,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		try {
			logger.info("--------新增/修改客观答案开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			
			for (TbAnswer tbAnswer : answers) {
				tbAnswer.setUpdateUser(currentUser.getUserId());
				tbAnswer.setUpdateTime(new Date());
				tbAnswer.setCreateUser(currentUser.getUserId());
				tbAnswer.setCreateTime(new Date());
			}			
			Boolean tag = answerService.addAnswer(answers);
			if(tag){
				//修改答案时要修改题目的修改人修改时间
				if(answers.get(0).getAnswerId()!=null){
					TbQuestion tempQuestion = new TbQuestion();
					tempQuestion.setQuestionId(answers.get(0).getQuestionId());
					tempQuestion.setUpdateUser(currentUser.getUserId());
					tempQuestion.setUpdateTime(new Date());
					questionService.updateQuestion(tempQuestion);
				}else{
					//更改题目状态为启用
					questionService.updateQuestionStatus(answers.get(0).getQuestionId());
				}
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}else{
				saveJson.put("msg", "保存失败");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");		           
			}
		} catch (Exception e) {
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	/**
	 * 获取客观题答案列表
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value="/answerList",method=RequestMethod.POST)
	@ResponseBody
	public List<TbAnswer> answerList(Integer questionId)
	{ 		
		return answerService.answerList(questionId);
	}
	
	/**
	 * 跳转到答案更新页面
	 * @param model
	 * @param questionId
	 * @param questionType
	 * @return
	 */
	@RequestMapping(value="/getUpdateAnswer")
	public String getUpdateAnswer(Model model,Integer questionId,Integer questionType)
	{
		if(questionType==0){
			TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			if(tbAnswer!=null&&tbAnswer.getAnswerImg()!=null&&!"".equals(tbAnswer.getAnswerImg().trim())){
				tbAnswer.setAnswerImg(Constants.ANSWERUPLOADRESS+File.separator+tbAnswer.getAnswerImg());
			}
			model.addAttribute("answerDetail", tbAnswer);
			model.addAttribute("questionId", questionId);
			return "question/updateSubjecAnswer";
		}else{
			model.addAttribute("questionId", questionId);
			return "question/updateObjecAnswer";
		}
	}
	
	/**
	 * 更新主观题答案
	 * @param file
	 * @param tbAnswer
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateSubjecAnswer",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSubjecAnswer(@RequestParam(value="answerFile",required = false) MultipartFile file,TbAnswer tbAnswer,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String realName = "";
		String filePath = "";
		try {
			logger.info("--------更新主观答案开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			String oldAnswerImg = tbAnswer.getAnswerImg();
			if(oldAnswerImg!=null&&!"".equals(oldAnswerImg.trim())){
				tbAnswer.setAnswerImg(null);
			}
			if(!file.isEmpty()){
				//保存上传的新附件
				String fileName = file.getOriginalFilename();  
				filePath = session.getServletContext().getRealPath(Constants.ANSWERUPLOADRESS);
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				realName = System.currentTimeMillis()+suffix;
				tbAnswer.setAnswerImg(realName);
				tbAnswer.setAnswerImgName(file.getOriginalFilename());
				try {  
	                file.transferTo(new File(filePath+File.separator+realName));  
	            } catch (Exception e) {  
	                e.printStackTrace(); 
	                logger.error("更新主观题保存文件失败", e);
	            } 
			}
			tbAnswer.setUpdateUser(currentUser.getUserId());
			tbAnswer.setUpdateTime(new Date());
			Boolean tag = false;
			//判重
			TbAnswer answer = answerService.getSubjectAnswer(tbAnswer.getQuestionId());
			if(tbAnswer.getAnswerId()==null&&answer==null){
				tbAnswer.setCreateUser(currentUser.getUserId());
				tbAnswer.setCreateTime(new Date());
				tbAnswer.setIsright(1);
				tag = answerService.addAnswer(tbAnswer);
			}else{
				tag = answerService.updateAnswer(tbAnswer);
			}
			if(tag){
				//修改对应题目的修改时间修改人
				TbQuestion tempQuestion = new TbQuestion();
				tempQuestion.setQuestionId(tbAnswer.getQuestionId());
				tempQuestion.setActive(1);
				tempQuestion.setUpdateUser(currentUser.getUserId());
				tempQuestion.setUpdateTime(new Date());
				questionService.updateQuestion(tempQuestion);
				
				//上传新图片时删除旧图片
				if(oldAnswerImg!=null&&!"".equals(oldAnswerImg.trim())&&!"".equals(realName)){
					File tempfile = new File(filePath+File.separator+oldAnswerImg);
			        if (tempfile.exists() && tempfile.isFile()) {
			            tempfile.delete();
			        }
				}
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}else{//更新失败删除刚保存的附件
				File tempfile = new File(filePath+File.separator+realName);
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }
		        saveJson.put("msg", "保存失败");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");
			}
		} catch (Exception e) {//更新失败删除刚保存的附件
			File tempfile = new File(filePath+File.separator+realName);
	        if (tempfile.exists() && tempfile.isFile()) {
	            tempfile.delete();
	        }
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	/**
	 * 删除主观题答案附件
	 * @param questionId
	 * @param answerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteImg",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteImg(@RequestParam(value="questionId",required = false) Integer questionId,@RequestParam(value="questionId",required = false) Integer answerId, HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		try {
			logger.info("--------删除主观题答案图片开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			//更新题目			
			TbQuestion tbQuestion = questionService.getQuestion(questionId);	
	        tbQuestion.setUpdateUser(currentUser.getUserId());
	        currentUser.setUpdateTime(new Date());
	        TbQuestion reQue = questionService.updateQuestion(tbQuestion);
	        //更新答案
	        TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			String filePath = session.getServletContext().getRealPath(Constants.ANSWERUPLOADRESS);
			String newFile = filePath+File.separator+tbAnswer.getAnswerImg();
	        tbAnswer.setAnswerImg("");
	        tbAnswer.setAnswerImgName("");	
	        tbAnswer.setUpdateUser(currentUser.getUserId());
	        tbAnswer.setUpdateTime(new Date());
	        boolean tag = answerService.updateAnswer(tbAnswer);
	        if(reQue!=null&&tag){
		        File tempfile = new File(newFile);
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }
		        saveJson.put("msg", "删除成功");
				saveJson.put("saveFlag", true);
	        }else{
				saveJson.put("msg", "删除失败");
				saveJson.put("saveFlag", false);
				logger.info("删除失败");
	        }
		} catch (Exception e) {
			saveJson.put("msg", "删除失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	
}

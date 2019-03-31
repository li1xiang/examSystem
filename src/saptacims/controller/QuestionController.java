package saptacims.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import pub.util.ComboboxVO;
import saptacims.cst.Constants;
import saptacims.cst.Levels;
import saptacims.cst.QuestionType;
import saptacims.cst.Status;
import saptacims.model.TbAnswer;
import saptacims.model.TbQuestion;
import saptacims.model.TbQuestionView;
import saptacims.model.TbUser;
import saptacims.service.IAnswerService;
import saptacims.service.IQuestionService;
import saptacims.vo.page.Pager;

/**
 * 考题管理
 * @author Xiangya Yan
 *
 */
@Controller
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IAnswerService answerService;
	
		
	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@RequestMapping(value="/getAddSubjectQuestion")
	public String getAddSubjectQuestion()
	{
		return "question/addSubjectQuestion";
	}
	
	@RequestMapping(value="/getAddObjectQuestion")
	public String getAddObjectQuestion()
	{
		return "question/addObjectQuestion";
	}
	
	@RequestMapping(value="/getQuestionManage")
	public String getQuestionManage()
	{
		return "question/questionManage";
	}

	@RequestMapping(value = "/getAddRejumentQuestion")
	public  String  getAddRejumentQuestion(){ return  "question/getAddRejumentQuestion";}
	// 题库查询
	@RequestMapping(value = "/getQuestionSelect")
	public String getQuestionSelect() {

		return "question/questionSelect";
	}

	@RequestMapping(value="/getMyQuestionManage")
	public String getMyQuestionManage(Model model,HttpSession session)
	{
		TbUser tbUser = (TbUser) session.getAttribute("currentUser");
		model.addAttribute("createUser",tbUser.getUserId());
		return "question/myQuestionManage";
	}
	
	@RequestMapping(value="/getAddAnswer")
	public String getAddAnswer(Model model,int questionId)
	{
		TbQuestion tbQuestion = questionService.getQuestion(questionId);
		String returnUrl = "";
		if(tbQuestion.getQuestionType() == 0){
			returnUrl = "question/addSubjecAnswer";
		}else{
			returnUrl = "question/addObjecAnswer";
		}
		model.addAttribute("question", tbQuestion);
		return returnUrl;
	}
	
	@RequestMapping(value="/getQuestionDetail")
	public String getQuestionDetail(Model model,Integer questionId,String action) throws ParseException
	{
		TbQuestionView tbQuestionView = questionService.getQuestionView(questionId);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String tempDate = format.format(tbQuestionView.getCreateTime());
		tbQuestionView.setCreateTimestr(tempDate);
		//后台控制前端展示图片的相对路径
		if(tbQuestionView.getSubjectImg()!=null&&!"".equals(tbQuestionView.getSubjectImg().trim())){
			tbQuestionView.setSubjectImg(Constants.QUESTIONUPLOADRESS+File.separator+tbQuestionView.getSubjectImg());
		}		
		//主观题要查询答案传到前端
		if(tbQuestionView.getQuestionType()==0){
			TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			if(tbAnswer!=null&&tbAnswer.getAnswerImg()!=null&&!"".equals(tbAnswer.getAnswerImg().trim())){
				tbAnswer.setAnswerImg(Constants.ANSWERUPLOADRESS+File.separator+tbAnswer.getAnswerImg());
			}
			model.addAttribute("answerDetail", tbAnswer);
		}
		model.addAttribute("questionDetail", tbQuestionView);
		model.addAttribute("action", action);

		String returnUrl = "question/questionDetail";
		return returnUrl;
	}

	// 题库试题明细
	@RequestMapping(value = "/getQuestionsDetail")
	public String getQuestionsDetail(Model model, Integer questionId) throws ParseException {
		TbQuestionView tbQuestionView = questionService.getQuestionView(questionId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String tempDate = format.format(tbQuestionView.getCreateTime());
		tbQuestionView.setCreateTimestr(tempDate);
		// 后台控制前端展示图片的相对路径
		if (tbQuestionView.getSubjectImg() != null && !"".equals(tbQuestionView.getSubjectImg().trim())) {
			tbQuestionView.setSubjectImg(Constants.QUESTIONUPLOADRESS + File.separator + tbQuestionView.getSubjectImg());
		}
		// 主观题要查询答案传到前端
		if (tbQuestionView.getQuestionType() == 0) {
			TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			if (tbAnswer != null && tbAnswer.getAnswerImg() != null && !"".equals(tbAnswer.getAnswerImg().trim())) {
				tbAnswer.setAnswerImg(Constants.ANSWERUPLOADRESS + File.separator + tbAnswer.getAnswerImg());
			}
			model.addAttribute("answerDetail", tbAnswer);
		}
		model.addAttribute("questionDetail", tbQuestionView);
		String returnUrl = "question/questionsDetail";
		return returnUrl;
	}

	//---------------------------------------获取下拉框值start--------------------------------------//
	@RequestMapping(value="/queTypeList")
	@ResponseBody
	public Object queTypeList(String page)
	{
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = ComboboxVO.getCstAllCombobox(QuestionType.class);
		}else{
			volist = ComboboxVO.getCstCombobox(QuestionType.class,String.valueOf(QuestionType.SUBJECTIVE));			
		}
		return volist;
	}
	
	@RequestMapping(value="/levelsList")
	@ResponseBody
	public Object levelsList(String page)
	{
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = ComboboxVO.getCstAllCombobox(Levels.class);
		}else{
			volist = ComboboxVO.getCstCombobox(Levels.class,String.valueOf(Levels.LEVEL3));			
		}
		return volist;
	}
	
	@RequestMapping(value="/activeList")
	@ResponseBody
	public Object activeList(String page)
	{
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = ComboboxVO.getCstAllCombobox(Status.class);
		}else{
			volist = ComboboxVO.getCstCombobox(Status.class,String.valueOf(Status.ENABLE));			
		}
		return volist;
	}
	
	@RequestMapping(value="/categoryList")
	@ResponseBody
	public Object categoryList(String page)
	{	
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = questionService.getCategoryList(true);
		}else{
			volist = questionService.getCategoryList(false);		
		}
		return volist;
	}
	
	@RequestMapping(value="/groupList")
	@ResponseBody
	public Object groupList(String page)
	{
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = questionService.getGroupList(true);
		}else{
			volist = questionService.getGroupList(false);		
		}
		return volist;
	}
	
	@RequestMapping(value="/createUserList")
	@ResponseBody
	public Object createUserList(String page)
	{
		List<ComboboxVO> volist = new LinkedList<ComboboxVO>();
		if("all".equals(page)){
			volist = questionService.getcreateUserList(true);
		}else{
			volist = questionService.getcreateUserList(false);		
		}
		return volist;
	}
	//---------------------------------------获取下拉框值end--------------------------------------//
	
	/**
	 * 新增主观题目和答案 新
	 * @param file
	 * @param question
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addSubjecQuestion",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addSubjecQuestion(@RequestParam(value="questionFile",required = false) MultipartFile file,@RequestParam(value="answerFile",required = false) MultipartFile answerFile,TbQuestionView question,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String questionRealName = "";
		String questionFilePath = "";
		String answerRealName = "";
		String answerFilePath = "";
		try {
			logger.info("--------新增试题开始--------");
			TbAnswer tbAnswer = new TbAnswer();
			//新增试题
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			if(!file.isEmpty()){//保存附件
				String fileName = file.getOriginalFilename();  
				questionFilePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);
				File tempFile = new File(questionFilePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				questionRealName = String.valueOf(System.currentTimeMillis())+suffix;
				question.setSubjectImg(questionRealName);
				question.setSubjectImgName(file.getOriginalFilename());
	            file.transferTo(new File(questionFilePath+File.separator+questionRealName));  

			}		
			if(!answerFile.isEmpty()){
				String fileName = answerFile.getOriginalFilename();  
				answerFilePath = session.getServletContext().getRealPath(Constants.ANSWERUPLOADRESS);
				File tempFile = new File(answerFilePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				answerRealName = String.valueOf(System.currentTimeMillis())+suffix;
				tbAnswer.setAnswerImg(answerRealName);
				tbAnswer.setAnswerImgName(answerFile.getOriginalFilename());
				answerFile.transferTo(new File(answerFilePath+File.separator+answerRealName));  

			}
			
			question.setCreateUser(currentUser.getUserId());
			question.setUpdateUser(currentUser.getUserId());
			question.setCreateTime(new Date());
			question.setUpdateTime(new Date());
			question.setActive(0);
			
			
			tbAnswer.setAnswer(question.getAnswer());
			tbAnswer.setIsright(1);
			tbAnswer.setCreateUser(currentUser.getUserId());
			tbAnswer.setCreateTime(new Date());
			tbAnswer.setUpdateUser(currentUser.getUserId());
			tbAnswer.setUpdateTime(new Date());
			Boolean tag = questionService.saveSubjecQuestion(question,tbAnswer);
			if(tag){
				//更改题目状态为启用
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}else{
				File tempQuefile = new File(questionFilePath+File.separator+questionRealName);
		        if (tempQuefile.exists() && tempQuefile.isFile()) {
		        	tempQuefile.delete();
		        }
				
				File tempfile = new File(answerFilePath+File.separator+answerRealName);
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }
		        saveJson.put("msg", "保存失败");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");
			}

		} catch (Exception e) {
			File tempQuefile = new File(questionFilePath+File.separator+questionRealName);
	        if (tempQuefile.exists() && tempQuefile.isFile()) {
	        	tempQuefile.delete();
	        }
			
			File tempfile = new File(answerFilePath+File.separator+answerRealName);
	        if (tempfile.exists() && tempfile.isFile()) {
	            tempfile.delete();
	        }
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.info("保存失败");
		}
		return saveJson;
	}
	
	/**
	 * 新增客观题 新
	 * @param file
	 * @param question
	 * @param answerTable
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addObjecQuestion",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addObjecQuestion(@RequestParam(value="questionFile",required = false) MultipartFile file,TbQuestionView question,String answerTable,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String questionRealName = "";
		String questionFilePath = "";
		try {
			logger.info("--------新增试题开始--------");
			//新增试题
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			if(!file.isEmpty()){//保存附件
				String fileName = file.getOriginalFilename();  
				questionFilePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);
				File tempFile = new File(questionFilePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				questionRealName = String.valueOf(System.currentTimeMillis())+suffix;
				question.setSubjectImg(questionRealName);
				question.setSubjectImgName(file.getOriginalFilename());
 
	            file.transferTo(new File(questionFilePath+File.separator+questionRealName));  

			}		
			question.setCreateUser(currentUser.getUserId());
			question.setUpdateUser(currentUser.getUserId());
			question.setCreateTime(new Date());
			question.setUpdateTime(new Date());
			question.setActive(0);
			
			Boolean tag = questionService.saveObjecQuestion(question,answerTable);
			if(tag){
				//更改题目状态为启用
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}else{

				File tempQuefile = new File(questionFilePath+File.separator+questionRealName);
		        if (tempQuefile.exists() && tempQuefile.isFile()) {
		        	tempQuefile.delete();
		        }
				
		        saveJson.put("msg", "保存失败");
				saveJson.put("saveFlag", false);
				logger.info("保存失败");
			}

		} catch (Exception e) {
			File tempQuefile = new File(questionFilePath+File.separator+questionRealName);
	        if (tempQuefile.exists() && tempQuefile.isFile()) {
	        	tempQuefile.delete();
	        }			
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.info("保存失败");
		}
		return saveJson;
	}

	/**
	 * 新增判断题
	 * @param tbQuestionView
	 * @param isright
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addAddRejumentQuestion",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addAddRejumentQuestion(TbQuestionView tbQuestionView,String isright,HttpSession session){
		JSONObject saveJson = new JSONObject();
		String questionRealName = "";
		String questionFilePath = "";
		try {
			logger.info("--------新增试题开始--------");
			//新增试题
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");

			tbQuestionView.setCreateUser(currentUser.getUserId());
			tbQuestionView.setUpdateUser(currentUser.getUserId());
			tbQuestionView.setCreateTime(new Date());
			tbQuestionView.setUpdateTime(new Date());
			tbQuestionView.setActive(0);

			Boolean tag = questionService.saveRejumentQuestion(tbQuestionView, isright);
			if (tag) {
				//更改题目状态为启用
				saveJson.put("msg", "保存成功");
				saveJson.put("saveFlag", true);
			}

		} catch (Exception e) {
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
			logger.info("保存失败");
		}
		return saveJson;
	}
	
	/**
	 * 获取题目列表
	 * @param question
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/questionList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> questionList(TbQuestion question,Pager page)
	{ 		
		logger.info("试题列表 开始 ------");
		Map<String, Object> result = questionService.questionListPage(question,page);
		logger.info("试题列表 结束 ------");
		return result;
	}
	
	
	/**
	 * 修改题目状态
	 * @param questionIds
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateStatus(@RequestParam("questionIds[]")List<Integer> questionIds,@RequestParam("status")Integer status,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		logger.info("--------批量禁用/启用试题开始--------");
		try {
			int num = questionService.batchUpdateStatus(questionIds,status);
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
			
	
	/**
	 * 删除题目附件
	 * @param questionId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteImg",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteImg(@RequestParam(value="questionId",required = false) Integer questionId, HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		try {
			logger.info("--------删除题目图片开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			TbQuestion tbQuestion = questionService.getQuestion(questionId);
			String filePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);
			String newFile = filePath+File.separator+tbQuestion.getSubjectImg();			
	        tbQuestion.setSubjectImg("");
	        tbQuestion.setSubjectImgName("");
	        tbQuestion.setUpdateUser(currentUser.getUserId());
	        currentUser.setUpdateTime(new Date());
	        TbQuestion reQue = questionService.updateQuestion(tbQuestion);
	        if(reQue!=null){
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
			logger.info("删除失败");
		}
		return saveJson;
	}
	
	/**
	 * 获取我的试题明细
	 * @param model
	 * @param questionId
	 * @param action
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/getMyQuestionDetail")
	public String getMyQuestionDetail(Model model,Integer questionId,String action) throws ParseException
	{
		TbQuestionView tbQuestionView = questionService.getQuestionView(questionId);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String tempDate = format.format(tbQuestionView.getCreateTime());
		tbQuestionView.setCreateTimestr(tempDate);
		//后台控制前端展示图片的相对路径
		if(tbQuestionView.getSubjectImg()!=null&&!"".equals(tbQuestionView.getSubjectImg().trim())){
			tbQuestionView.setSubjectImg(Constants.QUESTIONUPLOADRESS+File.separator+tbQuestionView.getSubjectImg());
		}		
		//主观题要查询答案传到前端
		if(tbQuestionView.getQuestionType()==0){
			TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			if(tbAnswer!=null&&tbAnswer.getAnswerImg()!=null&&!"".equals(tbAnswer.getAnswerImg().trim())){
				tbAnswer.setAnswerImg(Constants.ANSWERUPLOADRESS+File.separator+tbAnswer.getAnswerImg());
			}
			model.addAttribute("answerDetail", tbAnswer);
		}
		model.addAttribute("questionDetail", tbQuestionView);
		model.addAttribute("action", action);

		String returnUrl = "question/myQuestionDetail";
		return returnUrl;
	}
	
	
	/**
	 * 跳转到修改题目页面 新
	 * @param model
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value="/getMyUpdateQuestion")
	public String getMyUpdateQuestion(Model model,Integer questionId)
	{
		String url = "";
		TbQuestion tbQuestion = questionService.getQuestion(questionId);
		//后台控制前端展示图片的相对路径
		if(tbQuestion.getSubjectImg()!=null&&!"".equals(tbQuestion.getSubjectImg().trim())){
			tbQuestion.setSubjectImg(Constants.QUESTIONUPLOADRESS+File.separator+tbQuestion.getSubjectImg());
		}	
		model.addAttribute("tbQuestion",tbQuestion);
		//主观题 带答案
		if(0==tbQuestion.getQuestionType()){
			TbAnswer tbAnswer = answerService.getSubjectAnswer(questionId);
			if(tbAnswer!=null&&tbAnswer.getAnswerImg()!=null&&!"".equals(tbAnswer.getAnswerImg().trim())){
				tbAnswer.setAnswerImg(Constants.ANSWERUPLOADRESS+File.separator+tbAnswer.getAnswerImg());
			}
			model.addAttribute("answerDetail", tbAnswer);
			url = "question/updateSubQuestion" ;
		}else{
			url = "question/updateObQuestion";
		}
		
		return url;
	}
	
	
	
	/**
	 * 修改题目主观新
	 * @param file
	 * @param newQuestion
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateSubQuestion",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSubQuestion(@RequestParam(value="questionFile",required = false) MultipartFile file,@RequestParam(value="answerFile",required = false) MultipartFile answerFile,TbQuestion newQuestion,TbAnswer newAnswer,String delSubjectImg,String delAnswerImg, HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String questionRealName = "";
		String questionFilePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);;
		String answerRealName = "";
		String answerFilePath = session.getServletContext().getRealPath(Constants.ANSWERUPLOADRESS);
		try {		
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			logger.info("--------更新题目开始--------");
			//更新题目
			TbQuestion question = questionService.getQuestion(newQuestion.getQuestionId());
			question.setCategoryId(newQuestion.getCategoryId());
			question.setGroupId(newQuestion.getGroupId());
			question.setScore(newQuestion.getScore());
			question.setLevels(newQuestion.getLevels());
			question.setSubject(newQuestion.getSubject());		
			//操作题目图片
			String oldSubjectImg = question.getSubjectImg();//旧图片地址
			if("Y".equals(delSubjectImg)){
				question.setSubjectImg("");
				question.setSubjectImgName("");				
			}
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();  	
				File tempFile = new File(questionFilePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				questionRealName = String.valueOf(System.currentTimeMillis())+suffix;
				question.setSubjectImg(questionRealName);
				question.setSubjectImgName(file.getOriginalFilename());
	            file.transferTo(new File(questionFilePath+File.separator+questionRealName));  
			}
			question.setUpdateUser(currentUser.getUserId());
			question.setUpdateTime(new Date());
			
			//更新答案
			TbAnswer answer = answerService.getSubjectAnswer(newQuestion.getQuestionId());			
			answer.setAnswer(newAnswer.getAnswer());			
			//操作答案图片
			String oldAnswerImg = answer.getAnswerImg();
			if("Y".equals(delAnswerImg)){
				answer.setAnswerImg("");
				answer.setAnswerImgName("");
			}
			if(!answerFile.isEmpty()){
				//保存上传的新附件
				String fileName = answerFile.getOriginalFilename();  
				File tempFile = new File(answerFilePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				answerRealName = System.currentTimeMillis()+suffix;
				answer.setAnswerImg(answerRealName);
				answer.setAnswerImgName(answerFile.getOriginalFilename()); 
				answerFile.transferTo(new File(answerFilePath+File.separator+answerRealName));  
			}
			answer.setUpdateUser(currentUser.getUserId());
			answer.setUpdateTime(new Date());	
			//更新题目和答案			
			Boolean tag = questionService.updateSubQuestion(question,answer);
			if(tag){//更新成功操作图片
				//题目存在旧图片
				if(oldSubjectImg!=null&&!"".equals(oldSubjectImg)){
					if("Y".equals(delSubjectImg)||!"".equals(questionRealName)){//点击删除图片或上传了新图片
						File tempfile = new File(questionFilePath+File.separator+oldSubjectImg);
				        if (tempfile.exists() && tempfile.isFile()) {
				            tempfile.delete();
				        }
					}
				}				
				//答案存在旧图片
				if(oldAnswerImg!=null&&!"".equals(oldAnswerImg)){
					if("Y".equals(delAnswerImg)||!"".equals(answerRealName)){//点击删除图片或上传了新图片
						File tempfile = new File(answerFilePath+File.separator+oldAnswerImg);
				        if (tempfile.exists() && tempfile.isFile()) {
				            tempfile.delete();
				        }
					}
				}										
				saveJson.put("questionId", newQuestion.getQuestionId());
				saveJson.put("msg", "题目更新成功");
				saveJson.put("saveFlag", true);
			}else{//题目更新失败删除刚保存的附件
				File tempfile = new File(questionFilePath+File.separator+questionRealName);
		        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }
		        File tempAnfile = new File(answerFilePath+File.separator+answerRealName);
		        if (tempAnfile.exists() && tempAnfile.isFile()) {
		        	tempAnfile.delete();
		        }			
			}
		} catch (Exception e) {
			File tempQuefile = new File(questionFilePath+File.separator+questionRealName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (tempQuefile.exists() && tempQuefile.isFile()) {
	        	tempQuefile.delete();
	        }        
			File tempAnfile = new File(answerFilePath+File.separator+answerRealName);
	        if (tempAnfile.exists() && tempAnfile.isFile()) {
	        	tempAnfile.delete();
	        }
			saveJson.put("msg", "题目更新失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	/**
	 * 更新客观题
	 * @param file
	 * @param newQuestion
	 * @param answerTable
	 * @param session
	 * @return
	 */
	 
	@RequestMapping(value="/updateObQuestion",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateObQuestion(@RequestParam(value="questionFile",required = false) MultipartFile file,TbQuestionView newQuestion,String answerTable,String delSubjectImg,HttpSession session)
	{ 
		JSONObject saveJson = new JSONObject(); 
		String realName = "";
		String filePath = session.getServletContext().getRealPath(Constants.QUESTIONUPLOADRESS);
		try {
			logger.info("--------更新题目开始--------");
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			TbQuestion question = questionService.getQuestion(newQuestion.getQuestionId());
			question.setCategoryId(newQuestion.getCategoryId());
			question.setGroupId(newQuestion.getGroupId());
			question.setScore(newQuestion.getScore());
			question.setLevels(newQuestion.getLevels());
			question.setSubject(newQuestion.getSubject());				
			String oldSubjectImg = question.getSubjectImg();//旧图片地址
			if("Y".equals(delSubjectImg)){
				question.setSubjectImg("");
				question.setSubjectImgName("");				
			}
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();  	
				File tempFile = new File(filePath);
				if  (!tempFile.exists()  && !tempFile.isDirectory())      
				{        
				    tempFile.mkdirs();    
				}
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				realName = String.valueOf(System.currentTimeMillis())+suffix;
				question.setSubjectImg(realName);
				question.setSubjectImgName(file.getOriginalFilename());
	            file.transferTo(new File(filePath+File.separator+realName));  

			}
			question.setUpdateUser(currentUser.getUserId());
			question.setUpdateTime(new Date());
						
			Boolean tag = questionService.updateObQuestion(question,answerTable);
			if(tag){	
				//题目存在旧图片
				if(oldSubjectImg!=null&&!"".equals(oldSubjectImg)){
					if("Y".equals(delSubjectImg)||!"".equals(realName)){//点击删除图片或上传了新图片
						File tempfile = new File(filePath+File.separator+oldSubjectImg);
				        if (tempfile.exists() && tempfile.isFile()) {
				            tempfile.delete();
				        }
					}
				}	
				saveJson.put("questionId", newQuestion.getQuestionId());
				saveJson.put("msg", "题目更新成功");
				saveJson.put("saveFlag", true);
			}else{//题目更新失败删除刚保存的附件
				File tempfile = new File(filePath+File.separator+realName);
		        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		        if (tempfile.exists() && tempfile.isFile()) {
		            tempfile.delete();
		        }		           
			}
		} catch (Exception e) {
			File tempfile = new File(filePath+File.separator+realName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (tempfile.exists() && tempfile.isFile()) {
	            tempfile.delete();
	        }
			saveJson.put("msg", "题目更新失败");
			saveJson.put("saveFlag", false);
			logger.error(e.getMessage(),e);
		}
		return saveJson;
	}
	
	
	
}

package saptacims.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import pub.util.ComboboxVO;
import pub.util.RtfUtil;
import pub.util.StringUtil;
import saptacims.cst.Constants;
import saptacims.cst.Gender;
import saptacims.cst.QuestionType;
import saptacims.cst.Status;
import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbInterviewer;
import saptacims.model.TbPaperDetail;
import saptacims.model.TbUser;
import saptacims.service.IPaperService;
import saptacims.vo.base.PaperVo;
import saptacims.vo.base.QuestionVo;
import saptacims.vo.page.Pager;

/**
 * 答卷
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/paper")
public class PaperController {
	@Autowired
	private IPaperService paperService;

	private static Logger logger = LoggerFactory
			.getLogger(PaperController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/choosePaper")
	public String choosePaper() {
		return "paper/choosePaper";
	}

	/**
	 * 列出该面试者的关联试卷
	 * 
	 * @return
	 */
	@RequestMapping("paperList")
	@ResponseBody
	public Map<String, Object> paperList(Pager page,
			TbInterviewer interviewer) {
		logger.info("列出该面试者的关联试卷:paperList......");
		return paperService.qryInterviewerExamination(page, interviewer);
	}

	@RequestMapping(value = "/sexList")
	@ResponseBody
	public Object sexList() {
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Gender.class,
				String.valueOf(Gender.MALE));
		return volist;
	}

	@RequestMapping(value = "/statusList")
	@ResponseBody
	public Object statusList() {
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Status.class,
				String.valueOf(Status.ENABLE));
		return volist;
	}

	/**
	 * 插入答卷信息,跳转到答卷页面
	 * @param model
	 * @param examinationId
	 * @param name
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/answerPaper", produces = "text/html;charset=UTF-8" )
	public String answerPaper(Model model,int examinationId,String name,String phone, HttpSession session) {
		logger.info("试卷Id:" + examinationId);
		try{
			String interviewerName = new String(name.getBytes("iso-8859-1"),"utf-8");
			TbUser currentUser = (TbUser)session.getAttribute("currentUser");
			TbInterviewer interviewer = new TbInterviewer();
			interviewer.setName(interviewerName);
			interviewer.setPhone(phone);
			
			//插入答卷信息 返回插入的paperId
			int paperId = paperService.savePaper(interviewer,examinationId,currentUser);
			//查询答卷Id
			List<TbExaminationQuestion> questions = paperService.getAllQuestionIdByExaminationId(examinationId);
			model.addAttribute("paperId",paperId);
//			List<Integer> questionIds = new ArrayList<>();
//			for (int i = 0; i < 18; i++) {
//				questionIds.add(i);
//			}
			int fixSize = 10 - questions.size()%10;
			for (int i = 0; i < fixSize; i++) {
				TbExaminationQuestion question = new TbExaminationQuestion();
				question.setExaminationQuestionId(-1);
				questions.add(question);
			}
			model.addAttribute("questions", questions);
			model.addAttribute("examinationId",examinationId);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		
		return "paper/answerPaper";
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
	 * 下一题 保存上一题的结果
	 * @param paperDetail
	 * @return
	 */
	@RequestMapping(value="/nextQuestion", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject nextQuestion(TbPaperDetail paperDetail, HttpSession session){
		logger.info("下一题 保存上一题的结果：nextQuestion......");
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("msg", "保存失败");
			result.put("saveFlag", false);
		}
		
		
		return result;
	}
	
	/**
	 * 交卷
	 * @param paperDetails
	 * @return
	 */
	@RequestMapping(value="/submitPaper", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject submitPaper(int paperId, HttpSession session){
		JSONObject result = new JSONObject();
		logger.info("交卷:submitPaper......");
		logger.info("paperId" + paperId);
		try {
			TbUser currentUser = (TbUser)session.getAttribute("currentUser");
			boolean saveFlag = paperService.submitPaper(paperId, currentUser.getUserId());
			if(saveFlag)
			{
				result.put("msg", "交卷成功");
			}else{
				result.put("msg", "交卷失败");
			}
			result.put("submitFlag", saveFlag);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		result.put("msg", "交卷失败");
		result.put("submitFlag", false);
	}
		return result;
	}

	@RequestMapping(value="/downloadExamination", method = RequestMethod.GET)
	@ResponseBody
	public String downloadExamination(int examinationId,HttpServletRequest request, HttpSession session, HttpServletResponse response){
		logger.info("下载试卷");
		try {
			PaperVo paperVo = paperService.getFullExamination(examinationId);
			String path = session.getServletContext()
					.getRealPath(Constants.EXAMINATIONPRINTDRESS);
			File file = new File(path);
			// 判断路径是否存在
			if (!file.exists())
				file.mkdirs();
			String fileName = path + File.separator
					+ paperVo.getExaminationName() + System.currentTimeMillis()
					+ ".rtf";
			String imgPath = session.getServletContext()
					.getRealPath(Constants.QUESTIONUPLOADRESS);
			response.reset();//清空response
			response.setContentType("application/octet-stream");
			String filenamedisplay = "";
			if ("FF".equals(getBrowser(request))) {
				// 针对火狐浏览器处理方式不一样了
				filenamedisplay = new String(
						paperVo.getExaminationName().getBytes("UTF-8"),
						"iso-8859-1") + ".rtf";
			} else {
				filenamedisplay = URLEncoder
						.encode(paperVo.getExaminationName(), "UTF-8") + ".rtf";
			}
			response.setHeader("Content-Disposition","attachment;filename=" + filenamedisplay);
			//response.setCharacterEncoding("utf-8");
			OutputStream os=response.getOutputStream();
			createRtfPage(paperVo, fileName, imgPath, os);
//		    FileInputStream fis = new FileInputStream(new File(fileName));
		    
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
	
	public static PaperVo newPaperVo(){
		//新增试卷
		PaperVo paperVo = new PaperVo();
		paperVo.setExaminationName("试卷名");
		//	新增题目
		List<QuestionVo> questions = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			QuestionVo q = new QuestionVo();
			if(i%5 > 0)
				q.setQuestionTitle("问题" + i);
			q.setQuestionImgPath("QQ截图20170210100456.png");
			if(i%10 != 9)
				q.setQuestionImgName("QQ截图20170210100456.png");
			if(i>5){
				List<TbExaminationAnswer> alist = new ArrayList<>();
				q.setQuestionType(QuestionType.SUBJECTIVE);
				TbExaminationAnswer ta = new TbExaminationAnswer();
				ta.setAnswerText("答案");
				alist.add(ta);
				q.setOptions(alist);
			}
			else{
				q.setQuestionType(QuestionType.OBJECTIVE);
				List<TbExaminationAnswer> alist = new ArrayList<>();
				//新增选项
				for (int j = 0; j < 4; j++) {
					TbExaminationAnswer answer = new TbExaminationAnswer();
					answer.setAnswerText("选项" + j);
					if(j == 3)
						answer.setIsright(1);
					alist.add(answer);
				}
				q.setOptions(alist);
				
			}
			questions.add(q);
		}
		paperVo.setQuestionList(questions);
		return paperVo;
	}

	/**
	 * 根据文件名画examination
	 * @param paperVo
	 * @param fileName
	 * @param imgPath
	 * @param os
	 */
	public void createRtfPage(PaperVo paperVo, String fileName, String imgPath,OutputStream os) {
		String[] opIndex = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
//		PaperVo paperVo = newPaperVo();
		Document document = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
//			String imgPath = "e:\\";
			
//			String filePath = path + File.separator
//					+ paperVo.getExaminationName() + System.currentTimeMillis()
//					+ ".rtf";
			logger.debug(fileName);
			RtfUtil rtfUtil = new RtfUtil();
			document = rtfUtil.createDocument(PageSize.A4, fileName);
			RtfWriter2.getInstance(document, os);
			document.open();
			BaseFont bfChinese = BaseFont.createFont();// 设置中文字体
			Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 标题字体风格
			Font questionFont = new Font(bfChinese, 11, Font.NORMAL);// 题目字体风格
			Paragraph title = new Paragraph(paperVo.getExaminationName());
			title.setAlignment(Element.ALIGN_CENTER);// 设置标题格式对齐方式
			title.setFont(titleFont);
			document.add(title);
			// 答卷人 手机
			Paragraph testerInfo = new Paragraph("姓名:                     手机:                     试卷打印时间:" + sdf.format(paperVo.getExaminationUpdateDate()));
			testerInfo.setAlignment(Element.ALIGN_CENTER);// 设置标题格式对齐方式
			testerInfo.setSpacingBefore(questionFont.getSize());
			testerInfo.setSpacingAfter(questionFont.getSize());
			title.setFont(questionFont);
			document.add(testerInfo);
			// 循环写题目
			List<QuestionVo> questions = paperVo.getQuestionList();
			for (int i = 0; i < questions.size(); i++) {
				if(i > 0){
					//第一题不空行
					Paragraph betweenQuestionSpace = new Paragraph();
					document.add(betweenQuestionSpace);
				}
				QuestionVo question = questions.get(i);
				boolean isSubjective = question
						.getQuestionType() == QuestionType.SUBJECTIVE;
				// boolean resultFlag = false;
				if (question.getQuestionTitle() != null
						&& !"".equals(question.getQuestionTitle())) {
					String questionTitle = (i + 1) + "."
							+ question.getQuestionTitle();
					// 题目描述
					Paragraph questionContext = new Paragraph(
							questionTitle + (isSubjective ? "" : "   (    )"));
					questionContext.setAlignment(Element.ALIGN_LEFT);
					questionContext.setFont(questionFont);
					document.add(questionContext);
				} else {
					// 客观题，但是没有文字描述，写"请回答 （）"
					String questionTitle = (i + 1) + ".";
					Paragraph questionContext = new Paragraph(questionTitle
							+ (isSubjective ? "请回答" : "答案是   (    )"));
					questionContext.setAlignment(Element.ALIGN_LEFT);
					questionContext.setFont(questionFont);
					document.add(questionContext);
				}
				if (question.getQuestionImgName() != null
						&& !"".equals(question.getQuestionImgName())) {
					// 题目图片不为空
					Paragraph imgPara = new Paragraph();
					Image png = Image.getInstance(imgPath + File.separator
							+ question.getQuestionImgPath());
					rtfUtil.scaleImg(document, png);
					imgPara.add(png);
					document.add(imgPara);
				}
				if (isSubjective) {
					// 主观题答题区
					Paragraph resultArea = new Paragraph("\n\n\n");
					document.add(resultArea);
				}
				if (isSubjective)
					continue;
				List<TbExaminationAnswer> options = question.getOptions();
				for (int j = 0; j < options.size(); j++) {
					TbExaminationAnswer option = options.get(j);
					String optionText = opIndex[j] + "　"
							+ option.getAnswerText();
					// 答案描述
					Paragraph optionContext = new Paragraph(optionText);
					optionContext.setAlignment(Element.ALIGN_LEFT);
					optionContext.setFont(questionFont);
					// optionContext.setSpacingBefore(40);
					document.add(optionContext);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (DocumentException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (document != null)
				document.close();
		}
	}
	
	@RequestMapping(value="downloadAnswerPage", method = RequestMethod.GET)
	@ResponseBody
	public String downloadAnswerPage(int examinationId,HttpServletRequest request, HttpSession session, HttpServletResponse response){
		logger.info("下载试卷");
		try {
			PaperVo paperVo = paperService.getFullExamination(examinationId);
			String path = session.getServletContext()
					.getRealPath(Constants.EXAMINATIONPRINTDRESS);
			File file = new File(path);
			// 判断路径是否存在
			if (!file.exists())
				file.mkdirs();
			String fileName = path + File.separator
					+ paperVo.getExaminationName() + System.currentTimeMillis()
					+ ".rtf";
			String imgPath = session.getServletContext()
					.getRealPath(Constants.QUESTIONUPLOADRESS);
			response.reset();//清空response
			response.setContentType("application/octet-stream");
			String filenamedisplay = "";
			if ("FF".equals(getBrowser(request))) {
				// 针对火狐浏览器处理方式不一样了
				filenamedisplay = new String(
						paperVo.getExaminationName().getBytes("UTF-8"),
						"iso-8859-1") +new String(
								"的答案".getBytes("UTF-8"),
								"iso-8859-1") + ".rtf";
			} else {
				filenamedisplay = URLEncoder
						.encode(paperVo.getExaminationName(), "UTF-8") + URLEncoder
						.encode("的答案", "UTF-8") + ".rtf";
			}
			response.setHeader("Content-Disposition","attachment;filename="+filenamedisplay);
			response.setCharacterEncoding("utf-8");
			OutputStream os=response.getOutputStream();
			createRtfAnswerPage(paperVo, fileName, imgPath, os);
//		    FileInputStream fis = new FileInputStream(new File(fileName));
		    
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public void createRtfAnswerPage(PaperVo paperVo, String fileName, String imgPath, OutputStream os) {
		String[] opIndex = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		Document document = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
			RtfUtil rtfUtil = new RtfUtil();
			document = rtfUtil.createDocument(PageSize.A4, fileName);
			RtfWriter2.getInstance(document, os);
			document.open();
			BaseFont bfChinese = BaseFont.createFont();// 设置中文字体
			Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 标题字体风格
			Font QuestionFont = new Font(bfChinese, 11, Font.NORMAL);// 正文字体风格
			Font answerFont = new Font(bfChinese, 10, Font.NORMAL);// 正文字体风格
			Paragraph title = new Paragraph(paperVo.getExaminationName());
			title.setAlignment(Element.ALIGN_CENTER);// 设置标题格式对齐方式
			title.setFont(titleFont);
			document.add(title);
			// 答卷人 手机
			Paragraph testerInfo = new Paragraph("姓名:                     手机:                     试卷打印时间:" + sdf.format(paperVo.getExaminationUpdateDate()));
			testerInfo.setAlignment(Element.ALIGN_CENTER);// 设置标题格式对齐方式
			testerInfo.setSpacingBefore(QuestionFont.getSize());
			testerInfo.setSpacingAfter(QuestionFont.getSize());
			title.setFont(QuestionFont);
			document.add(testerInfo);
			// 循环写题目
			List<QuestionVo> questions = paperVo.getQuestionList();
			for (int i = 0; i < questions.size(); i++) {
				if(i > 0){
					Paragraph betweenQuestionSpace = new Paragraph();
					document.add(betweenQuestionSpace);
				}
				QuestionVo question = questions.get(i);
				boolean isSubjective = question
						.getQuestionType() == QuestionType.SUBJECTIVE;
				List<TbExaminationAnswer> optionss = question.getOptions();
				String answerText = "";//正确答案选项
				for (int j = 0; j < optionss.size(); j++) {
					TbExaminationAnswer option = optionss.get(j);
					logger.debug(option.getIsright()+"");
					if(option.getIsright() != null && option.getIsright()==1)
						answerText = opIndex[j];
				}
				// boolean resultFlag = false;
				if (question.getQuestionTitle() != null
						&& !"".equals(question.getQuestionTitle())) {
					//题目描述不为空
					String questionTitle = (i + 1) + "."
							+ question.getQuestionTitle();
					
					// 题目描述
					Paragraph questionContext = new Paragraph(
							questionTitle + (isSubjective ? "" : "   (  "+answerText+"  )"));
					questionContext.setAlignment(Element.ALIGN_LEFT);
					questionContext.setFont(QuestionFont);
					document.add(questionContext);
				} else {
					// 客观题，但是没有文字描述，写"请回答 （）"
					String questionTitle = (i + 1) + ".";
					Paragraph questionContext = new Paragraph(questionTitle
							+ (isSubjective ? "请回答" : "答案是   (  "+answerText+"  )"));
					questionContext.setAlignment(Element.ALIGN_LEFT);
					questionContext.setFont(QuestionFont);
					document.add(questionContext);
				}
				if (question.getQuestionImgName() != null
						&& !"".equals(question.getQuestionImgName())) {
					// 题目图片不为空
					Paragraph imgPara = new Paragraph();
					Image png = Image.getInstance(imgPath + File.separator
							+ question.getQuestionImgPath());
					rtfUtil.scaleImg(document, png);
					imgPara.add(png);
					document.add(imgPara);
				}
				if (isSubjective) {
					// 主观题答题区
//					Paragraph resultArea = new Paragraph("\n\n\n");
					String str = question.getOptions().get(0).getAnswerText();
					System.out.println(str);
					Paragraph resultArea = new Paragraph("    答:  " + StringUtil.replaceBlank(str));
					resultArea.setFont(answerFont);
					document.add(resultArea);
				}
				if (isSubjective)
					continue;
				List<TbExaminationAnswer> options = question.getOptions();
				for (int j = 0; j < options.size(); j++) {
					TbExaminationAnswer option = options.get(j);
					String optionText = opIndex[j] + "　"
							+ option.getAnswerText();
					// 答案描述
					Paragraph optionContext = new Paragraph(optionText);
					optionContext.setAlignment(Element.ALIGN_LEFT);
					optionContext.setFont(answerFont);
					// optionContext.setSpacingBefore(40);
					document.add(optionContext);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (DocumentException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (document != null)
				document.close();
		}
	}
	// 以下为服务器端判断客户端浏览器类型的方法  
    private String getBrowser(HttpServletRequest request) {  
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();  
        if (UserAgent != null) {  
            if (UserAgent.indexOf("msie") >= 0)  
                return "IE";  
            if (UserAgent.indexOf("firefox") >= 0)  
                return "FF";  
            if (UserAgent.indexOf("safari") >= 0)  
                return "SF";  
        }  
        return null;  
    }  
//	public static void main(String[] args) {
//	PaperVo paperVo = newPaperVo();
//	String path = "e:\\" + Constants.EXAMINATIONPRINTDRESS;
//	File file = new File(path);
//	// 判断路径是否存在
//	if (!file.exists())
//		file.mkdirs();
//	String fileName = path + File.separator
//			+ paperVo.getExaminationName() + System.currentTimeMillis()
//			+ ".rtf";
//	String imgPath = "e:\\";
//	createRtfAnswerPage(paperVo, fileName, imgPath);
//}
}

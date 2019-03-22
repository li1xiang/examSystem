package saptacims.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pub.util.ComboboxVO;
import saptacims.cst.Constants;
import saptacims.cst.InterviewerSource;
import saptacims.cst.Status;
import saptacims.model.TbExamination;
import saptacims.model.TbExaminationInterviewerRl;
import saptacims.model.TbInterviewer;
import saptacims.model.TbUser;
import saptacims.service.IExaminationService;
import saptacims.service.IInterviewerService;
import saptacims.vo.page.Pager;

/**
 * 面试者管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/interviewer")
public class InterviewerController {

	@Autowired
	private IInterviewerService interviewerService;

	@Autowired
	private IExaminationService examinationService;
	
	private static Logger logger = LoggerFactory
			.getLogger(InterviewerController.class);
	/**
     * 表单提交日期绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//写上你要的日期格式
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/interviewerManage")
	public String choosePaper() {
		return "interviewer/interviewerManage";
	}

	/**
	 * 加载面试者列表
	 * 
	 * @return
	 */
	@RequestMapping("interviewerList")
	@ResponseBody
	public Map<String, Object> interviewerList(Pager page,
			TbInterviewer interviewer) {
		logger.info("加载面试者列表:interviewerList......");

		return interviewerService.interviewerList(page, interviewer);
	}

	/**
	 * 加载面试者列表
	 * 
	 * @return
	 */
	@RequestMapping("examinationList")
	@ResponseBody
	public Map<String, Object> examinationList() {
		logger.info("加载面试者列表:interviewerList......");
		return interviewerService.examinationList();
	}
	
	/**
	 * 加载查询条件源
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sourceList")
	@ResponseBody
	public Object sourceList() {
		logger.info("加载面试者来源下拉框:sourceList......");
		List<ComboboxVO> volist = ComboboxVO
				.getCstAllCombobox(InterviewerSource.class);
		return volist;
	}

	/**
	 * 加载新增页面面试者来源下拉框
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sourceListInNew")
	@ResponseBody
	public Object sourceListInNew() {
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(
				InterviewerSource.class, InterviewerSource.FORMAL_TEXT);
		return volist;
	}
	
	/**
	 * 状态下拉框
	 * 
	 * @return
	 */
	@RequestMapping(value = "/statusList")
	@ResponseBody
	public Object statusList() {
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(
				Status.class, Status.ENABLE_TEXT);
		return volist;
	}

	/**
	 * 跳转到录入面试者下拉框
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newInterviewer")
	public Object newInterviewer() {
		logger.info("跳转到录入面试者下拉框:newInterviewer......");
		return "interviewer/newInterviewer";
	}

	/**
	 * 新增面试者
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/newInterviewer/save", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject save(
			@RequestParam(value = "attachFile", required = false) MultipartFile attachFile,
			TbInterviewer interviewer, HttpSession session) {
		logger.info("新增面试者:save......");
		JSONObject saveJson = new JSONObject();
		try {
			boolean existSamePhoneInterviewer = interviewerService.existSamePhoneInterviewer(interviewer.getPhone());
			//手机号码不能重复
			if(existSamePhoneInterviewer){
				//若存在相同手机号的面试者，则不继续新增，提示错误
				saveJson.put("msg", "面试者手机号不能相同！");
    			saveJson.put("saveFlag", false);
    			return saveJson;
			}
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			if (!attachFile.isEmpty()) {
				String realName = attachFile.getOriginalFilename();
				String attachPath = session.getServletContext()
						.getRealPath(Constants.INTERVIEWERRESUME );
				String pathAndName = attachPath + File.separator
						+ System.currentTimeMillis() + realName; 
				try{
					File file = new File(attachPath);
					if(!file.exists())
						file.mkdirs();
					attachFile.transferTo(new File(pathAndName));
				} catch (Exception e) {  
	                logger.error("保存文件失败", e);
	                saveJson.put("msg", "保存文件失败");
	    			saveJson.put("saveFlag", false);
	    			return saveJson;
	            } 
				interviewer.setAttachName(realName);
				interviewer.setAttachPath(pathAndName);
			}
			interviewer.setCreateUser(currentUser.getUserId());
			boolean tag = interviewerService.newInterviewer(interviewer);
			if (tag) {
				saveJson.put("msg", "保存成功");
			} else {
				saveJson.put("msg", "保存失败");
			}
			saveJson.put("saveFlag", tag);
		} catch (Exception e) {
			logger.info("保存失败");
			e.printStackTrace();
			saveJson.put("msg", e.getMessage());
			saveJson.put("saveFlag", false);
		}
		return saveJson;
	}

	/**
	 * 查询数据后，跳转到修改页面
	 * 
	 * @param model
	 * @param interviewerId
	 * @return
	 */
	@RequestMapping(value = "/updateInterviewer")
	public String updateInterviewer(Model model, int interviewerId) {
		TbInterviewer interviewer = interviewerService
				.qryInterviewerById(interviewerId);
		model.addAttribute("interviewer", interviewer);
		return "interviewer/modInterviewer";
	}

	@RequestMapping(value = "/doUpdateInterviewer")
	@ResponseBody
	public JSONObject doUpdateInterviewer(
			@RequestParam(value = "attachFile", required = false) MultipartFile attachFile,
			TbInterviewer interviewer, HttpSession session) {
		JSONObject saveJson = new JSONObject();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			interviewer.setUpdateUser(currentUser.getUserId());
			interviewer.setUpdateTime(new Date());
			TbInterviewer oldInterviewer = interviewerService
					.qryInterviewerById(interviewer.getInterviewerId());
			if(interviewerService.hasRelatedExamination(interviewer.getInterviewerId())){
				String oldName = oldInterviewer.getName();
				String oldPhone = oldInterviewer.getPhone();
				if(!oldName.equals(interviewer.getName())){
					saveJson.put("msg", "已存在关联试卷,不能修改姓名");
					saveJson.put("modFlag", false);
					saveJson.put("sameName", true);
					return saveJson;
				}
				if(!oldPhone.equals(interviewer.getPhone())){
					saveJson.put("msg", "已存在关联试卷,不能修改手机");
					saveJson.put("modFlag", false);
					saveJson.put("samePhone", true);
					return saveJson;
				}
			}
			
			String oldAttachName = oldInterviewer.getAttachName();
			String newAttachName = attachFile.getOriginalFilename();
			if (!attachFile.isEmpty()) {
				// 1.老附件名为空，新附件名不为空，添加新附件
				if ((oldAttachName == null || "".equals(oldAttachName))
						&& !attachFile.isEmpty()) {
					String realName = attachFile.getOriginalFilename();
					String attachPath = session.getServletContext()
							.getRealPath(File.separator + "upload"
									+ File.separator + "interviewerResume"
									+ File.separator
									+ System.currentTimeMillis() + realName);
					attachFile.transferTo(new File(attachPath));
					interviewer.setAttachName(realName);
					interviewer.setAttachPath(attachPath);
				}

				// 2.都不为空，删除文件重新上传
				if (oldAttachName != null && !attachFile.isEmpty() && !oldAttachName.equals(newAttachName)) {
					File oldFile = new File(oldInterviewer.getAttachPath());
					oldFile.delete();
					if (!attachFile.isEmpty()) {
						String realName = attachFile.getOriginalFilename();
						String attachPath = session.getServletContext()
								.getRealPath(File.separator + "upload"
										+ File.separator + "interviewerResume"
										+ File.separator
										+ System.currentTimeMillis()
										+ realName);
						attachFile.transferTo(new File(attachPath));
						interviewer.setAttachName(realName);
						interviewer.setAttachPath(attachPath);
					}
				}
			}
			int tag = interviewerService.modInterviewer(interviewer);
			if (tag == 0) {
				saveJson.put("msg", "修改失败");
				saveJson.put("modFlag", false);
			} else {
				saveJson.put("msg", "修改成功");
				saveJson.put("modFlag", true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			saveJson.put("msg", "修改失败");
			saveJson.put("modFlag", false);
		}
		return saveJson;
	}

	/**
	 * 删除附件
	 * @param interviewerId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteAttach(int interviewerId, HttpSession session) {
		JSONObject deleteJson = new JSONObject();
		TbInterviewer interviewer = interviewerService
				.qryInterviewerById(interviewerId);
		File attach = new File(interviewer.getAttachPath());
		boolean deleteFlag = attach.delete();
		if (deleteFlag) {
			int count = interviewerService.deleteAttach(interviewerId);
			if (count == 0) {
				deleteJson.put("msg", "附件删除失败");
				deleteJson.put("deleteFlag", false);
			} else {
				deleteJson.put("msg", "附件删除成功");
				deleteJson.put("deleteFlag", true);
			}

		} else {
			deleteJson.put("msg", "附件删除失败");
			deleteJson.put("deleteFlag", false);
		}
		return deleteJson;
	}
	
	
	/**
	 * 显示关联试卷和面试者的图片
	 * @param model
	 * @param interviewerId
	 * @return
	 */
	@RequestMapping(value = "/relatedExamination")
	public String relatedExamination(Model model, int interviewerId) {
		model.addAttribute("interviewerId", interviewerId);
		//查询
		List<TbExaminationInterviewerRl> oldRls = interviewerService.qryRelatedByInterviewer(interviewerId);
		List<TbExamination> oldExaminations = new ArrayList<>();
		for (TbExaminationInterviewerRl tbExaminationInterviewerRl : oldRls) {
			int examinationId = tbExaminationInterviewerRl.getExaminationId();
			TbExamination exam = interviewerService.qryByExaminationId(examinationId);
			if(exam!=null)
				oldExaminations.add(exam);
		}
		if(oldExaminations.size()>0){
			String json = JSONArray.toJSONString(oldExaminations);
			model.addAttribute("oldExaminations",json);
		}else
			model.addAttribute("oldExaminations","-1");
		
		return "interviewer/relatedInterviewer";
	}
	
	/**
	 * 
	 * 加载试卷下拉框
	 * @return
	 */
	@RequestMapping(value = "/loadExaminationList")
	@ResponseBody
	public Object loadExaminationList() {
		List<TbExamination> volist = examinationService.qryAllExamination();
		return volist;
	}
	
	
	/**
	 * 对面试者进行试卷关联
	 * @return
	 */
	@RequestMapping(value = "/doRelate")
	@ResponseBody
	public JSONObject doRelate(int interviewerId, HttpSession session, String relatedTable) {
		logger.info("对面试者进行试卷关联:doRelate......");
		JSONObject result = new JSONObject();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			List<TbExamination> list = JSONArray.parseArray(relatedTable,TbExamination.class);
			//构造关系vo 拼装需要改变的关系list
			List<TbExaminationInterviewerRl> rls = new ArrayList<>();
			if(list.size()!=0){
				for (TbExamination examination : list) {
					TbExaminationInterviewerRl vo = new TbExaminationInterviewerRl();
					vo.setInterviewerId(interviewerId);
					vo.setExaminationId(examination.getExaminationId());
					vo.setCreateUser(currentUser.getUserId());
					vo.setCreateTime(new Date());
					rls.add(vo);
				}
			}
			
			List<TbExaminationInterviewerRl> cantDeleteList = interviewerService.doInterviewerRelatedExamination(interviewerId, rls);
			if(cantDeleteList != null && cantDeleteList.size()>0){
				StringBuffer rtnMsg = new StringBuffer("关联失败!试卷ID：");
				for (TbExaminationInterviewerRl tbExaminationInterviewerRl : cantDeleteList) {
					rtnMsg.append(tbExaminationInterviewerRl.getExaminationId());
					rtnMsg.append(",");
				}
				rtnMsg.deleteCharAt(rtnMsg.length()-1);
				rtnMsg.append("已开始答题，无法删除绑定。");
				result.put("relatedFalg", false);
				result.put("msg", rtnMsg);
			}else{
				result.put("relatedFalg", true);
				result.put("msg", "关联成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			result.put("relatedFalg", false);
			result.put("msg", "关联失败");
		}
		return result;
	}
	
	@RequestMapping(value="/deleteInterviewer")
	@ResponseBody
	public JSONObject deleteInterviewer(HttpSession session,TbInterviewer interviewer)
	{
		JSONObject saveJson = new JSONObject();
		try {
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			int tag = interviewerService.deleteInterviewer(currentUser, interviewer);
			if(tag == 0)
			{
				saveJson.put("msg", "失败");
			}else{
				saveJson.put("msg", "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return saveJson;
	}
}

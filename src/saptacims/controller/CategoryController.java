package saptacims.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import pub.util.ComboboxVO;
import pub.util.DateUtil;
import saptacims.cst.Status;
import saptacims.model.TbCategory;
import saptacims.model.TbUpdateCategory;
import saptacims.model.TbUser;
import saptacims.service.ICategoryService;
import saptacims.vo.page.Pager;

/**
 * 类别管理Controller
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService categoryService;
	

	@RequestMapping(value="/statusList")
	@ResponseBody
	public Object statusList()
	{
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Status.class,String.valueOf(Status.ENABLE));
		return volist;
	}
	
	
	
	@RequestMapping(value="/typeManage")
	public String getTypeManage(TbCategory category){
		
		return "/baseInfo/typeManage";
	}
	
	@RequestMapping(value="/addCategory")
	public String addCategory(){
		
		return "baseInfo/addCategory";
	}
	
	@RequestMapping(value="/saveCategory")
	@ResponseBody
	public JSONObject saveCategory(TbCategory category,HttpSession session){
		
		JSONObject saveJson = new JSONObject();
		
		//新增类别
		try{
			logger.info("==========类别名称==========" + category.getCategoryName().length());
			
			if(category.getCategoryName().trim().length() == 0){//类别输出为空
				saveJson.put("saveFlag", false);
				saveJson.put("msg", "保存失败:类别不能为空");
			}else{
				
				if(categoryService.selectCategoryByName(category.getCategoryName())){//如果类别不存
					TbUser currentUser = (TbUser) session.getAttribute("currentUser");
					logger.info("创建人========" + currentUser.getUserId());
					category.setCreateUser(currentUser.getUserId());
					category.setUpdateUser(currentUser.getUserId());
					boolean flag = categoryService.saveCategory(category);
					
					if(flag){
						saveJson.put("msg", "保存成功");
					}else {
						saveJson.put("msg", "保存失败");
					}
					saveJson.put("saveFlag", flag);
				}else{//类别已经存在
					saveJson.put("saveFlag", false);
					saveJson.put("msg", "保存失败：类别已存在");
				}
			}
			
		}catch(Exception e){
			logger.info("保存失败");
			saveJson.put("msg", "保存失败");
			saveJson.put("saveFlag", false);
		}
		
		return saveJson;
	}
	
	@RequestMapping(value="/categoryList")
	@ResponseBody
	public Map<String, Object> categoryList(Pager page,TbCategory category){
		logger.info("-----------类别列表查询-----------------");
		
		return categoryService.categoryListPage(page, category);
	}
	
	
	@RequestMapping(value="/updateCategory")
	public String updateCategory(Model model,int categoryId){
		logger.info("--------------类别修改--------------" + categoryId);
		
		TbUpdateCategory updateCategory = categoryService.showInfoCategory(categoryId);
		String creatTimeStr = DateUtil.getFormattedDateTimeString(updateCategory.getCreateTime());
		String updateTimeStr = DateUtil.getFormattedDateTimeString(updateCategory.getUpdateTime());
		updateCategory.setCreateTimeStr(creatTimeStr);
		updateCategory.setUpdateTimeStr(updateTimeStr);
		model.addAttribute("category",updateCategory);
		return "baseInfo/updateCategory";
	}
	
	@RequestMapping(value="/doUpdateCategory")
	@ResponseBody
	public JSONObject doUpdateCategory(TbCategory category,HttpSession session){
		logger.info("--------------类别修改--------------状态" + category.getCategoryStatus());
		
		JSONObject updateJson = new JSONObject();
		
		TbUser user = (TbUser) session.getAttribute("currentUser");
		
		try {
				if(category.getCategoryId() == null){
					
					updateJson.put("modFlag", false);
					updateJson.put("msg", "更新失败");
				}else{
					
					boolean result = categoryService.updateCategory(category, user.getUserId());
					if(result){
						updateJson.put("msg", "更新成功");
						updateJson.put("modFlag", true);
					}else{
						updateJson.put("msg", "更新失败");
						updateJson.put("modFlag", false);
					}
				} 
		   }catch (Exception e) {
			   logger.info("========更新失败========");
			   logger.error(e.getMessage(), e);
				updateJson.put("modFlag", false);
				updateJson.put("msg", e.getMessage());
		   }
		return updateJson;
	}
	
	@RequestMapping(value="/deleteCategory")
	@ResponseBody
	public JSONObject deleteCategory(HttpSession session,TbCategory category){
		
		JSONObject deleteJson = new JSONObject();
		
		try {
		
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			int tag = categoryService.deleteCategory(category, currentUser.getUserId());
			if(tag == 0){
				deleteJson.put("msg", "失败");
			}else{
				deleteJson.put("msg", "成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			deleteJson.put("msg", "失败");
		}
		return deleteJson;
	}
	
}

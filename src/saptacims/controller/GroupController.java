package saptacims.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 群组管理
 * @author caolei
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pub.util.ComboboxVO;
import pub.util.DateUtil;
import saptacims.cst.Status;
import saptacims.model.TbGroup;
import saptacims.model.TbUpdateGroup;
import saptacims.model.TbUser;
import saptacims.service.IGroupService;
import saptacims.vo.page.Pager;
@Controller
@RequestMapping(value="/group")
public class GroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private IGroupService groupService;
	
	@RequestMapping("/groupManage")
	public String getGroupManage(){
		
		return "/baseInfo/groupManage";
	}
	
	@RequestMapping(value="/statusList")
	@ResponseBody
	public Object statusList()
	{
		List<ComboboxVO> volist = ComboboxVO.getCstCombobox(Status.class,String.valueOf(Status.ENABLE));
		return volist;
	}
	
	@RequestMapping(value="/addGroup")
	public String addGroup(){
		
		return "baseInfo/addGroup";
	}
	
	@RequestMapping(value="/saveGroup")
	@ResponseBody
	public JSONObject saveGroup(TbGroup group,HttpSession session){
		JSONObject saveJson = new JSONObject();
		
				//新增群组
				try{
					LOGGER.info("==========群组名称==========" + group.getGroupName().trim().length());
					
					if(group.getGroupName().trim().length() == 0){//群组输出为空
						saveJson.put("saveFlag", false);
						saveJson.put("msg", "保存失败:群组不能为空");
					}else{
						
						if(groupService.queryGroupByName(group.getGroupName())){//如果群组不存在
							TbUser currentUser = (TbUser) session.getAttribute("currentUser");
							LOGGER.info("创建人========" + currentUser.getUserId());
							
							group.setCreateUser(currentUser.getUserId());
							group.setUpdateUser(currentUser.getUserId());
							boolean flag = groupService.saveGroup(group);
							
							if(flag){
								saveJson.put("msg", "保存成功");
							}else {
								saveJson.put("msg", "保存失败");
							}
							saveJson.put("saveFlag", flag);
						}else{//群组已经存在
							saveJson.put("saveFlag", false);
							saveJson.put("msg", "保存失败：群组已存在");
						}
					}
					
				}catch(Exception e){
					LOGGER.info("保存失败");
					saveJson.put("msg", "保存失败");
					saveJson.put("saveFlag", false);
				}
				
				return saveJson;
			}
		
		
	@RequestMapping(value="/groupList")
	@ResponseBody
	public Map<String, Object> groupList(Pager page,TbGroup group){
		LOGGER.info("-----------群组列表查询-----------------");
		
		return groupService.groupListPage(page, group);
	}
	
	
	@RequestMapping(value="/deleteGroup")
	@ResponseBody
	public JSONObject deleteGroup(HttpSession session,TbGroup group){
		
		JSONObject deleteJson = new JSONObject();
		LOGGER.info("----------群组删除----------");
		try {
			
			TbUser currentUser = (TbUser) session.getAttribute("currentUser");
			int tag = groupService.deleteGroup(group, currentUser.getUserId());
			if(tag == 0){
				deleteJson.put("msg", "失败");
			}else{
				deleteJson.put("msg", "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			deleteJson.put("msg", "失败");
		}
		
		return deleteJson;
	}
	
	@RequestMapping(value="/updateGroup")
	public String updateGroup(Model model,int groupId){
		LOGGER.info("--------------群组修改--------------" + groupId);
		TbUpdateGroup updateGroup = groupService.showInfoGroup(groupId);
		String createTimeStr = DateUtil.getFormattedDateTimeString(updateGroup.getCreateTime());
		String updateTimeStr = DateUtil.getFormattedDateTimeString(updateGroup.getUpdateTime());
		updateGroup.setCreateTimeStr(createTimeStr);
		updateGroup.setUpdateTimeStr(updateTimeStr);
		model.addAttribute("group", updateGroup);
		return "baseInfo/updateGroup";
	}
	
	@RequestMapping(value="/doUpdateGroup")
	@ResponseBody
	public JSONObject doUpdateGroup(TbGroup group,HttpSession session){
		LOGGER.info("---------做群组修改---------");
		
		JSONObject updateJson = new JSONObject();
		
		TbUser user = (TbUser) session.getAttribute("currentUser");
		
		try {
		
			if(group.getGroupId() == null){
				updateJson.put("modFlag", false);
				updateJson.put("msg", "更新失败");
			}else{
				
				boolean result = groupService.updateGroup(group, user.getUserId());
				if(result){
					updateJson.put("msg", "更新成功");
					updateJson.put("modFlag", true);
				}else{
					updateJson.put("msg", "更新失败");
					updateJson.put("modFlag", false);
				}
			}
		} catch (Exception e) {
			LOGGER.info("========更新失败========");
			e.printStackTrace();
			updateJson.put("modFlag", false);
			updateJson.put("msg", e.getMessage());
		}
		return updateJson;
	}
	
}

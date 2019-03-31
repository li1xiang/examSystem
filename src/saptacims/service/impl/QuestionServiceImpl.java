package saptacims.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import pub.util.ComboboxVO;
import saptacims.cst.Constants;
import saptacims.cst.Status;
import saptacims.dao.base.TbAnswerMapper;
import saptacims.dao.base.TbCategoryMapper;
import saptacims.dao.base.TbGroupMapper;
import saptacims.dao.base.TbQuestionMapper;
import saptacims.dao.base.TbUserMapper;
import saptacims.model.TbAnswer;
import saptacims.model.TbCategory;
import saptacims.model.TbCategoryExample;
import saptacims.model.TbGroup;
import saptacims.model.TbGroupExample;
import saptacims.model.TbQuestion;
import saptacims.model.TbQuestionView;
import saptacims.model.TbUser;
import saptacims.model.TbUserExample;
import saptacims.service.IQuestionService;
import saptacims.vo.page.Pager;

@Service
public class QuestionServiceImpl implements IQuestionService {

	private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	@Resource
	private TbQuestionMapper questionMapper;	
	@Resource
	private TbAnswerMapper answerMapper;
	@Resource
	private TbCategoryMapper categoryMapper;
	@Resource
	private TbGroupMapper groupMapper;
	@Resource
	private TbUserMapper userMapper;
	private Map<String, Object> record;
	
	@Override
	public List<ComboboxVO> getCategoryList(Boolean all) {
		List<ComboboxVO> returnComboboxVOs = new LinkedList<ComboboxVO>();
		List<TbCategory> categories = new LinkedList<TbCategory>();
		if(all){
			returnComboboxVOs.add(ComboboxVO.addAllCombobox());
			TbCategoryExample categoryExample = new TbCategoryExample();
			categoryExample.setOrderByClause("CATEGORY_ID");
			categories = categoryMapper.selectAll(categoryExample);
		}else{
			TbCategory category = new TbCategory();
			category.setCategoryStatus(1);
			categories = categoryMapper.selectByCategory(category);
		}
		
		for (TbCategory tbCategory : categories) {
			ComboboxVO vo = new ComboboxVO();
			vo.setId(tbCategory.getCategoryId().toString());
			vo.setText(tbCategory.getCategoryName());
			returnComboboxVOs.add(vo);
		}
		if(returnComboboxVOs.size()!=0&&!all){
			returnComboboxVOs.get(0).setSelected(true);
		}		
		return returnComboboxVOs;
	}

	@Override
	public List<ComboboxVO> getGroupList(Boolean all) {
		List<ComboboxVO> returnComboboxVOs = new LinkedList<ComboboxVO>();
		List<TbGroup> groups = new LinkedList<TbGroup>();
		if(all){
			returnComboboxVOs.add(ComboboxVO.addAllCombobox());
			TbGroupExample groupExample = new TbGroupExample();
			groupExample.setOrderByClause("GROUP_ID");
			groups = groupMapper.selectAll(groupExample);
		}else{
			TbGroup group = new TbGroup();
			group.setGroupStatus(1);
			groups = groupMapper.selectByGroup(group);
		}
		
		for (TbGroup tbGroup : groups) {
			ComboboxVO vo = new ComboboxVO();
			vo.setId(tbGroup.getGroupId().toString());
			vo.setText(tbGroup.getGroupName());
			returnComboboxVOs.add(vo);
		}
		if(returnComboboxVOs.size()!=0&&!all){
			returnComboboxVOs.get(0).setSelected(true);
		}
		return returnComboboxVOs;
	}
	
	@Override
	public List<ComboboxVO> getcreateUserList(boolean all) {
		List<ComboboxVO> returnComboboxVOs = new LinkedList<ComboboxVO>();
		List<TbUser> users = new LinkedList<TbUser>();
		if(all){
			returnComboboxVOs.add(ComboboxVO.addAllCombobox());			
		}
		TbUserExample tbUserExample = new TbUserExample();
		tbUserExample.setOrderByClause("USER_ID");
		users = userMapper.selectByExample(tbUserExample);
		for (TbUser tbUser : users) {
			ComboboxVO vo = new ComboboxVO();
			vo.setId(tbUser.getUserId().toString());
			vo.setText(tbUser.getUserCname());
			returnComboboxVOs.add(vo);
		}
		if(returnComboboxVOs.size()!=0&&!all){
			returnComboboxVOs.get(0).setSelected(true);
		}		
		return returnComboboxVOs;
	}

	@Override
	public TbQuestion addQuestion(TbQuestion question) {
		int num = questionMapper.insertSelective(question);
		if(num==0){
			return null;
		}else{
			return question;			
		}
	}

	@Override
	public TbQuestion getQuestion(int questionId) {	
		return questionMapper.selectByPrimaryKey(questionId);
	}

	@Override
	public Map<String, Object> questionListPage(TbQuestion question, Pager page) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(question.getQuestionType() != null )
			{
				map.put("questionType", question.getQuestionType());
			}
			if(question.getCategoryId() != null )
			{
				map.put("categoryId", question.getCategoryId());
			}
			if(question.getGroupId() != null )
			{
				map.put("groupId", question.getGroupId());
			}
			if(question.getLevels() != null )
			{
				map.put("levels", question.getLevels());
			}
			if(question.getCreateUser() != null )
			{
				map.put("createUser", question.getCreateUser());
			}
			if(question.getActive() != null )
			{
				map.put("active", question.getActive());
			}
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<TbQuestionView> list = questionMapper.getPageList(map);
            for (TbQuestionView tbQuestionView : list) {
				if(tbQuestionView.getSubjectImgName()==null){
					tbQuestionView.setSubjectImgName("");
				}
				tbQuestionView.setSubjectImg(Constants.QUESTIONUPLOADRESS+File.separator+tbQuestionView.getSubjectImg());
			}
            int count = questionMapper.getPageCount(map);
            result.put("rows", list);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int batchUpdateStatus(List<Integer> questionIds, Integer status) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		result.put("questionIds", questionIds);
		result.put("status", status);
		return questionMapper.batchUpdateStatus(result);
	}

	@Override
	public TbQuestionView getQuestionView(Integer questionId) {
		return questionMapper.getQuestionView(questionId);
	}

	@Override
	public TbQuestion updateQuestion(TbQuestion question) {
		int num = questionMapper.updateByPrimaryKeySelective(question);
		if(num==0){
			return null;
		}else{
			return question;			
		}
	}

	@Override
	public int updateQuestionStatus(Integer questionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("questionId", questionId);
		result.put("status", Status.ENABLE);
		return questionMapper.singleUpdateStatus(result);	
	}

	@Override
	@Transactional
    public Boolean saveSubjecQuestion(TbQuestionView question, TbAnswer tbAnswer) {
		int num = questionMapper.insertSelective(question);
		if(num==0){
			return false;
		}else{
			tbAnswer.setQuestionId(question.getQuestionId());
			int numAnswer =  answerMapper.insertSelective(tbAnswer);
			if(numAnswer==0){
				return false;
			}else{
				return true;			
			}			
		}
    }

	@Override
	@Transactional
    public Boolean saveObjecQuestion(TbQuestionView question, String answerTable) {
		int num = questionMapper.insertSelective(question);
		if(num==0){
			return false;
		}else{
			int success = 0;
			List<TbAnswer> list = JSONArray.parseArray(answerTable,TbAnswer.class);
			int all = list.size();
			for (TbAnswer tbAnswer : list) {
				tbAnswer.setQuestionId(question.getQuestionId());
				tbAnswer.setUpdateUser(question.getUpdateUser());
				tbAnswer.setUpdateTime(new Date());
				tbAnswer.setCreateUser(question.getCreateUser());
				tbAnswer.setCreateTime(new Date());
				success+=answerMapper.insertSelective(tbAnswer);
	        }
			if(success==all){
				return true;
			}
		}
	    return false;
    }


    @Override
	@Transactional
    public Boolean  saveRejumentQuestion(TbQuestionView question ,String isright){
		int num = questionMapper.insertSelective(question);
		if(num==0){
			return false;
		}else{
			TbAnswer tbAnswer=new TbAnswer();
			tbAnswer.setQuestionId(question.getQuestionId());
			tbAnswer.setUpdateUser(question.getUpdateUser());
			tbAnswer.setUpdateTime(new Date());
			tbAnswer.setCreateUser(question.getCreateUser());
			tbAnswer.setCreateTime(new Date());
			tbAnswer.setIsright(Integer.valueOf(isright));
			int numb=answerMapper.insertSelective(tbAnswer);
			if(numb==1){
				return true;
			}
		}
		return false;
	}







	@Override
	@Transactional
    public Boolean updateSubQuestion(TbQuestion question, TbAnswer tbAnswer) {
		int num = questionMapper.updateByPrimaryKeySelective(question);
		TbAnswer answer = answerMapper.getSubjectAnswer(question.getQuestionId());
		if(tbAnswer.getAnswerId()==null&&answer==null){
			tbAnswer.setCreateUser(1);
			tbAnswer.setCreateTime(new Date());
			tbAnswer.setIsright(1);
			answerMapper.insertSelective(answer);
		}else{
			answerMapper.updateByPrimaryKey(tbAnswer);
		}
		return true;
    }

	@Override
	@Transactional
    public Boolean updateObQuestion(TbQuestion question, String answerTable){
		int num = questionMapper.updateByPrimaryKeySelective(question);
		if(num==0){
			return false;
		}else{
			answerMapper.deleteAnswer(question.getQuestionId());
			int success = 0;
			List<TbAnswer> list = JSONArray.parseArray(answerTable,TbAnswer.class);
			int all = list.size();
			for (TbAnswer tbAnswer : list) {
				tbAnswer.setQuestionId(question.getQuestionId());
				tbAnswer.setUpdateUser(question.getUpdateUser());
				tbAnswer.setUpdateTime(new Date());
				tbAnswer.setCreateUser(question.getUpdateUser());
				tbAnswer.setCreateTime(new Date());
				success+=answerMapper.insertSelective(tbAnswer);
	        }
			if(success==all){
				return true;
			}
		}
	    return false;
    }

}

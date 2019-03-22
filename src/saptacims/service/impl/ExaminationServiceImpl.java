package saptacims.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saptacims.cst.Status;
import saptacims.dao.base.TbAnswerMapper;
import saptacims.dao.base.TbExaminationAnswerMapper;
import saptacims.dao.base.TbExaminationInterviewerRlMapper;
import saptacims.dao.base.TbExaminationMapper;
import saptacims.dao.base.TbExaminationQuestionMapper;
import saptacims.dao.base.TbQuestionMapper;
import saptacims.model.TbAnswer;
import saptacims.model.TbAnswerExample;
import saptacims.model.TbExamination;
import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationInterviewerRl;
import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbQuestion;
import saptacims.model.TbQuestionExample;
import saptacims.model.TbQuestionExample.Criteria;
import saptacims.service.IExaminationService;
import saptacims.vo.page.Pager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class ExaminationServiceImpl implements IExaminationService {
	private static Logger logger = LoggerFactory.getLogger(ExaminationServiceImpl.class);
	
	@Autowired
	private TbExaminationMapper examinationMapper;
	
	@Autowired
	private TbQuestionMapper questionMapper;
	
	@Autowired
	private TbExaminationQuestionMapper examinationQuestionMapper;
	
	@Autowired
	private TbExaminationInterviewerRlMapper examinationInterviewrRlMapper;
	
	@Autowired
	private TbAnswerMapper answerMapper;
	
	@Autowired
	private TbExaminationAnswerMapper examinationAnswerMapper;
	
	
	@Override
	public List<TbQuestion> questionList(TbQuestion question){
		List<TbQuestion> list = new ArrayList<TbQuestion>();
		try {
			TbQuestionExample tee = new TbQuestionExample();
			Criteria c = tee.createCriteria();
			c.andActiveEqualTo(Status.ENABLE);
			if(question.getCategoryId() != null)
			{
				c.andCategoryIdEqualTo(question.getCategoryId());
			}
			if(question.getGroupId() != null)
			{
				c.andGroupIdEqualTo(question.getGroupId());
			}
			if(question.getLevels() != null)
			{
				c.andLevelsEqualTo(question.getLevels());
			}
			if(question.getQuestionType() != null)
			{
				c.andQuestionTypeEqualTo(question.getQuestionType());
			}
			list = questionMapper.selectByExample(tee);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	@Override
	public List<TbExamination> qryAllExamination() {
		return examinationMapper.selectAll();
	}
	
	@Override
	public boolean saveExamination(TbExamination te,String examinatioInfoTable, String queFilePath){
		boolean tag = false;
		List<String> queImgFile = new ArrayList<String>();
		try {
			examinationMapper.insertSelective(te);
			List<TbQuestion> list = JSONArray.parseArray(examinatioInfoTable,TbQuestion.class);
			for (int i = 0; i < list.size(); i++) {
				TbQuestion tq = list.get(i);
				TbExaminationQuestion teq = new TbExaminationQuestion();
				teq.setQuestionId(tq.getQuestionId());
				teq.setQuestionTitle(tq.getSubject());
				teq.setCreateUser(te.getCreateUser());
				teq.setCreateTime(new Date());
				teq.setExaminationId(te.getExaminationId());
				teq.setGroupId(tq.getGroupId());
				teq.setLevels(tq.getLevels());
				teq.setQuestionImgName(tq.getSubjectImgName());
				if(tq.getSubjectImg()!=null&&!"".equals(tq.getSubjectImg())){
					String realName = savePicture(tq.getSubjectImg(),queFilePath);
					teq.setQuestionImgPath(realName);
					queImgFile.add(queFilePath+File.separator+realName);
				}else{
					teq.setQuestionImgPath(tq.getSubjectImg());
				}
				teq.setQuestionType(tq.getQuestionType());
				teq.setTotalScore(tq.getScore());
				teq.setCategoryId(tq.getCategoryId());
				examinationQuestionMapper.insertSelective(teq);//保存试卷题目
				TbAnswerExample example = new TbAnswerExample();
				TbAnswerExample.Criteria criteria = example.createCriteria();
				criteria.andQuestionIdEqualTo(tq.getQuestionId());
				List<TbAnswer> tas = answerMapper.selectByExample(example);
				for (int j = 0; j < tas.size(); j++) {
					TbAnswer ta = tas.get(j);
					TbExaminationAnswer tea = new TbExaminationAnswer();
					tea.setAnswerId(ta.getAnswerId());
					tea.setAnswerText(ta.getAnswer());
					tea.setCreateTime(new Date());
					tea.setCreateUser(te.getCreateUser());
					tea.setIsright(ta.getIsright());
					tea.setQuestionId(teq.getExaminationQuestionId());
					examinationAnswerMapper.insertSelective(tea);
				}
			}
			tag = true;
		} catch (Exception e) {
			tag = false;
			for (String string : queImgFile) {
				deletePicture(string);
            }
			logger.error(e.getMessage(),e);
		}
		return tag;
	}
	
	/**
	 * 转存图片
	 * @param pictureName
	 * @param FilePath
	 * @return
	 * @throws FileNotFoundException 
	 */
	private String savePicture(String pictureName, String filePath) throws IOException {
		String suffix = pictureName.substring(pictureName.lastIndexOf("."));
		String realName = String.valueOf(System.currentTimeMillis()) + suffix;
		File newFile = new File(filePath + File.separator + realName);
		File oldFile = new File(filePath + File.separator + pictureName);
		if (oldFile.exists() && oldFile.isFile()) {
			FileUtils.copyFile(oldFile, newFile);
		}
		return realName;
	}
	
	/**
	 * 删除图片
	 * @param pictureName
	 * @param FilePath
	 */
	private void deletePicture(String FilePath){
		File tempfile = new File(FilePath);
        if (tempfile.exists() && tempfile.isFile()) {
            tempfile.delete();
        }		
	}

	@Override
	public Map<String, Object> examinationListPage(TbExamination examination,Pager page) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(examination.getExaminationName() != null )
			{
				map.put("examinationName", examination.getExaminationName());
			}
			if(examination.getExaminationStatus() != null )
			{
				map.put("examinationStatus", examination.getExaminationStatus());
			}
			if(examination.getCreateUser() != null )
			{
				map.put("createUser", examination.getCreateUser());
			}			
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<TbExamination> list = examinationMapper.getPageList(map);
            int count = examinationMapper.getCount(map);
            result.put("rows", list);
			result.put("total", count);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int batchUpdateStatus(List<Integer> examinationIds, Integer status) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		result.put("examinationIds", examinationIds);
		result.put("status", status);
		return examinationMapper.batchUpdateStatus(result);
	}

	@Override
	public TbExamination getExamination(Integer examinationId) {		
		return examinationMapper.selectByPrimaryKey(examinationId);
	}

	@Override
	public List<TbQuestion> questionList(Integer examinationId) {
		List<TbQuestion> tqList = new ArrayList<TbQuestion>();
		List<TbExaminationQuestion> teqList = examinationQuestionMapper.getQuestionList(examinationId);
		for (TbExaminationQuestion teq : teqList) {
			TbQuestion tq = new TbQuestion();
			tq.setQuestionId(teq.getQuestionId());
			tq.setQuestionType(teq.getQuestionType());
			tq.setCategoryId(teq.getCategoryId());
			tq.setGroupId(teq.getGroupId());
			tq.setLevels(teq.getLevels());
			tq.setSubject(teq.getQuestionTitle());
			tq.setSubjectImg(teq.getQuestionImgPath());
			tq.setSubjectImgName(teq.getQuestionImgName());
			tq.setScore(teq.getTotalScore());
			tq.setCreateTime(teq.getCreateTime());
			tq.setCreateUser(teq.getCreateUser());
			tq.setUpdateTime(teq.getUpdateTime());
			tq.setUpdateUser(teq.getUpdateUser());
			tqList.add(tq);
		}
		return tqList;
	}

	@Override
	@Transactional
	public boolean updateExamination(TbExamination te,String examinatioInfoTable, String queFilePath) {
		boolean tag = false;
		List<String> queOldFile = new ArrayList<String>();
		List<String> queNewFile = new ArrayList<String>();
		try {
			//更新试卷
			examinationMapper.updateByPrimaryKeySelective(te);
			//获取
			List<TbExaminationQuestion> tempTeqs = examinationQuestionMapper.getAllQuestionIdByExaminationId(te.getExaminationId());
			List<Integer> teqIds = new ArrayList<Integer>();
			for (TbExaminationQuestion tbExaminationQuestion : tempTeqs) {
				if(tbExaminationQuestion.getQuestionImgPath()!=null&&!"".equals(tbExaminationQuestion.getQuestionImgPath())){
					queOldFile.add(tbExaminationQuestion.getQuestionImgPath());
				}
				teqIds.add(tbExaminationQuestion.getExaminationQuestionId());
			}
			//删除试卷所有试题
			examinationQuestionMapper.delteByExaminationId(te.getExaminationId());
			//删除试卷所有答案
			if(teqIds.size()>0){
				examinationAnswerMapper.deleteByQuestionIds(teqIds);
			}
			//更新试卷题目
			List<TbQuestion> list = JSONArray.parseArray(examinatioInfoTable,TbQuestion.class);
			for (int i = 0; i < list.size(); i++) {
				TbQuestion tq = new TbQuestion();
				tq = list.get(i);
				TbExaminationQuestion teq = new TbExaminationQuestion();
				teq.setQuestionId(tq.getQuestionId());
				teq.setQuestionTitle(tq.getSubject());
				teq.setCreateUser(te.getUpdateUser());
				teq.setCreateTime(new Date());
				teq.setExaminationId(te.getExaminationId());
				teq.setGroupId(tq.getGroupId());
				teq.setLevels(tq.getLevels());
				teq.setQuestionImgName(tq.getSubjectImgName());
				if(tq.getSubjectImg()!=null&&!"".equals(tq.getSubjectImg())){
					String realName = savePicture(tq.getSubjectImg(),queFilePath);
					teq.setQuestionImgPath(realName);
					queNewFile.add(queFilePath+File.separator+realName);
				}else{
					teq.setQuestionImgPath(tq.getSubjectImg());
				}
				teq.setQuestionType(tq.getQuestionType());
				teq.setTotalScore(tq.getScore());
				teq.setCategoryId(tq.getCategoryId());			
				examinationQuestionMapper.insertSelective(teq);//保存试卷题目
				//更新试卷答案
				TbAnswerExample example = new TbAnswerExample();
				TbAnswerExample.Criteria criteria = example.createCriteria();
				criteria.andQuestionIdEqualTo(tq.getQuestionId());
				List<TbAnswer> tas = answerMapper.selectByExample(example);
				for (int j = 0; j < tas.size(); j++) {
					TbAnswer ta = tas.get(j);
					TbExaminationAnswer tea = new TbExaminationAnswer();
					tea.setAnswerId(ta.getAnswerId());
					tea.setAnswerText(ta.getAnswer());
					tea.setCreateTime(new Date());
					tea.setCreateUser(te.getUpdateUser());
					tea.setIsright(ta.getIsright());
					tea.setQuestionId(teq.getExaminationQuestionId());
					examinationAnswerMapper.insertSelective(tea);//保存试卷答案
				}
			}
			tag = true;
			//删除试卷题目旧图片
			for (String queImg : queOldFile) {
	            deletePicture(queFilePath+File.separator+queImg);
            }
		} catch (Exception e) {
			tag = false;
			for (String queImg : queNewFile) {
	            deletePicture(queImg);
            }
			logger.error(e.getMessage(),e);
		}
		return tag;
	}

	@Override
	public boolean hasRelatedInterviewer(Integer examinationId) {
		List<TbExaminationInterviewerRl> relatedInterviewers =  examinationInterviewrRlMapper.selectByExaminationId(examinationId);
		if(relatedInterviewers != null && relatedInterviewers.size() > 0)
			return true;
		
		return false;
	}
	
	@Override
	public Object deleteExamination(Integer examinationId) {
		JSONObject jb =new JSONObject();
		TbExamination examEntity = examinationMapper.selectByPrimaryKey(examinationId);
		if(examEntity !=null){
			List<TbExaminationInterviewerRl> elist = examinationInterviewrRlMapper.selectByExaminationId(examinationId);
			if(elist.isEmpty()){
				int res = examinationMapper.deleteByPrimaryKey(examinationId);
				if(res ==1){
					int res2 = examinationQuestionMapper.delteByExaminationId(examinationId);
					if(res2 ==0){
						jb.put("saveFlag", false);
						jb.put("msg", "删除失败");
						return jb;
					}else{
						jb.put("saveFlag", true);
						jb.put("msg", "删除成功");
						return jb;
					}
				}else{
					jb.put("saveFlag", false);
					jb.put("msg", "删除失败");
					return jb;
				}
			}else{
				jb.put("saveFlag", false);
				jb.put("msg", "试卷已被使用,无法删除");
				return jb;
			}
		}else{
			jb.put("saveFlag", false);
			jb.put("msg", "试卷不存在");
			return jb;
		}
	}
}

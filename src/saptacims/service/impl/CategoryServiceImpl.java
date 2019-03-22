package saptacims.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saptacims.cst.Status;
import saptacims.dao.base.TbCategoryMapper;
import saptacims.dao.base.TbUserMapper;
import saptacims.model.TbCategory;
import saptacims.model.TbCategoryExample;
import saptacims.model.TbUpdateCategory;
import saptacims.model.TbCategoryExample.Criteria;
import saptacims.service.ICategoryService;
import saptacims.vo.page.Pager;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private TbCategoryMapper categoryMapper;
	
	@Autowired
	private TbUserMapper userMapper;
	
	public boolean saveCategory(TbCategory category) throws Exception{
		LOGGER.info("-------新增类别--------");
		category.setCreateTime(new Date());
		category.setCategoryStatus(Status.ENABLE);
		category.setUpdateTime(new Date());
		int insertCount = categoryMapper.insertSelective(category);
		if(insertCount > 0){
			return true;
		}else{
			return false;
		}
		
	}

	public int deleteCategory(TbCategory deleteCategory,Integer userId) {
		LOGGER.info("-------删除类别--------");
		
		int tag = 0;
		try {
			deleteCategory.setUpdateTime(new Date());
			deleteCategory.setUpdateUser(userId);
			TbCategoryExample te = new TbCategoryExample();
			Criteria criteria = te.createCriteria();
			criteria.andCategoryIdEqualTo(deleteCategory.getCategoryId());
			tag = categoryMapper.updateByExampleSelective(deleteCategory, te);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return tag;
	}


	public boolean updateCategory(TbCategory category,Integer userId) throws Exception{
		LOGGER.info("-------修改类别------");
		
		//1.根据输入id和输入name校验数据库中是否类别重名，
		if(categoryMapper.queryCategoryCount(category) != 0){
			//类别名称已存在
			throw new Exception("类别名称已存在");
		}
		//2.根据输入ID加载类名
		TbCategory tbCategory = categoryMapper.selectByPrimaryKey(category.getCategoryId());
		if(tbCategory == null){
			//该类型已经不存在
			throw new Exception("该类型已经不存在");
		}
		//将修改的内容封装到保存的实体中
		tbCategory.setCategoryName(category.getCategoryName());
		tbCategory.setCategoryStatus(category.getCategoryStatus());
		tbCategory.setUpdateTime(new Date());
		tbCategory.setUpdateUser(userId);
		//更新数据库
		int count = categoryMapper.updateByPrimaryKey(tbCategory);
		if(count != 1){
			//更新失败
			throw new Exception("更新失败");
		}
		return true;
	}

	@Override
	public boolean selectCategoryByName(String categoryName) {
		
		int selectCount = categoryMapper.selectCategoryByName(categoryName);
		LOGGER.info("--------根据名称查询类别-------------" + selectCount);
		if(selectCount == 0){
			
			return true;
		}else{
			
			return false;
		}
		
	}
	
	public Map<String, Object> categoryListPage(Pager page,TbCategory category){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(category.getCategoryName() != null && !category.getCategoryName().equals("")){
				map.put("categoryName", category.getCategoryName());
			}
			map.put("pageOffset", (page.getPage()-1)*page.getRows());
            map.put("pageSize", page.getRows());
            map.put("order", page.getOrder());
            map.put("sort", page.getSort());
            List<TbCategory> list = categoryMapper.getPageList(map);
            int count = categoryMapper.getPageCount(map);
            result.put("rows", list);
			result.put("total", count);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			
		}
		
		return result;
	}

	@Override
	public TbCategory queryCategoryById(Integer categoryId) {
		
		return categoryMapper.selectByPrimaryKey(categoryId);
		
	}

	@Override
	public TbUpdateCategory showInfoCategory(Integer categoryId) {
	
		TbCategory category = categoryMapper.selectByPrimaryKey(categoryId);
		TbUpdateCategory updateCategory = new TbUpdateCategory();
		
		String createrName = userMapper.queryUsernameById(category.getCreateUser());
		String updaterName = userMapper.queryUsernameById(category.getUpdateUser());
		
		updateCategory.setCategoryId(category.getCategoryId());
		updateCategory.setCategoryName(category.getCategoryName());
		updateCategory.setCategoryStatus(category.getCategoryStatus());
		updateCategory.setCreateTime(category.getCreateTime());
		updateCategory.setUpdateTime(category.getUpdateTime());
		updateCategory.setCreateUsername(createrName);
		updateCategory.setUpdateUsername(updaterName);
		
		return updateCategory;
	}
	
	

}

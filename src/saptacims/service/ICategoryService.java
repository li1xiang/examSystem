package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.model.TbCategory;
import saptacims.model.TbUpdateCategory;
import saptacims.vo.page.Pager;

public interface ICategoryService {

	/**
	 * 新增类别
	 * @param category
	 * @return
	 */
	public boolean saveCategory(TbCategory category) throws Exception;
	
	
	/**
	 * 删除类别
	 * @param id
	 * @return
	 */
	public int deleteCategory(TbCategory category,Integer id);
	
	/**
	 * 修改类别
	 * @param category
	 * @return
	 */
	public boolean updateCategory(TbCategory category,Integer userId) throws Exception;
	
	
	/**
	 * 根据名字查询类别
	 * @param categoryName
	 * @return
	 */
	public boolean selectCategoryByName(String categoryName);
	
	/**
	 * 类别列表查询
	 * @param page
	 * @param category
	 * @return
	 */
	public Map<String, Object> categoryListPage(Pager page,TbCategory category);
	
	
	/**
	 * 根据ID查询类别
	 * @param categoryId
	 * @return
	 */
	public TbCategory queryCategoryById(Integer categoryId);
	
	
	/**
	 * 
	 * @return
	 */
	public TbUpdateCategory showInfoCategory(Integer categoryId);
}

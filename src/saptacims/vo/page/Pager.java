package saptacims.vo.page;

import java.io.Serializable;




public class Pager implements Serializable{
	/**
	 * 当前第几页
	 */
	private int page;
	/**
	 * 显示行数
	 */
	private int rows;
	/**
	 * 排序顺序(asc,desc)
	 */
	private String order;
	/**
	 * 排序字段 
	 */
	private String sort;
	/**
	 * 获取第一行
	 * @return
	 */
	public int getFirstRow(){
		return (page - 1) * rows + 1;
	}
	/**
	 * 获取最后一行行号
	 * @return
	 */
	public int getLastRow(){
		return page * rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}


	/**
	 * 将分页后的数据封装成前台需要的JSON格式
	 * @param <T>
	 * @param list 当页数据
	 * @param totalCount 记录总条数
	 * @return
	 *//*
	public static <T> JSONObject getPageData (List<T> list, int totalCount){
		JSONObject datagrid = new JSONObject();
		datagrid.put("rows", JSON.toJSON(list));
		datagrid.put("total", totalCount);
		return datagrid;
	}
	
	public static <T> String getPageDataString (List<T> list, int totalCount){
		return getPageData(list, totalCount).toJSONString();
	}*/
	
	
}

package saptacims.vo.base;

import java.util.List;
import java.util.Map;

public class ExcleData {

	private String path;//Excel模板路径
	
	private String title;//sheet名
	
	private String fileName;//导出的文件名
	
	private List<Map<String,ExcleRow>> rows;//表格数据

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Map<String, ExcleRow>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, ExcleRow>> rows) {
		this.rows = rows;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}


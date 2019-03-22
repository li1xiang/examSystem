package saptacims.helper.other;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import saptacims.vo.base.ExcleData;
import saptacims.vo.base.ExcleRow;

public class ExcelUtil {
	private  String sheetName="";
	private  BufferedInputStream pfs=null;//模板读取
	private  XSSFWorkbook wb=null;//表格
	private  XSSFSheet sheet=null;//工作表标签
	private  int rowNumber=0;//表头的行数
	
	//private static String FilePath="D:/test.xls";//保存路径（测试）
	
	/** 
     * 获取所读取excel模板的对象 
     */  
    private void getSheet(String templatePath) {  
        try {  
        	String filePath = ExcelUtil.class.getClassLoader().getResource(templatePath).getPath();//获取文件的路径
        	System.out.println("文件路径:" + filePath);  
        	String p=getRootPath(templatePath);
        	System.out.println("转换后的文件路径:" + p);  
            File fi = new File(p);  
            if (!fi.exists()) {  
                System.out.println("模板文件:" + p + "不存在!");  
                return;  
            }  
            pfs = new BufferedInputStream(new FileInputStream(fi));  
            wb = new XSSFWorkbook(pfs); 
            sheet = wb.getSheet(sheetName);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
    
    /** 
     * 设置数据 
     */
    public void getExcel(ExcleData datas){
    	sheetName=datas.getTitle();
    	String path="/ExcelTemplates/"+datas.getPath();
    	getSheet(path);
    	int total_cell=0;//总共多少列
    	if(wb!=null&&sheet!=null)
    	{
    		//rowNumber=sheet.getLastRowNum()+1;//获取当前表中的行数
    		rowNumber=sheet.getPhysicalNumberOfRows();//获取物理行数
    		List<Map<String,ExcleRow>> rowslist=datas.getRows();
    		 if(rowslist.size()>0){	 
    			 for(int i=0;i<rowslist.size();i++){
    				 Map<String,ExcleRow> rowsmap=rowslist.get(i);
    				 int celllen=0;//表示第几列
    				 XSSFRow row=sheet.createRow(i+rowNumber);
    				 for(String key:rowsmap.keySet()){
    					 ExcleRow e_row=rowsmap.get(key);
    					 XSSFCell cell = row.createCell(celllen); 
    					 XSSFCellStyle style=getStyle(e_row.getType());//设置样式
    					 cell.setCellStyle(style);
    					 String r_value=e_row.getRowValue();//获取单元格值
    					 cell.setCellValue(r_value);
    					 //cell=SetValue(r_value,e_row.getType(),cell);
    					 celllen++;
    				 }
    				 if(celllen>total_cell)
    					 total_cell=celllen;//如果当前行的列数大于记录的总列数，则替换
    			 }
    			// 自动调整宽度
 		    	for (int i = 0; i < total_cell; i++) {
 		           sheet.autoSizeColumn(i);
 		    	}
    		 }
    		 //下载到本地
//    		 try {
//    	         	OutputStream outXlsx = new FileOutputStream(FilePath);
//    	            wb.write(outXlsx);
//    	            wb.close();
//    	            //wb.dispose();
//    	         } catch (IOException e) {
//    	             e.printStackTrace();
//    	         }
    	}
    	 
    }
    
    /** 
     * 设置单元格样式，参数为值得类型 
     */
    private XSSFCellStyle getStyle(int type)
    {
    	XSSFCellStyle style=wb.createCellStyle();//设置样式
    	style.setVerticalAlignment(VerticalAlignment.CENTER);
    	 
    	switch (type) {
		case 1:
			style.setAlignment(HorizontalAlignment.LEFT);
			break;
		case 2:
			style.setAlignment(HorizontalAlignment.RIGHT);
			break;
		case 3:
			style.setAlignment(HorizontalAlignment.CENTER);
			break;
		case 4:
			style.setAlignment(HorizontalAlignment.CENTER);
			break;
		case 5:
			style.setAlignment(HorizontalAlignment.CENTER);
			break;
		default:
			style.setAlignment(HorizontalAlignment.LEFT);
			break;
		}
    	return style;
    }
    
    public void download(ExcleData datas,HttpServletResponse response) 
    {
    	try
    	{
			getExcel(datas);
			String filename=datas.getFileName()+".xlsx";
			response.reset();//清空response
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
		    response.setHeader("Content-Disposition","attachment;filename="+filename);
		    response.setCharacterEncoding("utf-8");
		    OutputStream os=response.getOutputStream();
		    wb.write(os);
		    wb.close();
    	}catch (IOException e) {
            e.printStackTrace();
        }
		//return response;
    	System.out.println("文件生成..."); 
    }
    
    
	public String getRootPath(String templatePath) {
		String classPath = ExcelUtil.class.getClassLoader().getResource(templatePath).getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			//rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
			rootPath = classPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			//rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
			rootPath = classPath.replace("\\", "/");
		}
		return rootPath;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
//	public XSSFCell SetValue(Object value,int type,XSSFCell cell){
//		switch (type) {
//		case 1:
//			cell.setCellValue(value.toString());
//			break;
//		case 2:
//			cell.setCellValue((int)value);
//			break;
//		case 3:
//			cell.setCellValue(DateUtil.getDate());
//			break;
//		case 4:
//			cell.setCellValue(DateUtil.getTime((Date)value));
//			break;
//		case 5:
//			cell.setCellValue(DateUtil.getTimestamp((Date)value));
//			break;
//		default:
//			cell.setCellValue(value.toString());
//			break;
//		}
//		return cell;
//	}

}

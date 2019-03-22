package pub.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ComboboxVO  implements Serializable{
    
	
	public final static String ALL="";
    
    private static final String CST_SUFFIX="_TEXT";

    private String id;
	private String text;
	private boolean selected;
	
	/**
	 * 
	 * @Description:添加全部选项
	 * @return
	 */
	public static ComboboxVO addAllCombobox(){
		ComboboxVO vo = new ComboboxVO();
		vo.setId("");
		vo.setText("全部");
		vo.setSelected(true);
		return vo;
	}
	
	public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * 
	 * @Description:获取常量下拉框且可以指定选中值
	 * @param cst
	 * @param selectValue
	 * @return
	 */
	public static List<ComboboxVO> getCstCombobox(Class cst, String selectValue){
        List<ComboboxVO> list=new ArrayList<ComboboxVO>();
        try
        {
            Field[] fields=cst.getDeclaredFields();
            String[] idArray=new String[fields.length/2];
            String[] textArray=new String[fields.length/2];
            for (int i = 0,k=0; i < fields.length; i++)
            {
                String className=fields[i].getName();
                if(className.endsWith(CST_SUFFIX)){
                    textArray[k]=fields[i].get(className)+"";
                    k++;
                }
            }
            for (int i = 0,k=0; i < fields.length; i++)
            {
                String className=fields[i].getName();
                if(!className.endsWith(CST_SUFFIX)){
                    idArray[k]=fields[i].get(className)+"";
                    k++;
                }
            }
            for (int i = 0; i < idArray.length; i++)
            {
                ComboboxVO vo=new ComboboxVO();
                vo.setId(idArray[i]);
                vo.setText(textArray[i]);          
                if(vo.getId().equals(selectValue))
                    vo.setSelected(true);
                list.add(vo);
            }
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        return list;
	}	
	   
	/**
	 * 
	 * @Description:获取常量下拉框（有全部选项）
	 * @param cst
	 * @return
	 */
    public static List<ComboboxVO> getCstAllCombobox(Class cst){
        List<ComboboxVO> list=new ArrayList<ComboboxVO>();
        try
        {
            ComboboxVO allVO=ComboboxVO.addAllCombobox();
            list.add(allVO);
            list.addAll(getCstCombobox(cst, ""));
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        return list;
    }
    
    public static String getCstText(Class cst,String key)
    {
    		String value = "";
    		try {
    			Field[] fields=cst.getDeclaredFields();
    			String tag = "";
    			for (int i = 0; i < fields.length; i++) {
    				Object className =fields[i].get(fields[i].getName());
    				if(className.equals(key))
    				{
    					tag = fields[i].getName();
    				}
    			}
    			tag+="_TEXT";
				for (int i = 0; i < fields.length; i++) {
					String className = fields[i].getName();
					if(tag.equals(className))
					{
						value = fields[i].get(className).toString();
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    		return value;
    }
	
}

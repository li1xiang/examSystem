package saptacims.cst.i18n;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class I18nPropertyUtil {

	private static Properties key_cst = null;
	
	private static void loadmsg(){
		//加载常量的名称
    	if(key_cst == null)
    	{
    		try {
    			key_cst = new Properties();
    			key_cst.load(I18nPropertyUtil.class.getResourceAsStream("cst.properties"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	public static String getValue(String key,String cstValue){
		loadmsg();
		String cst_key=KeyCst.getValue(key, cstValue);
		if(StringUtils.isBlank(cst_key)){
			return null;
		}
		return key_cst.getProperty(cst_key);
	}
}

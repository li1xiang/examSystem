package saptacims.exception;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author hajime
 *
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = -5464899701032641133L;
	
	private int errorCode;
	private List<Object> objs = new ArrayList<Object>();
	
    private static Properties msg_cn  = null;
    private static Properties msg_en  = null;
    
    private static void loadmsg()
    {
    	if(msg_cn == null)
    	{
    		try {
    			msg_cn = new Properties();
				msg_cn.load(AppException.class.getResourceAsStream("error_zh.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(msg_en == null)
    	{
    		try {
    			msg_en = new Properties();
				msg_en.load(AppException.class.getResourceAsStream("error_zh.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
	
	public AppException(int errorCode, Object... obj) {
		this(null, errorCode, obj);
	}

	public AppException( Throwable ex, int errorCode, Object... obj) {
		super(getErrorMsg(errorCode, obj), ex);
		this.errorCode = errorCode;
		this.objs.add(obj);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public static String getErrorMsg(int code, Object... objs)
	{
		loadmsg();
		if(code==0)return ""; 
		String msgcn = msg_cn.getProperty(""+code);
		if(msgcn==null)
			msgcn = "错误"+code;
		if(objs!=null)
			msgcn=MessageFormat.format(msgcn, objs);
		return msgcn;
	}
	
//	@Override
//	public String getMessage()
//	{
//		return getErrorMsg(this.errorCode);
//	}

}

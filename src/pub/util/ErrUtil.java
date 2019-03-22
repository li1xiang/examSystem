package pub.util;

import java.math.BigDecimal;

public class ErrUtil {
	
	/**
	 * String
	 */
	public static String ckStr(String obj){
		return StringUtil.isEmpty(obj) ? "" : obj;
	}
	
	/**
	 * Long
	 */
	public static String ckLong(Long obj){
		return obj==null ? "" : obj.toString();
	}
	
	/**
	 * Integer
	 */
	public static String ckInt(Integer obj){
		return obj==null ? "" : obj.toString();
	}
	
	/**
	 * Double
	 */
	public static String ckDbl(Double obj){
		if(obj==null){
			return "";
		}
		BigDecimal bd = BigDecimal.valueOf(obj);
		return bd.stripTrailingZeros().toString();
	}
	
	/**
	 * Double
	 */
	public static String ckRate(Double obj){
		if(obj==null){
			return "";
		}
		BigDecimal bd = BigDecimal.valueOf(obj).multiply(new BigDecimal(100));
		return bd.stripTrailingZeros().toString();
	}
	
//	/**
//	 * Date
//	 */
//	public static String ckDate(Date obj){
//		return obj==null ? "" : DateUtil.formatDate(obj);
//	}
//	
//	/**
//	 * Time
//	 */
//	public static String ckTime(Date obj){
//		return obj==null ? "" : DateUtil.formatTime(obj);
//	}
	

}

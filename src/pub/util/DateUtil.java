package pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import saptacims.exception.AppException;
import saptacims.exception.error;

/**
 * 日期转换公用
 * 
 * @author Sawyer
 * 
 */
public class DateUtil {

	public static final String DF_DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * "yyyy-MM-dd"
	 */
	public static final SimpleDateFormat DF_DATE 
								= new SimpleDateFormat(DF_DATE_PATTERN);
	
	public static final String DF_TIME_PATTERN = "HH:mm:ss";
	public static final String DF_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat DF_DATETIME = new SimpleDateFormat(DF_TIMESTAMP_PATTERN);
	public static final String DF_DATE_SHORT_PATTERN = "yyyyMMdd";
	public static final SimpleDateFormat DF_DATE_SHORT 
								= new SimpleDateFormat( DF_DATE_SHORT_PATTERN);
	public static final String DF_TIMEMIN_SHORT__PATTERN = "yyyyMMddHHmm";

	/**
	 * 返回当前系统日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * 返回当前系统日期 没有时分秒
	 * 
	 * @return
	 */
	public static Date getCurrentDateWithoutTime() {
			return getDateWithoutTime(new Date());
	}

	
	/**
	 * 返回没有时分秒的Date
	 * @param date
	 * @return
	 */
	public static Date getDateWithoutTime(Date date) {
		  try {
			return DF_DATE.parse(getFormattedDateString(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  return null;
	}

	
	
	/**
	 * 格式化yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getFormattedDateString(Date date) {
		return DF_DATE.format(date);
	}
	
	
	/**
	 * 格式化yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getFormattedDateTimeString(Date date) {
		return DF_DATETIME.format(date);
	}
	
	/**
	 * 格式化yyyyMMdd
	 * @return
	 */
	public static Date getDateFromShortString(String  dateString) {
		try {
			return DF_DATE_SHORT.parse(dateString);
		} catch (ParseException e) {
			throw new AppException(error.UNKNOWN_ERROR);
		}
	}

	/**
	 * 格式化yyyyMMdd
	 * 
	 * @return
	 */
	public static String getFormattedDateShortString(Date date) {
		return DF_DATE_SHORT.format(date);
	}

	/**
	 * 获取GregorianCalendar的now
	 * 
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar getGregorianCalendarNow() {
		Date nowTime = new Date();
		GregorianCalendar calender = new GregorianCalendar();
		calender.setTime(nowTime);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					calender);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw new AppException(error.UNKNOWN_ERROR);
		}
	}

	/**
	 * date类型转化为XMLGregorianCalendar
	 * 
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		XMLGregorianCalendar gc = null;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gc;
	}

	public static String getDate(Date date) {
		return new SimpleDateFormat(DF_DATE_PATTERN).format(date);
	}

	/**
	 * XMLGregorianCalendar->Date
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static Date getDateFromGregorianCalendar(
			XMLGregorianCalendar calender) {
		GregorianCalendar ca = calender.toGregorianCalendar();
		return ca.getTime();
	}

	/**
	 * 长整型格式化为时间
	 * @param ms
	 * @return
	 */
	public static String formatTime(long ms) {  
        
        int ss = 1000;  
        int mi = ss * 60;  
        int hh = mi * 60;  
        int dd = hh * 24;  

        long day = ms / dd;  
        long hour = (ms - day * dd) / hh;  
        long minute = (ms - day * dd - hour * hh) / mi;  
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  

        String strDay = day < 10 ? "0" + day : "" + day; //天  
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时  
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟  
        String strSecond = second < 10 ? "0" + second : "" + second;//秒  
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒  
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;  
         
        return strMinute + " 分 " + strSecond + " 秒";  
}
}
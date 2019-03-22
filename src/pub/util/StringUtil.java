package pub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
    static Pattern doublePattern = Pattern.compile("^([+-]?\\d+)(\\.\\d+)?$");
    static Pattern positiveDoublePattern = Pattern.compile("^[+]?(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
    static Pattern positiveIntPattern = Pattern.compile("^[+]?[0-9]*[1-9][0-9]*$");
    static Pattern positiveIntPattern2 = Pattern.compile("^[+]?[0-9]*[1-9][0-9]*.?0+$");
    static Pattern intPattern = Pattern.compile("^[+-]?\\d+$");
    static Pattern doubleByte = Pattern.compile("[^\\x00-\\xff]");
    static Pattern specialChar = Pattern.compile("^[\u4E00-\u9FA5A-Za-z0-9（）]+$");

    public static boolean isEmpty(String str)
    {
        if(str==null || str.trim().equals(""))
            return true;
        return false;
    }
    
    public static boolean isNotEmpty(String str)
    {
        if(str==null || str.trim().equals(""))
            return false;
        return true;
    }
    
    public static String trim(String str)
    {
        if(str == null)
            return "";
        return str.trim();
    }
    
    public static boolean isDouble(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return doublePattern.matcher(str).find();
    }
    
    private static Pattern getDigiPattern(boolean positive, int digit)
    {
        String p = positive?"":"-";
        return Pattern.compile("(^[+"+p+"]?\\d+)(\\.(\\d{1,"+digit+"}))?$");
    }
    
    public static boolean isDouble(String str, int digit)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return getDigiPattern(false, digit).matcher(str).find();
    }
    
    public static boolean isPositiveDouble(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return positiveDoublePattern.matcher(str).find();
    }
    
    public static boolean isPositiveDouble(String str, int digit)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return getDigiPattern(true, digit).matcher(str).find();
    }

    public static boolean isPositiveInt(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        //alter 考虑整数后面小数位为0的情况
        boolean r1 = positiveIntPattern.matcher(str).find();
        boolean r2 = positiveIntPattern2.matcher(str).find();
        if(!r1 && !r2)
        	return false;
        return true;
    }
    
    public static boolean isInt(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return intPattern.matcher(str).find();
    }
    
    public static boolean isCludeDoublebyte(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return doubleByte.matcher(str).find();
    }
    
    public static boolean isPositiveDoubleCurve(String str, int digit,int length)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        String beforCheck = str.replace("-", "");
        if(beforCheck.contains("."))
        {
	        if(beforCheck.substring(0, beforCheck.lastIndexOf(".")).length()>length)
	        	return false;
        }else
        {
        	if(beforCheck.length()>length)
	        	return false;
        }
        return getDigiPattern(false, digit).matcher(str).find();
    }
    
    public static boolean isSpecialChar(String str)
    {
        if (str == null || str.length() < 1) {
            return false;
        }
        return specialChar.matcher(str).find();
    }
    
    public static String getJsonFragment(String field, String value){
    	return "\""+field+"\":"+"\""+value+"\"";
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    public static void main(String[] args){
    	String str = "adfasdf\nfdsdfasdf\nfsdfsf\rfdsafsda\tdsfadsf";
    	System.out.println(replaceBlank(str));
    }
//        String[] all = new String[]{"a12","12a","0","123",
//                "0.32", ".32", "009.12", "+352452.00", "+352452.001", 
//                "-0.32", "-.32", "-009.12", "-352452.00", "-352452.001" };
//        
//        for(int i=0;i<all.length;i++)
//        {
//            System.out.println(isPositiveDouble(all[i],2)+"["+all[i]+"]");
//        }
//        
//        System.out.println(isPositiveInt("4444.0"));
//        System.out.println(isPositiveInt("4444"));
//        System.out.println(isPositiveInt("4444.43"));
//        System.out.println(isPositiveInt("0.43"));
//        System.out.println(isPositiveInt("5.0043"));
//        
//    }
    
}

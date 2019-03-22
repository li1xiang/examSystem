package pub.util;

import java.math.BigDecimal;

/**
 * 
 * @author Galliano
 *
 */
public class CalculateUtil {

	/**
	 * 求余
	 * @param num1 数字1
	 * @param num2 数字2
	 * @return 余数
	 */
	public static Double remainder(Double num1, Double num2){
		BigDecimal bd = BigDecimal.valueOf(num1).remainder(BigDecimal.valueOf(num2));
		return bd.doubleValue();
	}
	
	/**
	 * 是否整除
	 * @param num1 数字1
	 * @param num2 数字2
	 * @return true-整除; false-有余;
	 */
	public static boolean isDivisible(Double num1, Double num2){
		Double num = remainder(num1, num2);
		if(num==0){
			return true;
		}
		return false;
	}
	
}

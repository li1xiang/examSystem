
package saptacims.helper.other;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrintUtil {
	protected final Log log = LogFactory.getLog(PrintUtil.class);
	
	/**
	 * 打印对象
	 * @param obj
	 * @return
	 */
	public static String print(Object obj)
	{
		 StringBuffer bf=new StringBuffer();
		 if(obj instanceof String)
		 {
			 bf.append(obj+"");
		     return bf.toString();
		 }
		 if(null!=obj&&true==obj.getClass().isArray())
		 {
			Object[] objs=(Object[])obj;
			for(int i=0;i<objs.length;i++)
			{
				bf.append("[");
				bf.append(print(objs[i]));
				bf.append("]");
			}
		 }else
		 {
		   try{
			 Method[] meths = obj.getClass().getMethods();
			
			 for(int i = 0; i < meths.length; i++)
			 {
				String methname =isGetMethod(meths[i]);
				if(methname == null)
					continue;
				Object v=meths[i].invoke(obj,null);
				bf.append(methname);
				bf.append("=");
				bf.append(v);
				bf.append("|");
					
			 }
		  }catch(Throwable e){
			  //log.debug(e.getMessage());
		  }
		 }
		return bf.toString();
	}
	 /*
	  */
	private static String isGetMethod(Method meth)
		{
  		    if(meth.getParameterTypes().length!= 0)
			    return null;

		    if(meth.getReturnType().getName().equals("void"))
				return null;
			String name = meth.getName();
			
			if(name.substring(0, 3).equals("get"))
				name = name.substring(3);
			else
				return null;
			if(name.equals("Class"))
				return null;
			if(Character.isLowerCase(name.charAt(0)))
				return null;
			return name.toLowerCase();
		}
}

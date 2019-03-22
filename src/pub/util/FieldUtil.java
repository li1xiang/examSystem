/**
 * 公司：上海亚太神通有限公司Copyright (c) 2005 <br>
 * 作者: hajime<br>
 * 创建时间: 2008-7-3<br>
 * 单元描述(主要函数及其功能): <br>     
 */
package pub.util;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 名称：FieldHelper<br>
 * 描述：<br>
 * 版权：上海亚太计算机信息系统有限公司 Copyright@2006<br>
 * 作者：hajime<br>
 * 时间：2008-7-3<br>
 */

public class FieldUtil
{
    public static Set<String> getReadFields(Class<?> cls)
    {
        Method[] method = cls.getMethods();
        Set<String> fieldset = new HashSet<String>();
        for(int i=0;i<method.length;i++)
        {
            String mName = method[i].getName();
            if(mName.startsWith("get"))
            {
                if(mName.equals("getBytes"))
                    continue;
                if(method[i].getReturnType() == void.class)
                    continue;
                if(method[i].getParameterTypes().length >0)
                    continue;
                String fName = mName.substring(3);
                fName = fName.substring(0,1).toLowerCase() + fName.substring(1);
                fieldset.add(fName);
            }
        }
        return fieldset;
    }
    
    public static Set<String> getWriteFields(Class<?> cls)
    {
        Method[] method = cls.getMethods();
        Set<String> fieldset = new HashSet<String>();
        for(int i=0;i<method.length;i++)
        {
            String mName = method[i].getName();
            if(mName.startsWith("set"))
            {
                if(method[i].getReturnType() != void.class)
                    continue;
                if(method[i].getParameterTypes().length != 1)
                    continue;
                String fName = mName.substring(3);
                fName = fName.substring(0,1).toLowerCase() + fName.substring(1);
                fieldset.add(fName);
            }
        }
        return fieldset;
    }
    
    public static Object getFieldValue(Object obj, String fieldName)
    {
        if(fieldName == null || fieldName.length()==0)
            return null;
        String fName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        Method method;
        try
        {
            method = obj.getClass().getMethod("get" + fName, new Class[]{});
            return method.invoke(obj, new Object[]{});
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void setFieldValue(Object obj, String fieldName, Object value)
    {
        if(fieldName == null || fieldName.length()==0)
            return;
        try
        {
            String fName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            Method[] method = obj.getClass().getMethods();
            for(int i = 0;i<method.length;i++)
            {
                if(method[i].getName().equals("set"+fName))
                {
//                    if(method[i].getParameterTypes()[0].equals(value.getClass()))
//                    {
                        method[i].invoke(obj, new Object[]{value});
                        break;
//                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static Class<?> getFieldType(Class<?> objcls, String fieldName)
    {
        String fName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        Method method;
        try
        {
            method = objcls.getMethod("get" + fName, new Class[]{});
            return method.getReturnType();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    public static void main(String[] args)
    {
    }
}

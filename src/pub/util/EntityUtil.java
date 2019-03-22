/**
 * 公司：上海亚太神通有限公司Copyright (c) 2005 <br>
 * 作者: hajime<br>
 * 创建时间: 2008-7-8<br>
 * 单元描述(主要函数及其功能): <br>     
 */
package pub.util;

import java.util.Iterator;
import java.util.Set;


/**
 * 名称：ObjUtil<br>
 * 描述：<br>
 * 版权：上海亚太计算机信息系统有限公司 Copyright@2006<br>
 * 作者：hajime<br>
 * 时间：2008-7-8<br>
 */

public class EntityUtil
{
    public static void copyProperties(Object srcobj, Object destobj)
    {
        Set<?> srcf = FieldUtil.getReadFields(srcobj.getClass());
        Set<?> desf = FieldUtil.getWriteFields(destobj.getClass());
        Iterator<?> it = srcf.iterator();
        while(it.hasNext())
        {
            String fName = (String)it.next();
            if(desf.contains(fName))
            {
                Object value;
                try
                {
                    value = FieldUtil.getFieldValue(srcobj, fName);
                    FieldUtil.setFieldValue(destobj, fName, value);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }        
    }
    
    public static Object copyProperties(Object srcobj,Class<?> destclass)
    {
        try
        {
            Object destobj = destclass.newInstance();
            copyProperties(srcobj, destobj);
            return destobj;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

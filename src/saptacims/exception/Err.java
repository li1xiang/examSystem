package saptacims.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hajime
 * 
 */
public class Err
{
    static Log log = LogFactory.getLog(Err.class);
    
    public static void checkError(boolean exp, int code, Object... args)
    {
        if (true == exp)
        {
        	throw new AppException(code, args);
        }
    }
    
    public static void checkError(Throwable ex, int code, Object... args)
    {
    	throw new AppException(ex, code, args);
    }
    
    public static void unknownError(Throwable ex)
    {
    	throw new AppException(ex, error.UNKNOWN_ERROR);
    }

}

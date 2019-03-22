package pub.util;

import java.net.UnknownHostException;

/**
 * 
 * @author bird
 *
 */
public class SystemUtil {

	public static String getServerName()
	{
		String serverName = "";
		try {
			serverName = java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			throw new RuntimeException("获取系统名字异常",e);
		}
		
		return serverName;
	}
}

package saptacims.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saptacims.model.TbUser;

/**
 * 登录过滤器
 * 
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("loginFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// 从session里取的用户名信息
		TbUser currentUser = (TbUser) session.getAttribute("currentUser");
		String uri = ((HttpServletRequest) request).getRequestURI();
		if (currentUser != null || (uri.endsWith("/") || uri.endsWith("login") || uri.endsWith("getImg")
				|| uri.endsWith("signin") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".gif")
				|| uri.endsWith(".png") || uri.endsWith(".jpg"))) {
			chain.doFilter(req, res); 
		} else {// 获取登录用户的Session --基础权限检查，用户没有登陆，被拦截或者session超时请重新登录
			if (null == session || null == session.getAttribute("currentUser")) {// 如果是ajax请求响应头会有，x-requested-with
				if(req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
        		{
        			res.setHeader("sessionstatus", "timeout");//在响应头设置session状态 
        			//res.getWriter().print("errorMsg=\'登录超时！\'"); //打印一个返回值，没这一行，在tabs页中无法跳出（导航栏能跳出），具体原因不明
        		}else{ 
        			res.setHeader("sessionstatus", "timeout");//在响应头设置session状态 
        			//res.getWriter().print("errorMsg=\'未登录！\'"); //打印一个返回值，没这一行，在tabs页中无法跳出（导航栏能跳出），具体原因不明
        			//String path = req.getContextPath();
        			res.sendRedirect(req.getContextPath());
        			
        		}
        		return;
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

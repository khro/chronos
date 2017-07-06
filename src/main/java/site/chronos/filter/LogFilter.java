package site.chronos.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

	public FilterConfig config;

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

		String logonStrings = "/login"; // 登录登陆页面
		String redirectPath = hrequest.getContextPath() + logonStrings;// 没有登陆转向页面
		String exclude = "/|/index.html"; // 登录登陆页面

		if (isLoginPage(hrequest.getServletPath(), logonStrings)) {// 对登录页面不进行过滤
			chain.doFilter(request, response);
			return;
		}

		if (contains(hrequest.getServletPath(), exclude.split("|"))) {// 不需要过滤的页面
			chain.doFilter(request, response);
			return;
		}

		String user = (String) hrequest.getSession().getAttribute("useronly");// 判断用户是否登录
		if (user == null) {
			wrapper.sendRedirect(redirectPath);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean contains(String servletPath, String[] split) {
		return Arrays.asList(split).contains(servletPath);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public static boolean isLoginPage(String container, String regx) {
		return Arrays.asList(regx).contains(container);
	}

}

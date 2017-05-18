package com.itstar.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import com.itstar.model.BBSUser;

public class CheckUserSessionFilter implements Filter {
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public CheckUserSessionFilter() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#init(FilterConfig arg0)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String contextPath = httpServletRequest.getContextPath();
		String[] reqUrl = httpServletRequest.getRequestURI().split("/");
		String url = reqUrl[reqUrl.length - 1];
		BBSUser user = null;
		user = (BBSUser)httpServletRequest.getSession().getAttribute("user");
		/*
		 * 跳过登录页的过滤
		 */
		if ("web/adminlogin.jsp".endsWith(url)) {
			chain.doFilter(request, response);
		} else {
			/*
			 * 无登录Session的跳转
			 */
			if (null != user && user.getUserClass()==3) {
				chain.doFilter(request, response);
			} else if(url.contains(".jsp")) {
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("parent.window.location.href='../adminlogin.jsp'");
				out.println("</script>");
				//httpServletResponse.sendRedirect(contextPath + "/web/adminlogin.jsp");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("parent.window.location.href='web/adminlogin.jsp'");
				out.println("</script>");
			}
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
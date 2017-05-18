package com.itstar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterTest implements Filter
{
	private FilterConfig config;
	public void destroy()
	{
		this.config = null;
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	{
		try
		{
			String encod = config.getInitParameter("encod");
			request.setCharacterEncoding(encod);
			response.setContentType("text/html;charset="+encod);
			chain.doFilter(request, response);
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ServletException e)
		{
			e.printStackTrace();
		}
	}

	public void init(FilterConfig config)
	{
		this.config = config;
		
	}

}

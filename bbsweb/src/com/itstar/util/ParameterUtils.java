/*
 * 
 此类用来处理接收请求页面的参数进行处理，包括参数乱码处理，还有参数类型转换的问题
 * */
package com.itstar.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtils {

	// 参数乱码处理
	public static void serCharacterEncoding(
			HttpServletRequest httpservletrequest)
			throws UnsupportedEncodingException {

		httpservletrequest.setCharacterEncoding("GBK");
	}

	// 获取一个字符串类型的参数
	public static String getString(HttpServletRequest httpservletrequest,
			String s) throws UnsupportedEncodingException {

		return httpservletrequest.getParameter(s) != null ? httpservletrequest
				.getParameter(s).trim() : "";

	}

	// 获得一个二进制类型的参数
	public static byte getByte(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Byte.parseByte(gp);

	}

	// 获得一个整数类型的参数
	public static int getInt(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Integer.parseInt(gp);

	}

	// 获得一个Long类型的参数
	public static long getLong(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Long.parseLong(gp);

	}

	// 获得一个Short类型的参数
	public static short getShort(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Short.parseShort(gp);

	}

	// 获得一个Float类型的参数
	public static float getFlocat(HttpServletRequest httpservletrequest,
			String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Float.parseFloat(gp);

	}
	//返回一个布尔类型
	public static boolean getBoolean(HttpServletRequest httpservletrequest,
			String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return false;
		else
			return Boolean.parseBoolean(s);

	}

}

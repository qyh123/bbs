/*
 * 
 �������������������ҳ��Ĳ������д��������������봦�����в�������ת��������
 * */
package com.itstar.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtils {

	// �������봦��
	public static void serCharacterEncoding(
			HttpServletRequest httpservletrequest)
			throws UnsupportedEncodingException {

		httpservletrequest.setCharacterEncoding("GBK");
	}

	// ��ȡһ���ַ������͵Ĳ���
	public static String getString(HttpServletRequest httpservletrequest,
			String s) throws UnsupportedEncodingException {

		return httpservletrequest.getParameter(s) != null ? httpservletrequest
				.getParameter(s).trim() : "";

	}

	// ���һ�����������͵Ĳ���
	public static byte getByte(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Byte.parseByte(gp);

	}

	// ���һ���������͵Ĳ���
	public static int getInt(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Integer.parseInt(gp);

	}

	// ���һ��Long���͵Ĳ���
	public static long getLong(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Long.parseLong(gp);

	}

	// ���һ��Short���͵Ĳ���
	public static short getShort(HttpServletRequest httpservletrequest, String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Short.parseShort(gp);

	}

	// ���һ��Float���͵Ĳ���
	public static float getFlocat(HttpServletRequest httpservletrequest,
			String s)

	{
		String gp = httpservletrequest.getParameter(s);
		if (gp == null || gp.equals(""))
			return 0;
		else
			return Float.parseFloat(gp);

	}
	//����һ����������
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

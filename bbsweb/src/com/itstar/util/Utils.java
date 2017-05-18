package com.itstar.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class Utils {

	
	/**
	 *����������ַ���ת�� 
	 */
	public static String toChinese(String s,String encoding){
		if(s == null){
			return "";
		}
		try {
	       return new String(s.getBytes("ISO-8859-1"), encoding);
        }catch (Exception e) {
          return s;
        }
	}
	/**
	 *����������ַ���ת�� 
	 */
	public static String toChinese(String s){
		return toChinese(s,"UTF-8");
	}
	
	
	/**
	 * ��ȡ�ַ���ǰN���ַ�
	 * @param str
	 * @param len
	 * @return
	 */
	public static String leftStr(String str, int len)
    {
        if(str == null)
            return null;
        if(len < 0)
            return "";
        if(str.length() <= len)
            return str;
        else
            return str.substring(0, len);
    }
	
	/**
	 * ��JAVA����ת�������ݿ����ڸ�ʽ�������������
	 * @param rq
	 * @return
	 */
	public static java.sql.Date formatForSql(Date rq){
		return new java.sql.Date(rq.getTime());
	}
	
	/**
	 * �õ���ǰ����
	 * @param formatStr ��ʽ���ַ���
	 * @return
	 */
	public static String getNowDate(String formatStr) {
		Date dateTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String strTime = format.format(dateTime);
		return strTime;
	}
	/**
	 * ��ʽ�����ڳ��ַ���
	 * @param dtime
	 * @param dateformat
	 * @return
	 * ���þ���������formatDate(rq,"yyyy-MM-dd");
	 * 			formatDate(rq,"yyyy-MM-dd HH:mm:ss");��
	 */
	public String formatDate(Date dtime, String dateformat) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		String strTime = format.format(dtime);
		return strTime;
	}
	
	/**
	 * ���ַ�����ʽ��������
	 * 
	 * @param str
	 * @param parsePatterns
	 * @return
	 * ���þ���:
	 * parseDate("2001-1-1",new String[]{"yyyy-MM-dd"});
	 */
	public  Date parseDate(String str, String parsePatterns[]){
		if (str == null || parsePatterns == null) return null;
		SimpleDateFormat parser = null;
		ParsePosition pos = new ParsePosition(0);
		for (int i = 0; i < parsePatterns.length; i++) {
			if (i == 0)
				parser = new SimpleDateFormat(parsePatterns[0]);
			else
				parser.applyPattern(parsePatterns[i]);
			pos.setIndex(0);
			Date date = parser.parse(str, pos);
			if (date != null && pos.getIndex() == str.length())
				return date;
		}
		return null;
	}
	
	/**
	 * ת�������ַ�����ת����ָ����ǩ��ʽ���
	 * @param str ��Ҫת����str
	 * @return
	 */
	public String replaceString(String str) {
		str = str.replace("&lt;","<" );
		str = str.replace("&gt;",">" );
		str = str.replace("&quot;", "\"");
		str = str.replace("&xt;", "'");
		return str;
	}
	
	/**
	 * �������ַ����������ϴ���������ݿ�
	 * @param str ��Ҫת����str
	 * @return
	 */
	public String replaceInsertString(String str) {
		str =str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("\"", "&quot;");
		str = str.replace("'", "&xt;");
		return str;
	}
	
	/**
	 * ��'���������ϴ���������ݿ�
	 * @param str ��Ҫת����str
	 * @return
	 */
	public String replaceDouhao(String str) {
		str = str.replace("'", "&xt;");
		return str;
	}
	
	/**
	 * ��&xt;ת��Ϊ'
	 * @param str ��Ҫת����str
	 * @return
	 */
	public String replaceCharToDaohao(String str) {
		str = str.replace("&xt;","'");
		return str;
	}
	
	/**
	 * ���ñ�ǩ
	 * @param str ��Ҫת����str
	 * @return
	 */
	public String replaceQuote(String str) {
		str = str.replace("[quote]","<div class='quote'><h5>����:</h5><blockquote> ԭ����");
		str = str.replace("[/quote]","</blockquote></div>");
		return str;
	}
	public static void main(String arg[]) {
		Utils us = new Utils();
		 System.out.println(us.replaceInsertString("<font color='red'>asdf</font>"));
		 System.out.println(us.replaceString("[quote]dsaf[quote]sadfsdaf[quote]"));
		 
	}

}

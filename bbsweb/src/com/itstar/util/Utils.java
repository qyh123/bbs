package com.itstar.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class Utils {

	
	/**
	 *返回任意的字符串转换 
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
	 *返回任意的字符串转换 
	 */
	public static String toChinese(String s){
		return toChinese(s,"UTF-8");
	}
	
	
	/**
	 * 截取字符串前N个字符
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
	 * 把JAVA日期转换成数据库日期格式，方便参数传递
	 * @param rq
	 * @return
	 */
	public static java.sql.Date formatForSql(Date rq){
		return new java.sql.Date(rq.getTime());
	}
	
	/**
	 * 得到当前日期
	 * @param formatStr 格式化字符串
	 * @return
	 */
	public static String getNowDate(String formatStr) {
		Date dateTime = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String strTime = format.format(dateTime);
		return strTime;
	}
	/**
	 * 格式化日期成字符串
	 * @param dtime
	 * @param dateformat
	 * @return
	 * 调用举例：可以formatDate(rq,"yyyy-MM-dd");
	 * 			formatDate(rq,"yyyy-MM-dd HH:mm:ss");等
	 */
	public String formatDate(Date dtime, String dateformat) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		String strTime = format.format(dtime);
		return strTime;
	}
	
	/**
	 * 将字符串格式化成日期
	 * 
	 * @param str
	 * @param parsePatterns
	 * @return
	 * 调用举例:
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
	 * 转换特殊字符将其转换成指定标签形式输出
	 * @param str 需要转换的str
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
	 * 将特殊字符用其他符合代替插入数据库
	 * @param str 需要转换的str
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
	 * 将'用其他符合代替插入数据库
	 * @param str 需要转换的str
	 * @return
	 */
	public String replaceDouhao(String str) {
		str = str.replace("'", "&xt;");
		return str;
	}
	
	/**
	 * 将&xt;转换为'
	 * @param str 需要转换的str
	 * @return
	 */
	public String replaceCharToDaohao(String str) {
		str = str.replace("&xt;","'");
		return str;
	}
	
	/**
	 * 引用标签
	 * @param str 需要转换的str
	 * @return
	 */
	public String replaceQuote(String str) {
		str = str.replace("[quote]","<div class='quote'><h5>引用:</h5><blockquote> 原帖由");
		str = str.replace("[/quote]","</blockquote></div>");
		return str;
	}
	public static void main(String arg[]) {
		Utils us = new Utils();
		 System.out.println(us.replaceInsertString("<font color='red'>asdf</font>"));
		 System.out.println(us.replaceString("[quote]dsaf[quote]sadfsdaf[quote]"));
		 
	}

}

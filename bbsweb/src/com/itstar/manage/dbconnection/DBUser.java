package com.itstar.manage.dbconnection;


public class DBUser {
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://127.0.0.1:3306/forum";
	public static String username="root";
	public static String password="123";
	
	static{
//		String str=Configuration.class.getResource("").toString().replace("%20", " ");
//		Configuration rc = new Configuration(str.substring(6, str.length())+"/config.properties");//Ïà¶ÔÂ·¾¶
//		DBUser.driver=rc.getValue("driver");
//		DBUser.url=rc.getValue("url");
//		DBUser.username=rc.getValue("username");
//		DBUser.password=rc.getValue("password");

	}
}

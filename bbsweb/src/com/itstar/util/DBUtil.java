/**
 * DBUtil.java
 * com.itstar.util
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 Mar 22, 2013 		Administrator
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
*/

package com.itstar.util;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:DBUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Administrator
 * @version  
 * @since    Ver 1.1
 * @Date	 Mar 22, 2013		1:26:10 PM
 */
public class DBUtil {
	
	public static BasicDataSource BID = null;
	
	
	public static Connection getConnection() throws Exception 
	{
		
		Connection conn = null;
		
		if(BID == null)
		{
			try {
				ClassPathXmlApplicationContext xml = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
				BID = (BasicDataSource)xml.getBean("BID");
				//sessionFactory = c.getSessionFactory();
				//session = sessionFactory.openSession(); 
				//conn = session.connection();
				conn = BID.getConnection();
				System.out.println(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}
		}
		conn = BID.getConnection();
		return conn;
	}
	
	
}


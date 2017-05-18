package com.itstar.manage.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itstar.util.DBUtil;

/**
 * 数据库相关操作
 *
 */
public class DatabaseConnection {
	// static void conn=null;
	public Connection conn = null;
	
	
	public BasicDataSource BID = null;
	
	public SessionFactory sessionFactory=null;
	
	public ClassPathXmlApplicationContext xml=null;
	
	public DatabaseConnection c  = null;
	
	public Session session = null;
	
	public Statement stmt = null;

	public ResultSet rs = null;

	/**
	 * 获得数据库链接
	 * @param sql
	 */
	public Connection getHConnection() throws Exception {
		try {
			// Statement stmt = null;

			Class.forName(DBUser.driver);
			conn = DriverManager.getConnection(DBUser.url, DBUser.username,
					DBUser.password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}
	
	
	public Connection getConnection() throws Exception {
		
		
		return DBUtil.getConnection();

	}
	
	
	public Connection getTConnection() throws Exception {
		
		try {
			ClassPathXmlApplicationContext xml = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			c = (DatabaseConnection)xml.getBean("hibernateConnenction");
			
			//conn = this.getSessionFactory().getCurrentSession().connection();
			System.out.println(c.getConnection());
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		return conn;

	}

	/**
	 * 数据库执行查询操作
	 * @param sql
	 */
	public ResultSet Query(String sql,DatabaseConnection conn) {

		try {
			stmt = conn.getConnection().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			System.out.println(e.toString());
		}

		return rs;
	}
   
	/**
	 * 数据库执行修改操作
	 * @param sql
	 */
	public void Update(String sql,DatabaseConnection conn) {
		try {
			stmt = conn.getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			System.out.println(e.toString());
		}

	}
	
	/**
	 * 数据库执行修改操作
	 * @param sql
	 * @return int
	 */
	public int excuteUpdate(String sql,DatabaseConnection conn) {
		int status = 0;
		try {
			stmt = conn.getConnection().createStatement();
			if (stmt != null) {
				status = stmt.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * 数据库执行关闭操作
	 * @param sql
	 */
	public void close() {
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != stmt) {
				stmt.close();
			}
			
			if (null != conn) {
				conn.close();
			}
			
			if(BID!=null){
				BID.close();
			}
			if(session!=null&& session.isConnected()){
				session.close();
			}
			if(null!=sessionFactory){
				sessionFactory.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			System.out.println(e.toString());
		}

	}
	
	
	public static void main(String[] args) {
//		HibernateTransactionManager m =new HibernateTransactionManager();
//		try {
//			Connection connection = m.getDataSource().getConnection();
//			System.out.println(connection);
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			
//		}
		
		DatabaseConnection s = new DatabaseConnection();
		try {
			s.getTConnection();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
//		try {
//			System.out.println(s.getConnection());
//		} catch (Exception e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public BasicDataSource getBID() {
		return BID;
	}


	public void setBID(BasicDataSource bid) {
		BID = bid;
	}
	
}

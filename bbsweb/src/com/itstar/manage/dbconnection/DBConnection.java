package com.itstar.manage.dbconnection;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection  {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rst;
	private String str;

	/**
	 * 获得数据库的一个Connection连接
	 */
	private void init() {
		try {
			Class.forName(DBUser.driver);
			String url = DBUser.url;
			conn = DriverManager.getConnection(url,DBUser.username,DBUser.password);
			/*
			 * InitialContext ctx = new InitialContext(); DataSource ds =
			 * (DataSource)ctx.lookup("java:comp/env/jdbc/sql"); conn =
			 * ds.getConnection();
			 */

			/*******************************************************************
			 * 
			 * String str = "update userinfo set userPassword='"+Password+"'
			 * where userID='"+ss+"'"; 
			 * try {
			 *  DBConnection dbconnect = new DBConnection(); 
			 *  dbconnect.excuteUpdate(str);
			 * out.print("alert('修改密码成功！');"); } catch(Exception e) {
			 * e.printStackTrace(); } }
			 ******************************************************************/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *******************************************
	/**
	 * 不带参数的构造函数
	 */
	public DBConnection() {
		try {
			init(); // 获得一个数据库连接
			stmt = conn.createStatement();
			// System.out.print("数据库连接成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 带s参数的构造函数
	 */
	public DBConnection(String s) {
		try {
			init();
			pstmt = conn.prepareStatement(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批处理sql语句
	 */
	public DBConnection(StringBuffer sb) {
		try {
			init();
			stmt = conn.createStatement();
			stmt.addBatch(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *******************************************
	/**
	 * 执行数据库查询语句，s为sql语句，把查询结果赋给ResultSet
	 */
	public void excuteQuery(String s) {
		try {
			if (stmt != null) {
				rst = stmt.executeQuery(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excuteQuery() {
		try {
			if (pstmt != null) {
				rst = pstmt.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对数据库进行update操作
	 */
	public int excuteUpdate(String s) {
		int status = 0;
		try {
			if (stmt != null)
				status = stmt.executeUpdate(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	

	public int excuteUpdate() {
		int status = 0;
		try {
			if (pstmt != null) {
				status = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int[] excuteBatch() {
		int[] status = null;
		try {
			if (stmt != null) {
				status = stmt.executeBatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// *************************************************
	/**
	 * 
	 */
	public void setString(int i, String s) {
		try {
			pstmt.setString(i, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBoolean(int i, boolean flag) {
		try {
			pstmt.setBoolean(i, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setByte(int i, byte byte0) {
		try {
			pstmt.setByte(i, byte0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBytes(int i, byte abyte0[]) {
		try {
			pstmt.setBytes(i, abyte0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDate(int i, Date date) {
		try {
			pstmt.setDate(i, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTime(int i, Time time) {
		try {
			pstmt.setTime(i, time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setShort(int i, short word0) {
		try {
			pstmt.setShort(i, word0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setInt(int i, int j) {
		try {
			pstmt.setInt(i, j);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLong(int i, long l) {
		try {
			pstmt.setLong(i, l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFloat(int i, float f) {
		try {
			pstmt.setFloat(i, f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDouble(int i, double d) {
		try {
			pstmt.setDouble(i, d);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ******************************************
	/**
	 * 
	 */
	public boolean getBoolean(int i) throws Exception {
		return rst.getBoolean(i);
	}

	public boolean getBoolean(String s) throws Exception {
		return rst.getBoolean(s);
	}

	public byte getByte(int i) throws Exception {
		return rst.getByte(i);
	}

	public byte getByte(String s) throws Exception {
		return rst.getByte(s);
	}

	public byte[] getBytes(int i) throws Exception {
		return rst.getBytes(i);
	}

	public byte[] getBytes(String s) throws Exception {
		return rst.getBytes(s);
	}

	public Date getDate(int i) throws Exception {
		return rst.getDate(i);
	}

	public Date getDate(String s) throws Exception {
		return rst.getDate(s);
	}

	public Time getTime(int i) throws Exception {
		return rst.getTime(i);
	}

	public Time getTime(String s) throws Exception {
		return rst.getTime(s);
	}

	public double getDouble(int i) throws Exception {
		return rst.getDouble(i);
	}

	public double getDouble(String s) throws Exception {
		return rst.getDouble(s);
	}

	public float getFloat(int i) throws Exception {
		return rst.getFloat(i);
	}

	public float getFloat(String s) throws Exception {
		return rst.getFloat(s);
	}

	public int getInt(int i) throws Exception {
		return rst.getInt(i);
	}

	public int getInt(String s) throws Exception {
		return rst.getInt(s);
	}

	public long getLong(int i) throws Exception {
		return rst.getLong(i);
	}

	public long getLong(String s) throws Exception {
		return rst.getLong(s);
	}

	public short getShort(int i) throws Exception {
		return rst.getShort(i);
	}

	public short getShort(String s) throws Exception {
		return rst.getShort(s);
	}

	public String getString(int i) throws Exception {
		return rst.getString(i);
	}

	public String getString(String s) throws Exception {
		return rst.getString(s);
	}

	// ***************************************
	/**
	 * 
	 */
	public boolean next() {
		try {
			return rst.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (rst != null)
				rst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

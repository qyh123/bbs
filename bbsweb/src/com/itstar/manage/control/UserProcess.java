package com.itstar.manage.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.bbs.form.UserForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSUser;
import com.itstar.util.MD5;

public class UserProcess {

	private static  UserProcess us = new  UserProcess();
	private Connection conn = null;
	DatabaseConnection dbcon = null;

	/**
	 * 链接数据库
	 */
	private UserProcess() {
		dbcon = new DatabaseConnection();
		try {
			conn = dbcon.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UserProcess getInstance(){
		return us;
	}
	
    /**
     * 判断用户是否存在
     * @param userName
     * @return
     * @throws SQLException
     */
	public int findName(String userName) throws SQLException {
		String sql = "select UserName from BBSUser where Delsign = 0 and UserName='"
			+ userName + "'";
		ResultSet rs = dbcon.Query(sql,dbcon);
		if (rs.next()) {
			return 1;
		}
		dbcon.close();
		return 0;

	}

	/**
	 * 获取用户信息
	 * @param UserForm 存有用户名和密码
	 * @return
	 * @throws SQLException
	 */
	public BBSUser findLogin(UserForm form) throws SQLException {
		DatabaseConnection da = new DatabaseConnection();
		String userName = form.getUserName();
		String password = form.getUserPassword();
		//MD5加密
		MD5 md5 = MD5.getInstance();
		password = md5.strToMD5(password);
		
		BBSUser user = new BBSUser();
		try {
			conn = da.getConnection();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt .executeQuery("select * from bbsuser where Delsign = 0 and UserPassword='"
						+ password + "' and username='" + userName + "'");
		while (rs.next()) {
			user.setUserId(rs.getInt("UserId"));
			user.setUserName(rs.getString("UserName"));
			user.setUserPassword(rs.getString("UserPassword"));
			user.setUserNName(rs.getString("UserNName"));
			user.setHeadImage(rs.getString("UserImage"));
			user.setUserSex(rs.getInt("UserSex"));
			user.setUserEmail(rs.getString("UserEmail"));
			user.setUserRegDate(rs.getString("UserRegDate"));
			user.setUserClass(rs.getInt("UserClass"));
			user.setUserPoint(rs.getInt("UserPoint"));
			return user;
		}
		conn.close();
		return null;
	}
}

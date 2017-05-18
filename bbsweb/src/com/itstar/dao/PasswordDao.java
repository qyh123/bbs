package com.itstar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSPassword;
import com.itstar.util.MD5;
import com.itstar.util.Utils;

/**
 * 对密码找回表的操作
 * 
 * 
 */
public class PasswordDao {

	/**
	 * 添加密码找回信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	public void addFindPassword(int userId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		PreparedStatement stmt = null;
		Connection con = null;
		/*获得注册时间并格式化*/
		Utils utils = new Utils();
		String strFindTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		String passwordSql = "insert into BBSPassword(PasswordUserId,FindTime,Delsign)values(?,?,?)";
		try {
			con = dbcon.getConnection();
			stmt = con.prepareStatement(passwordSql);
			stmt.setInt(1, userId);
			stmt.setString(2, strFindTime);
			stmt.setInt(3, 0);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
				dbcon.close();
		}

	}

	
	/**
	 * 获取密码找回的用户信息列表
	 * 
	 * @return ArrayList
	 */
	public ArrayList<BBSPassword> getFindPassword() {
		DatabaseConnection dbcon = new DatabaseConnection();
		ResultSet rs = null;
		ArrayList<BBSPassword> list = new ArrayList<BBSPassword>();
		String sql = "select PasswordId,FindTime,UserID,UserName,UserEmail from BBSPassword as P,BBSUser as U "
				+ " where P.PasswordUserId = U.UserId and P.Delsign = 0 order by P.FindTime desc";
		try {
			rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				BBSPassword bbsp = new BBSPassword();
				bbsp.setPasswordId(rs.getInt("PasswordId"));
				bbsp.setPasswordUserId(rs.getInt("UserId"));
				bbsp.setUserName(rs.getString("UserName"));
				bbsp.setUserEmail(rs.getString("UserEmail"));
				bbsp.setFindTime(rs.getString("FindTime"));
				list.add(bbsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				dbcon.close();
		}
		return list;

	}

	/**
	 * 获取密码找回的用户信息
	 * 
	 * @return ArrayList
	 */
	public ArrayList<BBSPassword> getFindPasswordByName(String userName) {
		DatabaseConnection dbcon = new DatabaseConnection();
		ResultSet rs = null;
		ArrayList<BBSPassword> list = new ArrayList<BBSPassword>();
		String sql = "select PasswordId,FindTime,UserID,UserName,UserEmail from BBSPassword P,BBSUser U"
				+ "where P.Delsign = 0 and P.PassowrdUserId = U.UserId and U.UserName = '"
				+ userName + "'";
		try {
			rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				BBSPassword bbsp = new BBSPassword();
				bbsp.setPasswordId(rs.getInt("PasswordId"));
				bbsp.setPasswordUserId(rs.getInt("UserId"));
				bbsp.setUserName(rs.getString("UserName"));
				bbsp.setUserEmail(rs.getString("UserEmail"));
				bbsp.setFindTime(rs.getString("FindTime"));
				list.add(bbsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();	
		}
		return list;
	}
	

	/**
	 * 重设用户密码
	 * 
	 * @return void
	 */
	public void setUserPassword(String passwordId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		try {
			String password = "123456";
			String sql = "";
			// MD5加密
			MD5 md5 = MD5.getInstance();
			password = md5.strToMD5(password);
			
			if (passwordId != null &&!passwordId.equals("")) {
				String num[] = passwordId.split(",");
				for(int i = 0; i< num.length; i++) {
					 sql = "update BBSUser set UserPassword = '" + password
						+ "' where UserId In"
						+ " (select PasswordUserId from BBSPassword where PasswordId ="
						+ num[i] + ")";
				    dbcon.excuteUpdate(sql,dbcon);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			
		} finally {
				dbcon.close();
		}
	}
	
	
	/**
	 * 删除密码找回信息
	 * 
	 * @param passwordId 密码编号
	 * @return
	 */
	public void deleteFindPassword(String passwordId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		try {
			String sql = "";
			if (passwordId != null &&!passwordId.equals("")) {
				String num[] = passwordId.split(",");
				for(int i = 0; i< num.length; i++) {
					sql = "update BBSPassword set Delsign = 1 where PasswordId = "+ num[i];
					dbcon.excuteUpdate(sql,dbcon);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
				dbcon.close();
		}
	}

}

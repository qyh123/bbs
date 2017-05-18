package com.itstar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import com.itstar.manage.control.form.AddBunUserForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSBunUser;
import com.itstar.model.BBSUser;
import com.itstar.util.Utils;

/**
 * 本类是对禁止用户表的相关操作
 * 
 * 
 */
public class BunUserDao {

	Connection con = null;



	/**
	 * 插入禁止用户数据
	 * 
	 * @param addBunUserForm
	 * @return
	 */
	public boolean addBunUser(AddBunUserForm addBunUserForm) {
		Connection con = null;
		
		String sql = "";
		PreparedStatement pstat = null;
		String delpost = addBunUserForm.getDelpost();
		TopicDao tdao  = new TopicDao();
		/*
		 * 获得禁止用户的用户编号
		 */
		DatabaseConnection dbcon = new DatabaseConnection();
		UserDao udao = new UserDao();
		SectionDao sdao = new SectionDao();
		String username = addBunUserForm.getUsername();
		BBSUser buser = udao.getUser(username);
		int userId = buser.getUserId();

		/* 获得开始时间并格式化 */
		Utils utils = new Utils();
		String beginTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");

		/*
		 * 计算结束时间
		 */
		int day = Integer.parseInt(addBunUserForm.getBunOverTime())
				+ Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String overTime = beginTime.substring(0, 8) + String.valueOf(day)
				+ beginTime.substring(10);
		Date date = utils.parseDate(overTime,
				new String[] { "yyyy-MM-dd HH:mm:ss" });

		// 将date 格式化成String类型得到overTime
		overTime = utils.formatDate(date, "yyyy-MM-dd HH:mm:ss");

		// 插入操作
		try {
			con = dbcon.getConnection();
			sql = "insert into BBSBunUser(BunUserId,BunState,BeginTime,OverTime,BunReason,Delsign ) values(?,?,?,?,?,0)";
			pstat = con.prepareStatement(sql);
			pstat.setInt(1, userId);
			pstat.setString(2, addBunUserForm.getType());
			pstat.setString(3, beginTime);
			pstat.setString(4, overTime);
			pstat.setString(5, addBunUserForm.getBunReason());
			int i = pstat.executeUpdate();
			if (i == 1) {
				// 修改帖子
				if (!delpost.equals("2")) {
					String sqlTopic = " update BBSTopic set Delsign = "
							+ delpost + " where TopicUserId =" + userId;
					dbcon.excuteUpdate(sqlTopic,dbcon);

					/*修改版块内主题帖子数量开始 */

					sqlTopic = "select TopicSectionId from BBSTopic where TopicUserId ="
							+ userId;
					ResultSet rs = dbcon.Query(sqlTopic,dbcon);
					while (rs.next()) {
						sdao.editTopicCount(String.valueOf(rs.getInt(1)));
					}
					/*修改版块内主题帖子数量结束*** */
					
					// 修改回帖
					String sqlReply = " update BBSReply set Delsign = " + delpost + " where ReplyUserId =" + userId;
					dbcon.excuteUpdate(sqlReply,dbcon);

					/*修改版块内主题帖子数量开始 */

					sqlReply = "select ReplyTopicId from BBSReply Where ReplyUserId ="
							+ userId;
					rs = dbcon.Query(sqlReply,dbcon);
					while (rs.next()) {
						tdao.editReplyCount(String.valueOf(rs.getInt(1)),Integer.parseInt(delpost));
					}
					/*修改版块内主题帖子数量开始 */
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			dbcon.close();	
		}
	}

	/**
	 * 通过用户编号获得被禁止用户的禁止信息
	 * 
	 * @param userId
	 * @return
	 */
	public BBSBunUser getBunUserById(int userId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		BBSBunUser bunUser = null;
		judgeOverTimeById(userId);
		String sql = "select BunId,BunUserId,BunState,BeginTime,OverTime,BunReason from BBSBunUser where Delsign = 0 and BunUserId = "
				+ userId;
		ResultSet rs = dbcon.Query(sql,dbcon);
		try {
			while (rs.next()) {
				bunUser = new BBSBunUser();
				bunUser.setBunId(rs.getInt("BunId"));
				bunUser.setBunUserId(rs.getInt("BunUserId"));
				bunUser.setBunState(rs.getString("BunState"));
				bunUser.setBeginTime(rs.getString("BeginTime"));
				bunUser.setOverTime(rs.getString("OverTime"));
				bunUser.setBunReason(rs.getString("BunReason"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbcon.close();	
		}
		return bunUser;
	}

	/**
	 * 通过用户名获得被禁止用户的禁止信息
	 * 
	 * @param userName
	 * @return
	 */
	public BBSBunUser getBunUserByName(String userName) {
		BBSBunUser bunUser = null;
		DatabaseConnection dbcon = new DatabaseConnection();
		UserDao udao = new UserDao();
		BBSUser buser = udao.getUser(userName);
		judgeOverTimeByName(userName);
		String sql = "select BunId,BunUserId,BunState,BeginTime,OverTime,BunReason from BBSBunUser where Delsign = 0 and BunUserId = "
				+ buser.getUserId();
		ResultSet rs = dbcon.Query(sql,dbcon);
		try {
			while (rs.next()) {
				bunUser = new BBSBunUser();
				bunUser.setBunId(rs.getInt("BunId"));
				bunUser.setBunUserId(rs.getInt("BunUserId"));
				bunUser.setBunState(rs.getString("BunState"));
				bunUser.setBeginTime(rs.getString("BeginTime"));
				bunUser.setOverTime(rs.getString("OverTime"));
				bunUser.setBunReason(rs.getString("BunReason"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbcon.close();
		}
		return bunUser;
	}

	/**
	 * 修改被禁止用户的禁止信息
	 * 
	 * @param userName
	 * @return
	 */
	public int updateBunUser(AddBunUserForm form) {
		DatabaseConnection dbcon = new DatabaseConnection();
		UserDao udao = new UserDao();
		SectionDao sdao = new SectionDao();
		TopicDao tdao  = new TopicDao();
		String userName = form.getUsername();
		BBSUser buser = udao.getUser(userName);
		
		String delpost = form.getDelpost();
		int i = 0;

		/* 获得开始时间并格式化 */
		Utils utils = new Utils();
		String beginTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");

		/*
		 * 计算结束时间
		 */
		int day = Integer.parseInt(form.getBunOverTime())
				+ Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String overTime = beginTime.substring(0, 8) + String.valueOf(day)
				+ beginTime.substring(10);
		Date date = utils.parseDate(overTime,
				new String[] { "yyyy-MM-dd HH:mm:ss" });

		// 将date 格式化成String类型得到overTime
		overTime = utils.formatDate(date, "yyyy-MM-dd HH:mm:ss");

		String sql = "update BBSBunUser set BunState = '" + form.getType()
				+ "',BeginTime = '" + beginTime + "',OverTime = '" + overTime
				+ "',BunReason='" + form.getBunReason()
				+ "'  where Delsign = 0 and BunUserId =  " + buser.getUserId();
		i = dbcon.excuteUpdate(sql,dbcon);
		try {
			if (i == 1) {
				// 修改帖子
				if (!delpost.equals("2")) {
					String sqlTopic = " update BBSTopic set Delsign = "
							+ delpost + " where TopicUserId =" + buser.getUserId();
					dbcon.excuteUpdate(sqlTopic,dbcon);

					/*修改版块内主题帖子数量开始 */

					sqlTopic = "select TopicSectionId from BBSTopic where TopicUserId ="
							+ buser.getUserId();
					ResultSet rs = dbcon.Query(sqlTopic,dbcon);
					while (rs.next()) {
						sdao.editTopicCount(String.valueOf(rs.getInt(1)));
					}
					/*修改版块内主题帖子数量结束*** */
					
					// 修改回帖
					String sqlReply = " update BBSReply set Delsign = " + delpost + " where ReplyUserId =" + buser.getUserId();
					dbcon.excuteUpdate(sqlReply,dbcon);

					/*修改主题帖子回帖数量开始 */

					sqlReply = "select ReplyTopicId from BBSReply where ReplyUserId ="
							+ buser.getUserId();
					rs = dbcon.Query(sqlReply,dbcon);
					while (rs.next()) {
						tdao.editReplyCount(String.valueOf(rs.getInt(1)),Integer.parseInt(delpost));
					}
					/*修改版块内主题帖子数量开始 */
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			i = 0;
		} catch (SQLException e) {
			e.printStackTrace();
			i = 0;
		} finally {
			dbcon.close();	
		}
    return i;
	}

	/**
	 * 通过用户编号查看用户的禁止状态是否过期 过期则将其变为正常状态
	 * 
	 * @param userId
	 * @return
	 */
	private void judgeOverTimeById(int userId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		Calendar now = Calendar.getInstance();
		Utils utils = new Utils();
		String sql = "select OverTime from BBSBunUser where Delsign = 0 and BunUserId = "
				+ userId;
		ResultSet rs = dbcon.Query(sql,dbcon);
		try {
			while (rs.next()) {
				String overTime = rs.getString("OverTime");
				// 将overTime 格式化成Date 类型
				Date date = utils.parseDate(overTime,
						new String[] { "yyyy-MM-dd HH:mm:ss" });
				if (now.getTimeInMillis() >= date.getTime()) {
					String sqlUpdate = "update BBSBunUser set BunState = 1 where Delsign = 0 and BunUserId = "
							+ userId;
					dbcon.Update(sqlUpdate,dbcon);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbcon.close();	
		}

	}

	/**
	 * 通过用户姓名查看用户的禁止状态是否过期 过期则将其变为正常状态
	 * 
	 * @param userId
	 * @return
	 */
	private void judgeOverTimeByName(String userName) {
		Calendar now = Calendar.getInstance();
		DatabaseConnection dbcon = new DatabaseConnection();
		UserDao udao = new UserDao();
		Utils utils = new Utils();
		BBSUser buser = udao.getUser(userName);
		String sql = "select OverTime from BBSBunUser where Delsign = 0 and BunUserId = "
				+ buser.getUserId();
		ResultSet rs = dbcon.Query(sql,dbcon);
		try {
			while (rs.next()) {
				String overTime = rs.getString("OverTime");
				// 将overTime 格式化成Date 类型
				Date date = utils.parseDate(overTime,
						new String[] { "yyyy-MM-dd HH:mm:ss" });
				if (now.getTimeInMillis() >= date.getTime()) {
					String sqlUpdate = "update BBSBunUser set BunState = '1' where Delsign = 0 and BunUserId = "
							+ buser.getUserId();
					dbcon.Update(sqlUpdate,dbcon);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.close();	
		}
	}
}

package com.itstar.dao;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.itstar.bbs.form.EdituserForm;
import com.itstar.bbs.form.UserForm;
import com.itstar.manage.control.form.TopicInfoForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSBunUser;
import com.itstar.model.BBSTopic;
import com.itstar.model.BBSUser;
import com.itstar.util.DeleteFile;
import com.itstar.util.MD5;
import com.itstar.util.Utils;

public class UserDao {
	Connection con = null;

	/**
	 * 获取楼主信息
	 * 
	 * @param username
	 * @return
	 */
	public BBSUser getUser(String username) {
		BBSUser userForm = new BBSUser();
		BBSBunUser bunUser = null;
		DatabaseConnection dconn = new DatabaseConnection();
		BunUserDao bunDao = new BunUserDao();
		try {

			String sql = "SELECT UserId,UserPassword,UserNName,UserImage,UserEmail,UserRegDate,UserClass,UserPoint FROM BBSUser WHERE UserName='"
					+ username + "'";

			ResultSet rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				userForm.setUserId(rs.getInt("UserId"));
				userForm.setUserName(username);
				bunUser = bunDao.getBunUserById(userForm.getUserId());
				if(bunUser==null) {
					userForm.setBunState(1);
				} else {
					userForm.setBunState(Integer.parseInt(bunUser.getBunState()));
				}
				userForm.setUserPassword(rs.getString("UserPassword"));
				userForm.setUserNName(rs.getString("UserNName"));
				userForm.setHeadImage(rs.getString("UserImage"));
				userForm.setUserEmail(rs.getString("UserEmail"));
				userForm.setUserRegDate(rs.getString("UserRegDate"));
				userForm.setUserClass(rs.getInt("UserClass"));
				if (userForm.getUserClass() == 1) {
					userForm.setUserClassName("普通用户");
				} else if (userForm.getUserClass() == 2) {
					userForm.setUserClassName("版主");
				} else {
					userForm.setUserClassName("管理员");
				}
				userForm.setUserPoint(rs.getInt("UserPoint"));
			}
			return userForm;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * 获取楼主信息ID
	 * 
	 * @param username
	 * @return BBSUser
	 */
	public BBSUser getUserInfo(int id) {
		DatabaseConnection dconn = new DatabaseConnection();
		BBSUser userForm = new BBSUser();
		try {

			String sql = "SELECT UserId,UserName,UserPassword,UserNName,UserImage,UserSex,UserEmail,UserRegDate,UserClass,UserPoint FROM BBSUser WHERE UserId='"
					+ id + "'";

			ResultSet rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				userForm.setUserId(rs.getInt("UserId"));
				userForm.setUserName(rs.getString("UserName"));
				userForm.setUserPassword(rs.getString("UserPassword"));
				userForm.setUserNName(rs.getString("UserNName"));
				userForm.setHeadImage(rs.getString("UserImage"));
				String userSexName = null;
				if (rs.getInt("UserSex") == 0) {
					userSexName = "男";
				} else {
					userSexName = "女";
				}
				userForm.setUserSexName(userSexName);
				userForm.setUserEmail(rs.getString("UserEmail"));
				userForm.setUserRegDate(rs.getString("UserRegDate"));
				String UserClass = null;
				if (rs.getInt("UserClass") == 0) {
					UserClass = "普通用户";
				} else {
					UserClass = "版主";
				}
				userForm.setUserClassName(UserClass);
				userForm.setUserPoint(rs.getInt("UserPoint"));
			}
			return userForm;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * 获取用户MAP
	 * 
	 * @return map
	 */
	public Map getUserMap() {
		DatabaseConnection dconn = new DatabaseConnection();
		Map map = new HashMap();
		try {

			String sql = "SELECT UserId,UserName FROM BBSUser";
			ResultSet rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				map.put(rs.getInt("UserId"), rs.getString("UserName"));
			}
			map.put(0, "暂无版主");
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * 获取级别为版主的用户信息列表
	 * 
	 * @return List<BBSUser>
	 */
	public List<BBSUser> getUserList() {
		DatabaseConnection dbcon = new DatabaseConnection();
		List<BBSUser> list = new ArrayList<BBSUser>();
		BBSUser user = null;
		try {

			String sql = "SELECT UserId,UserName FROM BBSUser where UserClass != 1 and Delsign = '0'";
			ResultSet rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				user = new BBSUser();
				user.setUserId(rs.getInt("UserId"));
				user.setUserName(rs.getString("UserName"));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return null;
	}

	/**
	 * 获取数据条数
	 * 
	 * @param request
	 * @return int
	 */
	public int getAllRecorders(HttpServletRequest request) {
		DatabaseConnection dbcon = new DatabaseConnection();
		int allRecorders = 0;
		String sql = null;
		String extend1 = null;

		String title = request.getParameter("title");

		ResultSet rs = null;
		if (title != null && !"".equals(title)) {
			extend1 = " and UserName LIKE '%" + title + "%'";
		} else {
			extend1 = "";
		}

		try {

			sql = "SELECT COUNT(UserId) from BBSUser where Delsign = '0' "
					+ extend1;

			rs = dbcon.Query(sql,dbcon);
			if (rs.next()) {
				allRecorders = rs.getInt(1);
			}
			return allRecorders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return 0;
	}

	/**
	 * 获取所有的用户信息列表
	 * 
	 * @return List<BBSUser>
	 */
	public ArrayList<BBSUser> getUsers() {
		ArrayList<BBSUser> list = new ArrayList<BBSUser>();
		DatabaseConnection dbcon = new DatabaseConnection();
		BunUserDao bundao = new BunUserDao();
		BBSUser bUser = null;
		String sql = null;
		try {

			sql = "SELECT UserId,UserName,UserPassword,UserNName,UserImage,UserSex,UserEmail,UserRegDate,UserClass,UserPoint FROM BBSUser where  Delsign = 0 ";
			ResultSet rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				bUser = new BBSUser();
				bUser.setUserId(rs.getInt(1));
				//查看用户的状态并set
				BBSBunUser bunUser = bundao.getBunUserById(bUser.getUserId());
				if(bunUser == null) {
					bUser.setBunState(1);	
				}else {
					bUser.setBunState(Integer.parseInt(bunUser.getBunState()));
				}
				//查看状态结束
				bUser.setUserName(rs.getString(2));
				bUser.setUserPassword(rs.getString(3));
				bUser.setUserNName(rs.getString(4));
				bUser.setHeadImage(rs.getString("UserImage"));
				bUser.setUserSex(rs.getInt(6));

				String sex = rs.getString(6);
				if (sex.equals("1")) {
					sex = "男";
				} else {
					sex = "女";
				}
				bUser.setUserSexName(sex);
				bUser.setUserEmail(rs.getString(7));
				bUser.setUserRegDate(rs.getString(8));
				bUser.setUserClass(rs.getInt(9));
				bUser.setUserPoint(rs.getInt(10));
				String userclass = rs.getString(9);

				if (userclass.equals("1")) {
					userclass = "普通用户";
				} else if (userclass.equals("2")) {
					userclass = "版主";
				}
				bUser.setUserClassName(userclass);
				list.add(bUser);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return null;
	}

	/**
	 * 获取分页用户列表
	 * 
	 * @param request
	 * @param currentPage
	 * @return List<BBSUser>
	 * @throws UnsupportedEncodingException
	 */
	public List<BBSUser> getArray(HttpServletRequest request) {
		ArrayList<BBSUser> array = new ArrayList<BBSUser>();
		DatabaseConnection dbcon = new DatabaseConnection();
		BunUserDao bundao = new BunUserDao();
		ResultSet rs = null;
		String sql = null;
		String extend1 = null;

		String title = request.getParameter("title");

		if (title != null && !"".equals(title)) {
			extend1 = " and UserName LIKE '%" + title + "%'";
		} else {
			extend1 = "";
		}

		try {
             /***
              *  baiyp
              *  原因:sql语句From和字段连上了，没有空格
              *  date:2010-03-02 09:55
              * */
			sql = "SELECT UserId,UserName,UserPassword,UserNName,UserSex,UserEmail,UserRegDate,UserClass,UserPoint, UserImage FROM BBSUser where  Delsign = 0 "
					+ extend1;
			rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				BBSUser bUser = new BBSUser();
				bUser.setUserId(rs.getInt(1));
				//查看用户的状态并set
				BBSBunUser bunUser = bundao.getBunUserById(bUser.getUserId());
				if(bunUser == null) {
					bUser.setBunState(1);	
				}else {
					bUser.setBunState(Integer.parseInt(bunUser.getBunState()));
				}
				//查看状态结束
				bUser.setUserName(rs.getString(2));
				bUser.setUserPassword(rs.getString(3));
				bUser.setUserNName(rs.getString(4));
				bUser.setHeadImage(rs.getString("UserImage"));
				bUser.setUserSex(rs.getInt(5));

				String sex = rs.getString(5);
				if (sex.equals("1")) {
					sex = "男";
				} else {
					sex = "女";
				}
				bUser.setUserSexName(sex);
				bUser.setUserEmail(rs.getString(6));
				bUser.setUserRegDate(rs.getString(7));
				bUser.setUserClass(rs.getInt(8));
				bUser.setUserPoint(rs.getInt(9));
				String userclass = rs.getString(8);

				if (userclass.equals("1")) {
					userclass = "普通用户";
				} else if (userclass.equals("2")) {
					userclass = "版主";
				} else {
					userclass = "管理员";
				}
				bUser.setUserClassName(userclass);
				array.add(bUser);
			}
			return array;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return null;
	}

	/**
	 * 添加用户
	 * 
	 * @param username
	 * @param password
	 * @param userNName
	 *            昵称
	 * @param sex
	 * @param email
	 * @param filename
	 *            头像
	 */
	public boolean addUser(UserForm form) {
		DatabaseConnection dbcon = new DatabaseConnection();
		PreparedStatement pstmt = null;
		String password = form.getUserPassword();
		// md5加密
		MD5 md5 = MD5.getInstance();
		password = md5.strToMD5(password);
		
		/*获得注册时间并格式化*/
		Utils utils = new Utils();
		String strRegTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		//String strRegTime = "2010-02-03 09:10:20" ;
		System.out.println("strRegTime:" + strRegTime);
		
		form.setUserRegDate(strRegTime);
		
		// 添加新用户
		try {
			con = dbcon.getConnection();
			//FileInputStream str = new FileInputStream(form.getFileName());
			
			String sql = "insert into BBSUser(UserName,UserPassword,UserNName,UserImage,UserSex,UserEmail,UserRegDate,UserClass,Delsign,UserPoint) values(?,?,?,?,?,?,?,?,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, form.getUserName());
			pstmt.setString(2, password);
			pstmt.setString(3, form.getUserNName());
			//pstmt.setBinaryStream(4, str, str.available());
			pstmt.setString(4, form.getFileName());
			pstmt.setString(5, form.getUserSex());
			pstmt.setString(6, form.getUserEmail());
			pstmt.setString(7, form.getUserRegDate());
			pstmt.setInt(8, form.getUserClass());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pstmt.close();
				con.close();
				dbcon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param EdituserForm
	 */
	public int updateUser(EdituserForm form,String url) {
		String sql = "";
		int i = 0;
		ResultSet rs = null;
		DeleteFile del = new DeleteFile();
		String newPassword = form.getNewPassword();
		String imageUrl = form.getImage().getFileName();
		PreparedStatement pstmt = null;
		FileInputStream str = null;
		// md5加密
		MD5 md5 = MD5.getInstance();
		newPassword = md5.strToMD5(newPassword);
		DatabaseConnection dbcon = new DatabaseConnection();
		try {
			con = dbcon.getConnection();
			if (imageUrl.equals("") || imageUrl == null) {
				sql = "update BBSUser set UserPassword = ? ,UserEmail = ? where UserName =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newPassword);
				pstmt.setString(2, form.getEmail());
				pstmt.setString(3, form.getUserName());

			} else {
				//str = new FileInputStream(form.getFileName());
				sql = "select UserImage from BBSUser where UserName = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, form.getUserName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					imageUrl = rs.getString(1);
				}
				sql = "update BBSUser set UserPassword = ? ,UserEmail = ?, UserImage=?  where UserName =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newPassword);
				pstmt.setString(2, form.getEmail());
				//pstmt.setBinaryStream(3, str, str.available());
				pstmt.setString(3, form.getFileName());
				pstmt.setString(4, form.getUserName());
			}
			i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
				dbcon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return i;
	}

	/**
	 * 删除用戶
	 * 
	 * @param userId
	 * @param isDelTopic是否删除作者发表的帖子及其发表的回复
	 */
	public void deleteUser(String userId, boolean isDelTopic) {
		String sql = "";
		BBSUser user = null;
		TopicInfoForm topicForm = new TopicInfoForm();
		DatabaseConnection dbcon = new DatabaseConnection();
		List<BBSTopic> list = null;
		try {

			if (userId != null && !userId.equals("")) {
				sql = "update BBSUser set Delsign = 1 where UserId  in ("
						+ userId + ")";
				dbcon.Update(sql,dbcon);// 执行删除
			}
			if (isDelTopic) {
				TopicDao tdao  = new TopicDao();
			
				ReplyDao rdao = new ReplyDao();
				user = getUserInfo(Integer.parseInt(userId));
				topicForm.setUsername(user.getUserName());
				list = tdao.getArray(topicForm);
				for (int i = 0; i < list.size(); i++) {
					BBSTopic topic = list.get(i);
					tdao.deleteTopic(String.valueOf(topic.getTopicId()));
					rdao.deleteReplyByTopicId(topic.getTopicId());
					rdao.deleteReplyByUserId(Integer.parseInt(userId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 设置版主
	 * 
	 * @param id
	 */
	public void setUser(String id) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		SectionDao sdao = new SectionDao();
		try {
			if (id != null) {
				String mun[] = id.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select UserClass from BBSUser where UserId="
							+ mun[i];
					rs = dbcon.Query(sql1,dbcon);
					if (rs.next()) {
						if (rs.getString("UserClass").equals("2")
								|| rs.getString("UserClass") == "2") {
							sql2 = "update BBSUser set UserClass = 1 where UserId = "
									+ mun[i];
							dbcon.Update(sql2,dbcon);// 执行删除
							sdao.editSectionMaster("0", mun[i]);
							
						} else {
							sql2 = "update BBSUser set UserClass = 2 where UserId = "
									+ mun[i];
							dbcon.Update(sql2,dbcon);// 执行删除
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 设置管理员
	 * 
	 * @param id
	 */
	public void setManage(String id) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		SectionDao sdao = new SectionDao();
		try {
			if (id != null) {
				String mun[] = id.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select UserClass from BBSUser where UserId="
							+ mun[i];
					rs = dbcon.Query(sql1,dbcon);
					if (rs.next()) {
						
						if (!rs.getString("UserClass").equals("3")) {
							sql2 = "update BBSUser set UserClass = 3 where UserId = "
									+ mun[i];
							dbcon.Update(sql2,dbcon);// 执行删除
						} else {
							sql2 = "update BBSUser set UserClass = 1 where UserId = "
									+ mun[i];
							dbcon.Update(sql2,dbcon);// 执行删除
							sdao.editSectionMaster("0", mun[i]);
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 找回密码
	 * 
	 * @param form
	 *            用户对象
	 * @return 密码
	 * @throws SQLException
	 */
	public boolean findPassword(UserForm form) throws SQLException {
		DatabaseConnection dbcon = new DatabaseConnection();
		boolean bl = false;
		try {
			String sql = "select UserPassword from bbsuser where Delsign = 0 and UserEmail='"
					+ form.getUserEmail()
					+ "' and UserName = '"
					+ form.getUserName() + "'";

			ResultSet rs = dbcon.Query(sql,dbcon);
			if (rs.next()) {
				bl = true;
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return bl;
	}

}

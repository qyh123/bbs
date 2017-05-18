package com.itstar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.itstar.bbs.form.EditPostsForm;
import com.itstar.bbs.form.ReplyForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSReply;
import com.itstar.util.Utils;

public class ReplyDao {


	/**
	 * 添加回复
	 * 
	 * @param userName
	 * @param id
	 * @param str
	 */
	public void addReply(EditPostsForm editPostsForm) {
		int userid = 0;
		Utils utils = new Utils();
		
		String topicId = editPostsForm.getTopicid(); // 文章的编号
		String author = editPostsForm.getReplyAuthor(); // 作者的名字
		String str = editPostsForm.getTopicContent();// 回帖的内容
		
		//特殊字符处理
		str = utils.replaceInsertString(str); 
		DatabaseConnection dbcon = new DatabaseConnection();
		/*获得注册时间并格式化*/
		
		String strReplyTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss:www");
		try {
			String sql1 = "SELECT UserId FROM BBSUser WHERE UserName='"
					+ author + "'";

			ResultSet rs = dbcon.Query(sql1,dbcon);

			while (rs.next()) {
				userid = rs.getInt("UserId");

			}
			String sql2 = "INSERT INTO BBSReply (ReplyTopicId,ReplyUserId,ReplyContent,ReplyTime,Delsign) values('"
					+ topicId + "'," + userid + ",'" + str + "','" + strReplyTime + "','0')";
			String sql3 = "UPDATE BBSTopic SET TopicReplyCount=TopicReplyCount+1 WHERE TopicId='"
					+ topicId + "'";
			String sql4 = "UPDATE BBSUser SET UserPoint = UserPoint+1 WHERE UserId="
					+ userid;
			dbcon.Update(sql2,dbcon);
			dbcon.Update(sql3,dbcon);
			dbcon.Update(sql4,dbcon);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				dbcon.close();
		}
	}

	/**
	 * 修改回复内容
	 * 
	 * @param id
	 */
	public void editReply(EditPostsForm form) {
		String sql = "";
		Utils utils = new Utils();
		String topicContent = form.getTopicContent();
		DatabaseConnection dbcon = new DatabaseConnection();
		//特殊字符处理
		topicContent = utils.replaceInsertString(topicContent);
		
		try {

			if (form.getTopicid() != null && !form.getTopicid().equals("")) {
				sql = "update BBSReply set ReplyContent =' " + topicContent
						+ " 'where ReplyID  =" + form.getReplyid();
				dbcon.Update(sql,dbcon);// 执行删除
			}


		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbcon.close();
		}
	}

	/**
	 * 修改回复屏蔽状态
	 * 
	 * @param id
	 */
	public int bunReply(String replyId) {
		String sql = "";
		ResultSet rs = null;
		int replyState = 0;
		DatabaseConnection dbcon = new DatabaseConnection();
		try {

			String selectSql = "select ReplyState from BBSReply where ReplyID  ="
					+ replyId;
			rs = dbcon.Query(selectSql,dbcon);
			rs.next();
			if (rs.getInt("ReplyState") == 1) {
				sql = "update BBSReply set ReplyState = 0 where ReplyID  ="
						+ replyId;
			} else {
				sql = "update BBSReply set ReplyState = 1 where ReplyID  ="
						+ replyId;
			}
			dbcon.Update(sql,dbcon);// 执行删除
			rs = dbcon.Query(selectSql,dbcon);
			rs.next();
			replyState = rs.getInt("ReplyState");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return replyState;
	}

	/**
	 * 取出回帖内容
	 * 
	 * @param 回帖id
	 * @return BBSReply
	 */
	public BBSReply getReply(String id) {
		String sql = "";
		ResultSet rs = null;
		BBSReply reply = null;
		String replyContent = "";
		DatabaseConnection dbcon = new DatabaseConnection();
		Utils utils = new Utils();
		try {

			if (id != null && !id.equals("")) {
				sql = "select ReplyContent,ReplyTime from BBSReply where ReplyID  ="
						+ id;
				rs = dbcon.Query(sql,dbcon);// 执行查询
				while (rs.next()) {
					reply = new BBSReply();
					reply.setReplyID(Integer.parseInt(id));
					replyContent = utils.replaceString(rs.getString("ReplyContent"));
					reply.setReplyContent(replyContent);
					reply.setReplyTime(rs.getString("ReplyTime"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return reply;
	}

	/**
	 * 删除/恢复回贴
	 * 
	 * @param ReplyForm
	 */
	public void editReplyDelSign(ReplyForm form) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql = "update BBSReply set Delsign = 1 ";
		String userId = form.getRepleyUserId();
		String topicId = form.getRepleyTopicId();
		String replyId = form.getRepleyId();
		if (userId != null && !userId.equals("")) {
			sql += "where ReplyUserId = " + userId;
		}
		if (topicId != null && !topicId.equals("")) {
			sql += "where ReplyTopicId = " + topicId;
		}
		if (replyId != null && !replyId.equals("")) {
			sql += "where ReplyID = " + replyId;
		}
		try {
			dbcon.Update(sql,dbcon);// 执行删除
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 删除回贴通过回帖编号
	 * 
	 * @param replyId
	 */
	public void deleteReplyById(int replyId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sqlReply = "";
		TopicDao dao  = new TopicDao();
		try {
			sqlReply = "update BBSReply set Delsign = 1 where ReplyID ="
					+ replyId;
			dao.editReplyCount(String.valueOf(replyId),0);
			dbcon.Update(sqlReply,dbcon);// 执行删除

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 删除回贴通过主题贴编号
	 * 
	 * @param topicId
	 */
	public void deleteReplyByTopicId(int topicId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql = "";
		TopicDao dao  = new TopicDao();
		try {
			sql = "update BBSReply set Delsign = 1 where ReplyTopicId ="
					+ topicId;
			dbcon.Update(sql,dbcon);// 执行删除
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * 删除回贴通过作者编号
	 * 
	 * @param userId
	 */
	public void deleteReplyByUserId(int userId) {
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql = "";
		try {
			sql = "update BBSReply set Delsign = 1 where ReplyUserId ="
					+ userId;
			dbcon.Update(sql,dbcon);// 执行删除
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

}

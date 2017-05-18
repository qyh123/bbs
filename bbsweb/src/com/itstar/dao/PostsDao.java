package com.itstar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.itstar.manage.control.form.DeletePostsForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSPosts;
import com.itstar.util.words.WordsFilter;
import com.itstar.util.Utils;

/**
 * 同时对主题帖子及回复帖子操作的类
 *
 */
public class PostsDao {
	ResultSet rs = null;
	/**
	 * 获取所有主题及回帖的信息
	 * 
	 * @param content
	 *            帖子内容
	 * @param author
	 *            帖子作者
	 * @param section
	 *            帖子所在的版块
	 * @param currentPage
	 *            当前页
	 * @param lineSize
	 *            每页显示的数量
	 * @return
	 */
	public ArrayList<BBSPosts> getPostsArray(DeletePostsForm deletePostsForm) {
		DatabaseConnection dbcon = new DatabaseConnection();
		UserDao userDao = new UserDao();
		Map usermap = userDao.getUserMap();
		SectionDao sdao = new SectionDao();
		Map sectionmap = sdao.getSectionMap();
		ArrayList<BBSPosts> list = new ArrayList<BBSPosts>();
		TopicDao tdao  = new TopicDao();
		Utils utils = new Utils();
		ResultSet replyrs = null;
		String topicId = "";
		String str = "";
		String sqlTopic = null;
		String sqlReply = null;
		String extend1 = null;
		String extend2 = null;
		String extend3 = null;
		String topicAuthor = "";
		// 获取form 属性值
		String content = deletePostsForm.getPostsContent();
		String author = deletePostsForm.getAuthor();
		String section = deletePostsForm.getSection();
		try {
			if (content != null && !content.equals("")) {
				extend1 = " and TopicContent LIKE '%" + content + "%'";
			} else {
				extend1 = "";
			}
			
			UserDao userDao1 = new UserDao();
			if (author != null && !author.equals("")) {
				extend2 = " and TopicUserId  = "
						+ userDao1.getUser(author).getUserId();
			} else {
				extend2 = "";
			}
			SectionDao sdao1 = new SectionDao();
			if (section != null && !section.equals("")) {
				extend3 = " and TopicSectionId = "
						+ sdao1.getSection(section)
								.getSectionId();
			} else {
				extend3 = "";
			}
			sqlTopic = "select TopicId,TopicSectionId,TopicUserId,TopicContent,TopicTime from BBSTopic where Delsign = 0 "
					+ extend1 + extend2 + extend3;
			if (content != null && !content.equals("")) {
				extend1 = " and ReplyContent LIKE '%" + content + "%'";
			} else {
				extend1 = "";
			}
			
			UserDao userDao3 = new UserDao();
			if (author != null && !author.equals("")) {
				extend2 = " and ReplyUserId  = "
						+ userDao3.getUser(author).getUserId();
			} else {
				extend2 = "";
			}
			SectionDao sdao2 = new SectionDao();
			if (section != null && !section.equals("")) {
				extend3 = " and ReplyTopicId = TopicId and TopicSectionId = "
						+ sdao2.getSection(section)
								.getSectionId();
			} else {
				extend3 = "";
			}
			/*
			 * sqlReply = "select COUNT(ReplyID) from BBSReply,BBSTopic where
			 * ReplyTopicId = TopicId and BBSReply.Delsign = 0 " + extend1 +
			 * extend2 + extend3; replyrs = dbcon.Query(sqlReply);
			 * if(replyrs.next()){ replycorder = replyrs.getInt(1); }
			 */
			sqlReply = "select ReplyID,ReplyTopicId,ReplyUserId,ReplyContent,ReplyTime,TopicSectionId from BBSReply,BBSTopic where ReplyTopicId = TopicId and BBSReply.Delsign = 0 "
					+ extend1 + extend2 + extend3;
			rs = dbcon.Query(sqlTopic,dbcon);
			replyrs = dbcon.Query(sqlReply,dbcon);
			
			/**
			 * 获取主题帖子
			 */ 
			while (rs.next()) {
				WordsFilter wf = new WordsFilter();
				BBSPosts posts = new BBSPosts();
				posts.setPostsId(rs.getInt("TopicId"));
				posts.setSectionName((String) sectionmap.get(rs
						.getInt("TopicSectionId")));
				posts.setUserName((String) usermap
						.get(rs.getInt("TopicUserId")));
				str = rs.getString("TopicContent");
				if (str.length() > 30) {
					str = str.substring(0, 30) + "...";
				}
				str = utils.replaceInsertString(str);
				posts.setPostsContent(str);
				posts.setTopicId(posts.getPostsId());
				posts.setPostSign(1);
				posts.setPostsTime(rs.getString("TopicTime").toString()
						.substring(0, 19));
				posts.setAuthor(posts.getUserName());
				list.add(posts);
			}

			/**
			 * 获取回复帖子
			 */ 
			while (replyrs.next()) {
				BBSPosts posts = new BBSPosts();
				posts.setPostsId(replyrs.getInt("ReplyID"));
				posts.setTopicId(replyrs.getInt("ReplyTopicId"));
				posts.setPostSign(0);
				posts.setUserName((String) usermap.get(replyrs
						.getInt("ReplyUserId")));
				str = replyrs.getString("ReplyContent");
				if (str.length() > 30) {
					str = str.substring(0, 30) + "...";
				}
				str = utils.replaceInsertString(str);
				posts.setPostsContent(str);
				posts.setPostsTime(replyrs.getString("ReplyTime").toString()
						.substring(0, 19));
				posts.setSectionName((String) sectionmap.get(replyrs
						.getInt("TopicSectionId")));
				topicId = String.valueOf(posts.getTopicId());
				topicAuthor = tdao.getBBSTopic(topicId).getUserName();
				posts.setAuthor(topicAuthor);
				list.add(posts);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return list;
	}

	/**
	 * 删除帖子
	 */
	public void deletePosts(String id, ArrayList<BBSPosts> list) {
		String sql = "";
		DatabaseConnection dbcon = new DatabaseConnection();
		try {

			if (id != null && !id.equals("")) {
				BBSPosts posts = (BBSPosts) list.get(Integer.parseInt(id));
				if (posts.getPostsSign() == 1) {
					sql = "update BBSTopic set Delsign = 1 where TopicId ="
							+ posts.getPostsId();
					TopicDao tdao  = new TopicDao();
					tdao.deleteTopic(id);
					dbcon.Update(sql,dbcon);// 执行删除
				} else {
					sql = "update BBSReply set Delsign = 1 where ReplyID ="
							+ posts.getPostsId();
					TopicDao tdao  = new TopicDao();
					tdao.editReplyCount(
							posts.getPostsId().toString(),0);
					dbcon.Update(sql,dbcon);// 执行删除
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}
}

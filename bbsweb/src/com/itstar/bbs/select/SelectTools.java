/**
 * 
 */
package com.itstar.bbs.select;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itstar.bbs.form.SelectTopicForm;
import com.itstar.bbs.form.SelectUserForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSUser;
import com.itstar.model.BBSTopic;

/**
 * 
 */
public class SelectTools {
	private DatabaseConnection dbcon = null;
	public static int allRecoder = 0;

	/**
	 * 构造方法链接数据库
	 */
	public SelectTools() {
		dbcon = new DatabaseConnection();

	}

	/**
	 * 进行帖子搜索处理，根据不同的搜索方式查询数据库，得到结果集
	 * 
	 * @param sectionSearch

	 * @return
	 */
	public ArrayList<BBSTopic> judgementNote(SelectTopicForm topicForm) {
		String sql = null;
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		String topicTime = "";
		
		 //获取form 属性值
		String sectionSearch = topicForm.getSectionSearch();
		String note = topicForm.getNote();
		/*
		 * 帖子内容搜索
		 */
		if (note.equals("TopicContent")) {
			sql = "select BBSTopic.TopicId, BBSSection.SectionId, BBSSection.SectionName, BBSUser.UserName , BBSTopic.TopicTopic,BBSTopic.TopicContent, BBSTopic.TopicTime "
					+ "from BBSTopic,   BBSSection, BBSUser "
					+ "where   BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSTopic.TopicContent like '%"
					+ sectionSearch
					+ "%'"
					+ "order  by BBSTopic.TopicTime DESC ";

		}
		/*
		 * 帖子标题搜索
		 */
		if (note.equals("TopicTopic")) {
			sql = "select  BBSTopic.TopicId, BBSSection.SectionId, BBSSection.SectionName, BBSUser.UserName , BBSTopic.TopicTopic,BBSTopic.TopicContent,BBSTopic.TopicTime "
					+ "from BBSTopic,   BBSSection, BBSUser "
					+ "where   BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSTopic.TopicTopic like '%"
					+ sectionSearch
					+ "%'"
					+ "order  by BBSTopic.TopicTime DESC";

		}
		/*
		 * 帖子作者搜索
		 */
		if (note.equals("TopicAuthor")) {
			sql = "select  BBSTopic.TopicId, BBSSection.SectionId, BBSSection.SectionName, BBSUser.UserName , BBSTopic.TopicTopic,BBSTopic.TopicContent,  BBSTopic.TopicTime "
					+ "from BBSTopic,   BBSSection, BBSUser "
					+ "where   BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSUser.UserName ='"
					+ sectionSearch + "'" + "order  by BBSTopic.TopicTime DESC";

		}

		try {
			ResultSet re = dbcon.Query(sql,dbcon);

			while (re.next()) {
				BBSTopic btopic = new BBSTopic();
				btopic.setTopicId(re.getInt("TopicId"));
				btopic.setTopicSectionId(re.getInt("SectionId"));
				btopic.setSectionName(re.getString("SectionName"));
				btopic.setUserName(re.getString("UserName"));
				btopic.setTopicTopic(re.getString("TopicTopic"));
				btopic.setTopicContent(re.getString("TopicContent"));
				topicTime = re.getString("TopicTime").substring(0,16);
				btopic.setTopicTime(topicTime);
				list.add(btopic);// 将bean中所设置的对象装入ArrayList当中
			}
			re.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 進行會員搜索和結果的判斷！
	 * 
	 * @param SelectUserForm
	 * @return
	 */
	public List<BBSUser> judgementUser(SelectUserForm userform) {
		String sql = null;
		List<BBSUser> list2 = new ArrayList<BBSUser>();
		// 获得form值
		String userSearch = userform.getUserSearch();
		String user = userform.getUser();
		/*
		 * 会员搜索
		 */
		if (user.equals("UserName")) {

			sql = "select UserId,UserName,UserNName,UserImage,UserClass,UserPoint  from   BBSUser where  UserName like '%"
					+ userSearch + "%'";

		}
		/*
		 * 会员Id搜索
		 */
		if (user.equals("UserId")) {
			sql = "select UserId,UserName,UserNName,UserImage,UserClass,UserPoint  from   BBSUser  where  UserId='"
					+ userSearch + "'";

		}

		try {
			ResultSet rs = dbcon.Query(sql,dbcon);
			/*
			 * for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
			 * rs.next(); } int i = 0; for (int x = 0; x < lineSize; x++) {
			 */

			while (rs.next()) {
				BBSUser buser = new BBSUser();
				buser.setUserId(rs.getInt("UserId"));
				buser.setUserName(rs.getString("UserName"));
				buser.setUserNName(rs.getString("UserNName"));
				buser.setHeadImage(rs.getString("UserImage"));
				buser.setUserClass(rs.getInt("UserClass"));
				if (buser.getUserClass() == 1) {
					buser.setUserClassName("普通用户");
				} else if (buser.getUserClass() == 2) {
					buser.setUserClassName("版主");
				} else{
					buser.setUserClassName("管理员");
				}
				buser.setUserPoint(rs.getInt("UserPoint"));
				list2.add(buser);// 将bean中所设置的对象装入ArrayList当中
			}
			// }
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list2;
	}

	/**
	 * 图像的保存处理
	 * 
	 * @param userName
	 * @return InputStream
	 */
	public InputStream selectImage(String userName) {
		String sql = null;
		sql = "select UserImage  from   BBSUser where  UserName='" + userName
				+ "'";
		ResultSet res = null;
		InputStream ins = null;
		try {
			res = dbcon.Query(sql,dbcon);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			res.next();
			ins = res.getBinaryStream("UserImage");
			try {
				System.out.println(ins.available());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ins;

	}

	/**
	 * 获取得到帖子查询结果的总结果数！
	 * 
	 * @param sectionSearch
	 * @param note
	 * @return int
	 */
	public int resultTopic(String sectionSearch, String note) {
		String sql = null;
		int b = 0;
		// 内容关键字查询
		if (note.equals("TopicContent")) {
			sql = "select count(BBSTopic.TopicId)"
					+ "from BBSTopic,   BBSSection, BBSUser "
					+ "where BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSTopic.TopicContent like '%"
					+ sectionSearch + "%'";
		}
		// 标题关键字查询
		if (note.equals("TopicTopic")) {
			sql = "select count(BBSTopic.TopicId)"
					+ "from  BBSTopic,   BBSSection, BBSUser "
					+ "where BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSTopic.TopicTopic like '%"
					+ sectionSearch + "%'";
		}
		// 作者关键字查询
		if (note.equals("TopicAuthor")) {
			sql = "select count(BBSTopic.TopicId)"
					+ "from BBSTopic,BBSSection, BBSUser "
					+ "where BBSTopic.TopicSectionId=BBSSection.SectionId and BBSTopic.TopicUserId=BBSUser.UserId and BBSUser.UserName ='"
					+ sectionSearch + "'";
		}

		try {
			ResultSet re1 = dbcon.Query(sql,dbcon);
			while (re1.next()) {
				b = re1.getInt(1);
			}
			re1.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

}

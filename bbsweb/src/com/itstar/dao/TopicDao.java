package com.itstar.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itstar.bbs.form.EditPostsForm;
import com.itstar.manage.control.form.TopicInfoForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSBunUser;
import com.itstar.model.BBSSection;
import com.itstar.model.BBSTopic;
import com.itstar.model.ReplyAndUser;
import com.itstar.util.Utils;
import com.itstar.util.words.WordsFilter;

/**
 * 
 * 
 */
public class TopicDao {

	/**
	 * �������
	 * 
	 * @param replyId
	 */
	public Integer addTopic(EditPostsForm post) {
		String topicSql = ""; // ִ�в������ӵ����
		String sectionSql = ""; // ִ���޸İ������
		String selectSql = ""; // ���Ҹղ��������
		Utils utils = new Utils();
		String topicContent = post.getTopicContent(); // ��������
		String strTopicTitle = post.getTopicTopic(); // ���ӱ���
		
		/// �����ַ�����
		strTopicTitle = utils.replaceInsertString(strTopicTitle);
		topicContent = utils.replaceDouhao(topicContent);

		DatabaseConnection dconn = new DatabaseConnection();
		PreparedStatement stat = null;
		Connection conn = null;
		ResultSet rs = null;

		/* ���ע��ʱ�䲢��ʽ�� */
		String strTopicTime = utils.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss:www");
		int topicId = 0;
		try {
			conn = dconn.getConnection();
			topicSql = "INSERT INTO BBSTopic(TopicSectionId,TopicUserId,TopicTopic,TopicContent,TopicTime,TopicBunState,Delsign,TopicState,TopicReplyCount)"
					+ "values(?,?,?,?,?,?,0,0,0)";
			stat = conn.prepareStatement(topicSql);
			conn.setAutoCommit(false);
			stat.setInt(1, Integer.parseInt(post.getSectionId()));
			stat.setInt(2, Integer.parseInt(post.getUserId()));
			stat.setString(3, strTopicTitle);
			stat.setString(4, topicContent);
			stat.setString(5, strTopicTime);
			stat.setInt(6, 0);
			stat.execute();
			sectionSql = "UPDATE BBSSection SET SectionTopicCount=SectionTopicCount+1 WHERE SectionId ="
					+ post.getSectionId();
			dconn.Update(sectionSql,dconn);// ִ���޸�
			conn.commit();
			selectSql = "select TopicId from BBSTopic order by TopicId desc limit 0,1 ";
			rs = dconn.Query(selectSql,dconn);
			while (rs.next()) {
				topicId = rs.getInt("TopicId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			dconn.close();
		}
		return topicId;
	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param topicId
	 *            ���ӱ��
	 * @return BBSTopic
	 */
	public BBSTopic getBBSTopic(String topicId) {
		BBSTopic bTopic = null;
		DatabaseConnection dconn = new DatabaseConnection();
		String str = "";
		Utils utils = new Utils();
		try {

			String sql = "SELECT TopicId,TopicSectionId,TopicUserId,TopicTopic,TopicContent,TopicTime,TopTime,TopicBunState"
					+ " FROM BBSTopic WHERE TopicId='" + topicId + "'";
			sql += " order by TopTime desc";
			ResultSet rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				WordsFilter wf = new WordsFilter();
				bTopic = new BBSTopic();
				bTopic.setTopicId(rs.getInt("TopicId"));
				bTopic.setTopicSectionId(rs.getInt("TopicSectionId"));
				bTopic.setTopicUserId(rs.getInt("TopicUserId"));
				// �Ա��⼰���ݽ������ֹ���Ȼ��set
                str = wf.filterWords(rs.getString("TopicTopic"));
                
              //�ַ��Ļ�ԭ����
				str = utils.replaceString(str);
                if (str.length() >30){
    				str = str.substring(0, 30) + "...";
    			}
				bTopic.setTopicTopic(str);
				// str =
				// utils.replaceString(wf.filterWords(rs.getString("TopicContent")));
				str = utils.replaceQuote(wf.filterWords(rs.getString("TopicContent")));
				str = utils.replaceCharToDaohao(str);
				bTopic.setTopicContent(str);
				bTopic.setTopicTime(rs.getString("TopicTime"));
				if (bTopic.getTopTime() != null
						&& !bTopic.getTopTime().equals("")) {
					bTopic.setTopTime(bTopic.getTopTime().substring(0, 16));
				}
				bTopic.setTopicBunState(rs.getInt("TopicBunState"));
			}
			return bTopic;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * ����������ӵĻ�����Ϣ:���ߡ����⡢��š�������������ʱ��
	 * 
	 * @param btopic
	 * @return List<BBSTopic>
	 * @throws Exception
	 */
	public ArrayList<BBSTopic> getBasicTopic(BBSTopic btopic) throws Exception {

		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		DatabaseConnection dconn = new DatabaseConnection();
		
		try {
			// �����ö�����
			String sql = "SELECT A.TopicSectionId, A.TopicTopic,A.TopicTime,A.TopicReplyCount,A.TopicId,A.TopicState,A.TopTime,A.TopicBunState,B.UserName FROM BBSTopic A, BBSUser B" +
					" WHERE A.Delsign ='0' and A.TopicUserId=B.UserId and A.TopTime is not null  ";
			if(btopic.getTopicState()==1) {
				sql = " SELECT A.TopicSectionId, A.TopicTopic,A.TopicTime,A.TopicReplyCount,A.TopicId,A.TopicState,A.TopTime,A.TopicBunState,B.UserName FROM BBSTopic A, BBSUser B,BBSSection C" +
					" WHERE A.Delsign ='0' and A.TopicUserId=B.UserId and A.TopTime is not null and A.TopicState = 1 and A.TopicSectionId = C.SectionId and C.Delsign = 0";
			}
			if (btopic.getTopicSectionId() != 0) {
				sql += " and A.TopicSectionId='" + btopic.getTopicSectionId() + "' order by A.TopTime desc";
			}
			
			ResultSet rs = dconn.Query(sql,dconn);
			list = getTopicList(rs, list);

			// ���ҷ��ö�����
			sql = "SELECT A.TopicSectionId, A.TopicTopic,A.TopicTime,A.TopicReplyCount,A.TopicId,A.TopicState,A.TopTime,A.TopicBunState,B.UserName FROM BBSTopic A, BBSUser B" +
			" WHERE A.Delsign ='0' and A.TopicUserId=B.UserId and A.TopTime is null  ";
			if(btopic.getTopicState()==1) {
				sql = " SELECT A.TopicSectionId, A.TopicTopic,A.TopicTime,A.TopicReplyCount,A.TopicId,A.TopicState,A.TopTime,A.TopicBunState,B.UserName FROM BBSTopic A, BBSUser B,BBSSection C" +
				" WHERE A.Delsign ='0' and A.TopicUserId=B.UserId and A.TopTime is null and A.TopicState = 1 and A.TopicSectionId = C.SectionId and C.Delsign = 0";
			}
			if (btopic.getTopicSectionId() != 0) {
				sql += " and A.TopicSectionId='" + btopic.getTopicSectionId() + "' order by A.TopicTime desc";
				
			}
			rs = dconn.Query(sql,dconn);
			list = getTopicList(rs, list);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dconn.close();
		}
		return list;
	}

	/**
	 * ������������ӵĻ�����Ϣ:���ߡ����⡢��š�������������ʱ��
	 * 
	 * @param btopic
	 * @return List<BBSTopic>
	 * @throws Exception
	 */
	public ArrayList<BBSTopic> getNewTopic(BBSTopic btopic) throws Exception {

		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		DatabaseConnection dconn = new DatabaseConnection();
		try {
			Statement stmt = dconn.getConnection().createStatement();
			BBSSection section = new BBSSection();
			Utils utils = new Utils();
			String now = utils.formatDate(new Date(), "yyyy-MM-dd"); // ��õ�ǰʱ�䲢��ʽ��
			String sql = "SELECT A.TopicSectionId, A.TopicTopic,A.TopicTime,A.TopicReplyCount,A.TopicId,A.TopicState,A.TopTime,A.TopicBunState, B.UserName FROM BBSTopic A, BBSUser B,BBSSection C" +
						" WHERE A.Delsign ='0' And A.TopicSectionId = C.SectionId AND C.Delsign = 0  AND A.TopicUserId=B.UserId  order by A.TopicTime desc";
			ResultSet rs = stmt.executeQuery(sql);
			String str = "";
			while (rs.next()) {

				BBSTopic bbsto = new BBSTopic();
				String topicTime = "";
				bbsto.setUserName(rs.getString("UserName")); // ����
				bbsto.setTopicId(rs.getInt("TopicId")); // ���µı��
				bbsto.setTopicSectionId(rs.getInt("TopicSectionId")); //
				//�������ڰ��ı��
				SectionDao sdao2 = new SectionDao();
				section = sdao2.getSection(
				bbsto.getTopicSectionId());
				bbsto.setSectionName(section.getSectionName()); // ���ڵİ������
				str = rs.getString("TopicTopic");
				if (str.length() >30){
					str = str.substring(0, 30) + "...";
				}
				bbsto.setTopicTopic(str); // ���±���
				bbsto.setTopicReplyCount(rs.getInt("TopicReplyCount")); // �ظ�������
				bbsto.setTopicTime(rs.getString("topicTime").substring(0, 19));
				// // ������ʱ��
				topicTime = bbsto.getTopicTime();
				topicTime = topicTime.substring(0, 10);
				topicTime = topicTime.replace("-", "0");
				now = now.replace("-", "0");
				if ((Integer.parseInt(now) - Integer.parseInt(topicTime)) < 3) {
				//if (bbsto.isNewTopic()) {
					bbsto.setNewTopic(true);
					bbsto.setTopicState(Integer.parseInt(rs.getString("TopicState"))); // �Ƿ��Ǿ�������
					// �����Ƿ��ö�
					if (rs.getString("TopTime") != null && !rs.getString("TopTime").equals("")) {
					    bbsto.setTopTime(rs.getString("TopTime").substring(0, 16));
					 }
					bbsto.setTopicBunState(rs.getInt("TopicBunState"));
					list.add(bbsto);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			
		}finally{
			dconn.close();
		}
		return list;
	}

	/**
	 * ��ȡ���ӻظ���Ϣ
	 * 
	 * @param id
	 *            �ظ����������ӵı��
	 * @return List
	 */
	public List<ReplyAndUser> getTopicAndUser(String id) {
		List<ReplyAndUser> list = new ArrayList<ReplyAndUser>();
		Utils utils = new Utils();
		String str = "";
		BBSBunUser bunUser = null;
		DatabaseConnection dconn = new DatabaseConnection();
		BunUserDao bunDao = new BunUserDao();
		try {
			String strsql = "SELECT UserId,UserName,UserNName,UserImage,UserRegDate,UserClass,UserPoint,ReplyID,ReplyContent,ReplyTime,ReplyState FROM BBSUser,BBSReply"
					+ " WHERE BBSReply.Delsign = 0 and BBSUser.UserId=BBSReply.ReplyUserId AND ReplyTopicId='"
					+ id + "'order by ReplyTime";
			ResultSet rs = dconn.Query(strsql,dconn);
			while (rs.next()) {
				WordsFilter wf = new WordsFilter();
				ReplyAndUser topicAndUser = new ReplyAndUser();
				topicAndUser.setUserId(rs.getInt("UserId"));
				topicAndUser.setUserName(rs.getString("UserName"));
				topicAndUser.setUserNName(rs.getString("UserNName"));
				topicAndUser.setUserImage(rs.getString("UserImage"));
				topicAndUser.setUserRegDate(rs.getString("UserRegDate"));
				topicAndUser.setUserClass(rs.getInt("UserClass"));
				bunUser = bunDao.getBunUserByName(topicAndUser.getUserName());
				if (bunUser == null) {
					topicAndUser.setBunState(1);
				} else {
					topicAndUser.setBunState(Integer.parseInt(bunUser.getBunState()));
				}
				if (topicAndUser.getUserClass() == 1) {
					topicAndUser.setUserClassName("��ͨ�û�");
				} else if (topicAndUser.getUserClass() == 2) {
					topicAndUser.setUserClassName("����");
				} else {
					topicAndUser.setUserClassName("����Ա");
				}
				topicAndUser.setUserPoint(rs.getInt("UserPoint"));
				topicAndUser.setReplyID(rs.getInt("ReplyID"));
				// �Ի������ݽ������ֹ��˲����������ַ��Ĵ���
				// str =
				// utils.replaceString(wf.filterWords(rs.getString("ReplyContent")));
				str = utils.replaceQuote(wf.filterWords(rs
						.getString("ReplyContent")));
				str = utils.replaceCharToDaohao(str);
				topicAndUser.setReplyContent(str);
				topicAndUser.setReplyTime(rs.getString("ReplyTime"));
				topicAndUser.setReplyState(rs.getInt("ReplyState"));
				list.add(topicAndUser);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * �޸���������
	 * 
	 * @param id
	 */
	public void editTopic(EditPostsForm form) {
		DatabaseConnection dconn = new DatabaseConnection();
		String sql = "";
		Utils utils = new Utils();
		String topicContent = form.getTopicContent();
		//topicContent = utils.replaceDouhao(topicContent);
		topicContent = utils.replaceInsertString(topicContent);
		
		try {

			if (form.getTopicid() != null && !form.getTopicid().equals("")) {
				sql = "update BBSTopic set TopicContent = '" + topicContent
						+ "' where TopicId  =" + form.getTopicid();
				dconn.Update(sql,dconn);// ִ��ɾ��
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}

	/**
	 * ������������
	 * 
	 * @param id
	 */
	public int bunTopic(String topicId) {
		String sql = "";
		ResultSet rs = null;
		// topicContent = utils.replaceInsertString(topicContent);
		DatabaseConnection dconn = new DatabaseConnection();
		int topicBunState = 0;
		try {

			String selectSql = "select TopicBunState from BBSTopic where TopicId  ="
					+ topicId;
			rs = dconn.Query(selectSql,dconn);
			rs.next();
			if (rs.getInt("TopicBunState") == 1) {
				sql = "update BBSTopic set TopicBunState = 0 where TopicId  ="
						+ topicId;
			} else {
				sql = "update BBSTopic set TopicBunState = 1 where TopicId  ="
						+ topicId;
			}
			dconn.Update(sql,dconn);// ִ��ɾ��
			rs = dconn.Query(selectSql,dconn);
			rs.next();
			topicBunState = rs.getInt("TopicBunState");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return topicBunState;
	}

	/**
	 * ɾ����������
	 * 
	 * @param topicId
	 */
	public void deleteTopic(String topicId) {
		String sql = "";
		BBSTopic topic = new BBSTopic();
		String sectionId = "";
		DatabaseConnection dconn = new DatabaseConnection();
		SectionDao sdao = new SectionDao();
		ReplyDao rdao = new ReplyDao();
		try {

			if (topicId != null && !topicId.equals("")) {
				String num[] = topicId.split(",");
				for (int i = 0; i < num.length; i++) {
					sql = "update BBSTopic set Delsign = 1 where TopicId  in ("
							+ num[i] + ")";
					int j = dconn.excuteUpdate(sql,dconn);// ִ��ɾ��
					if(j==1) {
						topic = getBBSTopic(num[i]);
						sectionId = String.valueOf(topic.getTopicSectionId());
						sdao.editTopicCount(sectionId);
						rdao.deleteReplyByTopicId(Integer.parseInt(num[i]));
					}	
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}

	/**
	 * �ö�
	 * 
	 * @param id
	 */
	public void toptopic(String id) {
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		Utils utils = new Utils();
		String topTime = utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		DatabaseConnection dconn = new DatabaseConnection();
		try {

			if (id != null && !id.equals("")) {
				String mun[] = id.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select TopTime from BBSTopic where TopicId="
							+ mun[i];
					rs = dconn.Query(sql1,dconn);
					if (rs.next()) {
						if (null == rs.getString("TopTime")) {
							sql2 = "update BBSTopic set TopTime = '" + topTime
									+ "' where TopicId=" + mun[i];
						} else {
							sql2 = "update BBSTopic set TopTime = null where TopicId="
									+ mun[i];
						}
						dconn.Update(sql2,dconn);// ִ��ɾ��
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}

	/**
	 * ����
	 * 
	 * @param id
	 */
	public void statetopic(String topicId) {
		String sql1 = "";
		String sql2 = "";
		String sqlUser = "";
		ResultSet rs = null;
		DatabaseConnection dconn = new DatabaseConnection();
		try {

			if (topicId != null) {
				String mun[] = topicId.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select TopicState,TopicUserId from BBSTopic where TopicId="
							+ mun[i];
					rs = dconn.Query(sql1,dconn);
					if (rs.next()) {
						if (rs.getString("TopicState").equals("0")
								|| rs.getString("TopicState") == "0") {
							sql2 = "update BBSTopic set TopicState = 1 where TopicId="
									+ mun[i];
							sqlUser = "update BBSUser set UserPoint = UserPoint+10 where UserId = "
									+ rs.getInt("TopicUserId");
						} else {
							sql2 = "update BBSTopic set TopicState = 0 where TopicId="
									+ mun[i];
							sqlUser = "update BBSUser set UserPoint = UserPoint-10 where UserId = "
									+ rs.getInt("TopicUserId");
						}
						dconn.Update(sql2,dconn);// ִ��
						dconn.Update(sqlUser,dconn);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}

	/**
	 * �ı��������
	 * 
	 * @param replyId
	 */
	public void editReplyCount(String topicId, int delValue) {
		String sql = "";
		DatabaseConnection dconn = new DatabaseConnection();
		ResultSet rs = null;
		int count = 0;
		try {

			if (topicId != null) {
				// �ӻ���������ȡ�������ӵ�idֵ��Ȼ�����ô����Ļظ�����һ
				sql = "select count(ReplyID) from BBSReply where Delsign = "
						+ delValue + " and ReplyTopicId =" + topicId;
				rs = dconn.Query(sql,dconn);
				while (rs.next()) {
					count = rs.getInt(1);
				}
				sql = "update BBSTopic set TopicReplyCount = " + count
						+ " where   TopicId   =" + topicId;

				dconn.Update(sql,dconn);// ִ���޸�
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param TopicInfoForm
	 * @return
	 */
	public int getAllRecorders(TopicInfoForm form) {
		DatabaseConnection dconn = new DatabaseConnection();
		int allRecorders = 0;
		String sql = null;
		String extend1 = null;
		String extend2 = null;
		String extend3 = null;
		String extend4 = null;
		String title = form.getTitle();
		String username = form.getUsername();
		String section = form.getSection();
		String topicContent = form.getTopiccontent();
		ResultSet rs = null;

		if (title != null && !title.equals("")) {
			extend1 = " and TopicTopic LIKE '%" + title + "%'";
		} else {
			extend1 = "";
		}
			UserDao udao = new UserDao();
		if (username != null && !username.equals("")) {
			extend2 = " and TopicUserId  = "
					+ udao.getUser(username).getUserId();
		} else {
			extend2 = "";
		}
		SectionDao sdao = new SectionDao();
		if (section != null && !section.equals("")) {
			extend3 = " and TopicSectionId = "
					+ sdao.getSection(section)
							.getSectionId();
		} else {
			extend3 = "";
		}
		if (topicContent != null && !topicContent.equals("")) {
			extend4 = " and TopicContent like '%" + topicContent + "%'";
		} else {
			extend4 = "";
		}
		try {

			sql = "select COUNT(TopicId) from BBSTopic T,BBSSection S where T.Delsign = 0 and S.Delsign=0"
					+ " and T.TopicSectionId = S.SectionId and S.SectionState = 0 "
					+ extend1 + extend2 + extend3 + extend4;

			rs = dconn.Query(sql,dconn);
			if (rs.next()) {
				allRecorders = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return allRecorders;
	}

	/**
	 * ��ȡ�б�
	 * 
	 * @param request
	 * @param currentPage
	 * @return List<BBSTopic>
	 * @throws UnsupportedEncodingException
	 */
	public List<BBSTopic> getArray(TopicInfoForm form) {

		DatabaseConnection dconn = new DatabaseConnection();
		UserDao udao = new UserDao();
		Map map1 = udao.getUserMap();
		SectionDao sdao = new SectionDao();
		Map map2 = sdao.getSectionMap();
		ArrayList<BBSTopic> array = new ArrayList<BBSTopic>();
		Utils utils = new Utils();
		String str = "";
		ResultSet rs = null;
		String sql = null;
		String extend1 = null;
		String extend2 = null;
		String extend3 = null;
		String extend4 = null;
		String title = form.getTitle();
		String username = form.getUsername();
		String section = form.getSection();
		String topicContent = form.getTopiccontent();

		if (title != null && !title.equals("")) {
			extend1 = " and TopicTopic LIKE '%" + title + "%'";
		} else {
			extend1 = "";
		}
		UserDao udao1 = new UserDao();
		if (username != null && !username.equals("")) {
			extend2 = " and TopicUserId  = "
					+ udao1.getUser(username).getUserId();
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
		if (topicContent != null && !topicContent.equals("")) {
			extend4 = " and TopicContent like '%" + topicContent + "%'";
		} else {
			extend4 = "";
		}
		try {

			sql = "select TopicId,TopicSectionId,TopicUserId,TopicReplyCount,TopicTopic,TopicContent,"
					+ " TopicTime,TopicState,TopTime,TopicBunState from BBSTopic where Delsign = 0 "
					+ extend1 + extend2 + extend3 + " order by TopicTime desc";

			rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				WordsFilter wf = new WordsFilter();
				BBSTopic topic = new BBSTopic();
				topic.setTopicId(rs.getInt("TopicId"));
				topic.setTopicSectionId(rs.getInt("TopicSectionId"));
				topic.setSectionName((String) map2.get(topic
						.getTopicSectionId()));
				topic.setTopicUserId(rs.getInt("TopicUserId"));
				topic.setUserName((String) map1.get(topic.getTopicUserId()));
				topic.setTopicReplyCount(rs.getInt("TopicReplyCount"));
				// �Ա��⼰���ݽ������ֹ���
				str = wf.filterWords(rs.getString("TopicTopic"));
				if (str.length() >30){
    				str = str.substring(0, 30) + "...";
    			}
				topic.setTopicTopic(str);
				str = wf.filterWords(rs.getString("TopicContent"));
				if (str.length() > 30) {
					str = str.substring(0, 30) + "...";
				}
				str = utils.replaceInsertString(str);
				topic.setTopicContent(str);
				topic.setTopicTime(rs.getString("TopicTime").substring(0, 19));
				topic.setTopicState(rs.getInt("TopicState"));
				topic.setTopTime(rs.getString("TopTime"));
				topic.setTopicBunState(rs.getInt("TopicBunState"));
				array.add(topic);
			}
			return array;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * 
	 */
	private ArrayList<BBSTopic> getTopicList(ResultSet rs,
			ArrayList<BBSTopic> list) {
		
		BBSSection section = new BBSSection();
        String str = "";
		// ��õ�ǰʱ�䲢��ʽ��
		Utils utils = new Utils();
		String now = utils.formatDate(new Date(), "yyyy-MM-dd");
		try {
			while (rs.next()) {
				BBSTopic bbsto = new BBSTopic();
				String topicTime = "";
				bbsto.setUserName(rs.getString("UserName")); // ����
				bbsto.setTopicId(rs.getInt("TopicId")); // ���µı��
				bbsto.setTopicSectionId(rs.getInt("TopicSectionId")); // �������ڰ��ı��
				SectionDao sdao1 = new SectionDao();
				section = sdao1.getSection(
						rs.getInt("TopicSectionId"));
				// Map map = UserDao.getInstance().getUserMap();
				// String username
				// =(String)map.get(section.getSectionMasterID());
				bbsto.setSectionName(section.getSectionName()); // ���ڵİ������
				
				//�ַ��Ļ�ԭ����
				str = utils.replaceString(rs.getString("TopicTopic"));
				if (str.length() >30){
					str = str.substring(0, 30) + "...";
				}
				bbsto.setTopicTopic(str);                                 // ���µı���
				bbsto.setTopicReplyCount(rs.getInt("TopicReplyCount")); // �ظ�������
				bbsto.setTopicTime(rs.getString("topicTime").substring(0, 19)); // ������ʱ��
				topicTime = bbsto.getTopicTime();
				topicTime = topicTime.substring(0, 10);
				topicTime = topicTime.replace("-", "0");
				now = now.replace("-", "0");
				if ((Integer.parseInt(now) - Integer.parseInt(topicTime)) < 3) {
					bbsto.setNewTopic(true);
				}
				bbsto.setTopicState(Integer
						.parseInt(rs.getString("TopicState"))); // �Ƿ��Ǿ�������
				// �����Ƿ��ö�
				if (rs.getString("TopTime") != null
						&& !rs.getString("TopTime").equals("")) {
					bbsto.setTopTime(rs.getString("TopTime").substring(0, 16));
				}
				bbsto.setTopicBunState(rs.getInt("TopicBunState"));
				list.add(bbsto);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

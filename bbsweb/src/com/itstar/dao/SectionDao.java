package com.itstar.dao;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.itstar.manage.control.form.AddSectionForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSSection;
import com.itstar.util.Utils;

/**
 * �԰���б�ĸ�����ز��������ҡ�ɾ�����޸ġ��ö�������
 */
public class SectionDao {

	/**
	 * ��Ӱ������Ϣ
	 * 
	 * @param sid
	 * @param sname
	 * @param state
	 * @param sectionMasterID
	 * @param top
	 * @param profile
	 * @returnint sid, String sname, String state,
			String sectionMasterID, String profile)
	 */
	public boolean addSection(AddSectionForm form) {
		PreparedStatement pstmt = null;
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = null;
		
		Utils utils = new Utils();
		String strSectionName = form.getSectionName();       //�����
		String strSectionProfile = form.getSectionProfile(); // �����
		
		// �����ַ�����
		strSectionName = utils.replaceInsertString(strSectionName);
		strSectionName = utils.replaceDouhao(strSectionName);
		strSectionProfile = utils.replaceInsertString(strSectionProfile);
		strSectionProfile = utils.replaceDouhao(strSectionProfile);
		
		try {
			
			con = dbcon.getConnection();
			String sql = "insert into BBSSection(SectionName,SectionMasterID,SectionProfile,SectionTopicCount,SectionState,Delsign) values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, strSectionName);
			pstmt.setInt(2, 0);
			pstmt.setString(3, strSectionProfile);
			pstmt.setInt(4, 0);
			pstmt.setString(5, form.getSectionState());
			pstmt.setInt(6, 0);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  finally {
			dbcon.close();
		}
	}


	/**
	 * ��ȡ�����Ϣͨ���������
	 * 
	 * @param name  �������
	 * @return
	 */
	public BBSSection getSection(String name) {
		BBSSection section = new BBSSection();
		DatabaseConnection dbcon = new DatabaseConnection();
		

		//������
		Utils utils = new Utils(); 
		String str = "";
		try {

			String sql = "SELECT SectionId,SectionName,SectionProfile,SectionTopicCount FROM BBSSection WHERE SectionName ='" + name
					+ "'";

			ResultSet rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				section.setSectionId(rs.getInt("SectionId"));
				str = utils.replaceCharToDaohao(rs.getString("SectionName"));
				section.setSectionName(str);
				str = utils.replaceCharToDaohao(rs.getString("SectionProfile"));
				section.setSectionProfile(str);
				section.setSectionTopicCount(rs.getInt("SectionTopicCount"));
			}
			return section;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return null;
	}

	/**
	 * ��ȡ���
	 * 
	 * @return
	 */
	public Map getSectionMap() {
		Map map = new HashMap();
		DatabaseConnection dbcon = new DatabaseConnection();
		
		try {

			String sql = "SELECT SectionId,SectionName FROM BBSSection";
			ResultSet rs = dbcon.Query(sql,dbcon);
			while (rs.next()) {
				map.put(rs.getInt("SectionId"), rs.getString("SectionName"));
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		return null;
	}

	/**
	 * �޸���������
	 * 
	 * @param id
	 */
	public void editTopicCount(String id) {
		DatabaseConnection dbcon = new DatabaseConnection();
		ResultSet rs = null;
		int count = 0;
		try {
			String topicSql = "select count(TopicId) from BBSTopic where Delsign = 0 and TopicSectionId ="
					+ id ;
			rs = dbcon.Query(topicSql,dbcon);
			while(rs.next()) {
				count = rs.getInt(1);
			}
			String sql = "update BBSSection set SectionTopicCount = "+ count +" where SectionId ="
					+ id ;
			dbcon.Update(sql,dbcon);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param request
	 * @return
	 */
	public int getAllRecorders(HttpServletRequest request) {
		DatabaseConnection dconn = new DatabaseConnection();
		int allRecorders = 0;
		String sql = null;
		ResultSet rs = null;

		try {
			sql = "select COUNT(SectionId) from BBSSection where Delsign = 0 ";
			rs = dconn.Query(sql,dconn);
			if (rs.next()) {
				allRecorders = rs.getInt(1);
			}
			return allRecorders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return 0;
	}

	/**
	 * ��ȡ�б�
	 * 
	 * @param request
	 * @param currentPage
	 * @return List<BBSSection>
	 * @throws UnsupportedEncodingException
	 */
	public List<BBSSection> getArray(HttpServletRequest request, int currentPage,int lineSize) {

		DatabaseConnection dconn = new DatabaseConnection();
		ResultSet rs = null;
		String sql = null;
		//���幤����
        Utils utils = new Utils();
		UserDao udao = new UserDao();
		Map map = udao.getUserMap();
		ArrayList<BBSSection> array = new ArrayList<BBSSection>();
		
        String str = "";
		try {

			sql = "select SectionId,SectionName,SectionMasterID,SectionState,SectionTop from BBSSection where Delsign = 0 ";

			rs = dconn.Query(sql,dconn);
			for (int x = 0; x < (currentPage - 1) * lineSize; x++) {
				rs.next();
			}
			int i = 0;

			// �����������֮ǰҪ����ʾ��ҳ���ճ�
			for (int x = 0; x < lineSize; x++) {
				if (rs.next()) {
					i++;
					BBSSection section = new BBSSection();
					section.setSectionId(rs.getInt("SectionId"));
					str = utils.replaceCharToDaohao(rs.getString("SectionName"));
					section.setSectionName(str);
					section.setSectionMasterID((String) map.get(rs
							.getInt("SectionMasterID")));
					section.setSectionState(rs.getInt("SectionState"));
					section.setSectionTop(rs.getString("SectionTop"));
					array.add(section);
				}
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
	 * ɾ�����
	 * 
	 * @param id
	 */
	public void deleteSection(String id) {
		String sql = "";
		DatabaseConnection dconn = new DatabaseConnection();
		try {

			if (id != null) {
				sql = "update BBSSection set Delsign = 1 where SectionId in ("
						+ id + ")";
				SectionDao sdao2 = new SectionDao();
				sdao2.editTopicCount(id);
				dconn.Update(sql,dconn);// ִ��ɾ��

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
	public void topsection(String id) {
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		DatabaseConnection dconn = new DatabaseConnection();
		try {

			if (id != null) {
				String mun[] = id.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select SectionTop from BBSSection where SectionId="
							+ mun[i];
					rs = dconn.Query(sql1,dconn);
					if (rs.next()) {
						if (rs.getString("SectionTop").equals("0")
								|| rs.getString("SectionTop") == "0") {
							sql2 = "update BBSSection set SectionTop = 1 where SectionId="
									+ mun[i];
						} else {
							sql2 = "update BBSSection set SectionTop = 0 where SectionId="
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
	public void statesection(String id) {
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		DatabaseConnection dconn = new DatabaseConnection();
		try {

			if (id != null) {
				String mun[] = id.split(",");
				for (int i = 0; i < mun.length; i++) {
					sql1 = "select SectionState from BBSSection where SectionId="
							+ mun[i];
					rs = dconn.Query(sql1,dconn);
					if (rs.next()) {
						if (rs.getString("SectionState").equals("0")
								|| rs.getString("SectionState") == "0") {
							sql2 = "update BBSSection set SectionState = 1 where SectionId="
									+ mun[i];
						} else {
							sql2 = "update BBSSection set SectionState = 0 where SectionId="
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
	 * ��ȡ�����Ϣͨ�������
	 * 
	 * @param sectionId �����
	 * @return
	 */
	public BBSSection getSection(int sectionId) {
		BBSSection section = new BBSSection();
		DatabaseConnection dconn = new DatabaseConnection();
		//������
		Utils utils = new Utils(); 
		String str = "";
		try {

			String sql = "SELECT SectionId,SectionName,SectionMasterID,SectionProfile,SectionTopicCount FROM BBSSection WHERE SectionId ='" + sectionId + "'";

			ResultSet rs = dconn.Query(sql,dconn);
			while (rs.next()) {
				section.setSectionId(rs.getInt("SectionId"));
				str =  rs.getString("SectionName");
				str = utils.replaceCharToDaohao(str);
				section.setSectionName(str);
				str = utils.replaceCharToDaohao(str);
				section.setSectionMasterID(rs.getString("SectionMasterID"));
				str = rs.getString("SectionProfile");
				section.setSectionProfile(str);
				
				section.setSectionTopicCount(rs.getInt("SectionTopicCount"));
			}
			return section;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
		return null;
	}

	/**
	 * �޸İ������Ϣ
	 * 
	 * @param sid    �����
	 * @param sname  �����
	 * @param sectionMasterID �������
	 * @param profile  �����
	 * @return
	 */
	public void editSection(int sid, String sname,String sectionMasterID, String profile) {
		DatabaseConnection dconn = new DatabaseConnection();

		
		// �����ַ�����
		Utils utils = new Utils();
		sname = utils.replaceInsertString(sname);
		profile = utils.replaceDouhao(profile);

		try {
            
			
			String sql = "update BBSSection set SectionName='" + sname
					+ "',SectionProfile='" + profile + " ',SectionMasterID ="+ sectionMasterID + " where SectionId=" + sid + "";
			dconn.Update(sql,dconn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dconn.close();
		}
	}
	
	/**
	 * �޸İ�������
	 * 
	 * @param sid    �����
	 * @param sectionMasterID �������
	 * @return
	 */
	public void editSectionMaster(String sectionMasterID,String sid) {
		DatabaseConnection dbcon = new DatabaseConnection();
		try {

			String sql = "update BBSSection set SectionMasterID ="+ sectionMasterID + " where SectionMasterID=" + sid + "";
			dbcon.Update(sql,dbcon);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
	}
}

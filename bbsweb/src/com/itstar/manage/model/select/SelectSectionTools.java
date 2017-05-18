/**
 * 
 */
package com.itstar.manage.model.select;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itstar.dao.UserDao;
import com.itstar.manage.dbconnection.DatabaseConnection;

/**
 *
 */
public class SelectSectionTools {
	public SelectSectionTools(){
		
	}
	
	/**
	 * 获得所有的版块信息
	 * @return
	 */
  public List<Object> selectSectionAll(){
	     String sql=null;
         sql = "select * from  BBSSection where SectionState = 0 and Delsign = 0";
         DatabaseConnection dbcon2 = new DatabaseConnection();
 		 UserDao udao2 = new UserDao();
		 Map map = udao2.getUserMap();
         ResultSet res=null;
		 List<Object> list=new ArrayList<Object>();
			try {
				
				res = dbcon2.Query(sql,dbcon2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				while(res.next()){
				   SelectSectionBean st=new SelectSectionBean();
				   st.setSectionId(res.getInt("SectionId"));
				   st.setSectionName(res.getString("SectionName"));
				   st.setSectionMasterName((String)map.get(res.getInt("SectionMasterID")));
				   st.setSectionTopicCount(res.getString("SectionTopicCount"));
				   st.setSectionProfile(res.getString("SectionProfile"));
				   list.add(st);
				}
				
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbcon2.close();
			}
		
		return list;
	   
   }

}

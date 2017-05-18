package com.itstar.manage.control.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itstar.manage.control.form.LoginForm;
import com.itstar.manage.dbconnection.*;

public class searchAction extends Action {
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int userId=0;
		LoginForm log = (LoginForm) form;
        int SectionId =Integer.parseInt(request.getParameter("SectionId"));
        
		String TopicTopic = request.getParameter("TopicTopic");
		
		String TopicContent = request.getParameter("TopicContent");
		
		String userName =log.getUserName();
		DatabaseConnection dbconn = new DatabaseConnection();
		String sql1="select UserId from BBSUser where UserName='"+userName+"'";
		ResultSet res=dbconn.Query(sql1,dbconn);
		try{
			res.next();
		    userId=res.getInt("UserId");

		
		
		String sql = "INSERT INTO BBSTopic(TopicSectionId,TopicUserId,TopicTopic,TopicContent)values('"
				+ SectionId
				+ "',"
				+ userId
				+ ",'"
				+ TopicTopic
				+ "','"
				+ TopicContent + "')";
		try{
			dbconn.Update(sql,dbconn);
			
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.toString());
		}
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.toString());
		}finally{
			 dbconn.close();
		}
		return mapping.findForward("index");
	}

}

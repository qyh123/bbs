package com.itstar.manage.control.action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.manage.dbconnection.DatabaseConnection;

public class DisplayAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		int tid = Integer.parseInt(request.getParameter("topicId"));
		DatabaseConnection dbcon = new DatabaseConnection();
		String sql = "select TopicContent from BBSTopic where TopicId=" + tid
				+ "";
		ResultSet rs = dbcon.Query(sql,dbcon);
		String content = null;
		try {
			while (rs.next()) {
				content = rs.getString("TopicContent");
			}
			
			request.setAttribute("display3.array", content);
			return mapping.findForward("display3");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			dbcon.close();
		}

	}
}

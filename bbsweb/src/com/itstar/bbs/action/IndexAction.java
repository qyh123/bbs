package com.itstar.bbs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.dao.TopicDao;
import com.itstar.dao.UserDao;
import com.itstar.manage.control.form.TopicInfoForm;
import com.itstar.manage.model.select.SelectSectionTools;

public class IndexAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TopicInfoForm topicForm = (TopicInfoForm)form;
		UserDao userdao = new UserDao();
		TopicDao topicdao =new TopicDao();
		List<Object> list = new ArrayList<Object>();
		int topicsize = 0;
		int usersize = 0;
		SelectSectionTools st = new SelectSectionTools();
		try {
			//获得版块信息列表
			list = st.selectSectionAll();
			//获得主题帖子总数
			topicsize =	topicdao.getAllRecorders(topicForm);
			// 获得用户列表总数
			usersize = userdao.getUsers().size();
			request.setAttribute("usersize",usersize);
			request.setAttribute("topicsize",topicsize);
			request.setAttribute("sectionList",list);
			return mapping.findForward("index");
		} catch (RuntimeException e) {
			e.printStackTrace();
			request.setAttribute("error","运行出现异常<br>"+e);
			return mapping.findForward("error");
		}
	}
}
/**
 * 
 */
package com.itstar.bbs.action;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.bbs.form.SelectTopicForm;
import com.itstar.bbs.select.SelectTools;
import com.itstar.model.BBSTopic;
import com.itstar.util.Page;

/**
 * ��Ӧ�������������Action
 */
public class SelectTopicAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form
			,HttpServletRequest request,HttpServletResponse response)
	         throws ServletException, java.io.IOException{
		SelectTopicForm topicForm=(SelectTopicForm ) form;
		response.setContentType("text/html;charset=GBK");
	    request.setCharacterEncoding("GBK");
	 // 1�����嵱ǰҳ
		int currentPage = 1;
		
		// ���մ������ĵ�ǰҳ
		try {
			if (request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
				currentPage = Integer.parseInt(request.getParameter("pages"));
			}

		} catch (Exception e) {
			request.setAttribute("error","��ǰҳת�������ָ�ʽʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
		
		try {
			
			// ��ȡlist
			SelectTools s=new SelectTools();
			ArrayList<BBSTopic> topicList = new ArrayList<BBSTopic>();
			topicList = s.judgementNote(topicForm);
			
			// ����ܼ�¼��
			Page page = new Page(topicList, String.valueOf(currentPage));
			if(topicList.size()==0){
				request.setAttribute("em","��Ҫ�����Ӳ����ڻ��ߴ�������ʱ��û�з���");
			}
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", page.allpage);
			request.setAttribute("allRecorders", page.allCol);
			request.setAttribute("sectionSearch", topicForm.getSectionSearch());
			request.setAttribute("note", topicForm.getNote());
			request.setAttribute("list", page.relist);
	
			return mapping.findForward("success");
			
		} catch (RuntimeException e) {
			
			request.setAttribute("error","����ʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
		
	}

}

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
 * 响应帖子搜索处理的Action
 */
public class SelectTopicAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form
			,HttpServletRequest request,HttpServletResponse response)
	         throws ServletException, java.io.IOException{
		SelectTopicForm topicForm=(SelectTopicForm ) form;
		response.setContentType("text/html;charset=GBK");
	    request.setCharacterEncoding("GBK");
	 // 1、定义当前页
		int currentPage = 1;
		
		// 接收传过来的当前页
		try {
			if (request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
				currentPage = Integer.parseInt(request.getParameter("pages"));
			}

		} catch (Exception e) {
			request.setAttribute("error","当前页转换成数字格式时出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
		try {
			
			// 获取list
			SelectTools s=new SelectTools();
			ArrayList<BBSTopic> topicList = new ArrayList<BBSTopic>();
			topicList = s.judgementNote(topicForm);
			
			// 获得总记录数
			Page page = new Page(topicList, String.valueOf(currentPage));
			if(topicList.size()==0){
				request.setAttribute("em","您要的帖子不存在或者此作者暂时还没有发帖");
			}
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", page.allpage);
			request.setAttribute("allRecorders", page.allCol);
			request.setAttribute("sectionSearch", topicForm.getSectionSearch());
			request.setAttribute("note", topicForm.getNote());
			request.setAttribute("list", page.relist);
	
			return mapping.findForward("success");
			
		} catch (RuntimeException e) {
			
			request.setAttribute("error","运行时出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
	}

}

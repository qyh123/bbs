/**
 * 
 */
package com.itstar.bbs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.util.Page;
import com.itstar.bbs.form.SelectUserForm;
import com.itstar.bbs.select.SelectTools;
import com.itstar.model.BBSUser;

/**
 * 
 */
public class SelectUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		SelectUserForm userform = (SelectUserForm) form;

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
			// 获得form值
			String userSearch = userform.getUserSearch();
			String user = userform.getUser();
			
			// 获取list
			SelectTools st = new SelectTools();
			List<BBSUser> userlist = new ArrayList<BBSUser>();
			userlist = st.judgementUser(userform);
			
			// 获得总记录数
			Page page = new Page(userlist, String.valueOf(currentPage));
			
			// 判断是否有记录
			if (userlist.size() == 0) {
				request.setAttribute("errormessage", "不存在此会员！");
			}
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", page.allpage);
			request.setAttribute("allRecorders", page.allCol);
			request.setAttribute("userType", user);
			request.setAttribute("userSearch", userSearch);
			request.setAttribute("list", page.relist);
			
			return mapping.findForward("success");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","运行时出现异常<br>"+e);
			return mapping.findForward("error");
		}
	}

}

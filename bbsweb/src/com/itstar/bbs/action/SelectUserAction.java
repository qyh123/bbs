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
			// ���formֵ
			String userSearch = userform.getUserSearch();
			String user = userform.getUser();
			
			// ��ȡlist
			SelectTools st = new SelectTools();
			List<BBSUser> userlist = new ArrayList<BBSUser>();
			userlist = st.judgementUser(userform);
			
			// ����ܼ�¼��
			Page page = new Page(userlist, String.valueOf(currentPage));
			
			// �ж��Ƿ��м�¼
			if (userlist.size() == 0) {
				request.setAttribute("errormessage", "�����ڴ˻�Ա��");
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
			request.setAttribute("error","����ʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
	}

}

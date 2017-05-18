package com.itstar.bbs.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.bbs.form.UserForm;
import com.itstar.dao.PasswordDao;
import com.itstar.dao.UserDao;

/**
 * MyEclipse Struts Creation date: 08-23-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/findPassword" name="userForm" scope="request"
 *                validate="true"
 * @struts.action-forward name="success" path="/web/login.jsp"
 */
public class FindPasswordAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * 判断用户找回密码是否成功 Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		UserForm userForm = (UserForm) form;
		UserDao dao = new UserDao();
		String message = null;
		String userPassword = "";
		String userName = "";
		int userId = 0;
		userName = userForm.getUserName();
		PasswordDao pdao = new PasswordDao();
		//判断用户名和邮箱是否正确
		try {
			boolean bl = dao.findPassword(userForm);
			if (bl) {
				if(isTokenValid(request)) {
					userId = dao.getUser(userName).getUserId();
					pdao.addFindPassword(userId);
					message = "success";
					saveToken(request);
				} else {
					 message = "error";
					 resetToken(request);
				}
			} else {
				message = "error";
			}

			request.setAttribute("userPassword", userPassword);
			request.setAttribute("message", message);
		} catch (RuntimeException e) {
			return mapping.findForward("error");
		}
		return mapping.findForward("message");

	}
}
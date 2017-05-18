package com.itstar.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.bbs.form.UserForm;
import com.itstar.manage.control.UserProcess;
import com.itstar.model.BBSUser;

public class ValidateOldPasswordAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserForm userForm = (UserForm) form;

		String userName = "";

		UserProcess u = UserProcess.getInstance();

		BBSUser user = null;

		userName = userForm.getUserName();
		
		
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			
			user = u.findLogin(userForm);

				/*
				 * 校验用户名和密码
				 */
				if (user != null) {

					out.print("");

				} else {

					out.print("密码不正确！");
				}
				return null;
			// TODO Auto-generated method stub

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","验证旧密码出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
	}
}
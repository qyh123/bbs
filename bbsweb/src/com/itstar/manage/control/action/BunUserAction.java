
package com.itstar.manage.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itstar.dao.BunUserDao;
import com.itstar.manage.control.UserProcess;
import com.itstar.manage.control.form.AddBunUserForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSBunUser;


public class BunUserAction extends DispatchAction {
	/*
	 * Generated Methods
	 */

	
	/**
	 * �ж��û��Ƿ���� Method execute
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
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		AddBunUserForm userForm = (AddBunUserForm) form;
		PrintWriter out = response.getWriter();
		UserProcess u = UserProcess.getInstance();
	    if (u.findName(userForm.getUsername()) != 1) {
			out.print("���û�������");
		} 
			//saveToken(request); //  
			//return mapping.findForward("register");
			return null;
		// TODO Auto-generated method stub
	}
	
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward bun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddBunUserForm addBunUserForm = (AddBunUserForm) form;// TODO
		// Auto-generated
		// method stub
		BunUserDao bunDao = new BunUserDao();
		BBSBunUser bbuser = bunDao.getBunUserByName(addBunUserForm
				.getUsername());
		try {
			boolean bl = false;
			int i = 0;
			if (isTokenValid(request, true)) {
				// �жϽ�ֹ�û������Ƿ���ڸ��û�
				if (bbuser == null) {
					// ִ�в������
					bl = bunDao.addBunUser(addBunUserForm);
				} else {
					// ִ���޸Ĳ���
					i = bunDao.updateBunUser(addBunUserForm);
				}
				if (bl == true || i == 1) {
					request.setAttribute("message", "bunSuccess");
				} else {
					request.setAttribute("message", "bunError");
				}
				saveToken(request);
			} else {
				// 
				resetToken(request);
				request.setAttribute("message", "bunError");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		//		
		return mapping.findForward("info");
	}
}


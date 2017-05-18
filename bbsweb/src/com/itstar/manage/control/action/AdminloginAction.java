package com.itstar.manage.control.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.bbs.form.UserForm;
import com.itstar.dao.BunUserDao;
import com.itstar.dao.PasswordDao;
import com.itstar.manage.control.UserProcess;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSBunUser;
import com.itstar.model.BBSUser;

public class AdminloginAction extends Action {
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
		
		try {
			UserForm userForm = (UserForm) form;

			String userName = "";

			UserProcess u = UserProcess.getInstance();
			
			BunUserDao bundao = new BunUserDao();

			BBSUser user = null;

			userName = userForm.getUserName();

			user = u.findLogin(userForm);
			
			String flag = request.getParameter("flag");

		    HttpSession session = request.getSession();
			/*
			 * 判断操作的类型login 是登录操作 out是退出操作
			 */
			if (flag.equals("login")) {

				/*
				 * 判断用户是否存在
				 */
				if (u.findName(userName) == 1) {

					/*
					 * 校验用户名和密码
					 */
					if (user != null&&user.getUserClass()==3) {
						
						PasswordDao pdao = new PasswordDao();
					    int passwordRecorders = pdao.getFindPassword().size();
						session.setAttribute("passwordRecorders", passwordRecorders);
						BBSBunUser bunUser = bundao.getBunUserById(user.getUserId());
						
						if(bunUser == null) {
							bunUser = new BBSBunUser();
							bunUser.setBunState("1");	
						}
						session.setAttribute("bunUser", bunUser);

						session.setAttribute("user", user);

						return mapping.findForward("adminlogin");

					} else if(user != null&&user.getUserClass()!=3){
						request.setAttribute("powerwrong", "您没有权限访问！");
						return mapping.findForward("wrong");
					} else{
						request.setAttribute("passwordwrong", "您输入的密码有误！");
						return mapping.findForward("wrong");
					}

				} else {
					request.setAttribute("userwrong", "该用户不存在！");
					return mapping.findForward("wrong");
				}

			} else if (flag.equals("out")) {

				session.removeAttribute("user");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("parent.window.location.href='web/adminlogin.jsp'");
				out.println("</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;

	}
}

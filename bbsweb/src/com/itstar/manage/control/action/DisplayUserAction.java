package com.itstar.manage.control.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itstar.dao.UserDao;
import com.itstar.manage.control.form.AddUserForm;
import com.itstar.manage.dbconnection.DatabaseConnection;
import com.itstar.model.BBSUser;
import com.itstar.util.Page;

public class DisplayUserAction extends DispatchAction {

	/**
	 * Method execute
	 * �鿴�û���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("GBK");
		
		response.setCharacterEncoding("GBK");	
		
		// �������·�ҳ����
		int currentPage = 1;
		// ���մ������ĵ�ǰҳ
		try {
			currentPage = Integer.parseInt(request.getParameter("cp"));
		} catch (Exception e) {
		}
		// ��ȡ��Ϣ�б�
		try {
			UserDao udao2 = new UserDao();
			List<BBSUser> array = udao2.getArray(request);
			new Page(array,String.valueOf(currentPage));
			request.setAttribute("array", Page.relist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", Page.allpage);
			request.setAttribute("allRecorders", Page.allCol);
		} catch (RuntimeException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return mapping.findForward("showUser");
	}
	
	/**
	 * ɾ���û�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");//ȡ���û����
		boolean isDelTopic = false;
//	    if(request.getParameter("isDelTopic").equals("true")) {
//	    	isDelTopic = true;
//	    }
		UserDao udao2 = new UserDao();
		udao2.deleteUser(id,isDelTopic);
		return mapping.findForward("delete");
	}
	/**
	 * ���ð���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");//ȡ�����Ӻ�
		UserDao udao2 = new UserDao();
		udao2.setUser(id);
		
		return mapping.findForward("delete");
	}
	
	
	/**
	 * ���ù���Ա
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");//ȡ�����Ӻ�
		UserDao udao2 = new UserDao();
		udao2.setManage(id);
		
		return mapping.findForward("delete");
	}
	
	
	/**
	 * �鿴�û�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");//ȡ�����Ӻ�
		UserDao udao2 = new UserDao();
		BBSUser user = udao2.getUserInfo(Integer.parseInt(id));
		
		request.setAttribute("user",user);
		
		return mapping.findForward("view");
	}
	
}

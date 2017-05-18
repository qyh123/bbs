package com.itstar.bbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.itstar.bbs.form.EdituserForm;
import com.itstar.dao.UserDao;
import com.itstar.util.UpLoad;

public class EditUserAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * 修改用户信息 Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EdituserForm edituserForm = (EdituserForm) form;// TODO Auto-generated
														// method stub
		HttpSession session = request.getSession();
		FormFile image = null;
		String fileName = "";
		int i = 0;
		UserDao dao = new UserDao();
		UpLoad up = new UpLoad();
		image = edituserForm.getImage();
		String url = servlet.getServletContext().getRealPath("/uploadHeadImage");
		int fileSize = image.getFileSize();
		try {
			if (image.getFileName() != null && !image.getFileName().equals("")&&fileSize!=0) {
				//fileName = (url + "\\" + up.upload(url, image));
				fileName = "uploadHeadImage\\"+up.upload(url,image);
				edituserForm.setFileName(fileName);
				i = dao.updateUser(edituserForm,url);
				
				//del.delete(fileName);

			} else {
				fileName = "uploadHeadImage\\noavatar.gif";
				edituserForm.setFileName(fileName);
				i = dao.updateUser(edituserForm,url);
			}

		} catch (Exception e) {
			request.setAttribute("error", "文件上传失败");
			return mapping.findForward("error");
		}
		if (i == 1) {
			request.setAttribute("message", "editsuccess");
			session.setAttribute("user", dao
					.getUser(edituserForm.getUserName()));

		} else {
			request.setAttribute("message", "editerror");
		}
		return mapping.findForward("edituser");
	}
}
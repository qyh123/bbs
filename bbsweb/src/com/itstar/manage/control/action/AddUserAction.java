package com.itstar.manage.control.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.itstar.bbs.form.UserForm;
import com.itstar.dao.UserDao;
import com.itstar.util.DeleteFile;
import com.itstar.util.UpLoad;
public class AddUserAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws SQLException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		response.setContentType("text/html;charset=GBK");
	    request.setCharacterEncoding("GBK");
    	FormFile image = null;
    	String fileName = "";
    	boolean bl = true;
    	UpLoad up = new UpLoad();
    	DeleteFile del = new DeleteFile();
	    image =userForm.getImage();
	    int fileSize = image.getFileSize();
        String url = servlet.getServletContext().getRealPath("/uploadHeadImage");
	 	try {
	 		//不重复提交
		    if(isTokenValid(request)){
		    	if(image != null && fileSize!=0) {
		 			//fileName = (url+"\\"+up.upload(url,image));
		    		fileName = "uploadHeadImage\\"+up.upload(url,image);
		 			userForm.setFileName(fileName);
		 			 
		 			UserDao udao1 = new UserDao();
		 			bl = udao1.addUser(userForm);
			    	//del.delete(fileName);
		 		} else {
		 			fileName = (url+"\\"+"noavatar.gif");
		 			userForm.setFileName(fileName);
			 			UserDao udao1 = new UserDao();
		 			bl = udao1.addUser(userForm);
		 		}
		    	if(bl) {
		    	    request.setAttribute("message", "addsuccess");
		    	}
		    	saveToken(request);
		    	
		    	
			} else{
				request.setAttribute("message", "adderror");
				resetToken(request); 
		     }
	 	} catch (Exception e) {
	 		request.setAttribute("message", "adderror");
		}
       return mapping.findForward("point");
   }
}
package com.itstar.util;

import java.io.InputStream;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.itstar.manage.dbconnection.DatabaseConnection;

public class OutPicture extends HttpServlet {
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, java.io.IOException {
		String sql = null;
		DatabaseConnection dbconn = new DatabaseConnection();
		ResultSet rs = null;
		// ����ResultSet�������������
		String userId = request.getParameter("userId");
		// Integer id = Integer.parseInt(request.getParameter("id"));
		// System.out.println("���ܵ��ģ�"+id);
		sql = "select UserImage from BBSUser WHERE UserId=" + userId;
		// �����Ҫ��ʾͼƬ�ı��id����ת��Ϊ����

		try {
			rs = dbconn.Query(sql,dbconn);
			rs.next();
			// if (rs.getBinaryStream(1) != null) {
			InputStream in = rs.getBinaryStream(1);
			ServletOutputStream sout = response.getOutputStream();
			int len = 0;
			byte[] b = new byte[1024];
			response.reset();
			response.setContentType("image/gif");
			while ((len = in.read(b)) > 0) {
				sout.write(b, 0, len);
			}
			in.close();
			sout.close();
			// }
			rs.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.out.println("�˻�Ա������Ŀǰ��û���ϴ��Լ���ͷ��");
		}finally{
			dbconn.close();
		}
	}
}

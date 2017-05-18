package com.itstar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts.upload.FormFile;

public class UpLoad {
	/**
	 * �ļ��ϴ�
	 * 
	 * @param dir
	 *            �ϴ��ļ���·��
	 * @param formFile
	 *            �ϴ����ļ�
	 * @return ���������µ��ļ���
	 */
	public String upload(String dir, FormFile formFile) throws Exception {

		Date date = new Date();
		// ȡ���ϴ����ļ������ֺͳ���
		String fname = formFile.getFileName();
		File uploadFile = new File(fname);
		// ���ϴ�ʱ������ļ���
		int i = fname.lastIndexOf(".");
		String name = String.valueOf(date.getTime());
		String type = fname.substring(i + 1);
		fname = name + "." + type;
		InputStream streamIn = formFile.getInputStream(); // ������ȡ�û��ϴ��ļ��Ķ���
		uploadFile = new File(dir);
		// �������ϴ�����д��Ŀ���ļ��Ķ���
		if (!uploadFile.exists() || uploadFile == null) { // �ж�ָ��·���Ƿ���ڣ��������򴴽�·��
			uploadFile.mkdirs();
		}
		String path = uploadFile.getPath() + "/" + fname;
		OutputStream streamOut = new FileOutputStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
		formFile.destroy();
		return fname;
	}
}

package com.itstar.util;

import java.io.File;
/**
 * 
 */
public class DeleteFile {
	
	/**
	 * ɾ���ļ�����
	 * @param fileName 
	 * @return boolean
	 */
	public void  delete(String fileName) {

		File file = new File(fileName);
		if(!file.exists()) {
			System.out.print(fileName+"�ļ�������");
		} else {
			if(file.isFile()) {
			    file.delete();
			} 
		}
	}
	
//	public static void main(String arg[]) {
//		DeleteFile del = new DeleteFile();
//		del.delete("E:\\Java\\Tomcat 5.5\\webapps\\bbsweb\\uploadHeadImage\\1265164155625.gif");
//	}

}

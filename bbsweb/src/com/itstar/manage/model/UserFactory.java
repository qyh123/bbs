package com.itstar.manage.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.itstar.manage.dbconnection.*;

public abstract class UserFactory {

//	public Iterator ListUsers() {
//		ArrayList arraylist = new ArrayList();
//		String str = "select * from BBSManager";
//		User user;
//		try {
//			DBConnection dbconnect = new DBConnection();
//			dbconnect.excuteQuery(str);
//			while (dbconnect.next()) {
//				user = Factory.getInstance().InitUser();
//				user.setManagerName(dbconnect.getString("ManagerName"));
//				user.setManagerPassword(dbconnect.getString("ManagerPassword"));
//				arraylist.add(user);
//			}
//			dbconnect.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return arraylist.iterator();
//	}

	public User ListUser(String ManagerName) {
		String str = "select * from BBSManager where ManagerName='" +ManagerName+ "'";
		//User user = Factory.getInstance().InitUser();
		User user=Factory.getInstance().InitUser();
		try {
			DBConnection dbconnect = new DBConnection();
			dbconnect.excuteQuery(str);
			if (dbconnect.next()) {
				user.setManagerName(dbconnect.getString("ManagerName"));
				user.setManagerPassword(dbconnect.getString("ManagerPassword"));
			} else
				user = null;
			dbconnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

//	public abstract int Add(User user,int pID);
	public abstract User List(String ManagerName);
	public abstract String ChkUser(String ManagerName, String ManagerPassword);
//	public abstract String ChkSex(String userid, String password);



}

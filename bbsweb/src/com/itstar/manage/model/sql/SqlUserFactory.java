package com.itstar.manage.model.sql;

import com.itstar.manage.dbconnection.*;
import com.itstar.manage.model.*;
import com.itstar.manage.model.sql.*;


public class SqlUserFactory extends UserFactory {

	public String ChkUser(String ManagerName, String ManagerPassword ) {
		String info = "0";
		User user=this.ListUser(ManagerName);
		//用户名不为空
		//密码正确
		if (user!= null) {
			if ((user.getManagerPassword()).equals(ManagerPassword))
				info = "success";
			else
				//密码错误
				info = "2";
				
		} else
		{
			//用户名为空
			info = "1";
		}
		return info;

	}


	
	
/*	public String ChkSex(String userid, String password) {
		// TODO Auto-generated method stub
		String sexinfo = "1";
		User user = this.ListUser(userid);
		if (user != null) {
			if ((user.getManagerName().equals("1")))
				sexinfo = "1";
			else
				sexinfo = "0";
		}
		return sexinfo;
	}*/

	public User List(String ManagerName) {
		User user = Factory.getInstance().InitUser();
		String str = "select * from BBSManager where userID='" +ManagerName + "'";
		try {
			DBConnection dbconnect = new DBConnection(str);
			dbconnect.excuteQuery(str);
			if (dbconnect.next()) {
				user.setManagerName(dbconnect.getString(1));
				user.setManagerPassword(dbconnect.getString(2));
			}
			dbconnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}


/*	public int Add(User user, int pID) {
		int state = 0;
		String userID = user.getUserID();
		String userPassword = user.getUserPassword();
		String userName = user.getUserName();
		String sex=user.getSex();
		String userAddress = user.getUserAddress();
		String postalCode = user.getPostalCode();
		String eMail = user.getEMail();
		int userType = 1;
		String userPhone = user.getUserPhone();
		String userMobile = user.getUserMobil();
		String question = user.getQuestion();
		String answer = user.getAnswer();

		String str = "insert into userinfo(userID,userPassword,userName,sex,userAddress,postalCode,eMail,userType,userPhone,userMobil,question,answer) values('"
				+ userID
				+ "','"
				+ userPassword
				+ "','"
				+ userName
				+ "','"
				+ sex
				+ "','"
				+ userAddress
				+ "','"
				+ postalCode
				+ "','"
				+ eMail
				+ "','"
				+ userType
				+ "','"
				+ userPhone
				+ "','"
				+ userMobile
				+ "','"
				+ question
				+ "','"
				+ answer + "')";
		try {
			DBConnection dbconnect = new DBConnection(str);
			dbconnect.excuteUpdate();
			dbconnect.close();
			state = 1;
		} catch (Exception e) {
			e.printStackTrace();
			state = 0;
		}
		return state;
	}
*/
	/*@Override
	public int EditPassword(String ID,String passwordd) {
		int state = 0;
	
		//String str = "update userinfo set Password='"+Password+"' where ID='"+ID+"'";
		try
		{
	//		DBConnection dbconnect = new DBConnection(str);
		//	dbconnect.excuteUpdate();
		//	dbconnect.close();
			state=1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			state = 0;
		}
		return state;
	}*/

}

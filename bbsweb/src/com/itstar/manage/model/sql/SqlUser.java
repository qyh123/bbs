package com.itstar.manage.model.sql;

import com.itstar.manage.model.User;

public class SqlUser implements User {
	private String ManagerName;
	private String ManagerPassword;
	public String getManagerName() {
		// TODO Auto-generated method stub
		return ManagerName;
	}
	public String getManagerPassword() {
		// TODO Auto-generated method stub
		return ManagerPassword;
	}
	public void setManagerName(String ManagerName) {
		// TODO Auto-generated method stub
		this.ManagerName=ManagerName;
	}
	public void setManagerPassword(String ManagerPassword) {
		// TODO Auto-generated method stub
	this.ManagerPassword=ManagerPassword;	
	}
	


}

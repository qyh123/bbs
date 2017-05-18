package com.itstar.dao;

public class ManageDao {
	private static ManageDao getmanageDao = new ManageDao();

	private ManageDao() {

	}
	public static ManageDao getInstance() {
		return getmanageDao;
	}
	
}

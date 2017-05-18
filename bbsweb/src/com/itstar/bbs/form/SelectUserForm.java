/**
 * 
 */
package com.itstar.bbs.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 */
public class SelectUserForm  extends ActionForm{
	String userSearch;
	String user;

	public String getUserSearch() {
		return userSearch;
	}

	public void setUserSearch(String userSearch) {
		this.userSearch = userSearch;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}

package com.itstar.model;

/**
 * 
 * �һ�����
 */
public class BBSPassword {
	
	/** �û���� */
	private Integer passwordUserId;
	
	/** ������ */
	private Integer passwordId;
	
	/** �û��� */
	private String userName;
	 
	/** �û����� */
	private String userEmail;
	
	/** ɾ����־ */
	private int delsign;
	
	/** �һص�ʱ�� */
	private String findTime;

	
	/**
	 * @return the passwordUserId
	 */
	public Integer getPasswordUserId() {
		return passwordUserId;
	}

	/**
	 * @param passwordUserId the passwordUserId to set
	 */
	public void setPasswordUserId(Integer passwordUserId) {
		this.passwordUserId = passwordUserId;
	}

	/**
	 * @return the passwordId
	 */
	public Integer getPasswordId() {
		return passwordId;
	}

	/**
	 * @param passwordId the passwordId to set
	 */
	public void setPasswordId(Integer passwordId) {
		this.passwordId = passwordId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the findTime
	 */
	public String getFindTime() {
		return findTime;
	}

	/**
	 * @param findTime the findTime to set
	 */
	public void setFindTime(String findTime) {
		this.findTime = findTime;
	}

	/**
	 * @return the delsign
	 */
	public int getDelsign() {
		return delsign;
	}

	/**
	 * @param delsign the delsign to set
	 */
	public void setDelsign(int delsign) {
		this.delsign = delsign;
	}
	
}

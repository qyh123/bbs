/**
 * 
 */
package com.itstar.model;

import java.awt.Image;


/**
 */
public class BBSUser {
	
	/** 用户编号*/
	private Integer UserId;
    
	/** 用户名*/
	private String UserName;

	/** 用户密码*/
	private String UserPassword;
	
	/** 用户昵称*/
	private String UserNName;

	/** 用户头像*/
	private Image UserImage;
	
	/** 用户头像*/
	private String HeadImage;

	/** 用户性别编号*/
	private Integer UserSex;
	
	/** 用户性别*/
	private String UserSexName;

	/** 用户电子邮箱*/
	private String UserEmail;

	/** 用户注册时间*/
	private String UserRegDate;

	/** 用户级别编号*/
	private Integer UserClass;
	
	/** 用户级别名称*/
	private String UserClassName;

	/** 用户积分*/
	private Integer UserPoint;
	
	/** 用户状态*/
	private Integer bunState;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return UserId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		UserId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return UserPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	/**
	 * @return the userNName
	 */
	public String getUserNName() {
		return UserNName;
	}

	/**
	 * @param userNName the userNName to set
	 */
	public void setUserNName(String userNName) {
		UserNName = userNName;
	}

	/**
	 * @return the userImage
	 */
	public Image getUserImage() {
		return UserImage;
	}

	/**
	 * @param userImage the userImage to set
	 */
	public void setUserImage(Image userImage) {
		UserImage = userImage;
	}

	/**
	 * @return the userSex
	 */
	public Integer getUserSex() {
		return UserSex;
	}

	/**
	 * @param userSex the userSex to set
	 */
	public void setUserSex(Integer userSex) {
		UserSex = userSex;
	}

	/**
	 * @return the userSexName
	 */
	public String getUserSexName() {
		return UserSexName;
	}

	/**
	 * @param userSexName the userSexName to set
	 */
	public void setUserSexName(String userSexName) {
		UserSexName = userSexName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return UserEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	/**
	 * @return the userRegDate
	 */
	public String getUserRegDate() {
		return UserRegDate;
	}

	/**
	 * @param userRegDate the userRegDate to set
	 */
	public void setUserRegDate(String userRegDate) {
		UserRegDate = userRegDate;
	}

	/**
	 * @return the userClass
	 */
	public Integer getUserClass() {
		return UserClass;
	}

	/**
	 * @param userClass the userClass to set
	 */
	public void setUserClass(Integer userClass) {
		UserClass = userClass;
	}

	/**
	 * @return the userClassName
	 */
	public String getUserClassName() {
		return UserClassName;
	}

	/**
	 * @param userClassName the userClassName to set
	 */
	public void setUserClassName(String userClassName) {
		UserClassName = userClassName;
	}

	/**
	 * @return the userPoint
	 */
	public Integer getUserPoint() {
		return UserPoint;
	}

	/**
	 * @param userPoint the userPoint to set
	 */
	public void setUserPoint(Integer userPoint) {
		UserPoint = userPoint;
	}

	/**
	 * @return the bunState
	 */
	public Integer getBunState() {
		return bunState;
	}

	/**
	 * @param bunState the bunState to set
	 */
	public void setBunState(Integer bunState) {
		this.bunState = bunState;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return HeadImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		HeadImage = headImage;
	}

	
}

package com.itstar.model;

import java.awt.Image;

public class ReplyAndUser {
	//用户表
	private Integer UserId;

	private String UserName;

	private String UserPassword;

	private String UserNName;

	private String UserImage;

	private Integer UserSex;

	private String UserEmail;

	private String UserRegDate;
	
	private String UserClassName;

	private Integer UserClass;

	private Integer UserPoint;
	
	private Integer BunState;
	//回复表
	private Integer ReplyID;

	private Integer ReplyTopicId;

	private Integer ReplyUserId;

	private String ReplyContent;

	private String ReplyTime;
	
	private Integer ReplyState;

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserNName() {
		return UserNName;
	}

	public void setUserNName(String userNName) {
		UserNName = userNName;
	}

	public String getUserImage() {
		return UserImage;
	}

	public void setUserImage(String userImage) {
		UserImage = userImage;
	}

	public Integer getUserSex() {
		return UserSex;
	}

	public void setUserSex(Integer userSex) {
		UserSex = userSex;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserRegDate() {
		return UserRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		UserRegDate = userRegDate;
	}

	public Integer getUserClass() {
		return UserClass;
	}

	public void setUserClass(Integer userClass) {
		UserClass = userClass;
	}

	public Integer getUserPoint() {
		return UserPoint;
	}

	public void setUserPoint(Integer userPoint) {
		UserPoint = userPoint;
	}

	public Integer getReplyID() {
		return ReplyID;
	}

	public void setReplyID(Integer replyID) {
		ReplyID = replyID;
	}

	public Integer getReplyTopicId() {
		return ReplyTopicId;
	}

	public void setReplyTopicId(Integer replyTopicId) {
		ReplyTopicId = replyTopicId;
	}

	public Integer getReplyUserId() {
		return ReplyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		ReplyUserId = replyUserId;
	}

	public String getReplyContent() {
		return ReplyContent;
	}

	public void setReplyContent(String replyContent) {
		ReplyContent = replyContent;
	}

	public String getReplyTime() {
		return ReplyTime;
	}

	public void setReplyTime(String replyTime) {
		ReplyTime = replyTime;
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
	 * @return the bunState
	 */
	public Integer getBunState() {
		return BunState;
	}

	/**
	 * @param bunState the bunState to set
	 */
	public void setBunState(Integer bunState) {
		BunState = bunState;
	}

	/**
	 * @return the replyState
	 */
	public int getReplyState() {
		return ReplyState;
	}

	/**
	 * @param replyState the replyState to set
	 */
	public void setReplyState(Integer replyState) {
		ReplyState = replyState;
	}
}

package com.itstar.model;

/**
 * 
 * 
 */
public class BBSPosts {
	

	/** 帖子的编号*/
	private Integer postsId;
	
	/** 主题帖子的编号*/
	private Integer topicId;
	
	/** 帖子的标志
	 * 有两个值：1代表此贴子是主题；0代表此帖子是回贴*/
	private Integer postsSign;
	
	/** 版块的编号*/
	private Integer topicSectionId;

    /** 发表主题或者回帖时间*/
	private String postsTime;
	
	/** 发表主题或者回帖的内容*/
	private String postsContent;
	
	/** 作者的编号*/
	private Integer userId;
	
	/** 主题帖子的姓名*/
	private String author;
    
	/** 作者的姓名*/
	private String userName;
	
	/** 版块的名称*/
	private String sectionName;

	/**
	 * @return the topicSectionId
	 */
	public Integer getTopicSectionId() {
		return topicSectionId;
	}

	/**
	 * @param topicSectionId the topicSectionId to set
	 */
	public void setTopicSectionId(Integer topicSectionId) {
		this.topicSectionId = topicSectionId;
	}

	/**
	 * @return the postsTime
	 */
	public String getPostsTime() {
		return postsTime;
	}

	/**
	 * @param postsTime the postsTime to set
	 */
	public void setPostsTime(String postsTime) {
		this.postsTime = postsTime;
	}

	/**
	 * @return the postsContent
	 */
	public String getPostsContent() {
		return postsContent;
	}

	/**
	 * @param postsContent the postsContent to set
	 */
	public void setPostsContent(String postsContent) {
		this.postsContent = postsContent;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * @return the postsId
	 */
	public Integer getPostsId() {
		return postsId;
	}

	/**
	 * @param postsId the postsId to set
	 */
	public void setPostsId(Integer postsId) {
		this.postsId = postsId;
	}

	/**
	 * @return the postsSign
	 */
	public Integer getPostsSign() {
		return postsSign;
	}

	/**
	 * @param postSign the postsSign to set
	 */
	public void setPostSign(Integer postsSign) {
		this.postsSign = postsSign;
	}

	/**
	 * @return the topicId
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	/**
	 * @param postsSign the postsSign to set
	 */
	public void setPostsSign(Integer postsSign) {
		this.postsSign = postsSign;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}

package com.itstar.model;

/**
 * 
 * 
 */
public class BBSPosts {
	

	/** ���ӵı��*/
	private Integer postsId;
	
	/** �������ӵı��*/
	private Integer topicId;
	
	/** ���ӵı�־
	 * ������ֵ��1��������������⣻0����������ǻ���*/
	private Integer postsSign;
	
	/** ���ı��*/
	private Integer topicSectionId;

    /** ����������߻���ʱ��*/
	private String postsTime;
	
	/** ����������߻���������*/
	private String postsContent;
	
	/** ���ߵı��*/
	private Integer userId;
	
	/** �������ӵ�����*/
	private String author;
    
	/** ���ߵ�����*/
	private String userName;
	
	/** ��������*/
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

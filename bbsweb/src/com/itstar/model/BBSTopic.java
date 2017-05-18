/**
 * 
 */
package com.itstar.model;

/**
 * 
 */
public class BBSTopic {
	
	/**帖子的编号*/
	private Integer TopicId;
	
	/**帖子的所在的版块编号*/
	private Integer TopicSectionId;
	
	/**帖子的作者的编号*/
	private Integer TopicUserId;
	
	/**帖子的回帖量*/
	private Integer TopicReplyCount;
	
	/**帖子标题*/
	private String TopicTopic;
	
	/**帖子内容*/
	private String TopicContent;
	
	/**帖子发布的时间*/
	private String TopicTime;
	
	/**帖子是否是精华*/
	private Integer TopicState;
	
	/**帖子作者的姓名*/
	private String UserName;
	
	/**所在的版块名称*/
	private String SectionName;
	
	/**置顶的时间*/
	private String TopTime;
	
	/**帖子是否是屏蔽状态*/
	private Integer TopicBunState;
	
	/**是否是新帖*/
	private boolean newTopic;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getTopicContent() {
		return TopicContent;
	}

	public void setTopicContent(String topicContent) {
		TopicContent = topicContent;
	}

	public Integer getTopicReplyCount() {
		return TopicReplyCount;
	}

	public void setTopicReplyCount(Integer topicReplyCount) {
		TopicReplyCount = topicReplyCount;
	}

	public Integer getTopicSectionId() {
		return TopicSectionId;
	}

	public void setTopicSectionId(Integer topicSectionId) {
		TopicSectionId = topicSectionId;
	}

	public Integer getTopicState() {
		return TopicState;
	}

	public void setTopicState(Integer topicState) {
		TopicState = topicState;
	}

	public String getTopicTime() {
		return TopicTime;
	}

	public void setTopicTime(String topicTime) {
		TopicTime = topicTime;
	}

	public String getTopicTopic() {
		return TopicTopic;
	}

	public void setTopicTopic(String topicTopic) {
		TopicTopic = topicTopic;
	}

	public Integer getTopicUserId() {
		return TopicUserId;
	}

	public void setTopicUserId(Integer topicUserId) {
		TopicUserId = topicUserId;
	}

	public Integer getTopicId() {
		return TopicId;
	}

	public void setTopicId(Integer topicId) {
		TopicId = topicId;
	}

	public String getTopicStateName() {
		if(this.getTopicState().equals(new Integer(1))){
			return "是";
		}
		return "否";
	}


	public String getTopicTopName() {
		if(null!=this.getTopTime()){
			return "是";
		}
		return "否";
	}


	public String getSectionName() {
		return SectionName;
	}

	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	/**
	 * @return the topTime
	 */
	public String getTopTime() {
		return TopTime;
	}

	/**
	 * @param topTime the topTime to set
	 */
	public void setTopTime(String topTime) {
		TopTime = topTime;
	}

	/**
	 * @return the newTopic
	 */
	public boolean isNewTopic() {
		return newTopic;
	}

	/**
	 * @param newTopic the newTopic to set
	 */
	public void setNewTopic(boolean newTopic) {
		this.newTopic = newTopic;
	}
	
	/**
	 * @return the topicBunState
	 */
	public int getTopicBunState() {
		return TopicBunState;
	}

	/**
	 * @param topicBunState the topicBunState to set
	 */
	public void setTopicBunState(Integer topicBunState) {
		TopicBunState = topicBunState;
	}
}

/**
 * 
 */
package com.itstar.model;

/**
 * 
 */
public class BBSTopic {
	
	/**���ӵı��*/
	private Integer TopicId;
	
	/**���ӵ����ڵİ����*/
	private Integer TopicSectionId;
	
	/**���ӵ����ߵı��*/
	private Integer TopicUserId;
	
	/**���ӵĻ�����*/
	private Integer TopicReplyCount;
	
	/**���ӱ���*/
	private String TopicTopic;
	
	/**��������*/
	private String TopicContent;
	
	/**���ӷ�����ʱ��*/
	private String TopicTime;
	
	/**�����Ƿ��Ǿ���*/
	private Integer TopicState;
	
	/**�������ߵ�����*/
	private String UserName;
	
	/**���ڵİ������*/
	private String SectionName;
	
	/**�ö���ʱ��*/
	private String TopTime;
	
	/**�����Ƿ�������״̬*/
	private Integer TopicBunState;
	
	/**�Ƿ�������*/
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
			return "��";
		}
		return "��";
	}


	public String getTopicTopName() {
		if(null!=this.getTopTime()){
			return "��";
		}
		return "��";
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

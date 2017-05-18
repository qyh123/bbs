package com.itstar.manage.control.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	
   private int SectionId;
   private String TopicTopic;
   private String TopicContent;
   private String userID;
   private String userName;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}



	public int getSectionId() {
		return SectionId;
	}

	public void setSectionId(int sectionId) {
		SectionId = sectionId;
	}

	public String getTopicContent() {

		return TopicContent;
	}

	public void setTopicContent(String topicContent) {
		TopicContent = topicContent;
	}

	public String getTopicTopic() {

		return TopicTopic;
	}

	public void setTopicTopic(String topicTopic) {

		TopicTopic = topicTopic;
	}


}

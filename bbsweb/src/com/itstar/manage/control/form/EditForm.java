package com.itstar.manage.control.form;

import org.apache.struts.action.ActionForm;

public class EditForm extends ActionForm {
	private String TopicTopic;
	private String TopicContent;
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

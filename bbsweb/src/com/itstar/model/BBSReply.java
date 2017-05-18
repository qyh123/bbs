/**
 * 
 */
package com.itstar.model;

/**
 * 
 */
public class BBSReply {
	private Integer ReplyID;

	private Integer ReplyTopicId;

	private Integer ReplyUserId;

	private String ReplyContent;

	private String ReplyTime;

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

	
}

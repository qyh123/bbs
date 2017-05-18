package com.itstar.bbs.form;

import org.apache.struts.action.ActionForm;

public class ReplyForm extends ActionForm {
	
	private String repleyId;
	
	private String  repleyTopicId;
	
	private String repleyUserId;
	
	private String replyContent;

	private String replytime;
	
	private Integer delsign;
	
	/**
	 * @return the replyContent
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * @param replyContent the replyContent to set
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * @return the replytime
	 */
	public String getReplytime() {
		return replytime;
	}

	/**
	 * @param replytime the replytime to set
	 */
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	/**
	 * @return the delsign
	 */
	public Integer getDelsign() {
		return delsign;
	}

	/**
	 * @param delsign the delsign to set
	 */
	public void setDelsign(Integer delsign) {
		this.delsign = delsign;
	}

	/**
	 * @return the repleyId
	 */
	public String getRepleyId() {
		return repleyId;
	}

	/**
	 * @param repleyId the repleyId to set
	 */
	public void setRepleyId(String repleyId) {
		this.repleyId = repleyId;
	}

	/**
	 * @return the repleyTopicId
	 */
	public String getRepleyTopicId() {
		return repleyTopicId;
	}

	/**
	 * @param repleyTopicId the repleyTopicId to set
	 */
	public void setRepleyTopicId(String repleyTopicId) {
		this.repleyTopicId = repleyTopicId;
	}

	/**
	 * @return the repleyUserId
	 */
	public String getRepleyUserId() {
		return repleyUserId;
	}

	/**
	 * @param repleyUserId the repleyUserId to set
	 */
	public void setRepleyUserId(String repleyUserId) {
		this.repleyUserId = repleyUserId;
	}

	
	
}

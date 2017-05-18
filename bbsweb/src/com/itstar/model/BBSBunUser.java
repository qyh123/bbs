package com.itstar.model;

public class BBSBunUser {
	
	/** 禁止表的自动编号 */
	private Integer bunId;
	
	/** 禁止表的自动编号 */
	private Integer bunUserId;
	
	/** 状态 */
	private String bunState;
	
	/** 禁止的开始时间 */
	private String beginTime;
	
	/** 禁止的结束时间 */
	private String overTime;
	 
	/** 禁止的理由 */
	private String bunReason;
	
	/** 删除标志 */
	private String delsign;

	/**
	 * @return the bunId
	 */
	public Integer getBunId() {
		return bunId;
	}

	/**
	 * @param bunId the bunId to set
	 */
	public void setBunId(Integer bunId) {
		this.bunId = bunId;
	}

	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the overTime
	 */
	public String getOverTime() {
		return overTime;
	}

	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	/**
	 * @return the bunReason
	 */
	public String getBunReason() {
		return bunReason;
	}

	/**
	 * @param bunReason the bunReason to set
	 */
	public void setBunReason(String bunReason) {
		this.bunReason = bunReason;
	}

	/**
	 * @return the bunUserId
	 */
	public Integer getBunUserId() {
		return bunUserId;
	}

	/**
	 * @param bunUserId the bunUserId to set
	 */
	public void setBunUserId(Integer bunUserId) {
		this.bunUserId = bunUserId;
	}

	/**
	 * @return the bunState
	 */
	public String getBunState() {
		return bunState;
	}

	/**
	 * @param bunState the bunState to set
	 */
	public void setBunState(String bunState) {
		this.bunState = bunState;
	}

	/**
	 * @return the delsign
	 */
	public String getDelsign() {
		return delsign;
	}

	/**
	 * @param delsign the delsign to set
	 */
	public void setDelsign(String delsign) {
		this.delsign = delsign;
	}

	
}

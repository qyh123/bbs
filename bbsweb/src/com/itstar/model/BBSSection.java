/**
 * 
 */
package com.itstar.model;

/**
 * 
 */
public class BBSSection {
	private Integer SectionId;

	private String SectionName;

	private String SectionMasterID;

	private String SectionProfile;

	private Integer SectionTopicCount;

	private Integer SectionState;
	
//	private String SectionStateName;
	
	private String SectionTop;
	
//	private String SectionTopName;

	public String getSectionStateName() {
			if(this.getSectionState().equals(new Integer(1))){
				return "ÊÇ";
			}
			return "·ñ";
		}

	public Integer getSectionId() {
		return SectionId;
	}

	public void setSectionId(Integer sectionId) {
		SectionId = sectionId;
	}

	public String getSectionName() {
		return SectionName;
	}

	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public String getSectionMasterID() {
		return SectionMasterID;
	}

	public void setSectionMasterID(String sectionMasterID) {
		SectionMasterID = sectionMasterID;
	}

	public String getSectionProfile() {
		return SectionProfile;
	}

	public void setSectionProfile(String sectionProfile) {
		SectionProfile = sectionProfile;
	}

	public Integer getSectionTopicCount() {
		return SectionTopicCount;
	}

	public void setSectionTopicCount(Integer sectionTopicCount) {
		SectionTopicCount = sectionTopicCount;
	}

	public Integer getSectionState() {
		return SectionState;
	}

	public void setSectionState(Integer sectionState) {
		SectionState = sectionState;
	}

	public String getSectionTop() {
		return SectionTop;
	}

	public void setSectionTop(String sectionTop) {
		SectionTop = sectionTop;
	}

	public String getSectionTopName() {
		if(this.getSectionTop().equals("1")){
			return "ÊÇ";
		}
		return "·ñ";
	}
	
}

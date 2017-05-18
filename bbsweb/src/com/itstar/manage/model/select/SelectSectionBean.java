/**
 * 
 */
package com.itstar.manage.model.select;

/**
 * 
 */
public class SelectSectionBean {
	private int    SectionId;
	private String SectionName;
	private String SectionMasterName;
	private String SectionTopicCount;
	private String SectionProfile;

	public String getSectionName() {
		return SectionName;
	}

	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public String getSectionMasterName() {
		return SectionMasterName;
	}

	public void setSectionMasterName(String sectionMasterName) {
		SectionMasterName = sectionMasterName;
	}

	public String getSectionTopicCount() {
		return SectionTopicCount;
	}

	public void setSectionTopicCount(String sectionTopicCount) {
		SectionTopicCount = sectionTopicCount;
	}

	public String getSectionProfile() {
		return SectionProfile;
	}

	public void setSectionProfile(String sectionProfile) {
		SectionProfile = sectionProfile;
	}

	public int getSectionId() {
		return SectionId;
	}

	public void setSectionId(int sectionId) {
		SectionId = sectionId;
	}

}

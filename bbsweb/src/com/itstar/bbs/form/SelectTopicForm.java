/**
 * 
 */
package com.itstar.bbs.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 */
public class SelectTopicForm extends ActionForm {
	String sectionSearch;
	String note;

	public String getSectionSearch() {
		return sectionSearch;
	}

	public void setSectionSearch(String sectionSearch) {
		this.sectionSearch = sectionSearch;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

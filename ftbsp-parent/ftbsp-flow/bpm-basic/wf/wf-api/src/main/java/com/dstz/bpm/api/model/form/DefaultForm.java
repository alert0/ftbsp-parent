package com.dstz.bpm.api.model.form;

import com.dstz.base.core.util.StringUtil;
import com.dstz.form.api.model.FormCategory;

public class DefaultForm implements BpmForm {
	private String name;
	private FormCategory type;
	private String formValue;
	private String formHandler;
	private String formHtml;
	private String nodeId;

	public String getFormHandler() {
		if (FormCategory.INNER == this.type) {
			return null;
		}
		return this.formHandler;
	}

	public void setFormHandler(String formHandler) {
		this.formHandler = formHandler;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormCategory getType() {
		return this.type;
	}

	public void setType(FormCategory type) {
		this.type = type;
	}

	public String getFormValue() {
		return this.formValue;
	}

	public void setFormValue(String formValue) {
		this.formValue = formValue;
	}

	public void setId(String id) {
	}

	public String getId() {
		return "";
	}

	public boolean isFormEmpty() {
		return StringUtil.isEmpty(this.formValue);
	}

	public String getFormHtml() {
		return this.formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}
}
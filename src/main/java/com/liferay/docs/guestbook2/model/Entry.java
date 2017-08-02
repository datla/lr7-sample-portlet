package com.liferay.docs.guestbook2.model;

public class Entry {
	private String type;
	private String description;

	public Entry() {
		this.type = null;
		this.description = null;
	}

	public Entry(String type, String description) {
		setType(type);
		setDescription(description);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

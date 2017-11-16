package com.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Quibble {

	private int id;
	private String text;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

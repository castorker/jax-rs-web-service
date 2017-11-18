package com.webservice.model;

import java.io.Serializable;

// import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Quibble implements Serializable {

	private int id;
	private String text;
	private String category;
	private Author author;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// @XmlElement(name="joke")
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
}

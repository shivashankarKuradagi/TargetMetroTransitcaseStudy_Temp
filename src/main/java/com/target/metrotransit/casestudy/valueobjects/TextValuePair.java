package com.target.metrotransit.casestudy.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextValuePair {
	
	@JsonProperty("Text")
	private String text;
	
	@JsonProperty("Value")
	private String value;

	public TextValuePair() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextValuePair(String text, String value) {
		super();
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	

}

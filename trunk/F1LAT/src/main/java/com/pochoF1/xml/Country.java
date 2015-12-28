package com.pochoF1.xml;

import java.io.Serializable;

public class Country implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String abbr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public Country(String id, String name, String abbr) {
		super();
		this.id = id;
		this.name = name;
		this.abbr = abbr;
	}
	public Country() {
		super();
	}

	
}

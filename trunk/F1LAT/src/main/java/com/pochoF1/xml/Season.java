package com.pochoF1.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Season implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String year;
	private String complete;
	private List<Race> races;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public List<Race> getRaces() {
		return races;
	}
	public void setRaces(List<Race> races) {
		this.races = races;
	}
	
	public Season(String id, String year, String complete, List<Race> races) {
		super();
		this.id = id;
		this.year = year;
		this.complete = complete;
		this.races = races;
	}
	public Season() {
		this.races = new ArrayList<Race>();
	}
	
	
}


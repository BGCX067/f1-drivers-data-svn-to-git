package com.pochoF1.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Day implements Serializable{
	private static final long serialVersionUID = 3722486873360686029L;
	private String date;
	private String type;
	private String night;
	private String complete;
	private String low;
	private String high;
	private String icon;
	private String name;
	private List<Session> sessions;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public Day(String date, String type, String night, String complete,
			String low, String high, String icon, String name,
			List<Session> sessions) {
		super();
		this.date = date;
		this.type = type;
		this.night = night;
		this.complete = complete;
		this.low = low;
		this.high = high;
		this.icon = icon;
		this.name = name;
		this.sessions = sessions;
	}
	public Day() {
		this.sessions = new ArrayList<Session>();
	}
}

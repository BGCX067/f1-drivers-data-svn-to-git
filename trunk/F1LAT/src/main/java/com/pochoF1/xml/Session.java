package com.pochoF1.xml;

import java.io.Serializable;

public class Session implements Serializable{
	private static final long serialVersionUID = -6175402818839551256L;
	private String id;
	private String type;
	private String complete;
	private String officialResults;
	private String local;
	private String utc;
	private String duration;
	private String resultUrl;
	private String name;
	private String abbr;
	private String startTime;
	private String endTime;
	private String date;
	private String qualifying107PctTime;
	private String icon;
	private String caption;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getOfficialResults() {
		return officialResults;
	}
	public void setOfficialResults(String officialResults) {
		this.officialResults = officialResults;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getUtc() {
		return utc;
	}
	public void setUtc(String utc) {
		this.utc = utc;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Session(String id, String type, String complete,
			String officialResults, String local, String utc, String duration,
			String resultUrl, String name, String abbr, String startTime,
			String endTime, String date) {
		super();
		this.id = id;
		this.type = type;
		this.complete = complete;
		this.officialResults = officialResults;
		this.local = local;
		this.utc = utc;
		this.duration = duration;
		this.resultUrl = resultUrl;
		this.name = name;
		this.abbr = abbr;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}
	public Session() {
		super();
	}
	public String getQualifying107PctTime() {
		return qualifying107PctTime;
	}
	public void setQualifying107PctTime(String qualifying107PctTime) {
		this.qualifying107PctTime = qualifying107PctTime;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Session(String id, String name, String icon, String caption) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.caption = caption;
	}
}

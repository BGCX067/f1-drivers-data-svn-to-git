package com.pochoF1.stats;

import java.io.Serializable;

public class PilotoPunPosTemp implements Serializable{

	private static final long serialVersionUID = 1L;
	private Float points;
	private Integer position;
	private Integer season;
	
	public Float getPoints() {
		return points;
	}
	public void setPoints(Float points) {
		this.points = points;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
}

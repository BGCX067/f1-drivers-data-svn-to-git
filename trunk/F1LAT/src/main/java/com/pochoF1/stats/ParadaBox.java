package com.pochoF1.stats;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pitstops")
public class ParadaBox implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ParadaBoxId id;
	
	private Integer lap;
	private Time time;
	private String duration;
	private Integer milliseconds;
	
	public ParadaBox() {
		super();
	}
	public ParadaBox(ParadaBoxId id, Integer lap, Time time, String duration, Integer milliseconds) {
		super();
		this.id = id;
		this.lap = lap;
		this.time = time;
		this.duration = duration;
		this.milliseconds = milliseconds;
	}
	public ParadaBoxId getId() {
		return id;
	}
	public void setId(ParadaBoxId id) {
		this.id = id;
	}
	public Integer getLap() {
		return lap;
	}
	public void setLap(Integer lap) {
		this.lap = lap;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(Integer milliseconds) {
		this.milliseconds = milliseconds;
	}
	
}

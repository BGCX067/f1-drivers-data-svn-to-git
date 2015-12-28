package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="laptimes")
public class Vuelta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private VueltaId id;
	private Integer position;
	private String time;
	private Integer milliseconds;
	
	public Vuelta() {
		super();
	}
	public Vuelta(VueltaId id, Integer position, String time, Integer milliseconds) {
		super();
		this.id = id;
		this.position = position;
		this.time = time;
		this.milliseconds = milliseconds;
	}
	public VueltaId getId() {
		return id;
	}
	public void setId(VueltaId id) {
		this.id = id;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(Integer milliseconds) {
		this.milliseconds = milliseconds;
	}
	
	

}

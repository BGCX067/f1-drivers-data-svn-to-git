package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="laptimes")
public class VueltaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private VueltaEntityId id;
	private Integer position;
	private String time;
	@Column(name="milliseconds")
	private Integer timeMilis;
	
	public VueltaEntityId getId() {
		return id;
	}
	public void setId(VueltaEntityId id) {
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
	public Integer getTimeMilis() {
		return timeMilis;
	}
	public void setTimeMilis(Integer timeMilis) {
		this.timeMilis = timeMilis;
	}
	
	public VueltaEntity(VueltaEntityId id, Integer position, String time,
			Integer timeMilis) {
		this.id = id;
		this.position = position;
		this.time = time;
		this.timeMilis = timeMilis;
	}
	public VueltaEntity() {
	}
	
	

}

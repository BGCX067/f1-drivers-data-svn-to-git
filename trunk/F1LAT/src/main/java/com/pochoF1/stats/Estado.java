package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Estado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer statusId;
	private String status;
	
	public Estado() {
		super();
	}
	public Estado(Integer statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

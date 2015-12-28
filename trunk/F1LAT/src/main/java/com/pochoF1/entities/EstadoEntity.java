package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="status")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class EstadoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer statusId;
	private String status;
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
	public EstadoEntity(Integer statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public EstadoEntity() {
	}
	
	

}

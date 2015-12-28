package com.pochoF1.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="drivers")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class PilotoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer driverId;
	private String driverRef;
	private String forename;
	private String surname;
	private Date dob;
	private String nationality;
	private String url;
	
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public String getDriverRef() {
		return driverRef;
	}
	public void setDriverRef(String driverRef) {
		this.driverRef = driverRef;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public PilotoEntity(Integer driverId, String driverRef, String forename,
			String surname, Date dob, String nationality, String url) {
		super();
		this.driverId = driverId;
		this.driverRef = driverRef;
		this.forename = forename;
		this.surname = surname;
		this.dob = dob;
		this.nationality = nationality;
		this.url = url;
	}
	public PilotoEntity() {
	}
	
}

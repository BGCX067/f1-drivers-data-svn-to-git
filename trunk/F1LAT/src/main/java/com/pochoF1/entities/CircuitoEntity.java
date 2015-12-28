package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="circuits")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class CircuitoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer circuitId;
	private String circuitRef;
	private String name;
	private String location;
	private String country;
	private Float lat;
	private Float lng;
	private Integer alt;
	private String url;
	
	public Integer getCircuitId() {
		return circuitId;
	}
	public void setCircuitId(Integer circuitId) {
		this.circuitId = circuitId;
	}
	public String getCircuitRef() {
		return circuitRef;
	}
	public void setCircuitRef(String circuitRef) {
		this.circuitRef = circuitRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	public Integer getAlt() {
		return alt;
	}
	public void setAlt(Integer alt) {
		this.alt = alt;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CircuitoEntity(Integer circuitId, String circuitRef, String name,
			String location, String country, Float lat, Float lng, Integer alt,
			String url) {
		super();
		this.circuitId = circuitId;
		this.circuitRef = circuitRef;
		this.name = name;
		this.location = location;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.url = url;
	}
	public CircuitoEntity() {
	}
	
	

}

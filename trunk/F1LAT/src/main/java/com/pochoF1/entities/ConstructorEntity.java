package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="constructors")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ConstructorEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer constructorId;
	private String constructorRef;
	private String name;
	private String nationality;
	private String url;
	
	public Integer getConstructorId() {
		return constructorId;
	}
	public void setConstructorId(Integer constructorId) {
		this.constructorId = constructorId;
	}
	public String getConstructorRef() {
		return constructorRef;
	}
	public void setConstructorRef(String constructorRef) {
		this.constructorRef = constructorRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ConstructorEntity(Integer constructorId, String constructorRef,
			String name, String nationality, String url) {
		super();
		this.constructorId = constructorId;
		this.constructorRef = constructorRef;
		this.name = name;
		this.nationality = nationality;
		this.url = url;
	}
	public ConstructorEntity() {
	}
	
	
	
}

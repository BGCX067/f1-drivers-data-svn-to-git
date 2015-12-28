package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seasons")
public class Temporada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer year;
	private String url;
	
	public Temporada() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Temporada(Integer year, String url) {
		super();
		this.year = year;
		this.url = url;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

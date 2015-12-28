package com.pochoF1.xml;

import java.io.Serializable;

public class Circuit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private GrandPrix grandPrix;
	private Country country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GrandPrix getGrandPrix() {
		return grandPrix;
	}
	public void setGrandPrix(GrandPrix grandPrix) {
		this.grandPrix = grandPrix;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Circuit() {
		this.grandPrix = new GrandPrix();
		this.country = new Country();
	}
	public Circuit(String id, String name, GrandPrix grandPrix, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.grandPrix = grandPrix;
		this.country = country;
	}
	
	
	
}

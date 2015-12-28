package com.pochoF1.xml;

import java.io.Serializable;

public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Season season;

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Data(Season season) {
		super();
		this.season = season;
	}

	public Data() {
		super();
	}
	
	
	

}

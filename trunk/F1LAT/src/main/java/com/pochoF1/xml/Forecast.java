package com.pochoF1.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Forecast implements Serializable{
	private static final long serialVersionUID = 7019799911079539152L;
	private List<Day> days;
	
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
	public Forecast(List<Day> days) {
		super();
		this.days = days;
	}
	public Forecast() {
		super();
		this.days = new ArrayList<Day>();
	}
	
	
}

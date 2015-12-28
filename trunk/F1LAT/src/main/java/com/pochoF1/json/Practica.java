package com.pochoF1.json;

import java.util.List;

public class Practica {
	private String title;
	private String status;
	private String weather_text;
	private Temperatura temperature;
	private List<Resultados> results;
	private List<Incidentes> incidents;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWeather_text() {
		return weather_text;
	}
	public void setWeather_text(String weather_text) {
		this.weather_text = weather_text;
	}
	public Temperatura getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperatura temperature) {
		this.temperature = temperature;
	}
	public List<Resultados> getResults() {
		return results;
	}
	public void setResults(List<Resultados> results) {
		this.results = results;
	}
	public List<Incidentes> getIncidents() {
		return incidents;
	}
	public void setIncidents(List<Incidentes> incidents) {
		this.incidents = incidents;
	}
	
	

}

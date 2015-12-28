package com.pochoF1.model.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DatosCarrera {
	
	private String driverId;
	@SerializedName("Races")
	private List<Carrera> carreras;
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public List<Carrera> getRaces() {
		return carreras;
	}
	public void setRaces(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	
}

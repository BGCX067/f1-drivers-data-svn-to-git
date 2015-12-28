package com.pochoF1.model.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DatosPiloto {
	
	private String driverId;
	@SerializedName("Drivers")
	private List<Piloto> pilotos;
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public List<Piloto> getPilotos() {
		return pilotos;
	}
	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}
	
	

}

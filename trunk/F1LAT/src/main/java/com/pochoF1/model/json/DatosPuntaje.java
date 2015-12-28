package com.pochoF1.model.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DatosPuntaje {
	
	private String driverId;
	@SerializedName("StandingsLists")
	private List<Puntaje> listaPuntajes;
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public List<Puntaje> getListaPuntajes() {
		return listaPuntajes;
	}
	public void setListaPuntajes(List<Puntaje> listaPuntajes) {
		this.listaPuntajes = listaPuntajes;
	}
	
	
}

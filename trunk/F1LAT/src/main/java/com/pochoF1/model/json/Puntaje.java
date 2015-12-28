package com.pochoF1.model.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Puntaje {
	
	private Integer season;
	private Integer round;
	@SerializedName("DriverStandings")
	private List<PuntajePiloto> puntajePiloto;
	
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public List<PuntajePiloto> getPuntajePiloto() {
		return puntajePiloto;
	}
	public void setPuntajePiloto(List<PuntajePiloto> puntajePiloto) {
		this.puntajePiloto = puntajePiloto;
	}
	
	
	

}

package com.pochoF1.model.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PuntajePiloto {
	
	private Integer position;
	private String positionText;
	private Float points;
	private Integer wins;
	private String driverId;
	@SerializedName("Driver")
	private Piloto piloto;
	@SerializedName("Constructors")
	private List<Equipo> equipo;
	

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getPositionText() {
		return positionText;
	}
	public void setPositionText(String positionText) {
		this.positionText = positionText;
	}
	public Float getPoints() {
		return points;
	}
	public void setPoints(Float points) {
		this.points = points;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public List<Equipo> getEquipo() {
		return equipo;
	}
	public void setEquipo(List<Equipo> equipo) {
		this.equipo = equipo;
	}
	
}

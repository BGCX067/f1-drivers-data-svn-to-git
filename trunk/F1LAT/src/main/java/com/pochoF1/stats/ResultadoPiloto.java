package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="driverstandings")
public class ResultadoPiloto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer driverStandingsId;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Piloto piloto;
	
	private Float points;
	private Integer position;
	private String positionText;
	private Integer wins;
	
	public ResultadoPiloto() {
		super();
	}
	public ResultadoPiloto(Integer driverStandingsId, Carrera carrera, Piloto piloto, Float points, Integer position, String positionText, Integer wins) {
		super();
		this.driverStandingsId = driverStandingsId;
		this.carrera = carrera;
		this.piloto = piloto;
		this.points = points;
		this.position = position;
		this.positionText = positionText;
		this.wins = wins;
	}
	public Integer getDriverStandingsId() {
		return driverStandingsId;
	}
	public void setDriverStandingsId(Integer driverStandingsId) {
		this.driverStandingsId = driverStandingsId;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public Float getPoints() {
		return points;
	}
	public void setPoints(Float points) {
		this.points = points;
	}
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
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	
	
	
}
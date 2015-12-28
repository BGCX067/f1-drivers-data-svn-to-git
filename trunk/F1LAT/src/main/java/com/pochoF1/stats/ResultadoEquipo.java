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
@Table(name="constructorstandings")
public class ResultadoEquipo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer constructorStandingsId;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="constructorId")
	private Equipo equipo;
	
	private Float points;
	private Integer position;
	private String positionText;
	private Integer wins;
	
	public ResultadoEquipo() {
		super();
	}
	public ResultadoEquipo(Integer constructorStandingsId, Carrera carrera, Equipo equipo, Float points, Integer position, String positionText, Integer wins) {
		super();
		this.constructorStandingsId = constructorStandingsId;
		this.carrera = carrera;
		this.equipo = equipo;
		this.points = points;
		this.position = position;
		this.positionText = positionText;
		this.wins = wins;
	}
	public Integer getConstructorStandingsId() {
		return constructorStandingsId;
	}
	public void setConstructorStandingsId(Integer constructorStandingsId) {
		this.constructorStandingsId = constructorStandingsId;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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


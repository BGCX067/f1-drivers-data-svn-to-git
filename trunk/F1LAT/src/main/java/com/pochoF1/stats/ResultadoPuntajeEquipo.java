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
@Table(name="constructorresults")
public class ResultadoPuntajeEquipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer constructorResultsId;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="constructorId")
	private Equipo equipo;
	
	private Float points;
	private String status;
	
	
	public ResultadoPuntajeEquipo() {
		super();
	}
	public ResultadoPuntajeEquipo(Integer constructorResultsId, Carrera carrera, Equipo equipo, Float points, String status) {
		super();
		this.constructorResultsId = constructorResultsId;
		this.carrera = carrera;
		this.equipo = equipo;
		this.points = points;
		this.status = status;
	}
	public Integer getConstructorResultsId() {
		return constructorResultsId;
	}
	public void setConstructorResultsId(Integer constructorResultsId) {
		this.constructorResultsId = constructorResultsId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
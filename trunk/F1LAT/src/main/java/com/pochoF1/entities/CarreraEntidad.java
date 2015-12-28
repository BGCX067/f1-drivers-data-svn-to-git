package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carreras")
public class CarreraEntidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String carrera;
	private String ronda;
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getRonda() {
		return ronda;
	}
	public void setRonda(String ronda) {
		this.ronda = ronda;
	}
	public CarreraEntidad(String carrera, String ronda) {
		super();
		this.carrera = carrera;
		this.ronda = ronda;
	}
	public CarreraEntidad() {
	}
	
	

}

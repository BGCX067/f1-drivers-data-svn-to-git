package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Embeddable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class VueltaEntityId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private CarreraEntity carrera;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private PilotoEntity piloto;
	@Column(name="lap")
	private Integer vuelta;
	
	public CarreraEntity getCarrera() {
		return carrera;
	}
	public void setCarrera(CarreraEntity carrera) {
		this.carrera = carrera;
	}
	public PilotoEntity getPiloto() {
		return piloto;
	}
	public void setPiloto(PilotoEntity piloto) {
		this.piloto = piloto;
	}
	public Integer getVuelta() {
		return vuelta;
	}
	public void setVuelta(Integer vuelta) {
		this.vuelta = vuelta;
	}
	public VueltaEntityId(CarreraEntity carrera, PilotoEntity piloto, Integer vuelta) {
		this.carrera = carrera;
		this.piloto = piloto;
		this.vuelta = vuelta;
	}
	public VueltaEntityId() {
	}
	
	

}

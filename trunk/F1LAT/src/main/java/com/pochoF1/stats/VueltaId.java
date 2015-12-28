package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class VueltaId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Piloto piloto;
	private Integer lap;
	
	public VueltaId() {
		super();
	}
	public VueltaId(Carrera carrera, Piloto piloto, Integer lap) {
		super();
		this.carrera = carrera;
		this.piloto = piloto;
		this.lap = lap;
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
	public Integer getLap() {
		return lap;
	}
	public void setLap(Integer lap) {
		this.lap = lap;
	}

	
}

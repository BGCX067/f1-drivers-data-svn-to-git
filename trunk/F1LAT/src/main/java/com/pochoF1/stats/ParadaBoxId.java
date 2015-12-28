package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ParadaBoxId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Piloto piloto;
	
	private Integer stop;

	public ParadaBoxId() {
		super();
	}

	public ParadaBoxId(Carrera carrera, Piloto piloto, Integer stop) {
		super();
		this.carrera = carrera;
		this.piloto = piloto;
		this.stop = stop;
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

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}
}

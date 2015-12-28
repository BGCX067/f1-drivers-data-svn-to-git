package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Ariel
 *
 */
@Entity
@Table(name="vueltas")
public class VueltaEntidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idVuelta;
	private Integer tiempo;
	private String tiempoVuelta;
	private Integer nroVuelta;
	private Boolean vueltaSalida;
	private Boolean pits;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idSesion")
    private SesionEntidad sesion;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idPiloto")
    private PilotoEntidad piloto;

	public Integer getIdVuelta() {
		return idVuelta;
	}

	public void setIdVuelta(Integer idVuelta) {
		this.idVuelta = idVuelta;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public String getTiempoVuelta() {
		return tiempoVuelta;
	}

	public void setTiempoVuelta(String tiempoVuelta) {
		this.tiempoVuelta = tiempoVuelta;
	}

	public Integer getNroVuelta() {
		return nroVuelta;
	}

	public void setNroVuelta(Integer nroVuelta) {
		this.nroVuelta = nroVuelta;
	}

	public Boolean getVueltaSalida() {
		return vueltaSalida;
	}

	public void setVueltaSalida(Boolean vueltaSalida) {
		this.vueltaSalida = vueltaSalida;
	}

	public Boolean getPits() {
		return pits;
	}

	public void setPits(Boolean pits) {
		this.pits = pits;
	}

	public SesionEntidad getSesion() {
		return sesion;
	}

	public void setSesion(SesionEntidad sesion) {
		this.sesion = sesion;
	}

	public PilotoEntidad getPiloto() {
		return piloto;
	}

	public void setPiloto(PilotoEntidad piloto) {
		this.piloto = piloto;
	}

	public VueltaEntidad(Integer idVuelta, Integer tiempo, String tiempoVuelta,
			Integer nroVuelta, Boolean vueltaSalida, Boolean pits,
			SesionEntidad sesion, PilotoEntidad piloto) {
		super();
		this.idVuelta = idVuelta;
		this.tiempo = tiempo;
		this.tiempoVuelta = tiempoVuelta;
		this.nroVuelta = nroVuelta;
		this.vueltaSalida = vueltaSalida;
		this.pits = pits;
		this.sesion = sesion;
		this.piloto = piloto;
	}

	public VueltaEntidad() {
		// TODO Auto-generated constructor stub
	}
	

	
}

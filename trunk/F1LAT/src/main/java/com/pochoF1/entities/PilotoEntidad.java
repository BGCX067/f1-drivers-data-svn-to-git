package com.pochoF1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pilotos")

public class PilotoEntidad implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idPiloto", unique = true, nullable = false)
	private Integer idPiloto;
	
	private String nombre;
	private Integer numero;
	private VueltaEntidad mejorVuelta;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="equipo")
    private EquipoEntidad equipo;
	
	@Transient
	private List<VueltaEntidad> vueltasActuales = new ArrayList<VueltaEntidad>();

	public Integer getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public EquipoEntidad getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntidad equipo) {
		this.equipo = equipo;
	}

	public List<VueltaEntidad> getVueltasActuales() {
		return vueltasActuales;
	}

	public void setVueltasActuales(List<VueltaEntidad> vueltasActuales) {
		this.vueltasActuales = vueltasActuales;
	}

	public PilotoEntidad(Integer idPiloto, String nombre, Integer numero, VueltaEntidad mejorVuelta,
			EquipoEntidad equipo, List<VueltaEntidad> vueltasActuales) {
		super();
		this.idPiloto = idPiloto;
		this.nombre = nombre;
		this.numero = numero;
		this.equipo = equipo;
		this.vueltasActuales = vueltasActuales;
		this.mejorVuelta = mejorVuelta;
	}

	public PilotoEntidad() {
	}

	public VueltaEntidad getMejorVuelta() {
		return mejorVuelta;
	}

	public void setMejorVuelta(VueltaEntidad mejorVuelta) {
		this.mejorVuelta = mejorVuelta;
	}
	

	
}

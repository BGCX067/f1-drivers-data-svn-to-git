package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="equipos")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EquipoEntidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String equipo;
    private String descripcion;

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EquipoEntidad(String equipo, String descripcion) {
		this.equipo = equipo;
		this.descripcion = descripcion;
	}

	public EquipoEntidad() {
	}

	
    
    
}

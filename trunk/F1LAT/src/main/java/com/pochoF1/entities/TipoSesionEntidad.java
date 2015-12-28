package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tipo_sesiones")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TipoSesionEntidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String tipoSesion;
	private String descripcion;
	public TipoSesionEntidad(String tipoSesion, String descripcion) {
		super();
		this.tipoSesion = tipoSesion;
		this.descripcion = descripcion;
	}
	public TipoSesionEntidad() {
	}
	public String getTipoSesion() {
		return tipoSesion;
	}
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}

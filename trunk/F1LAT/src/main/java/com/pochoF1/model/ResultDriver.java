package com.pochoF1.model;

import java.io.Serializable;

import com.pochoF1.enums.Equipos;

public class ResultDriver implements Serializable{

	private static final long serialVersionUID = 1L;
	public String posicion;
	public String nombre;
	public String nacionalidad;
	public Equipos equipo;
	public String puntaje;
	public String equipoString;
	public String equipoAbbr;
	
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	
	public Equipos getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	public String getEquipoString() {
		return equipoString;
	}
	public void setEquipoString(String equipoString) {
		this.equipoString = equipoString;
	}
	@Override
	public String toString() {
		return this.posicion + " - " + this.nombre + " [" + this.equipo + "]" +" - " + this.puntaje;
	}
	public String getEquipoAbbr() {
		return equipoAbbr;
	}
	public void setEquipoAbbr(String equipoAbbr) {
		this.equipoAbbr = equipoAbbr;
	}
	
	

}

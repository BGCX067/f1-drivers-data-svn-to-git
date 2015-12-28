package com.pochoF1.model;

import java.io.Serializable;

import com.pochoF1.enums.Equipos;

public class ResultTeam implements Serializable{

	private static final long serialVersionUID = 1L;
	private String posicion;
	private Equipos equipo;
	private String puntaje;
	private String year;
	public String equipoString;
	public String equipoAbbr;
	
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public Equipos getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return this.posicion + " - " + this.equipo + " - " + this.puntaje; 
	}
	public String getEquipoString() {
		return equipoString;
	}
	public void setEquipoString(String equipoString) {
		this.equipoString = equipoString;
	}
	public String getEquipoAbbr() {
		return equipoAbbr;
	}
	public void setEquipoAbbr(String equipoAbbr) {
		this.equipoAbbr = equipoAbbr;
	}
	
	
}

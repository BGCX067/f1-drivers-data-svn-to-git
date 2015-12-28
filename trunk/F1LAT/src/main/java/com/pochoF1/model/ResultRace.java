package com.pochoF1.model;

import java.io.Serializable;

import com.pochoF1.enums.Equipos;

public class ResultRace implements Serializable{

	private static final long serialVersionUID = 1L;
	private String posicion;
	private String numeroPiloto;
	private String piloto;
	private Equipos equipo;
	private String vueltas;
	private String tiempoTotal;
	private String grid;
	private String puntos;
	private String equipoString;
	
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getNumeroPiloto() {
		return numeroPiloto;
	}
	public void setNumeroPiloto(String numeroPiloto) {
		this.numeroPiloto = numeroPiloto;
	}
	public String getPiloto() {
		return piloto;
	}
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
	public Equipos getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	public String getVueltas() {
		return vueltas;
	}
	public void setVueltas(String vueltas) {
		this.vueltas = vueltas;
	}
	public String getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(String tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public String getGrid() {
		return grid;
	}
	public void setGrid(String grid) {
		this.grid = grid;
	}
	public String getPuntos() {
		return puntos;
	}
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	@Override
	public String toString() {
		return "\nPosicion " + this.posicion + " Piloto " + this.piloto ;
	}
	public String getEquipoString() {
		return equipoString;
	}
	public void setEquipoString(String equipoString) {
		this.equipoString = equipoString;
	}

}

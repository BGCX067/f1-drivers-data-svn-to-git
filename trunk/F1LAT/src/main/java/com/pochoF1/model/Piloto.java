package com.pochoF1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pochoF1.entities.PilotoEntidad;
import com.pochoF1.entities.VueltaEntidad;
import com.pochoF1.enums.Equipos;
import com.pochoF1.enums.Sesiones;

public class Piloto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private Equipos equipo;
	private int numero;
	private List<Vuelta> vueltasActuales;
	private HashMap<Sesiones, List<Vuelta>> vueltasPorSesion = new  HashMap<Sesiones, List<Vuelta>>();
	private Vuelta mejorVuelta;
	
	
	public Piloto() {
		vueltasActuales = new ArrayList<Vuelta>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Equipos getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public List<Vuelta> getVueltasActuales() {
		return vueltasActuales;
	}
	public void setVueltasActuales(List<Vuelta> vueltasActuales) {
		this.vueltasActuales = vueltasActuales;
	}
	public HashMap<Sesiones, List<Vuelta>> getVueltasPorSesion() {
		return vueltasPorSesion;
	}
	public void setVueltasPorSesion(HashMap<Sesiones, List<Vuelta>> vueltasPorSesion) {
		this.vueltasPorSesion = vueltasPorSesion;
	}
	@Override
	public String toString() {
		return this.nombre + " " + this.equipo;
	}
	
	public Piloto(PilotoEntidad piloto){
		this.nombre = piloto.getNombre();
		this.equipo = Equipos.valueOf(piloto.getEquipo().getEquipo());
		this.numero = piloto.getNumero();
		this.vueltasActuales = new ArrayList<Vuelta>();
		for(VueltaEntidad vuelta : piloto.getVueltasActuales()){
			vueltasActuales.add(new Vuelta(vuelta));
		}
	}
	public Vuelta getMejorVuelta() {
		return mejorVuelta;
	}
	public void setMejorVuelta(Vuelta mejorVuelta) {
		this.mejorVuelta = mejorVuelta;
	}
	
	
	
}

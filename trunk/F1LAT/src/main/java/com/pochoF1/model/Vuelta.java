package com.pochoF1.model;

import java.io.Serializable;

import com.pochoF1.entities.VueltaEntidad;
import com.pochoF1.enums.Neumaticos;

public class Vuelta implements Serializable{

	private static final long serialVersionUID = 1L;
	private int tiempo;
	private String tiempoVuelta;
	private int nroVuelta;
	private boolean vueltaSalida;
	private boolean pits;
	private Neumaticos neumatico;
	
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public int getNroVuelta() {
		return nroVuelta;
	}
	public void setNroVuelta(int nroVuelta) {
		this.nroVuelta = nroVuelta;
	}
	public boolean isVueltaSalida() {
		return vueltaSalida;
	}
	public void setVueltaSalida(boolean vueltaSalida) {
		this.vueltaSalida = vueltaSalida;
	}
	public boolean isPits() {
		return pits;
	}
	public void setPits(boolean pits) {
		this.pits = pits;
	}
	public Neumaticos getNeumatico() {
		return neumatico;
	}
	public void setNeumatico(Neumaticos neumatico) {
		this.neumatico = neumatico;
	}
	public String getTiempoVuelta() {
		return tiempoVuelta;
	}
	public void setTiempoVuelta(String tiempoVuelta) {
		this.tiempoVuelta = tiempoVuelta;
	}
	@Override
	public String toString() {
		return this.nroVuelta + " - " + this.tiempoVuelta + "[" + this.pits +"]";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vuelta){
			Vuelta v = (Vuelta) obj;
			if(this.nroVuelta == v.nroVuelta)
				return true;
			else
				return false;
		}
		return false;
	}
	public Vuelta() {
	}
	public Vuelta(VueltaEntidad vuelta) {
		this.nroVuelta = vuelta.getNroVuelta();
		this.pits = vuelta.getPits()!=null?vuelta.getPits():false;
		this.tiempo = vuelta.getTiempo();
		this.tiempoVuelta = vuelta.getTiempoVuelta();
		this.vueltaSalida = vuelta.getVueltaSalida()!=null?vuelta.getVueltaSalida():false;
	}
	
}

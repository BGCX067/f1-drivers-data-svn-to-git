package com.pochoF1.chart;

import java.io.Serializable;

public class PilotoTiempo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Integer tiempo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
}

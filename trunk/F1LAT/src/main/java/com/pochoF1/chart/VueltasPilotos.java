package com.pochoF1.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VueltasPilotos  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer numeroVuelta;
	private List<PilotoTiempo> pilotosTiempo = new ArrayList<PilotoTiempo>();
	
	public Integer getNumeroVuelta() {
		return numeroVuelta;
	}
	public void setNumeroVuelta(Integer numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}
	public List<PilotoTiempo> getPilotosTiempo() {
		return pilotosTiempo;
	}
	public void setPilotosTiempo(List<PilotoTiempo> pilotosTiempo) {
		this.pilotosTiempo = pilotosTiempo;
	}
	
	
}

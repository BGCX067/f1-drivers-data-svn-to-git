package com.pochoF1.model.json;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ResultadosPilotos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer total;
	private Integer limit;
	private Integer offset;
	private String url;
	private DatosCarrera RaceTable;
	@SerializedName("DriverTable")
	private DatosPiloto tablaPiloto;
	@SerializedName("StandingsTable")
	private DatosPuntaje standings;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public DatosCarrera getRaceTable() {
		return RaceTable;
	}
	public void setRaceTable(DatosCarrera raceTable) {
		RaceTable = raceTable;
	}
	public DatosPiloto getTablaPiloto() {
		return tablaPiloto;
	}
	public void setTablaPiloto(DatosPiloto tablaPiloto) {
		this.tablaPiloto = tablaPiloto;
	}
	public DatosPuntaje getStandings() {
		return standings;
	}
	public void setStandings(DatosPuntaje standings) {
		this.standings = standings;
	}
	
	
}

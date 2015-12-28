package com.pochoF1.stats;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="qualifying")
public class Clasificacion  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer qualifyId;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private Carrera carrera;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Piloto piloto;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="constructorId")
	private Equipo constructor;
	private Integer number;
	private Integer position;
	private String q1;
	private String q2;
	private String q3;
	
	public Clasificacion() {
		super();
	}
	public Clasificacion(Integer qualifyId, Carrera carrera, Piloto piloto, Equipo constructor, Integer number, Integer position, String q1, String q2, String q3) {
		super();
		this.qualifyId = qualifyId;
		this.carrera = carrera;
		this.piloto = piloto;
		this.constructor = constructor;
		this.number = number;
		this.position = position;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}
	public Integer getQualifyId() {
		return qualifyId;
	}
	public void setQualifyId(Integer qualifyId) {
		this.qualifyId = qualifyId;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public Equipo getConstructor() {
		return constructor;
	}
	public void setConstructor(Equipo constructor) {
		this.constructor = constructor;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getQ1() {
		return q1;
	}
	public void setQ1(String q1) {
		this.q1 = q1;
	}
	public String getQ2() {
		return q2;
	}
	public void setQ2(String q2) {
		this.q2 = q2;
	}
	public String getQ3() {
		return q3;
	}
	public void setQ3(String q3) {
		this.q3 = q3;
	}

	
}

package com.pochoF1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="results")
public class ResultadosEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer resultId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="raceId")
	private CarreraEntity carrera;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private PilotoEntity piloto;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="constructorId")
	private ConstructorEntity constructor;
	
	@Column(name="number")
	private Integer numeroPiloto;
	
	@Column(name="grid")
	private Integer posicionSalida;
	
	@Column(name="position")
	private Integer posicionFinal;
	
	@Column(name="positionText")
	private String posicionFinalTexto;
	
	@Column(name="positionOrder")
	private Integer posicionFinalOrden;
	
	@Column(name="points")
	private Float puntos;
	
	@Column(name="laps")
	private Integer vueltasCompletadas;

	@Column(name="time")
	private String tiempo;
	
	@Column(name="milliseconds")
	private Integer tiempoMilis;
	
	@Column(name="fastestLap")
	private Integer vueltaRapida;
	
	@Column(name="rank")
	private Integer vueltaRapidaRanking;
	
	@Column(name="fastestLapTime")
	private String vueltaRapidaTiempo;
	
	@Column(name="fastestLapSpeed")
	private String vueltaRapidaVelocidad;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="statusId")
	private EstadoEntity estado;

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public CarreraEntity getCarrera() {
		return carrera;
	}

	public void setCarrera(CarreraEntity carrera) {
		this.carrera = carrera;
	}

	public PilotoEntity getPiloto() {
		return piloto;
	}

	public void setPiloto(PilotoEntity piloto) {
		this.piloto = piloto;
	}

	public ConstructorEntity getConstructor() {
		return constructor;
	}

	public void setConstructor(ConstructorEntity constructor) {
		this.constructor = constructor;
	}

	public Integer getNumeroPiloto() {
		return numeroPiloto;
	}

	public void setNumeroPiloto(Integer numeroPiloto) {
		this.numeroPiloto = numeroPiloto;
	}

	public Integer getPosicionSalida() {
		return posicionSalida;
	}

	public void setPosicionSalida(Integer posicionSalida) {
		this.posicionSalida = posicionSalida;
	}

	public Integer getPosicionFinal() {
		return posicionFinal;
	}

	public void setPosicionFinal(Integer posicionFinal) {
		this.posicionFinal = posicionFinal;
	}

	public String getPosicionFinalTexto() {
		return posicionFinalTexto;
	}

	public void setPosicionFinalTexto(String posicionFinalTexto) {
		this.posicionFinalTexto = posicionFinalTexto;
	}

	public Integer getPosicionFinalOrden() {
		return posicionFinalOrden;
	}

	public void setPosicionFinalOrden(Integer posicionFinalOrden) {
		this.posicionFinalOrden = posicionFinalOrden;
	}

	public Float getPuntos() {
		return puntos;
	}

	public void setPuntos(Float puntos) {
		this.puntos = puntos;
	}

	public Integer getVueltasCompletadas() {
		return vueltasCompletadas;
	}

	public void setVueltasCompletadas(Integer vueltasCompletadas) {
		this.vueltasCompletadas = vueltasCompletadas;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getTiempoMilis() {
		return tiempoMilis;
	}

	public void setTiempoMilis(Integer tiempoMilis) {
		this.tiempoMilis = tiempoMilis;
	}

	public Integer getVueltaRapida() {
		return vueltaRapida;
	}

	public void setVueltaRapida(Integer vueltaRapida) {
		this.vueltaRapida = vueltaRapida;
	}

	public Integer getVueltaRapidaRanking() {
		return vueltaRapidaRanking;
	}

	public void setVueltaRapidaRanking(Integer vueltaRapidaRanking) {
		this.vueltaRapidaRanking = vueltaRapidaRanking;
	}

	public String getVueltaRapidaTiempo() {
		return vueltaRapidaTiempo;
	}

	public void setVueltaRapidaTiempo(String vueltaRapidaTiempo) {
		this.vueltaRapidaTiempo = vueltaRapidaTiempo;
	}

	public String getVueltaRapidaVelocidad() {
		return vueltaRapidaVelocidad;
	}

	public void setVueltaRapidaVelocidad(String vueltaRapidaVelocidad) {
		this.vueltaRapidaVelocidad = vueltaRapidaVelocidad;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
	}

	public ResultadosEntity(Integer resultId, CarreraEntity carrera,
			PilotoEntity piloto, ConstructorEntity constructor,
			Integer numeroPiloto, Integer posicionSalida,
			Integer posicionFinal, String posicionFinalTexto,
			Integer posicionFinalOrden, Float puntos,
			Integer vueltasCompletadas, String tiempo, Integer tiempoMilis,
			Integer vueltaRapida, Integer vueltaRapidaRanking,
			String vueltaRapidaTiempo, String vueltaRapidaVelocidad,
			EstadoEntity estado) {
		this.resultId = resultId;
		this.carrera = carrera;
		this.piloto = piloto;
		this.constructor = constructor;
		this.numeroPiloto = numeroPiloto;
		this.posicionSalida = posicionSalida;
		this.posicionFinal = posicionFinal;
		this.posicionFinalTexto = posicionFinalTexto;
		this.posicionFinalOrden = posicionFinalOrden;
		this.puntos = puntos;
		this.vueltasCompletadas = vueltasCompletadas;
		this.tiempo = tiempo;
		this.tiempoMilis = tiempoMilis;
		this.vueltaRapida = vueltaRapida;
		this.vueltaRapidaRanking = vueltaRapidaRanking;
		this.vueltaRapidaTiempo = vueltaRapidaTiempo;
		this.vueltaRapidaVelocidad = vueltaRapidaVelocidad;
		this.estado = estado;
	}

	public ResultadosEntity() {
	}

	
	
}

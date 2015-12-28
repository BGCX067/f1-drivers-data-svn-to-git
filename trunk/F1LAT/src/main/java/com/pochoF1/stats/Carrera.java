package com.pochoF1.stats;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="races")
public class Carrera implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Integer raceId;
	private Integer year;
	private Integer round;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="circuitId")
	private Circuito circuito;
	private String name;
	private Date date;
	private Time time;
	private String url;
	
	public Integer getRaceId() {
		return raceId;
	}
	public void setRaceId(Integer raceId) {
		this.raceId = raceId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public Circuito getCircuito() {
		return circuito;
	}
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Carrera(Integer raceId, Integer year, Integer round, Circuito circuito, String name, Date date, Time time, String url) {
		super();
		this.raceId = raceId;
		this.year = year;
		this.round = round;
		this.circuito = circuito;
		this.name = name;
		this.date = date;
		this.time = time;
		this.url = url;
	}
	public Carrera() {
		super();
	}
	
	

}

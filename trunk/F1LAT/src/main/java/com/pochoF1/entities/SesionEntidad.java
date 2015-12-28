package com.pochoF1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sesiones")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SesionEntidad implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idSesion", unique = true, nullable = false)
	private Integer idSesion;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoSesion")
	private TipoSesionEntidad tipoSesion;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="carrera")
	private CarreraEntidad carrera;
	
	private Integer year;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="sesiones_pilotos", uniqueConstraints= { @UniqueConstraint(columnNames = {"idSesion","idPiloto"}) }, joinColumns={@JoinColumn(name="idSesion", referencedColumnName="idSesion")}
    , inverseJoinColumns={@JoinColumn(name="idPiloto", referencedColumnName="idPiloto")})
	private List<PilotoEntidad> listaPilotos = new ArrayList<PilotoEntidad>();

	public Integer getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}

	public TipoSesionEntidad getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesionEntidad tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public CarreraEntidad getCarrera() {
		return carrera;
	}

	public void setCarrera(CarreraEntidad carrera) {
		this.carrera = carrera;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<PilotoEntidad> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(List<PilotoEntidad> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}
	
	
	
}
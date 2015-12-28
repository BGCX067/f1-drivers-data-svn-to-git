package com.pochoF1.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable {
	private static final long serialVersionUID = -8145472812903111448L;
	private String id;
	private String round;
	private String provisional;
	private String complete;
	private String date;
	private String name;
	private String image;
	private Circuit circuit;
	private String dates;
	private String distance;
	private String estimatedDuration;
	private String laps;
	private Forecast forecast;
	private List<Session> sessions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getProvisional() {
		return provisional;
	}

	public void setProvisional(String provisional) {
		this.provisional = provisional;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(String estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public String getLaps() {
		return laps;
	}

	public void setLaps(String laps) {
		this.laps = laps;
	}

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Race(String id, String round, String provisional, String complete, String date, String name, String image, Circuit circuit, String dates, String distance, String estimatedDuration, String laps, Forecast forecast, List<Session> sessions) {
		super();
		this.id = id;
		this.round = round;
		this.provisional = provisional;
		this.complete = complete;
		this.date = date;
		this.name = name;
		this.image = image;
		this.circuit = circuit;
		this.dates = dates;
		this.distance = distance;
		this.estimatedDuration = estimatedDuration;
		this.laps = laps;
		this.forecast = forecast;
		this.sessions = sessions;
	}

	public Race() {
		this.sessions = new ArrayList<Session>();
	}

	@Override
	public String toString() {
		return this.circuit.getCountry().getName() + "-" + this.name;
	}

	public Integer cantidadVueltas() {
		try {
			return Integer.parseInt(this.laps);
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Race))
			return false;

		Race other = (Race) o;
		return this.id.equalsIgnoreCase(other.getId());
	}
	
	public int hashCode(){
	    return (int) Integer.parseInt(this.id) * Integer.parseInt(this.laps) * Integer.parseInt(this.round);
	  }

}

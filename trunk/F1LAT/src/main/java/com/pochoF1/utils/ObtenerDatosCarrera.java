package com.pochoF1.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pochoF1.exceptions.DescargarFormulaOneException;
import com.pochoF1.exceptions.ParsingRaceTimeTableException;
import com.pochoF1.xml.Circuit;
import com.pochoF1.xml.Country;
import com.pochoF1.xml.Data;
import com.pochoF1.xml.Day;
import com.pochoF1.xml.Forecast;
import com.pochoF1.xml.GrandPrix;
import com.pochoF1.xml.Race;
import com.pochoF1.xml.Season;
import com.pochoF1.xml.Session;
import com.thoughtworks.xstream.XStream;

public class ObtenerDatosCarrera {

	private static Logger log = LoggerFactory.getLogger(ObtenerDatosCarrera.class);
	
	
	public static Race carreraPorFecha(Data data){
		Race carreraElegida = null;
		Date hoy = GregorianCalendar.getInstance().getTime();
		long diffDays = -1;
		for(Race r : data.getSeason().getRaces()){
			Date fechaCarrera = new Date();
			String[] fechaCarreraSplit = r.getDate().split("-");
			Calendar fechaCarreraCalendar = GregorianCalendar.getInstance();
			fechaCarreraCalendar.set(Integer.parseInt(fechaCarreraSplit[0]), Integer.parseInt(fechaCarreraSplit[1])-1, Integer.parseInt(fechaCarreraSplit[2]));
			fechaCarrera.setTime(fechaCarreraCalendar.getTimeInMillis());
			long diffDaysActual = (fechaCarrera.getTime() - hoy.getTime()) / (24 * 60 * 60 * 1000);
			if(diffDaysActual >= 0 && (diffDaysActual < diffDays || diffDays == -1)){
				diffDays = diffDaysActual;
				carreraElegida = r;
			}
		}
		return carreraElegida;
	}
	
	public static Season obtenerDatosTemporada() throws Exception{
		XStream xstream = new XStream();
		
		xstream.alias("data", Data.class);
		xstream.alias("season", Season.class);
		xstream.useAttributeFor(Season.class, "id");
		xstream.useAttributeFor(Season.class, "year");
		xstream.useAttributeFor(Season.class, "complete");
		xstream.alias("race", Race.class);
		xstream.useAttributeFor(Race.class, "id");
		xstream.useAttributeFor(Race.class, "round");
		xstream.useAttributeFor(Race.class, "provisional");
		xstream.useAttributeFor(Race.class, "complete");
		xstream.useAttributeFor(Race.class, "date");
		xstream.alias("circuit", Circuit.class);
		xstream.useAttributeFor(Circuit.class, "id");
		xstream.alias("grandPrix", GrandPrix.class);
		xstream.useAttributeFor(GrandPrix.class, "id");
		xstream.alias("country", Country.class);
		xstream.useAttributeFor(Country.class, "id");
		
		xstream.addImplicitCollection(Forecast.class, "days");
		xstream.alias("day", Day.class);
		xstream.useAttributeFor(Day.class, "date");
		xstream.useAttributeFor(Day.class, "type");
		xstream.useAttributeFor(Day.class, "night");
		xstream.useAttributeFor(Day.class, "complete");
		xstream.useAttributeFor(Day.class, "low");
		xstream.useAttributeFor(Day.class, "high");
		xstream.useAttributeFor(Day.class, "icon");
		xstream.useAttributeFor(Day.class, "name");
		xstream.addImplicitCollection(Day.class, "sessions");
		
		xstream.alias("session", Session.class);
		xstream.useAttributeFor(Session.class, "id");
		xstream.useAttributeFor(Session.class, "type");
		xstream.useAttributeFor(Session.class, "complete");
		xstream.useAttributeFor(Session.class, "officialResults");
		xstream.useAttributeFor(Session.class, "local");
		xstream.useAttributeFor(Session.class, "utc");
		xstream.useAttributeFor(Session.class, "duration");
		xstream.useAttributeFor(Session.class, "resultUrl");
		xstream.useAttributeFor(Session.class, "name");
		xstream.useAttributeFor(Session.class, "icon");
		xstream.useAttributeFor(Session.class, "caption");
		
		Date hoy = GregorianCalendar.getInstance().getTime();
		
		Boolean descargar= true;
		try {
			File archivo = new File(Constants.urlSave+"race_timetable_flash.xml");
			if(archivo.exists()){
				descargar= false;
				Date ultimaFechaModificacion = new Date();
				ultimaFechaModificacion.setTime(archivo.lastModified());
				long diffDaysArchivo = (hoy.getTime() - ultimaFechaModificacion.getTime()) / (24 * 60 * 60 * 1000);
				if(diffDaysArchivo>13)
					descargar = true;
			}
		} catch (Exception e1) {
			log.error("Hubo un error en el proceso de levantar archivo xml y buscar carrera");
			log.error(e1.getMessage(),e1);
		}
		
		if(descargar){
			URL website = new URL(Constants.urlFormula1TimeTable);
			HttpURLConnection con = Constants.obtenerHTTPConexion(website);
			
			InputStream is = null;
			try {
				is = con.getInputStream();
			} catch (FileNotFoundException e) {
				System.out.println("No se pudo descargar el archivo " + e.getMessage());
				throw new DescargarFormulaOneException(e.getMessage());
			}
			ReadableByteChannel rbc = Channels.newChannel(is);
			
			File files = new File(Constants.urlSave);
			if (!files.exists()) {
				files.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(Constants.urlSave+"race_timetable_flash.xml");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		}
		
		Data data = (Data) xstream.fromXML(new File(Constants.urlSave+"race_timetable_flash.xml"));
		
		return data.getSeason();
	}
	
	public static Race carreraPorFechaHoy() throws Exception{
		Race carreraElegida = null;
		Long empezo = System.currentTimeMillis();
		try {
			log.info("Empezó el proceso de obtener carrera por fecha de hoy");
			XStream xstream = new XStream();
			
			xstream.alias("data", Data.class);
			xstream.alias("season", Season.class);
			xstream.useAttributeFor(Season.class, "id");
			xstream.useAttributeFor(Season.class, "year");
			xstream.useAttributeFor(Season.class, "complete");
			xstream.alias("race", Race.class);
			xstream.useAttributeFor(Race.class, "id");
			xstream.useAttributeFor(Race.class, "round");
			xstream.useAttributeFor(Race.class, "provisional");
			xstream.useAttributeFor(Race.class, "complete");
			xstream.useAttributeFor(Race.class, "date");
			xstream.alias("circuit", Circuit.class);
			xstream.useAttributeFor(Circuit.class, "id");
			xstream.alias("grandPrix", GrandPrix.class);
			xstream.useAttributeFor(GrandPrix.class, "id");
			xstream.alias("country", Country.class);
			xstream.useAttributeFor(Country.class, "id");
			
			xstream.addImplicitCollection(Forecast.class, "days");
			xstream.alias("day", Day.class);
			xstream.useAttributeFor(Day.class, "date");
			xstream.useAttributeFor(Day.class, "type");
			xstream.useAttributeFor(Day.class, "night");
			xstream.useAttributeFor(Day.class, "complete");
			xstream.useAttributeFor(Day.class, "low");
			xstream.useAttributeFor(Day.class, "high");
			xstream.useAttributeFor(Day.class, "icon");
			xstream.useAttributeFor(Day.class, "name");
			xstream.addImplicitCollection(Day.class, "sessions");
			
			xstream.alias("session", Session.class);
			xstream.useAttributeFor(Session.class, "id");
			xstream.useAttributeFor(Session.class, "type");
			xstream.useAttributeFor(Session.class, "complete");
			xstream.useAttributeFor(Session.class, "officialResults");
			xstream.useAttributeFor(Session.class, "local");
			xstream.useAttributeFor(Session.class, "utc");
			xstream.useAttributeFor(Session.class, "duration");
			xstream.useAttributeFor(Session.class, "resultUrl");
			xstream.useAttributeFor(Session.class, "name");
			xstream.useAttributeFor(Session.class, "icon");
			xstream.useAttributeFor(Session.class, "caption");
			
			Date hoy = GregorianCalendar.getInstance().getTime();
			Boolean descargar= true;
			try {
				File archivo = new File(Constants.urlSave+"race_timetable_flash.xml");
				if(archivo.exists()){
					descargar= false;
					Date ultimaFechaModificacion = new Date();
					ultimaFechaModificacion.setTime(archivo.lastModified());
					long diffDaysArchivo = (hoy.getTime() - ultimaFechaModificacion.getTime()) / (24 * 60 * 60 * 1000);
					if(diffDaysArchivo>13)
						descargar = true;
				}
			} catch (Exception e1) {
				log.error("Hubo un error en el proceso de levantar archivo xml y buscar carrera");
				log.error(e1.getMessage(),e1);
			}
			
			if(descargar){
				URL website = new URL(Constants.urlFormula1TimeTable);
				HttpURLConnection con = Constants.obtenerHTTPConexion(website);
				InputStream is = null;
				
				try {
					is = con.getInputStream();
				} catch (FileNotFoundException e) {
					System.out.println("No se pudo descargar el archivo " + e.getMessage());
					throw new DescargarFormulaOneException(e.getMessage());
				}
				ReadableByteChannel rbc = Channels.newChannel(is);
				
				File files = new File(Constants.urlSave);
				if (!files.exists()) {
					files.mkdirs();
				}
				
				FileOutputStream fos = new FileOutputStream(Constants.urlSave+"race_timetable_flash.xml");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			}
				 
			Data data = (Data) xstream.fromXML(new File(Constants.urlSave+"race_timetable_flash.xml"));
			
			
			double diffDays = -1;
			for(Race r : data.getSeason().getRaces()){
				Date fechaCarrera = new Date();
				String[] fechaCarreraSplit = r.getDate().split("-");
				Calendar fechaCarreraCalendar = GregorianCalendar.getInstance();
				fechaCarreraCalendar.set(Integer.parseInt(fechaCarreraSplit[0]), Integer.parseInt(fechaCarreraSplit[1])-1, Integer.parseInt(fechaCarreraSplit[2]));
				fechaCarrera.setTime(fechaCarreraCalendar.getTimeInMillis());
				double diffDaysActual = Math.round(((double)(fechaCarrera.getTime() - hoy.getTime())) / ((double)(24 * 60 * 60 * 1000)));
				if(diffDaysActual >= 0 && (diffDaysActual < diffDays || diffDays == -1)){
					diffDays = diffDaysActual;
					carreraElegida = r;
				}
			}
		} catch (NumberFormatException e) {
			log.error(e.getMessage(),e);
			throw new ParsingRaceTimeTableException();
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ParsingRaceTimeTableException();
		}
		if(carreraElegida==null){
			log.error("La carrera elegida es null, revisar porque");
			throw new ParsingRaceTimeTableException();
		}
		log.info("Terminó el proceso de obtener carrera por fecha de hoy - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
		return carreraElegida;
	}
}

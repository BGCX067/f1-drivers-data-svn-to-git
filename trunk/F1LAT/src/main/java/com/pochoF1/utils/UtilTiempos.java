package com.pochoF1.utils;

import java.util.List;

import com.pochoF1.model.Piloto;
import com.pochoF1.model.Vuelta;

public class UtilTiempos {
	
	
	public static String getMedia(List<Integer> tiempos){
		int tiempoTotal = 0;
		
		for(Integer tiempo : tiempos)
			tiempoTotal+=tiempo;
		
		if(tiempos.size()>0)
			tiempoTotal = tiempoTotal/tiempos.size();
				
		int seconds = (int) (tiempoTotal / 1000) % 60 ;
		int minutes = (int) ((tiempoTotal / (1000*60)) % 60);
		int millis   = (int) (tiempoTotal - 1000*60*minutes - 1000*seconds);
		
		return minutes + ":" + String.format("%02d", seconds) + "." + String.format("%03d", millis);
	}
	
	public static String getMedia(Piloto p){
		int tiempoTotal = 0;
		
		for(Vuelta v: p.getVueltasActuales())
			tiempoTotal+=v.getTiempo();
		
		if( p.getVueltasActuales().size()>0)
			tiempoTotal = tiempoTotal/ p.getVueltasActuales().size();
				
		int seconds = (int) (tiempoTotal / 1000) % 60 ;
		int minutes = (int) ((tiempoTotal / (1000*60)) % 60);
		int millis   = (int) (tiempoTotal - 1000*60*minutes - 1000*seconds);
		
		return minutes + ":" + String.format("%02d", seconds) + "." + String.format("%03d", millis);
	}
	
	public static String getMediaPorVueltas(List<Vuelta> vueltas){
		int tiempoTotal = 0;
		
		for(Vuelta v: vueltas)
			tiempoTotal+=v.getTiempo();
		
		if( vueltas.size()>0)
			tiempoTotal = tiempoTotal/ vueltas.size();
				
		int seconds = (int) (tiempoTotal / 1000) % 60 ;
		int minutes = (int) ((tiempoTotal / (1000*60)) % 60);
		int millis   = (int) (tiempoTotal - 1000*60*minutes - 1000*seconds);
		
		
		return minutes + ":" + String.format("%02d", seconds) + "." + String.format("%03d", millis);
	}
	
	public static String getMediaSinPits(Piloto p){
		int tiempoTotal = 0;
		int cantidadTotalVueltas = 0;
		
		for(Vuelta v: p.getVueltasActuales()){
			if(!v.isPits()){
				tiempoTotal+=v.getTiempo();
				cantidadTotalVueltas++;
			}
		}
		
		if( cantidadTotalVueltas>0)
			tiempoTotal = tiempoTotal/ cantidadTotalVueltas;
				
		int seconds = (int) (tiempoTotal / 1000) % 60 ;
		int minutes = (int) ((tiempoTotal / (1000*60)) % 60);
		int millis   = (int) (tiempoTotal - 1000*60*minutes - 1000*seconds);
		
		return minutes + ":" + String.format("%02d", seconds) + "." + String.format("%03d", millis);
	}
	
	public static Integer getMediaSinPitsInteger(Piloto p){
		int tiempoTotal = 0;
		int cantidadTotalVueltas = 0;
		
		for(Vuelta v: p.getVueltasActuales()){
			if(!v.isPits() && !v.isVueltaSalida()){
				tiempoTotal+=v.getTiempo();
				cantidadTotalVueltas++;
			}
		}
		
		if( cantidadTotalVueltas>0)
			tiempoTotal = tiempoTotal/ cantidadTotalVueltas;
				
		return tiempoTotal;
	}
}

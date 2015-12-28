package com.pochoF1.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pochoF1.daos.PilotoDAO;
import com.pochoF1.daos.ResultadoDAO;
import com.pochoF1.daos.ResultadoPilotoDAO;
import com.pochoF1.enums.PilotosEnum;
import com.pochoF1.model.json.DatosPuntaje;
import com.pochoF1.model.json.Puntaje;
import com.pochoF1.stats.Piloto;
import com.pochoF1.stats.PilotoPunPosTemp;
import com.pochoF1.stats.Resultado;

public class UtilsPilotos {
	
private static Logger log = LoggerFactory.getLogger(ObtenerDatosCarrera.class);
	
	
	/*public static ResultadosPilotos obtenerResultadosPiloto(String driverId){
		ResultadosPilotos res = null;
		
		try {
			
			String urlConsulta = Constants.URLAPI + Constants.URLAPI_PILOTOS + Constants.URLAPI_PILOTOS_RESULTADOS; 
			urlConsulta = urlConsulta.replaceAll("%PILOTO%", driverId);
			
			log.info("LLamando a la URL: " + urlConsulta);
			
			URL url = new URL(urlConsulta);
			URLConnection uc = Constants.obtenerURLConexion(url);
			BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String inputLineString = "";
			while ((inputLineString = input.readLine()) != null) {
				sb.append(inputLineString);
			}
			input.close();
			String json = sb.toString();
			
			Long empezo = System.currentTimeMillis();
			
			Gson gson = new Gson();
			
			JsonParser jsonParser = new JsonParser();
			JsonObject o = (JsonObject) ((JsonObject)jsonParser.parse(json)).get("MRData");
			res = gson.fromJson(o, ResultadosPilotos.class);
			
			log.info("Terminó el proceso de obtener los resultados del piloto " + PilotosEnum.valueOf(driverId).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			
			return res;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return res;
		
	}*/

	public static List<Resultado> obtenerResultadosPiloto(String driverName){
		try {
			Long empezo = System.currentTimeMillis();
			List<Resultado> resultados = ResultadoDAO.getInstance().getResultadosByPiloto(driverName);
			log.info("Terminó el proceso de obtener los datos propios del piloto " + PilotosEnum.valueOf(driverName).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			return resultados;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	public static List<Resultado> obtenerResultadosPiloto(Piloto piloto){
		try {
			Long empezo = System.currentTimeMillis();
			List<Resultado> resultados = ResultadoDAO.getInstance().getResultadosByPiloto(piloto);
			log.info("Terminó el proceso de obtener los resultados del piloto " + piloto.getForename() + " " + piloto.getSurname() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			return resultados;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	
	/*public static DatosPiloto obtenerDatosPiloto(String driverId){
		ResultadosPilotos res = null;
		
		try {
			Long empezo = System.currentTimeMillis();
			
			String urlConsulta = Constants.URLAPI + Constants.URLAPI_PILOTOS + Constants.URLAPI_PILOTOS_DATOS; 
			urlConsulta = urlConsulta.replaceAll("%PILOTO%", driverId);
			
			log.info("LLamando a la URL: " + urlConsulta);
			
			URL url = new URL(urlConsulta);
			URLConnection uc = Constants.obtenerURLConexion(url);
			BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String inputLineString = "";
			while ((inputLineString = input.readLine()) != null) {
				sb.append(inputLineString);
			}
			input.close();
			String json = sb.toString();
			
			
			
			Gson gson = new Gson();
			
			JsonParser jsonParser = new JsonParser();
			JsonObject o = (JsonObject) ((JsonObject)jsonParser.parse(json)).get("MRData");
			res = gson.fromJson(o, ResultadosPilotos.class);
			
			log.info("Terminó el proceso de obtener los datos propios del piloto " + PilotosEnum.valueOf(driverId).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			
			return res.getTablaPiloto();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return res.getTablaPiloto();
		
	}*/
	
	public static Piloto obtenerDatosPiloto(String driverId){
		try {
			Long empezo = System.currentTimeMillis();
			Piloto piloto = PilotoDAO.getInstance().getPilotoByName(driverId);
			log.info("Terminó el proceso de obtener los datos propios del piloto " + PilotosEnum.valueOf(driverId).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			return piloto;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return null;
		
	}
	
	/*public static DatosPuntaje obtenerPuntajesPiloto(String driverId){
		ResultadosPilotos res = null;
		
		try {
			Long empezo = System.currentTimeMillis();
			
			String urlConsulta = Constants.URLAPI + Constants.URLAPI_PILOTOS + Constants.URLAPI_PILOTOS_PUNTAJES; 
			urlConsulta = urlConsulta.replaceAll("%PILOTO%", driverId);
			
			log.info("LLamando a la URL: " + urlConsulta);
			
			URL url = new URL(urlConsulta);
			URLConnection uc = Constants.obtenerURLConexion(url);
			BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String inputLineString = "";
			while ((inputLineString = input.readLine()) != null) {
				sb.append(inputLineString);
			}
			input.close();
			String json = sb.toString();
			
			
			
			Gson gson = new Gson();
			
			JsonParser jsonParser = new JsonParser();
			JsonObject o = (JsonObject) ((JsonObject)jsonParser.parse(json)).get("MRData");
			res = gson.fromJson(o, ResultadosPilotos.class);
			
			log.info("Terminó el proceso de obtener los datos de puntajes del piloto " + PilotosEnum.valueOf(driverId).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			
			return res.getStandings();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return res.getStandings();
		
	}*/
	
	public static List<PilotoPunPosTemp> obtenerPuntajesPiloto(String driverId){
		try {
			Long empezo = System.currentTimeMillis();
			List<PilotoPunPosTemp> puntajes = ResultadoPilotoDAO.getInstance().getResultadosPuntajePosicionByPiloto(driverId);
			log.info("Terminó el proceso de obtener los puntajes y posiciones del piloto " + PilotosEnum.valueOf(driverId).getPilotoString() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			if(puntajes.isEmpty())
				log.error("La lista de puntajes vino vacia");
			return puntajes;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static List<PilotoPunPosTemp> obtenerPuntajesPiloto(Piloto piloto){
		try {
			Long empezo = System.currentTimeMillis();
			List<PilotoPunPosTemp> puntajes = ResultadoPilotoDAO.getInstance().getResultadosPuntajePosicionByPiloto(piloto);
			log.info("Terminó el proceso de obtener los puntajes y posiciones del piloto " + piloto.getForename() + " "  + piloto.getSurname() + " - Tiempo total: " + (System.currentTimeMillis()-empezo) + "ms");
			if(puntajes.isEmpty())
				log.error("La lista de puntajes vino vacia");
			return puntajes;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public static Map<String, Integer> obtenerPosicionesPorPiloto(List<Resultado> resultadosPilotos) {
		Map<String, Integer> posiciones;
		posiciones = new TreeMap<String,Integer>();

		for (Resultado resultado : resultadosPilotos) {
			String posText = resultado.getPosicionFinalTexto();// + "_" + carrera.getResultados().get(0).getStatus();
			if(posiciones.get(posText) != null){
				Integer valor = posiciones.get(posText) + 1; 
				posiciones.put(posText, valor);
			}else{
				posiciones.put(posText, 1);
			}
		}
		return posiciones;
	}
	
	public static Map<Integer, String> obtenerPuntajePosicionesPorPiloto(DatosPuntaje datosPuntaje) {
		Map<Integer, String> temporadasPuntajes = new TreeMap<Integer,String>();
		
		for (Puntaje puntaje : datosPuntaje.getListaPuntajes()) {
			Integer season = puntaje.getSeason();
			if(temporadasPuntajes.get(season) == null){
				if(!puntaje.getPuntajePiloto().isEmpty()){
					temporadasPuntajes.put(season, puntaje.getPuntajePiloto().get(0).getPoints() + ";" + puntaje.getPuntajePiloto().get(0).getPosition());
				}else{
					temporadasPuntajes.put(season, "0;0");
				}
			}
		}
		return temporadasPuntajes;
	}

}

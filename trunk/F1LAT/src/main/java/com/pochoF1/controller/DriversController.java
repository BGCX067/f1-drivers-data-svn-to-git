package com.pochoF1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pochoF1.enums.PilotosEnum;
import com.pochoF1.stats.Piloto;
import com.pochoF1.stats.PilotoPunPosTemp;
import com.pochoF1.stats.Resultado;
import com.pochoF1.utils.AlphanumComparator;
import com.pochoF1.utils.UtilsPilotos;

@Controller
public class DriversController {
	
	private static Logger log = LoggerFactory.getLogger(DriversController.class);
	
	@RequestMapping(value = "/driverInfo", method = RequestMethod.GET)
	public String showDrivers(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		model.addAttribute("PILOTOS", PilotosEnum.values());
		
		return "drivers";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/driverInfo", method = RequestMethod.POST)
	public String processDriverInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		HttpSession session = request.getSession();
		
		Boolean errorResultadosPilotos = false;
		Boolean errorPuntajePilotos = false;
		
		if (session.getAttribute("driverId") != null)
			session.removeAttribute("driverId");
		
		String driverId = request.getParameter("driverId");
		String dataResultsChart = "";
		String categoriesResultsChart = "";
		String dataStandingsChart = "";
		
		if (!StringUtils.isEmpty(driverId)) {
			List<Resultado> resultadosPilotos = (List<Resultado>) session.getAttribute(driverId);
			Map<String,Integer> posiciones = (TreeMap<String,Integer>) session.getAttribute(driverId + "_POSICIONES");
			
			Piloto datosPiloto = (Piloto) session.getAttribute(driverId + "_DATOSPILOTO");
			
			List<PilotoPunPosTemp> datosPuntaje = (List<PilotoPunPosTemp>) session.getAttribute(driverId + "_DATOSPUNTAJEPILOTO");
			
			try {
				
				
				/** LLAMADOS A LA BD **/
				
				datosPiloto = obtenerDatosPiloto(model, session, driverId, datosPiloto);
				datosPuntaje = obtenerDatosPuntajePiloto(model, session, driverId, datosPiloto, datosPuntaje);
				resultadosPilotos = obtenerResultadosPilotos(model, session, driverId, datosPiloto, resultadosPilotos);
				
				
				/** TRANSFORMACIONES A MAPS PARA HACER MAS FACIL LOS GRÁFICOS **/
				
				posiciones = obtenerPosicionesPilotos(model, session, driverId, resultadosPilotos, posiciones);
				
				
				/** ARMADO DE LA SERIE DE DATOS DEL GRAFICO DE RESULTADOS HISTORICOS **/
				if(!resultadosPilotos.isEmpty()){
					
					ArrayList<String> ordenados = new ArrayList<String>(posiciones.keySet()); 
					Collections.sort(ordenados, new AlphanumComparator());
					
					dataResultsChart += "{ name: '" + PilotosEnum.valueOf(driverId).getPilotoString() + "'," + "data: [";
					
					
					for (String key : ordenados) {
						categoriesResultsChart += "'" + key + "',";
						dataResultsChart += posiciones.get(key) + ",";
					}
					
					
					dataResultsChart = dataResultsChart.substring(0, dataResultsChart.lastIndexOf(","));
					dataResultsChart += "]}";
					
					categoriesResultsChart = categoriesResultsChart.substring(0, categoriesResultsChart.lastIndexOf(","));
				}else{
					errorResultadosPilotos = true;
					model.addAttribute("ERROR", appCtx.getMessage("error.driverController.getting.driver.info", new Object[] { PilotosEnum.valueOf(driverId).getPilotoString() }, "Error getting career results for " + PilotosEnum.valueOf(driverId).getPilotoString() + ". Please try again later.", request.getLocale()));
				}
				
				
				/** Armar datos para grafico de puntajes y posiciones historicos - MAP igual Season - Puntos;Posicion **/ 
				if(!datosPuntaje.isEmpty()){
					
					dataStandingsChart += "{ name: 'Puntos', yAxis: 0, data: [";
					
					for (PilotoPunPosTemp pppt : datosPuntaje) {
						dataStandingsChart += "[" + pppt.getSeason() + "," + pppt.getPoints() + "],";
					}
					
					dataStandingsChart = dataStandingsChart.substring(0, dataStandingsChart.lastIndexOf(","));
					dataStandingsChart += "]},";
					
					dataStandingsChart += "{ name: 'Posiciones', yAxis: 1, data: [";
					
					for (PilotoPunPosTemp pppt : datosPuntaje) {
						Integer position = pppt.getPosition()!=null?pppt.getPosition():0;
						dataStandingsChart += "[" + pppt.getSeason() + "," + position + "],";
					}
					
					dataStandingsChart = dataStandingsChart.substring(0, dataStandingsChart.lastIndexOf(","));
					dataStandingsChart += "]}";
					
					
				}else{
					errorPuntajePilotos = true;
					model.addAttribute("ERRORPUNTAJE", appCtx.getMessage("error.driverController.getting.driver.info", new Object[] { PilotosEnum.valueOf(driverId).getPilotoString() }, "Error getting standings data for " + PilotosEnum.valueOf(driverId).getPilotoString() + ". Please try again later.", request.getLocale()));
				}
				
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				errorResultadosPilotos = true;
				model.addAttribute("ERROR", appCtx.getMessage("error.driverController.getting.driver.info", new Object[] { PilotosEnum.valueOf(driverId).getPilotoString() }, "Error getting info for " + PilotosEnum.valueOf(driverId).getPilotoString() + ". Please try again later.", request.getLocale()));
			}
		}else{
			errorResultadosPilotos = true;
			model.addAttribute("ERROR", appCtx.getMessage("error.driverController.no.driver.selected", null, "You haven't selected any driver.", request.getLocale()));
		}
		
		model.addAttribute("errorResultadosPilotos", errorResultadosPilotos);
		model.addAttribute("errorPuntajePilotos", errorPuntajePilotos);
		model.addAttribute("driverId",driverId);
		model.addAttribute("dataDriversResultChart", dataResultsChart);
		model.addAttribute("categoriesDriversResultChart", categoriesResultsChart);
		model.addAttribute("dataStandingsChart", dataStandingsChart);
		return "driverInfo";
	}

	private Piloto obtenerDatosPiloto(Model model, HttpSession session, String driverId, Piloto datosPiloto) {
		if (datosPiloto == null) {
			datosPiloto = UtilsPilotos.obtenerDatosPiloto(driverId);
			
			model.addAttribute("datosPiloto", datosPiloto);
			session.setAttribute(driverId + "_DATOSPILOTO", datosPiloto);
		} else {
			model.addAttribute("datosPiloto", datosPiloto);
		}
		return datosPiloto;
	}
	
	private List<PilotoPunPosTemp> obtenerDatosPuntajePiloto(Model model, HttpSession session, String driverId,Piloto datosPiloto, List<PilotoPunPosTemp> datosPuntajePiloto) {
		if (datosPuntajePiloto == null) {
			datosPuntajePiloto = UtilsPilotos.obtenerPuntajesPiloto(datosPiloto);
			
			model.addAttribute("datosPuntajePiloto", datosPuntajePiloto);
			session.setAttribute(driverId + "_DATOSPUNTAJEPILOTO", datosPuntajePiloto);
		} else {
			model.addAttribute("datosPuntajePiloto", datosPuntajePiloto);
		}
		return datosPuntajePiloto;
	}

	private Map<String, Integer> obtenerPosicionesPilotos(Model model, HttpSession session, String driverId, List<Resultado> resultadosPilotos, Map<String, Integer> posiciones) {
		if (posiciones == null) {
			posiciones = UtilsPilotos.obtenerPosicionesPorPiloto(resultadosPilotos);
			
			model.addAttribute("posiciones", posiciones);
			session.setAttribute(driverId + "_POSICIONES", posiciones);
		} else {
			model.addAttribute("posiciones", posiciones);
		}
		return posiciones;
	}
	
	private List<Resultado> obtenerResultadosPilotos(Model model, HttpSession session, String driverId, Piloto datosPiloto, List<Resultado> resultadosPilotos) {
		if (resultadosPilotos == null) {
			resultadosPilotos = UtilsPilotos.obtenerResultadosPiloto(driverId);
			model.addAttribute("resultadosPilotos", resultadosPilotos);
			model.addAttribute("nombrePiloto",PilotosEnum.valueOf(driverId).getPilotoString());
			session.setAttribute(driverId, resultadosPilotos);
		} else {
			model.addAttribute("resultadosPilotos", resultadosPilotos);
			model.addAttribute("nombrePiloto",PilotosEnum.valueOf(driverId).getPilotoString());
		}
		return resultadosPilotos;
	}

	

}

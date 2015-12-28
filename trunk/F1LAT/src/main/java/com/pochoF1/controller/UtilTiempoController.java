package com.pochoF1.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pochoF1.model.Piloto;
import com.pochoF1.model.Vuelta;
import com.pochoF1.utils.UtilTiempos;

@Controller
public class UtilTiempoController {
	
	private static Logger log = LoggerFactory.getLogger(UtilTiempoController.class);
	private HashMap<Piloto,List<Vuelta>> hashPilotos = null;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/utilTiempos")
	public void utilLoadRaces(HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		try {
			List<Piloto> listaPilotos = null;
			List<Vuelta> vueltas = null;
			HttpSession session = request.getSession();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			hashPilotos = session.getAttribute("HASHPILOTOS")!=null ? (HashMap<Piloto, List<Vuelta>>) session.getAttribute("HASHPILOTOS") : null;
			
			String consulta = request.getParameter("query");
			if(session.getAttribute("LISTAPILOTOS")!=null){
				listaPilotos = (List<Piloto>) session.getAttribute("LISTAPILOTOS");
				if(consulta.equalsIgnoreCase("U")){
					String idPiloto = request.getParameter("driver");
				    String idVuelta = request.getParameter("lap");
				    Integer numeroPiloto = Integer.parseInt(idPiloto);
				    Integer numeroVuelta = Integer.parseInt(idVuelta);
					vueltas = consultaVueltaUnica(idPiloto, numeroPiloto,numeroVuelta, listaPilotos, vueltas);
					session.setAttribute("HASHPILOTOS", hashPilotos);
					if(vueltas!=null)
			    		out.print(UtilTiempos.getMediaPorVueltas(vueltas));
				}else if(consulta.equalsIgnoreCase("A")){
					String idPiloto = request.getParameter("driver");
					String[] vueltasTiempo = request.getParameterValues("laps[]");
					Integer numeroPiloto = Integer.parseInt(idPiloto);
					vueltas = consultaTodasVueltas(idPiloto, numeroPiloto,vueltasTiempo, listaPilotos, vueltas);
					session.setAttribute("HASHPILOTOS", hashPilotos);
					if(vueltas!=null)
			    		out.print(UtilTiempos.getMediaPorVueltas(vueltas));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	private List<Vuelta> consultaTodasVueltas(String idPiloto, Integer numeroPiloto, String[] vueltasTiempo,List<Piloto> listaPilotos, List<Vuelta> vueltas) {
		for(Piloto piloto : listaPilotos){
			if(piloto.getNumero() == numeroPiloto){
				for(String nroVuelta : vueltasTiempo){
					for(Vuelta vuelta : piloto.getVueltasActuales()){
						if(vuelta.getNroVuelta() == Integer.parseInt(nroVuelta)){
							if(hashPilotos==null){
								hashPilotos = new HashMap<Piloto, List<Vuelta>>();
								vueltas = new ArrayList<Vuelta>();
								vueltas.add(vuelta);
								hashPilotos.put(piloto, vueltas);
								break;
							}
							else{
								if(hashPilotos.get(piloto)!=null){ 
									vueltas = hashPilotos.get(piloto);
									if(existeVuelta(vueltas, vuelta)){
										vueltas.remove(vuelta);
									}else{
										vueltas.add(vuelta);
									}
									hashPilotos.put(piloto, vueltas);
									break;
								}else{
									vueltas = new ArrayList<Vuelta>();
									vueltas.add(vuelta);
									hashPilotos.put(piloto, vueltas);
									break;
								}
							}
						}
					}
				}
			}
		}
		return vueltas;
	}
	
	private List<Vuelta> consultaVueltaUnica(String idPiloto, Integer numeroPiloto, Integer numeroVuelta,List<Piloto> listaPilotos, List<Vuelta> vueltas) {
		boolean encontro = false;
		for(Piloto piloto : listaPilotos){
			if(piloto.getNumero() == numeroPiloto){
				for(Vuelta vuelta : piloto.getVueltasActuales()){
					if(vuelta.getNroVuelta() == numeroVuelta){
						if(hashPilotos==null){
							hashPilotos = new HashMap<Piloto, List<Vuelta>>();
							vueltas = new ArrayList<Vuelta>();
							vueltas.add(vuelta);
							hashPilotos.put(piloto, vueltas);
							encontro=true;
							break;
						}
						else{
							if(hashPilotos.get(piloto)!=null){
								vueltas = hashPilotos.get(piloto);
								if(existeVuelta(vueltas, vuelta)){
									vueltas.remove(vuelta);
								}else{
									vueltas.add(vuelta);
								}
								hashPilotos.put(piloto, vueltas);
								encontro=true;
								break;
							}else{
								vueltas = new ArrayList<Vuelta>();
								vueltas.add(vuelta);
								hashPilotos.put(piloto, vueltas);
								encontro=true;
								break;
							}
							
						}
					}
				}
				if(encontro)
					break;
			}
		}
		return vueltas;
	}
	
	private Boolean existeVuelta(List<Vuelta> vueltas, Vuelta v){
		for (Vuelta vuelta : vueltas){ 
			if(vuelta.getNroVuelta() == v.getNroVuelta())
				return true;
		}
		return false;
	}

}
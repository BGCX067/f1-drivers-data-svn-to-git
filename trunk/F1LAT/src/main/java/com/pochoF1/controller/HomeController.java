package com.pochoF1.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pochoF1.model.ResultDriver;
import com.pochoF1.model.ResultTeam;
import com.pochoF1.utils.LeerFIA;
import com.pochoF1.utils.ObtenerDatosCarrera;
import com.pochoF1.xml.Race;

@Controller
@SuppressWarnings("unchecked")
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/test")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		Boolean errorCurrentRace = false;
		HttpSession session = request.getSession();
		Race race = (Race)session.getAttribute("race");
		
		try {
			if (session.getAttribute("race") == null) {
				race = ObtenerDatosCarrera.carreraPorFechaHoy();
				model.addAttribute("race", race);
				session.setAttribute("race", race);
			} else {
				model.addAttribute("race", race);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorCurrentRace = true;
		}

		model.addAttribute("errorCurrentRace", errorCurrentRace);
		return "index";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		Boolean errorCurrentRace = false;
		Boolean errorEquipos = false;
		Boolean errorPiloto = false;
		HttpSession session = request.getSession();
		Race race = (Race)session.getAttribute("race");
		List<ResultTeam> resultadosEquipos = (List<ResultTeam>)session.getAttribute("resultadosEquipos");
		List<ResultDriver> resultadosPilotos = (List<ResultDriver>)session.getAttribute("resultadosPilotos");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);
		
		try {
			if (session.getAttribute("race") == null) {
				race = ObtenerDatosCarrera.carreraPorFechaHoy();
				model.addAttribute("race", race);
				session.setAttribute("race", race);
			} else {
				model.addAttribute("race", race);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorCurrentRace = true;
		}

		try {
			if (session.getAttribute("resultadosEquipos") == null) {
				resultadosEquipos = LeerFIA.getResultadosEquipos(new Date());
				model.addAttribute("resultadosEquipos", resultadosEquipos);
				model.addAttribute("anioEquipo", new Date());
				session.setAttribute("resultadosEquipos", resultadosEquipos);
			} else {
				model.addAttribute("resultadosEquipos", resultadosEquipos);
			}
		} catch (Exception e) {
			try {
				resultadosEquipos = LeerFIA.getResultadosEquipos(calendar.getTime());
				model.addAttribute("resultadosEquipos", resultadosEquipos);
				model.addAttribute("anioEquipo", calendar.getTime());
				session.setAttribute("resultadosEquipos", resultadosEquipos);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
				errorEquipos = true;
			}
		}
		
		try {
			if (session.getAttribute("resultadosPilotos") == null) {
				resultadosPilotos = LeerFIA.getResultadosPilotos(new Date());
				model.addAttribute("resultadosPilotos", resultadosPilotos);
				model.addAttribute("anioPiloto", new Date());
				session.setAttribute("resultadosPilotos", resultadosPilotos);
			} else {
				model.addAttribute("resultadosPilotos", resultadosPilotos);
			}
		} catch (Exception e) {
			try {
				resultadosPilotos = LeerFIA.getResultadosPilotos(calendar.getTime());
				model.addAttribute("resultadosPilotos", resultadosPilotos);
				model.addAttribute("anioPiloto", calendar.getTime());
				session.setAttribute("resultadosPilotos", resultadosPilotos);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
				errorPiloto = true;
			}
		}

		model.addAttribute("errorCurrentRace", errorCurrentRace);
		model.addAttribute("errorEquipos", errorEquipos);
		model.addAttribute("errorPiloto", errorPiloto);
		return "index";
	}
}
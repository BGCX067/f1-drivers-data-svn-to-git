package com.pochoF1.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.google.gson.Gson;
import com.pochoF1.model.ResultDriver;
import com.pochoF1.model.ResultTeam;
import com.pochoF1.utils.Constants;
import com.pochoF1.utils.LeerFIA;
import com.pochoF1.xml.Season;

@Controller
public class UtilAjaxController {

	private static Logger log = LoggerFactory.getLogger(UtilCarreraActualController.class);

	@RequestMapping("/utilLoadRaces")
	public void utilLoadRaces(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
			String year = request.getParameter("year");
			HttpSession session = request.getSession();
			Season season = (Season) session.getAttribute("SEASON-" + year);

			if (season == null) {
				season = LeerFIA.obtenerCarrerasPorTemporada(year);
				session.setAttribute("SEASON-" + year, season);
			}

			String json = new Gson().toJson(season);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/utilResultadosPilotos")
	public void utilResultadosPilotos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();
		List<ResultDriver> resultadosPilotos = new ArrayList<ResultDriver>();
		session.setAttribute("ERRORPILOTOS", false);
		String json = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);

		try {
			if (session.getAttribute("resultadosPilotos") == null) {
				resultadosPilotos = LeerFIA.getResultadosPilotos(new Date());
				session.setAttribute("anioPiloto", new Date());
				session.setAttribute("resultadosPilotos", resultadosPilotos);
				json = new Gson().toJson(resultadosPilotos);
			} else {
				json = new Gson().toJson(session.getAttribute("resultadosPilotos"));
				session.setAttribute("anioPiloto", new Date());
			}
		} catch (Exception e) {
			try {
				resultadosPilotos = LeerFIA.getResultadosPilotos(calendar.getTime());
				session.setAttribute("resultadosPilotos", resultadosPilotos);
				session.setAttribute("anioPiloto", calendar.getTime());
				json = new Gson().toJson(resultadosPilotos);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
				session.setAttribute("ERRORPILOTOS", true);
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	@RequestMapping("/utilResultadosEquipos")
	public void utilResultadosEquipos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();
		List<ResultTeam> resultadosEquipos = new ArrayList<ResultTeam>();
		session.setAttribute("ERROREQUIPOS", false);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);

		String json = "";

		try {
			if (session.getAttribute("resultadosEquipos") == null) {
				resultadosEquipos = LeerFIA.getResultadosEquipos(new Date());
				session.setAttribute("resultadosEquipos", resultadosEquipos);
				json = new Gson().toJson(resultadosEquipos);
			} else {
				json = new Gson().toJson(session.getAttribute("resultadosEquipos"));
				session.setAttribute("anioEquipo", new Date());
			}
		} catch (Exception e) {
			try {
				resultadosEquipos = LeerFIA.getResultadosEquipos(calendar.getTime());
				session.setAttribute("resultadosEquipos", resultadosEquipos);
				session.setAttribute("anioEquipo", calendar.getTime());
				json = new Gson().toJson(resultadosEquipos);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
				session.setAttribute("ERROREQUIPOS", true);
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	@RequestMapping("/utilgetJSONTest")
	public void utilgetJSONTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();
		String json = "";
		session.setAttribute("ERRORPRACTICA", Boolean.valueOf(false));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			HashMap<String, String> map = new HashMap<String, String>();
			BufferedReader in = new BufferedReader(new FileReader(Constants.urlSave + "test.properties"));
			String line = "";
			while ((line = in.readLine()) != null) {
				String[] parts = line.split(" ");
				map.put(parts[0], parts[1]);
			}
			in.close();

			String key = sdf.format(Calendar.getInstance().getTime());
			String codigo = (String) map.get(key);

			String urlTests = Constants.URLTEST2015.replaceAll("%CODIGOTEST%", codigo.toString());

			URL urlTest = new URL(urlTests);
			URLConnection uc = Constants.obtenerURLConexion(urlTest);
			BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String inputLineString = "";
			while ((inputLineString = input.readLine()) != null) {
				sb.append(inputLineString);
			}
			input.close();
			json = sb.toString();
		} catch (Exception e) {
			session.setAttribute("ERRORPRACTICA", Boolean.valueOf(true));
			log.error(e.getMessage(), e);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
package com.pochoF1.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

import com.google.gson.Gson;
import com.pochoF1.enums.Sesiones;
import com.pochoF1.exceptions.DescargarFiaException;
import com.pochoF1.exceptions.DescargarFormulaOneException;
import com.pochoF1.exceptions.DescargarFormulaOneParsingException;
import com.pochoF1.exceptions.NoFileFiaException;
import com.pochoF1.json.Practica;
import com.pochoF1.model.Piloto;
import com.pochoF1.model.ResultRace;
import com.pochoF1.model.Vuelta;
import com.pochoF1.utils.Constants;
import com.pochoF1.utils.LeerFIA;
import com.pochoF1.utils.LeerPDF;
import com.pochoF1.utils.ObtenerDatosCarrera;
import com.pochoF1.utils.UtilTiempos;
import com.pochoF1.xml.Race;
import com.pochoF1.xml.Season;

@Controller
@SuppressWarnings("unchecked")
public class RacesController {

	private static Logger log = LoggerFactory.getLogger(RacesController.class);
	private static String pagina = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();

		obtenerDatosCarrera(model, session);

		Boolean errorSeason = false;
		Season season = (Season) session.getAttribute("season");
		if (session.getAttribute("raceRound") != null)
			session.removeAttribute("raceRound");
		if (session.getAttribute("raceYear") != null)
			session.removeAttribute("raceYear");
		if (session.getAttribute("raceName") != null)
			session.removeAttribute("raceName");
		if (session.getAttribute("raceId") != null)
			session.removeAttribute("raceId");

		String raceYear = request.getParameter("raceYear");
		try {
			if (season == null) {
				season = ObtenerDatosCarrera.obtenerDatosTemporada();
				model.addAttribute("season", season);
				model.addAttribute("raceYear", raceYear == null ? Calendar.getInstance().get(Calendar.YEAR) : raceYear);
				session.setAttribute("season", season);
			} else {
				model.addAttribute("season", season);
				model.addAttribute("raceYear", raceYear == null ? Calendar.getInstance().get(Calendar.YEAR) : raceYear);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorSeason = true;
		}
		
		//obtenerDatosTest(model);

		model.addAttribute("errorSeason", errorSeason);
		return "showRaces";
	}


	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitIndexForm(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();

		String raceRound = request.getParameter("raceRound");
		String raceYear = request.getParameter("raceYear");
		String raceName = request.getParameter("raceName");
		String raceId = request.getParameter("raceId");

		session.setAttribute("raceRound", raceRound);
		session.setAttribute("raceYear", raceYear);
		session.setAttribute("raceName", raceName);
		session.setAttribute("raceId", raceId);

		model.addAttribute("raceRound", raceRound);
		model.addAttribute("raceYear", raceYear);
		model.addAttribute("raceName", raceName);
		model.addAttribute("raceId", raceId);

		return "raceInfo";
	}

	@RequestMapping(value = "/showRaces", method = RequestMethod.GET)
	public String showRaces(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		Boolean errorSeason = false;
		HttpSession session = request.getSession();

		obtenerDatosCarrera(model, session);

		Season season = (Season) session.getAttribute("season");
		if (session.getAttribute("raceRound") != null)
			session.removeAttribute("raceRound");
		if (session.getAttribute("raceYear") != null)
			session.removeAttribute("raceYear");
		if (session.getAttribute("raceName") != null)
			session.removeAttribute("raceName");
		if (session.getAttribute("raceId") != null)
			session.removeAttribute("raceId");

		String raceYear = request.getParameter("raceYear");
		try {
			if (season == null) {
				season = ObtenerDatosCarrera.obtenerDatosTemporada();
				model.addAttribute("season", season);
				model.addAttribute("raceYear", raceYear == null ? Calendar.getInstance().get(Calendar.YEAR) : raceYear);
				session.setAttribute("season", season);
			} else {
				model.addAttribute("season", season);
				model.addAttribute("raceYear", raceYear == null ? Calendar.getInstance().get(Calendar.YEAR) : raceYear);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorSeason = true;
		}
		
		//obtenerDatosTest(model);

		model.addAttribute("errorSeason", errorSeason);
		return "showRaces";
	}

	@RequestMapping(value = "/showRaces", method = RequestMethod.POST)
	public String submitShowRacesForm(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();

		String raceRound = request.getParameter("raceRound");
		String raceYear = request.getParameter("raceYear");
		String raceName = request.getParameter("raceName");
		String raceId = request.getParameter("raceId");

		session.setAttribute("raceRound", raceRound);
		session.setAttribute("raceYear", raceYear);
		session.setAttribute("raceName", raceName);
		session.setAttribute("raceId", raceId);

		model.addAttribute("raceRound", raceRound);
		model.addAttribute("raceYear", raceYear);
		model.addAttribute("raceName", raceName);
		model.addAttribute("raceId", raceId);

		return "raceInfo";
	}

	@RequestMapping(value = "/raceInfo", method = RequestMethod.POST)
	public String submitRaceInfoForm(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		HttpSession session = request.getSession();
		Season season = (Season) session.getAttribute("season");
		HashMap<String, Object> hashSesionLista = (HashMap<String, Object>) request.getSession().getAttribute("HASHSESIONRESULTADO");
		if (session.getAttribute("HASHPILOTOS") != null)
			session.removeAttribute("HASHPILOTOS");
		if (session.getAttribute("LISTAPILOTOS") != null)
			session.removeAttribute("LISTAPILOTOS");

		String idSesion = request.getParameter("session");
		String raceRound = session.getAttribute("raceRound") != null ? session.getAttribute("raceRound").toString() : "";
		String raceYear = session.getAttribute("raceYear") != null ? session.getAttribute("raceYear").toString() : "";
		String raceName = session.getAttribute("raceName") != null ? session.getAttribute("raceName").toString() : "";
		String raceId = session.getAttribute("raceId") != null ? session.getAttribute("raceId").toString() : "";

		model.addAttribute("session", idSesion);
		model.addAttribute("raceRound", raceRound);
		model.addAttribute("raceYear", raceYear);
		model.addAttribute("raceName", raceName);
		model.addAttribute("raceId", raceId);

		List<Piloto> listaPilotos = new ArrayList<Piloto>();
		TreeMap<Sesiones, List<Piloto>> resultado = new TreeMap<Sesiones, List<Piloto>>();

		Boolean ok = true;
		if (season == null) {
			try {
				season = ObtenerDatosCarrera.obtenerDatosTemporada();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Race carreraElegida = this.obtenerCarreraPorId(raceId, season);

		/**
		 * Si por alguna razon no viene de la session alguno de esos 3 datos
		 * cancelo, porque me faltan datos Hago el llamado para obtener el PDF
		 * de las sesiones excepto resultados que lo voy a buscar a la pagina de
		 * la f1 El Hash esta puesto para guardar un unico resultado del pdf en
		 * la sesion y no haga tantos llamados
		 **/

		if (!StringUtils.isEmpty(raceRound) && !StringUtils.isEmpty(raceYear) && !StringUtils.isEmpty(raceName) && !StringUtils.isEmpty(raceId)) {
			pagina = "raceDetail";
			if (idSesion != null) {
				Sesiones sesion = Sesiones.valueOf(idSesion);
				model.addAttribute("SESION", sesion);
				String key = raceName + raceYear + sesion;
				if (sesion.equals(Sesiones.RESULTS)) {
					ok = obtenerDatosResultados(model, request, session, hashSesionLista, raceRound, raceYear, raceName, raceId, key, ok);
				} else if (sesion.equals(Sesiones.FAST)) {
					ok = obtenerDatosTodasSesiones(model, session, hashSesionLista, raceRound, raceYear, raceName, resultado, ok, key, raceId, carreraElegida, request);
				} else {
					ok = obtenerDatosSesiones(model, session, hashSesionLista, raceRound, raceYear, raceName, listaPilotos, ok, sesion, key, raceId, carreraElegida, request);
				}
			} else {
				ok = false;
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.no.session.selected", null, "No session selected.", request.getLocale()));
			}
		} else {
			ok = false;
			model.addAttribute("ERROR", appCtx.getMessage("error.racesController.data.missing", null, "Some data are missing, please go back home and try it again.", request.getLocale()));
		}

		model.addAttribute("OK", ok);
		return pagina;
	}

	@RequestMapping(value = "/compareDrivers", method = RequestMethod.POST)
	public String submitDriverCompare(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		HttpSession session = request.getSession();

		if (session.getAttribute("HASHPILOTOS") != null)
			session.removeAttribute("HASHPILOTOS");

		List<Piloto> listaPilotosFiltrada = new ArrayList<Piloto>();
		Boolean ok = true;

		try {
			String _listaPilotosElegidos = request.getParameter("driversSel");
			String[] listaPilotosElegidos = _listaPilotosElegidos.split(",");

			List<Piloto> listaPilotos = (List<Piloto>) session.getAttribute("LISTAPILOTOS");

			for (String _numeroPiloto : listaPilotosElegidos) {
				for (Piloto piloto : listaPilotos) {
					if (piloto.getNumero() == Integer.parseInt(_numeroPiloto)) {
						listaPilotosFiltrada.add(piloto);
						break;
					}
				}
			}
			model.addAttribute("LISTAPILOTOSFILTRADA", listaPilotosFiltrada);
			session.setAttribute("LISTAPILOTOSFILTRADA", listaPilotosFiltrada);
			pagina = "compareDrivers";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ERROR", appCtx.getMessage("error.general", null, "An error has ocurred, try it later", request.getLocale()));
			ok = false;
		}

		model.addAttribute("OKCOMPARE", ok);
		return pagina;
	}

	@RequestMapping(value = "/lapChart", method = RequestMethod.POST)
	public String showChart(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		HttpSession session = request.getSession();
		String data = "";
		Boolean ok = true;

		try {
			String _listaPilotosElegidos = request.getParameter("driversSelChart");
			String[] listaPilotosElegidos = _listaPilotosElegidos.split(",");

			List<Piloto> listaPilotos = (List<Piloto>) session.getAttribute("LISTAPILOTOS");

			for (String _numeroPiloto : listaPilotosElegidos) {
				for (Piloto piloto : listaPilotos) {
					if (piloto.getNumero() == Integer.parseInt(_numeroPiloto) && !piloto.getVueltasActuales().isEmpty()) {
						Integer media = (int) (UtilTiempos.getMediaSinPitsInteger(piloto) + 25 * 1000);
						data += "{ name: '" + piloto.getNombre() + "'," + "data: [";
						for (Vuelta vuelta : piloto.getVueltasActuales()) {
							if (vuelta.getNroVuelta() > 1 && vuelta.getTiempo() < media) {
								if (vuelta.isPits()) {
									data += "{ y: " + new Double(new Double(vuelta.getTiempo()) / 1000) + ", x: " + vuelta.getNroVuelta() + ", marker: {symbol: 'url(static/img/p1.png)'} },";
								} else if (vuelta.isVueltaSalida()) {
									data += "{ y: " + new Double(new Double(vuelta.getTiempo()) / 1000) + ", x: " + vuelta.getNroVuelta() + ", marker: {symbol: 'url(static/img/s1.png)'} },";
								} else {
									data += "[" + vuelta.getNroVuelta() + "," + new Double(new Double(vuelta.getTiempo()) / 1000) + "],";
								}

							}
						}
						if (media > 25 * 1000)
							data = data.substring(0, data.lastIndexOf(","));
						data += "]},";
						break;
					}

				}
			}
			data = data.substring(0, data.lastIndexOf(","));
			model.addAttribute("dataChart", data);
			pagina = "lapChart";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ERROR", appCtx.getMessage("error.general", null, "An error has ocurred, please try it later.", request.getLocale()));
			ok = false;
		}

		model.addAttribute("OKDATACHART", ok);
		return pagina;
	}

	private Boolean obtenerDatosResultados(Model model, HttpServletRequest request, HttpSession session, HashMap<String, Object> hashSesionLista, String raceRound, String raceYear, String raceName, String raceId, String key, Boolean ok) {
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		Season season = (Season) session.getAttribute("SEASON-" + raceYear);
		if (season == null)
			season = (Season) session.getAttribute("season");

		List<ResultRace> resultadosCarreras = new ArrayList<ResultRace>();
		model.addAttribute("ERRORCARRERAS", false);

		try {
			if (hashSesionLista != null) {
				if (hashSesionLista.get(key) != null) {
					resultadosCarreras = (List<ResultRace>) hashSesionLista.get(key);
					model.addAttribute("RESULTADOCARRERAS", resultadosCarreras);
				} else {
					resultadosCarreras = LeerFIA.getResultadosCarrera(raceYear, raceRound, raceName, raceId);
					hashSesionLista.put(key, resultadosCarreras);
					model.addAttribute("RESULTADOCARRERAS", resultadosCarreras);
					session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
				}
			} else {
				hashSesionLista = new HashMap<String, Object>();
				resultadosCarreras = LeerFIA.getResultadosCarrera(raceYear, raceRound, raceName, raceId);
				hashSesionLista.put(key, resultadosCarreras);
				model.addAttribute("RESULTADOCARRERAS", resultadosCarreras);
				session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
			}
		} catch (Exception e) {
			if (e instanceof DescargarFormulaOneException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.f1website.not.available", new Object[] { raceName }, "Formula 1 website data for " + raceName + " is not available at this moment.", request.getLocale()));
				model.addAttribute("ERRORCARRERAS", true);
				ok = false;
			} else if (e instanceof DescargarFormulaOneParsingException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.f1website.not.parsed", new Object[] { raceName }, "Formula 1 website data for " + raceName + " cannot be parsed right now. Try it later", request.getLocale()));
				model.addAttribute("ERRORCARRERAS", true);
				ok = false;
			} else {
				model.addAttribute("ERROR", appCtx.getMessage("error.general", null, "An error has ocurred, please try it later", request.getLocale()));
				model.addAttribute("ERRORCARRERAS", true);
				ok = false;
			}
		}
		return ok;
	}

	private Boolean obtenerDatosSesiones(Model model, HttpSession session, HashMap<String, Object> hashSesionLista, String raceRound, String raceYear, String raceName, List<Piloto> listaPilotos, Boolean ok, Sesiones sesion, String key, String raceId, Race race, HttpServletRequest request) {
		long empezo = System.currentTimeMillis();
		Race carreraActual = (Race) request.getSession().getAttribute("race");
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		try {
			if (hashSesionLista != null) {
				if (hashSesionLista.get(key) != null) {
					listaPilotos = (List<Piloto>) hashSesionLista.get(key);
					model.addAttribute("LISTAPILOTOS", listaPilotos);
					session.setAttribute("LISTAPILOTOS", listaPilotos);
				} else {
					listaPilotos = LeerPDF.leerPDF(raceRound, raceYear, raceName, sesion, race, carreraActual);
					hashSesionLista.put(key, listaPilotos);
					model.addAttribute("LISTAPILOTOS", listaPilotos);
					session.setAttribute("LISTAPILOTOS", listaPilotos);
					session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
				}
			} else {
				hashSesionLista = new HashMap<String, Object>();
				listaPilotos = LeerPDF.leerPDF(raceRound, raceYear, raceName, sesion, race, carreraActual);
				hashSesionLista.put(key, listaPilotos);
				model.addAttribute("LISTAPILOTOS", listaPilotos);
				session.setAttribute("LISTAPILOTOS", listaPilotos);
				session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
			}
		} catch (Exception e) {
			if (e instanceof DescargarFiaException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.fia.not.available", new Object[] { raceYear, raceName, sesion.toString() }, "FIA documents for " + raceYear + " " + raceName + " " + sesion.toString() + " are not available at this moment.", request.getLocale()));
				ok = false;
			} else if (e instanceof NoFileFiaException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.fia.not.parsed", new Object[] { raceYear, raceName, sesion.toString() }, "FIA documents for " + raceYear + " " + raceName + " " + sesion.toString() + " cannot be parsed right now. Try it later", request.getLocale()));
				ok = false;
			} else {
				model.addAttribute("ERROR", appCtx.getMessage("error.general", null, "An error has ocurred, please try it later", request.getLocale()));
				ok = false;
			}
		}
		log.info("Tiempo en procesar el PDF " + raceName + " | " + sesion + " : " + (System.currentTimeMillis() - empezo));
		return ok;
	}

	private Boolean obtenerDatosTodasSesiones(Model model, HttpSession session, HashMap<String, Object> hashSesionLista, String raceRound, String raceYear, String raceName, TreeMap<Sesiones, List<Piloto>> resultado, Boolean ok, String key, String raceId, Race race, HttpServletRequest request) {
		long empezo = System.currentTimeMillis();
		Race carreraActual = (Race) request.getSession().getAttribute("race");
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		try {
			if (hashSesionLista != null) {
				if (hashSesionLista.get(key) != null) {
					resultado = (TreeMap<Sesiones, List<Piloto>>) hashSesionLista.get(key);
					model.addAttribute("LISTAPILOTOS", resultado);
					session.setAttribute("LISTAPILOTOS", resultado);
				} else {
					resultado = LeerPDF.leerPDFTodasSesiones(raceRound, raceYear, raceName, race, carreraActual);
					hashSesionLista.put(key, resultado);
					model.addAttribute("LISTAPILOTOS", resultado);
					session.setAttribute("LISTAPILOTOS", resultado);
					session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
				}
			} else {
				hashSesionLista = new HashMap<String, Object>();
				resultado = LeerPDF.leerPDFTodasSesiones(raceRound, raceYear, raceName, race, carreraActual);
				hashSesionLista.put(key, resultado);
				model.addAttribute("LISTAPILOTOS", resultado);
				session.setAttribute("LISTAPILOTOS", resultado);
				session.setAttribute("HASHSESIONRESULTADO", hashSesionLista);
			}
		} catch (Exception e) {
			if (e instanceof DescargarFiaException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.fia.not.available", new Object[] { raceYear, raceName, "TODAS" }, "FIA documents for " + raceYear + " " + raceName + " " + "TODAS" + " are not available at this moment.", request.getLocale()));
				ok = false;
			} else if (e instanceof NoFileFiaException) {
				model.addAttribute("ERROR", appCtx.getMessage("error.racesController.fia.not.parsed", new Object[] { raceYear, raceName, "TODAS" }, "FIA documents for " + raceYear + " " + raceName + " " + "TODAS" + " cannot be parsed right now. Try it later", request.getLocale()));
				ok = false;
			} else {
				model.addAttribute("ERROR", appCtx.getMessage("error.general", null, "An error has ocurred, please try it later", request.getLocale()));
				ok = false;
			}
		}
		log.info("Tiempo en procesar el PDF de todas las sesiones " + raceName + " : " + (System.currentTimeMillis() - empezo));
		return ok;
	}

	private Race obtenerCarreraPorId(String raceId, Season season) {
		for (Race r : season.getRaces()) {
			if (r.getId().equalsIgnoreCase(raceId))
				return r;
		}
		return null;

	}

	private void obtenerDatosCarrera(Model model, HttpSession session) {
		Race race = (Race) session.getAttribute("race");

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
		}
	}
	
	@SuppressWarnings("unused")
	private void obtenerDatosTest(Model model) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			HashMap<String,String> map = new HashMap<String,String>();
			BufferedReader in = new BufferedReader(new FileReader(Constants.urlSave + "test.properties"));
			String line = "";
			while ((line = in.readLine()) != null) {
				String[] parts = line.split(" ");
				map.put(parts[0], parts[1]);
			}
			in.close();

			String key = sdf.format(Calendar.getInstance().getTime());

			Integer codigo = Integer.valueOf(Integer.parseInt((String) map.get(key)));
			try {
				String urlTests = Constants.URLTEST2015.replaceAll("%CODIGOTEST%", codigo.toString());
				log.debug("Llamando a la URL: " + urlTests);

				URL urlTest = new URL(urlTests);
				URLConnection uc = Constants.obtenerURLConexion(urlTest);
				BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

				StringBuilder sb = new StringBuilder();
				String inputLineString = "";
				while ((inputLineString = input.readLine()) != null) {
					sb.append(inputLineString);
				}
				input.close();
				String json = sb.toString();
				Gson gson = new Gson();
				Practica practica = (Practica) gson.fromJson(json, Practica.class);
				model.addAttribute("practica", practica);
				model.addAttribute("ERRORPRACTICA", Boolean.valueOf(false));
			} catch (Exception e) {
				log.error(e.getMessage(), e);

				codigo = Integer.valueOf(codigo.intValue() - 1);
				try {
					String urlTests = Constants.URLTEST2015.replaceAll("%CODIGOTEST%", codigo.toString());
					log.debug("Llamando a la URL: " + urlTests);

					URL urlTest = new URL(urlTests);
					URLConnection uc = Constants.obtenerURLConexion(urlTest);
					BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));

					StringBuilder sb = new StringBuilder();
					String inputLineString = "";
					while ((inputLineString = input.readLine()) != null) {
						sb.append(inputLineString);
					}
					input.close();
					String json = sb.toString();
					Gson gson = new Gson();
					Practica practica = (Practica) gson.fromJson(json, Practica.class);
					model.addAttribute("practica", practica);
					model.addAttribute("ERRORPRACTICA", Boolean.valueOf(false));
				} catch (Exception e1) {
					model.addAttribute("ERRORPRACTICA", Boolean.valueOf(true));
					log.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			model.addAttribute("ERRORPRACTICA", Boolean.valueOf(true));
			log.error(e.getMessage(), e);
		}
	}

}
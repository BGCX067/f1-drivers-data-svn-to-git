package com.pochoF1.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pochoF1.enums.Carreras;
import com.pochoF1.enums.Equipos;
import com.pochoF1.exceptions.DescargarFormulaOneException;
import com.pochoF1.exceptions.DescargarFormulaOneParsingException;
import com.pochoF1.model.ResultDriver;
import com.pochoF1.model.ResultRace;
import com.pochoF1.model.ResultTeam;
import com.pochoF1.xml.Circuit;
import com.pochoF1.xml.Country;
import com.pochoF1.xml.Race;
import com.pochoF1.xml.Season;

public class LeerFIA {
	
	private static Logger log = LoggerFactory.getLogger(LeerFIA.class);

	private static String URLRESULTADOSPILOTOS = "http://www.formula1.com/results/driver/";
	private static String URLRESULTADOSEQUIPOS = "http://www.formula1.com/results/team/";
	private static String URLRESULTADOSCARRERAS = "http://www.formula1.com/results/season/";

	public static List<ResultDriver> getResultadosPilotos() throws Exception {
		List<ResultDriver> resultadosPilotos = new ArrayList<ResultDriver>();
		try {
			URL url = new URL(URLRESULTADOSPILOTOS);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 5) {
							ResultDriver rd = new ResultDriver();
							rd.setPosicion(tds.get(i).text());
							rd.setNombre(tds.get(i + 1).text());
							rd.setNacionalidad(tds.get(i + 2).text());
							rd.setEquipo(Equipos.getEquipos(tds.get(i + 3).text()));
							rd.setPuntaje(tds.get(i + 4).text());
							rd.setEquipoString(rd.getEquipo().getEquipo());
							resultadosPilotos.add(rd);
						}
					}
				}
			}
			return resultadosPilotos;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static List<ResultDriver> getResultadosPilotos(Date date) throws Exception {
		List<ResultDriver> resultadosPilotos = new ArrayList<ResultDriver>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			
			URL url = new URL(URLRESULTADOSPILOTOS+sdf.format(date));
			URLConnection yc = Constants.obtenerURLConexion(url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 5) {
							ResultDriver rd = new ResultDriver();
							rd.setPosicion(tds.get(i).text());
							rd.setNombre(tds.get(i + 1).text());
							rd.setNacionalidad(tds.get(i + 2).text());
							try {
								rd.setEquipo(Equipos.getEquipos(tds.get(i + 3).text()));
							} catch (Exception e) {
								rd.setEquipo(Equipos.DESCONOCIDO);
							}
							rd.setPuntaje(tds.get(i + 4).text());
							rd.setEquipoString(tds.get(i + 3).text());
							try {
								rd.setEquipoAbbr(tds.get(i + 3).text().split("-")[0]);
							} catch (Exception e) {
								rd.setEquipoAbbr(tds.get(i + 3).text());
							}
							resultadosPilotos.add(rd);
						}
					}
				}
			}
			return resultadosPilotos;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static List<ResultTeam> getResultadosEquipos(Date date) throws Exception {
		List<ResultTeam> resultadosEquipos = new ArrayList<ResultTeam>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			URL url = new URL(URLRESULTADOSEQUIPOS+sdf.format(date));
			URLConnection yc = Constants.obtenerURLConexion(url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			
			
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 3) {
							ResultTeam rt = new ResultTeam();
							rt.setPosicion(tds.get(i).text());
							try {
								rt.setEquipo(Equipos.getEquipos(tds.get(i + 1).text()));
							} catch (Exception e) {
								rt.setEquipo(Equipos.DESCONOCIDO);
							}
							rt.setPuntaje(tds.get(i + 2).text());
							rt.setYear(sdf.format(date));
							rt.setEquipoString(tds.get(i + 1).text());
							try {
								rt.setEquipoAbbr(tds.get(i + 1).text().split("-")[0]);
							} catch (Exception e) {
								rt.setEquipoAbbr(tds.get(i + 1).text());
							}
							resultadosEquipos.add(rt);
						}
					}
				}
			}
			return resultadosEquipos;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static List<ResultTeam> getResultadosEquipos() throws Exception {
		List<ResultTeam> resultadosEquipos = new ArrayList<ResultTeam>();
		try {
			URL url = new URL(URLRESULTADOSEQUIPOS);
			URLConnection yc = Constants.obtenerURLConexion(url);

			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 3) {
							ResultTeam rt = new ResultTeam();
							rt.setPosicion(tds.get(i).text());
							rt.setEquipo(Equipos.getEquipos(tds.get(i + 1).text()));
							rt.setPuntaje(tds.get(i + 2).text());
							resultadosEquipos.add(rt);
						}
					}
				}
			}
			return resultadosEquipos;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static List<ResultRace> getResultadosCarrera(Carreras carrera) throws Exception {
		String urlString = URLRESULTADOSCARRERAS + Calendar.getInstance().get(Calendar.YEAR) + "/";

		if (carrera.equals(Carreras.AUSTRALIA))
			urlString += "893/";
		else if (carrera.equals(Carreras.MALAYSIA))
			urlString += "894/";
		else if (carrera.equals(Carreras.CHINA))
			urlString += "895/";
		else if (carrera.equals(Carreras.BAHRAIN))
			urlString += "896/";
		else if (carrera.equals(Carreras.SPAIN))
			urlString += "897/";
		else if (carrera.equals(Carreras.MONACO))
			urlString += "898/";
		else if (carrera.equals(Carreras.CANADA))
			urlString += "899/";
		else if (carrera.equals(Carreras.BRITISH))
			urlString += "901/";
		else if (carrera.equals(Carreras.GERMANY))
			urlString += "902/";
		else if (carrera.equals(Carreras.HUNGARY))
			urlString += "903/";
		else if (carrera.equals(Carreras.BELGIUM))
			urlString += "904/";
		else if (carrera.equals(Carreras.ITALY))
			urlString += "905/";
		else if (carrera.equals(Carreras.SINGAPORE))
			urlString += "906/";
		else if (carrera.equals(Carreras.KOREA))
			urlString += "907/";
		else if (carrera.equals(Carreras.JAPAN))
			urlString += "908/";
		else if (carrera.equals(Carreras.INDIA))
			urlString += "909/";
		else if (carrera.equals(Carreras.EAU))
			urlString += "910/";
		else if (carrera.equals(Carreras.USA))
			urlString += "911/";
		else if (carrera.equals(Carreras.BRAZIL))
			urlString += "912/";

		List<ResultRace> resultadosCarrerasAux = new ArrayList<ResultRace>();

		try {
			URL url = new URL(urlString);
			URLConnection yc = Constants.obtenerURLConexion(url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 8) {
							ResultRace rr = new ResultRace();
							rr.setPosicion(tds.get(i).text());
							rr.setNumeroPiloto(tds.get(i + 1).text());
							rr.setPiloto(tds.get(i + 2).text());
							rr.setEquipo(Equipos.getEquipos(tds.get(i + 3).text()));
							rr.setEquipoString(tds.get(i + 3).text());
							rr.setVueltas(tds.get(i + 4).text());
							rr.setTiempoTotal(tds.get(i + 5).text());
							rr.setGrid(tds.get(i + 6).text());
							rr.setPuntos(tds.get(i + 7).text());
							resultadosCarrerasAux.add(rr);
						}
					}
				}
			}
			return resultadosCarrerasAux;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static List<ResultRace> getResultadosCarrera(String raceYear, String raceRound, String raceName, String raceId) throws Exception {
		String urlString = URLRESULTADOSCARRERAS + raceYear + "/" + raceId + "/";

		List<ResultRace> resultadosCarrerasAux = new ArrayList<ResultRace>();

		try {
			URL url = new URL(urlString);
			URLConnection yc = Constants.obtenerURLConexion(url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			String html = a.toString();
			Document doc = Jsoup.parse(html);
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 8) {
							ResultRace rr = new ResultRace();
							rr.setPosicion(tds.get(i).text());
							rr.setNumeroPiloto(tds.get(i + 1).text());
							rr.setPiloto(tds.get(i + 2).text());
							rr.setEquipo(Equipos.getEquipos(tds.get(i + 3).text()));
							rr.setEquipoString(tds.get(i + 3).text());
							rr.setVueltas(tds.get(i + 4).text());
							rr.setTiempoTotal(tds.get(i + 5).text());
							rr.setGrid(tds.get(i + 6).text());
							rr.setPuntos(tds.get(i + 7).text());
							resultadosCarrerasAux.add(rr);
						}
					}
				}
			}
			return resultadosCarrerasAux;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneParsingException(e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DescargarFormulaOneException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public static Season obtenerCarrerasPorTemporada(String year) throws Exception {
		long empezo = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Integer yearHoy = calendar.get(Calendar.YEAR);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		
		
		if (!yearHoy.toString().equalsIgnoreCase(year)) {
			String urlString = URLRESULTADOSCARRERAS + year + "/";
			Season season = new Season();
			season.setComplete("true");
			season.setYear(year);
			List<Race> races = new ArrayList<Race>();
			Integer round = 1;
			try {
				URL url = new URL(urlString);
				log.info("Llamando a la URL: " + urlString);
				URLConnection yc = Constants.obtenerURLConexion(url);

				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuilder a = new StringBuilder();
				while ((inputLine = in.readLine()) != null)
					a.append(inputLine);
				in.close();
				String html = a.toString();
				Document doc = Jsoup.parse(html);
				Document docTR = null;
				Elements tables = doc.select("table");
				for (Element table : tables) {
					if (table.hasClass("raceResults")) {
						String tableHTML = table.outerHtml();
						docTR = Jsoup.parse(tableHTML);
						Elements trs = docTR.select("tr");
						for (Element tr : trs) {
							Elements tds = tr.select("td");
							for (int i = 0; i < tds.size(); i = i + 6) {
								Calendar c = Calendar.getInstance();
								c.setTime(formatter.parse(tds.get(i+1).text()));
								Date domingo = c.getTime();
								c.add(Calendar.DATE, -1);
								Date sabado = c.getTime();
								c.add(Calendar.DATE, -1);
								Date viernes = c.getTime();
								
								Race race = new Race();
								race.setRound(String.format("%02d", round));
								race.setName(tds.get(i).text());
								race.setDates(dayFormat.format(viernes) + "," + dayFormat.format(sabado) + "," + dayFormat.format(domingo) + " " + monthFormat.format(domingo));
								Circuit circuit = new Circuit();
								circuit.setName(tds.get(i).text());
								Country country = new Country();
								country.setName(tds.get(i).text());
								circuit.setCountry(country);
								Element anchor = tds.get(i).select("a").first();
								String linkHref = anchor.attr("href");
								String[] separador = linkHref.split("/");
								race.setId(separador[separador.length-1]);
								race.setCircuit(circuit);
								races.add(race);
								round++;
							}
						}
					}
				}
				season.setRaces(races);
				log.info("Termino el llamado a la consulta de las carreras del año "+year+": " + (System.currentTimeMillis()-empezo) + "ms");
				return season;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new DescargarFormulaOneException(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new DescargarFormulaOneParsingException(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new DescargarFormulaOneException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else
			return ObtenerDatosCarrera.obtenerDatosTemporada();
	}
}

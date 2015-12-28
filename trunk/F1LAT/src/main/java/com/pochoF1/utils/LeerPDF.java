package com.pochoF1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import com.pochoF1.enums.Carreras;
import com.pochoF1.enums.Sesiones;
import com.pochoF1.exceptions.DescargarFiaException;
import com.pochoF1.exceptions.NoFileFiaException;
import com.pochoF1.model.Piloto;
import com.pochoF1.model.Vuelta;
import com.pochoF1.xml.Race;

public class LeerPDF {
	private static Logger log = LoggerFactory.getLogger(LeerPDF.class);

	private static void descargarPDF(Carreras carrera, Sesiones sesion) throws Exception {
		String urlS = Constants.urlSave + carrera.getCarreraString() + "/";
		String urlR = Constants.urlRead + carrera.getCarreraString() + "/";

		File files = new File(urlS);
		if (!files.exists()) {
			files.mkdirs();
		}

		if (sesion.equals(Sesiones.FP1)) {
			urlR += Constants.FP1R;
			urlS += Constants.FP1W;
		} else if (sesion.equals(Sesiones.FP2)) {
			urlR += Constants.FP2R;
			urlS += Constants.FP2W;
		} else if (sesion.equals(Sesiones.FP3)) {
			urlR += Constants.FP3R;
			urlS += Constants.FP3W;
		} else if (sesion.equals(Sesiones.QUALY)) {
			urlR += Constants.QUALYR;
			urlS += Constants.QUALYW;
		} else if (sesion.equals(Sesiones.RACE)) {
			urlR += Constants.RACER;
			urlS += Constants.RACEW;
		}

		File archivo = new File(urlS + ".pdf");
		if (!archivo.exists()) {
			log.info("Descargando el archivo: " + urlR.replace("%20", " ") + ".pdf");

			try {
				URL website = new URL(urlR + ".pdf");
				InputStream is = null;

				try {
					is = website.openStream();
				} catch (FileNotFoundException e) {
					log.info("No se pudo descargar el archivo " + e.getMessage());
					throw new DescargarFiaException(e.getMessage());
				}
				ReadableByteChannel rbc = Channels.newChannel(is);

				FileOutputStream fos = new FileOutputStream(urlS + ".pdf");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				log.info("Guardando archivo: " + urlS + ".pdf");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new DescargarFiaException(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new NoFileFiaException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else
			log.info("Ya existe el archivo: " + urlS + ".pdf");

	}

	private static void descargarPDF(String raceRound, String raceYear, String raceName, Sesiones sesion, Race race, Race carreraActual) throws Exception {
		String urlS = Constants.urlSavePDF + raceYear + "/" + raceRound + "/";
		String urlR = "";
		File files = new File(urlS);
		if (!files.exists()) {
			files.mkdirs();
		}

		if (Integer.parseInt(raceYear) >= 2014) {
			if ((Integer.parseInt(raceYear) == 2014 && Integer.parseInt(raceRound) >= 12) || Integer.parseInt(raceYear) >= 2015) {

				if (race.equals(carreraActual)) {
					urlR += Constants.urlReadNew;
					if (sesion.equals(Sesiones.FP1)) {
						urlR += Constants.FP1R_NEW_ACTUAL;
						urlS += Constants.FP1W;
					} else if (sesion.equals(Sesiones.FP2)) {
						urlR += Constants.FP2R_NEW_ACTUAL;
						urlS += Constants.FP2W;
					} else if (sesion.equals(Sesiones.FP3)) {
						urlR += Constants.FP3R_NEW_ACTUAL;
						urlS += Constants.FP3W;
					} else if (sesion.equals(Sesiones.QUALY)) {
						urlR += Constants.QUALYR_NEW_ACTUAL;
						urlS += Constants.QUALYW;
					} else if (sesion.equals(Sesiones.RACE)) {
						urlR += Constants.RACER_NEW_ACTUAL;
						urlS += Constants.RACEW;
					}
				} else {
					urlR += Constants.urlReadNew + raceYear + "_" + raceRound + "_" + getOCICode(race.getCircuit().getCountry().getName()) + "_";
					if (sesion.equals(Sesiones.FP1)) {
						urlR += Constants.FP1R_NEW;
						urlS += Constants.FP1W;
					} else if (sesion.equals(Sesiones.FP2)) {
						urlR += Constants.FP2R_NEW;
						urlS += Constants.FP2W;
					} else if (sesion.equals(Sesiones.FP3)) {
						urlR += Constants.FP3R_NEW_A12;
						urlS += Constants.FP3W;
					} else if (sesion.equals(Sesiones.QUALY)) {
						urlR += Constants.QUALYR_NEW_A12;
						urlS += Constants.QUALYW;
					} else if (sesion.equals(Sesiones.RACE)) {
						urlR += Constants.RACER_NEW_A12;
						urlS += Constants.RACEW;
					}
				}

			} else {
				urlR += Constants.urlRead + raceYear + "/f1-" + raceYear + "-" + raceRound + "/" + raceYear + "_" + raceRound + "_" + getOCICode(race.getCircuit().getCountry().getName()) + "_";
				if (sesion.equals(Sesiones.FP1)) {
					urlR += Constants.FP1R_NEW;
					urlS += Constants.FP1W;
				} else if (sesion.equals(Sesiones.FP2)) {
					urlR += Constants.FP2R_NEW;
					urlS += Constants.FP2W;
				} else if (sesion.equals(Sesiones.FP3)) {
					urlR += Constants.FP3R_NEW;
					urlS += Constants.FP3W;
				} else if (sesion.equals(Sesiones.QUALY)) {
					urlR += Constants.QUALYR_NEW;
					urlS += Constants.QUALYW;
				} else if (sesion.equals(Sesiones.RACE)) {
					urlR += Constants.RACER_NEW;
					urlS += Constants.RACEW;
				}
			}

		} else {
			urlR += Constants.urlRead + raceYear + "/f1-" + raceYear + "-" + raceRound + "/";
			if (sesion.equals(Sesiones.FP1)) {
				urlR += Constants.FP1R;
				urlS += Constants.FP1W;
			} else if (sesion.equals(Sesiones.FP2)) {
				urlR += Constants.FP2R;
				urlS += Constants.FP2W;
			} else if (sesion.equals(Sesiones.FP3)) {
				urlR += Constants.FP3R;
				urlS += Constants.FP3W;
			} else if (sesion.equals(Sesiones.QUALY)) {
				urlR += Constants.QUALYR;
				urlS += Constants.QUALYW;
			} else if (sesion.equals(Sesiones.RACE)) {
				urlR += Constants.RACER;
				urlS += Constants.RACEW;
			}
		}

		File archivo = new File(urlS + ".pdf");
		if (!archivo.exists()) {
			log.info("Descargando el archivo: " + urlR.replace("%20", " ") + ".pdf");

			try {
				URL website = new URL(urlR + ".pdf");
				InputStream is = null;

				HttpURLConnection yc = Constants.obtenerHTTPConexion(website);

				try {
					is = yc.getInputStream();
				} catch (FileNotFoundException e) {
					log.info("No se pudo descargar el archivo " + e.getMessage());
					throw new DescargarFiaException(e.getMessage());
				}
				ReadableByteChannel rbc = Channels.newChannel(is);

				FileOutputStream fos = new FileOutputStream(urlS + ".pdf");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				log.info("Guardando archivo: " + urlS + ".pdf");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new DescargarFiaException(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new NoFileFiaException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else
			log.info("Ya existe el archivo: " + urlS + ".pdf");
	}

	private static void descargarPDFConGet(String raceRound, String raceYear, String raceName, Sesiones sesion, Race race, Race carreraActual) throws Exception {
		String urlS = Constants.urlSavePDF + raceYear + "/" + raceRound + "/";
		String urlR = "";
		File files = new File(urlS);
		if (!files.exists()) {
			files.mkdirs();
		}

		if (sesion.equals(Sesiones.FP1)) {
			urlS += Constants.FP1W;
		} else if (sesion.equals(Sesiones.FP2)) {
			urlS += Constants.FP2W;
		} else if (sesion.equals(Sesiones.FP3)) {
			urlS += Constants.FP3W;
		} else if (sesion.equals(Sesiones.QUALY)) {
			urlS += Constants.QUALYW;
		} else if (sesion.equals(Sesiones.RACE)) {
			urlS += Constants.RACEW;
		}

		File archivo = new File(urlS + ".pdf");
		if (!archivo.exists()) {
			if (Integer.parseInt(raceYear) >= 2014) {
				if ((Integer.parseInt(raceYear) == 2014 && Integer.parseInt(raceRound) >= 12) || Integer.parseInt(raceYear) >= 2015) {
					String urlCarreras = "http://www.fia.com/sport/championships/news/formula-1-world-championship";
					String paginaConDatos = "";
					String htmlParsed = "";
					try {
						URL urlCarrera = new URL(urlCarreras);
						URLConnection uc = Constants.obtenerURLConexion(urlCarrera);
						log.info("OBTENER PDF - Llamando a la URL: " + urlCarreras);
						BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
						String inputLineString;
						StringBuilder sb = new StringBuilder();
						while ((inputLineString = input.readLine()) != null)
							sb.append(inputLineString);
						input.close();
						htmlParsed = sb.toString();
					} catch (Exception e) {
						throw new DescargarFiaException();
					}
					Document docJsoup = Jsoup.parse(htmlParsed);
					Document docJsoupDIV = null;
					Elements divsJsoup = docJsoup.select("div");
					for (Element divJsoup : divsJsoup) {
						if (divJsoup.hasClass("view-championships-event")) {
							String divHTML = divJsoup.outerHtml();
							docJsoupDIV = Jsoup.parse(divHTML);
							Elements divsDocJsoup = docJsoupDIV.select("div");
							for (Element divInterno : divsDocJsoup) {
								if (divInterno.hasClass("views-row-" + race.getRound())) {
									Element link = divInterno.select("a").first();
									paginaConDatos = link.attr("href");
									break;
								}
							}
							break;
						}
					}
					log.info("OBTENER PDF - Termino el llamado de la URL: " + urlCarreras);
					String urlGET = "";
					
					if (sesion.equals(Sesiones.FP1) || sesion.equals(Sesiones.FP2) || sesion.equals(Sesiones.FP3)) {
						urlGET += Constants.URLFIA + paginaConDatos.split("/")[1] + "/reports/" + paginaConDatos.split("/")[3] + "/" + paginaConDatos.split("/")[4] + "/" + paginaConDatos.split("/")[4] + "-" + paginaConDatos.split("/")[5] + "-practices";
					} else if (sesion.equals(Sesiones.QUALY)) {
						urlGET += Constants.URLFIA + paginaConDatos.split("/")[1] + "/reports/" + paginaConDatos.split("/")[3] + "/" + paginaConDatos.split("/")[4] + "/" + paginaConDatos.split("/")[4] + "-" + paginaConDatos.split("/")[5] + "-qualifying";
					} else if (sesion.equals(Sesiones.RACE)) {
						urlGET += Constants.URLFIA + paginaConDatos.split("/")[1] + "/reports/" + paginaConDatos.split("/")[3] + "/" + paginaConDatos.split("/")[4] + "/" + paginaConDatos.split("/")[4] + "-" + paginaConDatos.split("/")[5] + "-race";
					}
					String ubicacionArchivo = "";
					String html = "";
					try {
						URL url = new URL(urlGET);
						log.info("OBTENER PDFGET - Llamando a la URL: " + urlGET);
						URLConnection yc = Constants.obtenerURLConexion(url);
						BufferedReader in = null;

						in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
						String inputLine;
						StringBuilder a = new StringBuilder();
						while ((inputLine = in.readLine()) != null)
							a.append(inputLine);
						in.close();
						html = a.toString();
						log.info("OBTENER PDFGET - Termino el llamado a la URL: " + urlGET);
					} catch (Exception e) {
						log.error("OBTENER PDFGET - Termino el llamado a la URL: " + urlGET ,e);
						throw new DescargarFiaException();
					}
					
					Document doc = Jsoup.parse(html);
					Document docDIV = null;
					Elements divs = doc.select("div");
					for (Element div : divs) {
						if (div.hasClass("field-type-text-with-summary")) {
							String divHTML = div.outerHtml();
							docDIV = Jsoup.parse(divHTML);
							Elements anchors = docDIV.select("a");
							int i = 1;
							for (Element anchor : anchors) {
								if (anchor.text().toUpperCase().contains("LAP TIMES") && sesion.equals(Sesiones.FP1) && i == 1) {
									ubicacionArchivo = anchor.attr("href");
									break;
								} else if (anchor.text().toUpperCase().contains("LAP TIMES") && sesion.equals(Sesiones.FP2) && i == 2) {
									ubicacionArchivo = anchor.attr("href");
									break;
								} else if (anchor.text().toUpperCase().contains("LAP TIMES") && sesion.equals(Sesiones.FP3) && i == 3) {
									ubicacionArchivo = anchor.attr("href");
									break;
								} else if (anchor.text().toUpperCase().contains("LAP TIMES") && sesion.equals(Sesiones.QUALY)) {
									ubicacionArchivo = anchor.attr("href");
									break;
								} else if (anchor.text().toUpperCase().contains("LAP ANALYSIS") && sesion.equals(Sesiones.RACE)) {
									ubicacionArchivo = anchor.attr("href");
									break;
								}
								if (anchor.text().toUpperCase().contains("LAP TIMES")) {
									i++;
								}
							}
							break;
						}

					}
					if ("".equalsIgnoreCase(ubicacionArchivo)){
						throw new DescargarFiaException("EL archivo para la sesion " + sesion.getSesionString() + " no ha sido subido al sitio de la FIA");
					}
						

					urlR += Constants.URLFIA + ubicacionArchivo.substring(1, ubicacionArchivo.lastIndexOf("."));

				} else {
					urlR += Constants.urlRead + raceYear + "/f1-" + raceYear + "-" + raceRound + "/" + raceYear + "_" + raceRound + "_" + getOCICode(race.getCircuit().getCountry().getName()) + "_";
					if (sesion.equals(Sesiones.FP1)) {
						urlR += Constants.FP1R_NEW;
					} else if (sesion.equals(Sesiones.FP2)) {
						urlR += Constants.FP2R_NEW;
					} else if (sesion.equals(Sesiones.FP3)) {
						urlR += Constants.FP3R_NEW;
					} else if (sesion.equals(Sesiones.QUALY)) {
						urlR += Constants.QUALYR_NEW;
					} else if (sesion.equals(Sesiones.RACE)) {
						urlR += Constants.RACER_NEW;
					}
				}

			} else {
				urlR += Constants.urlRead + raceYear + "/f1-" + raceYear + "-" + raceRound + "/";
				if (sesion.equals(Sesiones.FP1)) {
					urlR += Constants.FP1R;
				} else if (sesion.equals(Sesiones.FP2)) {
					urlR += Constants.FP2R;
				} else if (sesion.equals(Sesiones.FP3)) {
					urlR += Constants.FP3R;
				} else if (sesion.equals(Sesiones.QUALY)) {
					urlR += Constants.QUALYR;
				} else if (sesion.equals(Sesiones.RACE)) {
					urlR += Constants.RACER;
				}
			}

			log.info("Descargando el archivo: " + urlR.replace("%20", " ") + ".pdf");

			try {
				URL website = new URL(urlR.replace(" ", "%20") + ".pdf");
				InputStream is = null;

				HttpURLConnection yc = Constants.obtenerHTTPConexion(website);

				try {
					is = yc.getInputStream();
				} catch (FileNotFoundException e) {
					log.info("No se pudo descargar el archivo " + e.getMessage());
					throw new DescargarFiaException(e.getMessage());
				}
				ReadableByteChannel rbc = Channels.newChannel(is);

				FileOutputStream fos = new FileOutputStream(urlS + ".pdf");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				log.info("Guardando archivo: " + urlS + ".pdf");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new DescargarFiaException(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new NoFileFiaException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		} else
			log.info("Ya existe el archivo: " + urlS + ".pdf");
	}

	public static List<Piloto> leerPDF(Carreras carrera, Sesiones sesion) throws Exception {

		descargarPDF(carrera, sesion);

		String urlS = Constants.urlSave + carrera.getCarreraString() + "/";
		boolean proximaSalida = true;

		if (sesion.equals(Sesiones.FP1))
			urlS += Constants.FP1W;
		else if (sesion.equals(Sesiones.FP2))
			urlS += Constants.FP2W;
		else if (sesion.equals(Sesiones.FP3))
			urlS += Constants.FP3W;
		else if (sesion.equals(Sesiones.QUALY))
			urlS += Constants.QUALYW;
		else if (sesion.equals(Sesiones.RACE))
			urlS += Constants.RACEW;

		List<Piloto> pilotosSesion = new ArrayList<Piloto>();
		PdfReader reader = null;
		try {
			reader = new PdfReader(urlS + ".pdf");
			StringBuilder sb = new StringBuilder();
			PdfTextExtractor pdfTE = new PdfTextExtractor(reader);
			Piloto p = null;
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				sb.append(pdfTE.getTextFromPage(i));
			}
			String[] lines = sb.toString().split("\\n");

			for (int i = 0; i < lines.length; i++) {
				String s = lines[i];
				if (s.contains("NO  NO  TIME       TIME       ") || s.contains("LAP  LAP  TIME       TIME       ")) {
					p = new Piloto();
					proximaSalida = true;
					String piloto = lines[i - 1].replace("\r", "");
					String numero = piloto.substring(0, piloto.indexOf(" "));
					String nombre = piloto.substring(piloto.indexOf(" ") + 1);
					p.setNombre(nombre);

					try {
						p.setNumero(Integer.parseInt(numero));
					} catch (Exception e) {
					}

					pilotosSesion.add(p);
				}
				if (esUnaVueltaIText(s)) {
					if (p != null) {
						s = s.replace("\r", "");
						String vueltaPit = lines[i - 1].replace("\r", "");
						Vuelta v = new Vuelta();
						int nroVuelta = 0;
						try {
							nroVuelta = Integer.parseInt(vueltaPit.split(" ")[0]);
						} catch (Exception e) {
						}
						v.setNroVuelta(nroVuelta);
						String tiempoVuelta = s.replace(" ", "");
						String _min = "";
						String _seg = "";
						String _milli = "";
						String[] tiempoSplit = tiempoVuelta.split(":");
						if (tiempoSplit.length > 2) {
							_min = tiempoSplit[0];
							_seg = tiempoSplit[1];
							_milli = tiempoSplit[2];
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						} else {
							_min = tiempoSplit[0];
							_seg = tiempoSplit[1].substring(0, tiempoSplit[1].indexOf("."));
							_milli = tiempoSplit[1].substring(tiempoSplit[1].indexOf(".") + 1);
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						}
						int min = 0;
						int seg = 0;
						int milli = 0;

						try {
							min = Integer.parseInt(_min) * 60 * 1000;
						} catch (Exception e) {
						}
						try {
							seg = Integer.parseInt(_seg) * 1000;
						} catch (Exception e) {
						}
						try {
							milli = Integer.parseInt(_milli);
						} catch (Exception e) {
						}

						v.setTiempo(min + seg + milli);
						v.setTiempoVuelta(tiempoVuelta);
						v.setVueltaSalida(proximaSalida);

						if (vueltaPit.split(" ").length > 1) {
							v.setPits(true);
							proximaSalida = true;
						} else
							proximaSalida = false;

						p.getVueltasActuales().add(v);
					}
				}
			}

			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pilotosSesion;
	}

	public static List<Piloto> leerPDF(String raceRound, String raceYear, String raceName, Sesiones sesion, Race race, Race carreraActual) throws Exception {
		if (raceRound.length() == 1)
			raceRound = "0" + raceRound;
		descargarPDFConGet(raceRound, raceYear, raceName, sesion, race, carreraActual);

		String urlS = Constants.urlSavePDF + raceYear + "/" + raceRound + "/";
		boolean proximaSalida = true;

		if (sesion.equals(Sesiones.FP1))
			urlS += Constants.FP1W;
		else if (sesion.equals(Sesiones.FP2))
			urlS += Constants.FP2W;
		else if (sesion.equals(Sesiones.FP3))
			urlS += Constants.FP3W;
		else if (sesion.equals(Sesiones.QUALY))
			urlS += Constants.QUALYW;
		else if (sesion.equals(Sesiones.RACE))
			urlS += Constants.RACEW;

		List<Piloto> pilotosSesion = new ArrayList<Piloto>();
		PdfReader reader = null;
		try {
			reader = new PdfReader(urlS + ".pdf");
			StringBuilder sb = new StringBuilder();
			PdfTextExtractor pdfTE = new PdfTextExtractor(reader);
			Piloto p = null;
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				sb.append(pdfTE.getTextFromPage(i));
			}
			String[] lines = sb.toString().split("\\n");

			for (int i = 0; i < lines.length; i++) {
				String s = lines[i];
				if (s.contains("NO  NO  TIME       TIME       ") || s.contains("LAP  LAP  TIME       TIME       ")) {
					p = new Piloto();
					proximaSalida = true;
					String piloto = lines[i - 1].replace("\r", "");
					String numero = piloto.substring(0, piloto.indexOf(" "));
					String nombre = piloto.substring(piloto.indexOf(" ") + 1);
					p.setNombre(nombre);

					try {
						p.setNumero(Integer.parseInt(numero));
					} catch (Exception e) {
					}

					pilotosSesion.add(p);
				}
				if (esUnaVueltaIText(s)) {
					if (p != null) {
						s = s.replace("\r", "");
						String vueltaPit = lines[i - 1].replace("\r", "");
						Vuelta v = new Vuelta();
						int nroVuelta = 0;
						try {
							nroVuelta = Integer.parseInt(vueltaPit.split(" ")[0]);
						} catch (Exception e) {
						}
						v.setNroVuelta(nroVuelta);
						String tiempoVuelta = s.replace(" ", "");
						String _min = "";
						String _seg = "";
						String _milli = "";
						String[] tiempoSplit = tiempoVuelta.split(":");
						if (tiempoSplit.length > 2) {
							_min = tiempoSplit[0];
							_seg = tiempoSplit[1];
							_milli = tiempoSplit[2];
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						} else {
							_min = tiempoSplit[0];
							_seg = tiempoSplit[1].substring(0, tiempoSplit[1].indexOf("."));
							_milli = tiempoSplit[1].substring(tiempoSplit[1].indexOf(".") + 1);
						}
						int min = 0;
						int seg = 0;
						int milli = 0;

						try {
							min = Integer.parseInt(_min) * 60 * 1000;
						} catch (Exception e) {
						}
						try {
							seg = Integer.parseInt(_seg) * 1000;
						} catch (Exception e) {
						}
						try {
							milli = Integer.parseInt(_milli);
						} catch (Exception e) {
						}

						v.setTiempo(min + seg + milli);
						v.setTiempoVuelta(tiempoVuelta);
						v.setVueltaSalida(proximaSalida);

						if (vueltaPit.split(" ").length > 1) {
							v.setPits(true);
							proximaSalida = true;
						} else
							proximaSalida = false;

						p.getVueltasActuales().add(v);
					}
				}
			}

			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pilotosSesion;
	}

	public static TreeMap<Sesiones, List<Piloto>> leerPDFTodasSesiones(String raceRound, String raceYear, String raceName, Race race, Race carreraActual) throws Exception {
		if (raceRound.length() == 1)
			raceRound = "0" + raceRound;

		TreeMap<Sesiones, List<Piloto>> resultado = new TreeMap<Sesiones, List<Piloto>>();

		for (Sesiones sesion : Sesiones.values()) {
			if (!sesion.equals(Sesiones.FAST) && !sesion.equals(Sesiones.RESULTS)) {

				String urlS = Constants.urlSavePDF + raceYear + "/" + raceRound + "/";
				boolean proximaSalida = true;

				if (sesion.equals(Sesiones.FP1))
					urlS += Constants.FP1W;
				else if (sesion.equals(Sesiones.FP2))
					urlS += Constants.FP2W;
				else if (sesion.equals(Sesiones.FP3))
					urlS += Constants.FP3W;
				else if (sesion.equals(Sesiones.QUALY))
					urlS += Constants.QUALYW;
				else if (sesion.equals(Sesiones.RACE))
					urlS += Constants.RACEW;

				List<Piloto> pilotosSesion = new ArrayList<Piloto>();
				PdfReader reader = null;
				try {
					descargarPDF(raceRound, raceYear, raceName, sesion, race, carreraActual);

					reader = new PdfReader(urlS + ".pdf");
					StringBuilder sb = new StringBuilder();
					PdfTextExtractor pdfTE = new PdfTextExtractor(reader);
					Piloto p = null;
					for (int i = 1; i <= reader.getNumberOfPages(); i++) {
						sb.append(pdfTE.getTextFromPage(i));
					}
					String[] lines = sb.toString().split("\\n");

					for (int i = 0; i < lines.length; i++) {
						String s = lines[i];
						if (s.contains("NO  NO  TIME       TIME       ") || s.contains("LAP  LAP  TIME       TIME       ")) {
							p = new Piloto();
							proximaSalida = true;
							String piloto = lines[i - 1].replace("\r", "");
							String numero = piloto.substring(0, piloto.indexOf(" "));
							String nombre = piloto.substring(piloto.indexOf(" ") + 1);
							p.setNombre(nombre);

							try {
								p.setNumero(Integer.parseInt(numero));
							} catch (Exception e) {
							}

							pilotosSesion.add(p);
						}
						if (esUnaVueltaIText(s)) {
							if (p != null) {
								s = s.replace("\r", "");
								String vueltaPit = lines[i - 1].replace("\r", "");
								Vuelta v = new Vuelta();
								int nroVuelta = 0;
								try {
									nroVuelta = Integer.parseInt(vueltaPit.split(" ")[0]);
								} catch (Exception e) {
								}
								v.setNroVuelta(nroVuelta);
								String tiempoVuelta = s.replace(" ", "");
								String _min = "";
								String _seg = "";
								String _milli = "";
								String[] tiempoSplit = tiempoVuelta.split(":");
								if (tiempoSplit.length > 2) {
									_min = tiempoSplit[0];
									_seg = tiempoSplit[1];
									_milli = tiempoSplit[2];
									_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
								} else {
									_min = tiempoSplit[0];
									_seg = tiempoSplit[1].substring(0, tiempoSplit[1].indexOf("."));
									_milli = tiempoSplit[1].substring(tiempoSplit[1].indexOf(".") + 1);
								}
								int min = 0;
								int seg = 0;
								int milli = 0;

								try {
									min = Integer.parseInt(_min) * 60 * 1000;
								} catch (Exception e) {
								}
								try {
									seg = Integer.parseInt(_seg) * 1000;
								} catch (Exception e) {
								}
								try {
									milli = Integer.parseInt(_milli);
								} catch (Exception e) {
								}

								v.setTiempo(min + seg + milli);
								v.setTiempoVuelta(tiempoVuelta);
								v.setVueltaSalida(proximaSalida);

								if (vueltaPit.split(" ").length > 1) {
									v.setPits(true);
									proximaSalida = true;
								} else
									proximaSalida = false;

								p.getVueltasActuales().add(v);
							}
						}
					}

					if (reader != null) {
						reader.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (Piloto piloto : pilotosSesion) {
					Vuelta mejorVuelta = piloto.getVueltasActuales().size() > 0 ? piloto.getVueltasActuales().get(0) : new Vuelta();
					for (Vuelta vuelta : piloto.getVueltasActuales()) {
						if (vuelta.getTiempo() < mejorVuelta.getTiempo())
							mejorVuelta = vuelta;
					}
					piloto.setMejorVuelta(mejorVuelta);
				}

				resultado.put(sesion, pilotosSesion);
			}

		}

		return resultado;
	}

	private static Boolean esUnaVueltaIText(String linea) {

		Pattern pattern = Pattern.compile("(\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d[:]\\d\\d[.]\\d\\d\\d)");
		Matcher matcher = pattern.matcher(linea);
		if (matcher.find())
			return true;
		return false;
	}

	private static String getOCICode(String countryName) {
		try {
			Properties props = new Properties();
			InputStream fis = LeerPDF.class.getClassLoader().getResourceAsStream("OCI_CODES.xml");

			props.loadFromXML(fis);
			return props.getProperty(countryName.toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}

}

/*package com.pochoF1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pochoF1.enums.Carreras;
import com.pochoF1.enums.Equipos;
import com.pochoF1.enums.Sesiones;
import com.pochoF1.exceptions.DescargarFiaException;
import com.pochoF1.exceptions.NoFileFiaException;
import com.pochoF1.model.Piloto;
import com.pochoF1.model.Vuelta;

public class LeerPDFbkp {
	private static Logger log = LoggerFactory.getLogger(LeerPDFbkp.class);

	private static HashMap<String, Document> mapYearRaceTeams = new HashMap<String, Document>();

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

	private static void descargarPDF(String raceRound, String raceYear, String raceName, Sesiones sesion) throws Exception {
		String urlS = Constants.urlSavePDF + raceYear + "/" + raceRound + "/";
		String urlR = Constants.urlRead + raceYear + "/f1-" + raceYear + "-" + raceRound + "/";

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

		PDDocument pd;
		List<Piloto> pilotosSesion = new ArrayList<Piloto>();
		try {
			File input = new File(urlS + ".pdf");
			StringBuilder sb = new StringBuilder();
			pd = PDDocument.load(input);
			PDFTextStripper stripper = new PDFTextStripper();
			sb.append(stripper.getText(pd));
			String[] lines = sb.toString().split("\\n");
			Piloto p = null;
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

					getEquipoByPiloto(p);
					pilotosSesion.add(p);
				}
				if (esUnaVuelta(s)) {
					if (p != null) {

						s = s.replace("\r", "");
						Vuelta v = new Vuelta();
						int nroVuelta = 0;
						try {
							nroVuelta = Integer.parseInt(s.substring(0, s.indexOf(" ")));
						} catch (Exception e) {
						}
						v.setNroVuelta(nroVuelta);
						String tiempoVuelta = s.substring(s.lastIndexOf(" ")).replace(" ", "");

						String _min = "";
						String _seg = "";
						String _milli = "";

						if (tiempoVuelta.split(":").length > 2) {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.lastIndexOf(":"));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(":") + 1);
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						} else {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.indexOf("."));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(".") + 1);
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

						if (s.contains("P")) {
							v.setPits(true);
							proximaSalida = true;
						} else
							proximaSalida = false;

						p.getVueltasActuales().add(v);
					}
				}
			}

			if (pd != null) {
				pd.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pilotosSesion;
	}

	public static List<Piloto> leerPDF(String raceRound, String raceYear, String raceName, Sesiones sesion) throws Exception {

		descargarPDF(raceRound, raceYear, raceName, sesion);

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

		PDDocument pd;
		List<Piloto> pilotosSesion = new ArrayList<Piloto>();
		try {
			File input = new File(urlS + ".pdf");
			StringBuilder sb = new StringBuilder();
			pd = PDDocument.load(input);
			PDFTextStripper stripper = new PDFTextStripper();
			sb.append(stripper.getText(pd));
			String[] lines = sb.toString().split("\\n");
			Piloto p = null;
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

					getEquipoByPiloto(p);
					pilotosSesion.add(p);
				}
				if (esUnaVuelta(s)) {
					if (p != null) {

						s = s.replace("\r", "");
						Vuelta v = new Vuelta();
						int nroVuelta = 0;
						try {
							nroVuelta = Integer.parseInt(s.substring(0, s.indexOf(" ")));
						} catch (Exception e) {
						}
						v.setNroVuelta(nroVuelta);
						String tiempoVuelta = s.substring(s.lastIndexOf(" ")).replace(" ", "");

						String _min = "";
						String _seg = "";
						String _milli = "";

						if (tiempoVuelta.split(":").length > 2) {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.lastIndexOf(":"));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(":") + 1);
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						} else {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.indexOf("."));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(".") + 1);
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

						if (s.contains("P")) {
							v.setPits(true);
							proximaSalida = true;
						} else
							proximaSalida = false;

						p.getVueltasActuales().add(v);
					}
				}
			}

			if (pd != null) {
				pd.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pilotosSesion;
	}

	public static List<Piloto> leerPDF(String raceRound, String raceYear, String raceName, Sesiones sesion, String raceId) throws Exception {

		descargarPDF(raceRound, raceYear, raceName, sesion);

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

		PDDocument pd;
		List<Piloto> pilotosSesion = new ArrayList<Piloto>();
		try {
			File input = new File(urlS + ".pdf");
			StringBuilder sb = new StringBuilder();
			pd = PDDocument.load(input);
			PDFTextStripper stripper = new PDFTextStripper();
			sb.append(stripper.getText(pd));
			String[] lines = sb.toString().split("\\n");
			Piloto p = null;
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

					getEquipoByPiloto(p, raceYear, raceId);
					pilotosSesion.add(p);
				}
				if (esUnaVuelta(s)) {
					if (p != null) {

						s = s.replace("\r", "");
						Vuelta v = new Vuelta();
						int nroVuelta = 0;
						try {
							nroVuelta = Integer.parseInt(s.substring(0, s.indexOf(" ")));
						} catch (Exception e) {
						}
						v.setNroVuelta(nroVuelta);
						String tiempoVuelta = s.substring(s.lastIndexOf(" ")).replace(" ", "");

						String _min = "";
						String _seg = "";
						String _milli = "";

						if (tiempoVuelta.split(":").length > 2) {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.lastIndexOf(":"));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(":") + 1);
							_milli = _milli.length() >= 2 ? _milli.concat("0") : _milli.concat("00");
						} else {
							_min = tiempoVuelta.substring(0, tiempoVuelta.indexOf(":"));
							_seg = tiempoVuelta.substring(tiempoVuelta.indexOf(":") + 1, tiempoVuelta.indexOf("."));
							_milli = tiempoVuelta.substring(tiempoVuelta.lastIndexOf(".") + 1);
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

						if (s.contains("P")) {
							v.setPits(true);
							proximaSalida = true;
						} else
							proximaSalida = false;

						p.getVueltasActuales().add(v);
					}
				}
			}

			if (pd != null) {
				pd.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pilotosSesion;
	}

	private static Boolean esUnaVuelta(String linea) {

		Pattern pattern = Pattern
				.compile("(\\d\\s\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\s[P]\\s\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\s\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\s[P]\\s\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\s\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\s[P]\\s\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\d\\s\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\d\\s[P]\\s\\d\\d[:]\\d\\d[:]\\d\\d)|(\\d\\d\\s\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\d\\s[P]\\s\\d\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\d\\s\\d[:]\\d\\d[.]\\d\\d\\d)|(\\d\\d\\s[P]\\s\\d[:]\\d\\d[.]\\d\\d\\d)");
		Matcher matcher = pattern.matcher(linea);
		if (matcher.find())
			return true;
		return false;
	}

	private static void getEquipoByPiloto(Piloto p) {
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(Constants.urlSavePDF + "equipos_pilotos.xml");

			props.loadFromXML(fis);
			for (Object o : props.keySet()) {
				if (p.getNombre().toUpperCase().contains(o.toString().toUpperCase()))
					p.setEquipo(Equipos.valueOf(props.getProperty(o.toString())));
			}

		} catch (Exception e) {
			p.setEquipo(Equipos.DESCONOCIDO);
		}
	}

	private static void getEquipoByPiloto(Piloto p, String year, String raceId) {
		String urlString = Constants.URLRESULTADOSCARRERAS + year + "/" + raceId + "/";
		Document doc = null;
		try {
			if(mapYearRaceTeams.get(urlString) == null){
				URL url = new URL(urlString);
				URLConnection yc = Constants.obtenerURLConexion(url);

				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuilder a = new StringBuilder();
				while ((inputLine = in.readLine()) != null)
					a.append(inputLine);
				in.close();
				
				String html = a.toString();
				
				
				doc = Jsoup.parse(html);
				mapYearRaceTeams.put(urlString, doc);
			}else{
				doc = mapYearRaceTeams.get(urlString);
			}
			
			Document docTR = null;
			Elements tables = doc.select("table");
			for (Element table : tables) {
				if (table.hasClass("raceResults")) {
					String tableHTML = table.outerHtml();
					docTR = Jsoup.parse(tableHTML);
					Elements trs = docTR.select("tr");
					for (Element tr : trs) {
						if(p.getEquipo()!=null)
							break;
						Elements tds = tr.select("td");
						for (int i = 0; i < tds.size(); i = i + 8) {
							if(tds.get(i + 2).text().replaceAll("ä", "a").replaceAll("ö", "o").toLowerCase().contains(p.getNombre().split(" ")[1].toLowerCase())){
								if (tds.get(i + 3).text().toLowerCase().startsWith("ferrari")) {
									p.setEquipo(Equipos.FERRARI);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("lotus")) {
									p.setEquipo(Equipos.LOTUS);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("red bull")) {
									p.setEquipo(Equipos.RBR);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("mercedes")) {
									p.setEquipo(Equipos.MERCEDES);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("mclaren")) {
									p.setEquipo(Equipos.MCLAREN);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("str")) {
									p.setEquipo(Equipos.TORO_ROSSO);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("sauber")) {
									p.setEquipo(Equipos.SAUBER);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("williams")) {
									p.setEquipo(Equipos.WILLIAMS);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("marussia")) {
									p.setEquipo(Equipos.MARUSSIA);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("caterham")) {
									p.setEquipo(Equipos.CATERHAM);
								} else if (tds.get(i + 3).text().toLowerCase().startsWith("force")) {
									p.setEquipo(Equipos.FORCEINDIA);
								}
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			p.setEquipo(Equipos.DESCONOCIDO);
		}
	}
}
*/
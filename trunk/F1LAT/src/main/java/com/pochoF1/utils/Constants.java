package com.pochoF1.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class Constants {

	public static final String FP1R = "First%20Practice%20Session%20Lap%20Times";
	public static final String FP2R = "Second%20Practice%20Session%20Lap%20Times";
	public static final String FP3R = "Third%20Practice%20Session%20Lap%20Times";
	public static final String QUALYR = "Qualifying%20Session%20Lap%20Times";
	public static final String RACER = "Race%20Lap%20Analysis";

	public static final String FP1R_NEW = "F1_P1_Timing_FirstPracticeSessionLapTimes_V01";
	public static final String FP1R_NEW_ACTUAL = "FP1LapTimes";
	public static final String FP2R_NEW = "F1_P2_Timing_SecondPracticeSessionLapTimes_V01";
	public static final String FP2R_NEW_ACTUAL = "Lap%20Times";
	public static final String FP3R_NEW = "F1_P3_Timing_ThirdPracticeSessionLapTimes_V01";
	public static final String FP3R_NEW_A12 = "ThirdPracticeSessionLapTimes";
	public static final String FP3R_NEW_ACTUAL = "FP3LapTimes";
	public static final String QUALYR_NEW = "F1_Q0_Timing_QualifyingSessionLapTimes_V01";
	public static final String QUALYR_NEW_A12 = "QualifyingSessionLapTimes";
	public static final String QUALYR_NEW_ACTUAL = "QUALYLapTimes";
	public static final String RACER_NEW = "F1_R0_Timing_RaceLapAnalysis_V01";
	public static final String RACER_NEW_A12 = "RaceLapAnalysis";
	public static final String RACER_NEW_ACTUAL = "RaceLapTimes";
	
	public static final String URLTEST2015 = "http://f1today.net/en/jsonlive/%CODIGOTEST%/status.0.json?_=" + Calendar.getInstance().getTimeInMillis();
	
	public static final String FP1W = "FP1";
	public static final String FP2W = "FP2";
	public static final String FP3W = "FP3";
	public static final String QUALYW = "QUALY";
	public static final String RACEW = "RACE";

	public static final String URLRESULTADOSPILOTOS = "http://www.formula1.com/results/driver/";
	public static final String URLRESULTADOSEQUIPOS = "http://www.formula1.com/results/team/";
	public static final String URLRESULTADOSCARRERAS = "http://www.formula1.com/results/season/";

	public static final String URLFIA = "http://www.fia.com/";
	
	
	public static final String URLAPI = "http://ergast.com/api/f1/";
	public static final String URLAPI_PILOTOS = "drivers/";
	public static final String URLAPI_PILOTOS_RESULTADOS = "%PILOTO%/results.json?limit=500";
	public static final String URLAPI_PILOTOS_DATOS = "%PILOTO%.json";
	public static final String URLAPI_PILOTOS_PUNTAJES = "%PILOTO%/driverStandings.json";

	/** Casa **/
	// public static final String urlSave =
	// "D:/pochoF1-docs/"+Calendar.getInstance().get(Calendar.YEAR)+"/";
	// public static final String urlSavePDF = "D:/pochoF1-docs/";

	/** Server OS **/
	// public static final String urlSave =
	// System.getenv("OPENSHIFT_DATA_DIR")+"pochoF1-docs/"+Calendar.getInstance().get(Calendar.YEAR)+"/";
	// public static final String urlSavePDF =
	// System.getenv("OPENSHIFT_DATA_DIR")+"pochoF1-docs/";

	/** Server MOCHA **/
	// public static final String urlSave =
	// "/home/arieljr/pochoF1-docs/"+Calendar.getInstance().get(Calendar.YEAR)+"/";
	// public static final String urlSavePDF = "/home/arieljr/pochoF1-docs/";

	/** Server DW **/
	// public static final String urlSave =
	// "/home/pochoF1-docs/"+Calendar.getInstance().get(Calendar.YEAR)+"/";
	// public static final String urlSavePDF = "/home/pochoF1-docs/";

	/** Work **/
	public static final String urlSave = "C:/pochoF1-docs/" + Calendar.getInstance().get(Calendar.YEAR) + "/";
	public static final String urlSavePDF = "C:/pochoF1-docs/";

	/** EC2 **/
//	public static final String urlSave = "/home/ec2-user/pochoF1-docs/" + Calendar.getInstance().get(Calendar.YEAR) + "/";
//	public static final String urlSavePDF = "/home/ec2-user/pochoF1-docs/";

	public static final String urlRead = "http://184.106.145.74/f1-championship/f1-";
	public static final String urlReadNew = "http://www.fia.com/sites/default/files/championship/event_report/documents/";

	public static final String urlFormula1TimeTable = "http://www.formula1.com/xml/race_timetable_flash.xml";

	public static final Boolean estoyConProxy = true;

	public static HttpURLConnection obtenerHTTPConexion(URL url) throws IOException {
		HttpURLConnection con = null;
		if (estoyConProxy) {
			System.setProperty("http.proxyHost", "192.168.113.13");
			System.setProperty("http.proxyPort", "8080");
			con = (HttpURLConnection) url.openConnection();
			Base64Encoder encoder = new Base64Encoder();
			String encodedUserPwd = encoder.encode("CCBA\\P044506:Febrero2015".getBytes());
			con.setRequestProperty("Proxy-Authorization", "Basic " + encodedUserPwd);
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		} else {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		}
		return con;
	}

	public static URLConnection obtenerURLConexion(URL url) throws IOException {
		HttpURLConnection con = null;
		if (estoyConProxy) {
			System.setProperty("http.proxyHost", "192.168.113.13");
			System.setProperty("http.proxyPort", "8080");
			con = (HttpURLConnection) url.openConnection();
			Base64Encoder encoder = new Base64Encoder();
			String encodedUserPwd = encoder.encode("CCBA\\P044506:Febrero2015".getBytes());
			con.setRequestProperty("Proxy-Authorization", "Basic " + encodedUserPwd);
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		} else {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		}
		return con;
	}

}
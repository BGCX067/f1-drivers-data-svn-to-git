var currentGPOffset = 0;
var currentSessionOffset = 0;
var previousGPOffset = 0;
var clockMode = 0;
var now,nowfudge;
var SEASON_ENDED = false;
var Session_ID = 0;
var sessionFinished = false;
var grand_prix = new Array();

function GrandPrix(id, tiempo, circuito, pais, fecha) {
	this.sesiones = new Array();
	this.urls = new Array();
	this.id = id;
	this.tiempo = tiempo;
	this.circuito = circuito;
	this.pais = pais;
	this.fecha = fecha;
	return this;
}

function ListaGrandPrix(s, d, l, bGP, c, sessionId) {
	this.session = s;
	this.start = new Date(d);
	this.end = new Date;
	this.end.setTime(this.start.getTime() + l * 60 * 1000);
	this.countdown = new Date(0);
	this.scountdown = new Date(0);
	this.inProgress = false;
	this.isComplete = c;
	this.isGP = bGP;
	this.sessionID = sessionId;	
	return this;
}


function getGPData() {
	var gp,s,gps,ss;
	var ret = this;
	
	gp = grand_prix[currentGPOffset];
	gps = gp.sessions;
	ss = gps[gps.length-1];
	previousGPOffset = currentGPOffset;
	currentSessionOffset = 0;

	ret.isComplete = false;
	
    for (; currentSessionOffset < gps.length; currentSessionOffset++) {
    	s = gps[currentSessionOffset];
        if (s.start > new Date()) {
	        ret.inProgress = false;
	        ret.afterRace = false;
	        break;
        }
        else if (s.end >= new Date()) {
			ret.isComplete = (s.isComplete === 'true');
			ret.inProgress = true;
	        ret.afterRace = false;
	        break;
        }
        else if (s.isGP == true) {
			ret.isComplete = true;
	        ret.inProgress = false;
	        ret.afterRace = true;
	        //console.log("Is After Race");
	        break;
       }
    }

    ret.id = gp.id;
	ret.offset = gp.offset;
	ret.session = s.session;
	ret.start = s.start;
	ret.end = s.end
	ret.isGP = s.isGP;
	ret.sessionID = s.sessionID;
	ret.countdown = new Date(ss.start - new Date());
	ret.scountdown = new Date(s.start - new Date());
	ret.circuitname = gp.circuitname;
	ret.countryname = gp.countryname;
	ret.daterange = gp.daterange;
	return this;
}

function setTime(id,valor){
	if(valor<10)
		valor = "0"+valor;
	jQuery("#"+id).text(valor);
	jQuery("#"+id).innerHTML = valor;
}

function updateCountdown(){
	now = new Date();
    jQuery("#currentRaceDiv").css('display', '');
    if (SEASON_ENDED) {
		jQuery("#currentRaceDiv").css('display', 'none');
		setVisibility("raceon", false);
		setVisibility("finDeTemporada", true);
		setVisibility("countdown", false);
		clockMode = 3;
	} else {
	    var gp = getGPData();	
		if (gp)	{
			do {
				var timeRemaining = gp.scountdown.getUTCSeconds();
				setTime("seg",timeRemaining);
				
				timeRemaining = gp.scountdown.getUTCMinutes();
				setTime("min",timeRemaining);
				
				timeRemaining = gp.scountdown.getUTCHours();
				setTime("horas",timeRemaining);
				
				timeRemaining = Math.floor(gp.scountdown.getTime() / (24 * 3600000));
				setTime("dias",timeRemaining);
				
				
			} while(0);

			jQuery('#session_name').text(gp.session.toUpperCase() + "");
			$("#sessionNow").text(gp.session.toUpperCase() + " ");

			if (gp.inProgress && !gp.isComplete) {
			    if (clockMode != 2) {
			        setVisibility("raceon", true);
			        setVisibility("finDeTemporada", false);
			        setVisibility("countdown", false);
			        setVisibility("sessionNameHolder", false);
			        setVisibility("sleeping", false);
			        setVisibility("weather", false);
			        setVisibility("clockSubItem", false);
			        clockMode = 2;
			    }
			} else if ((gp.afterRace) || (gp.inProgress && gp.isComplete)) {
			    if (clockMode != 4) {
			        setVisibility("raceon", false);
			        setVisibility("finDeTemporada", false);
			        setVisibility("countdown", false);
			        setVisibility("sessionNameHolder", false);
			        setVisibility("sleeping", true);
			        setVisibility("weather", false);
			        setVisibility("clockSubItem", false);
			        clockMode = 4;
			    }
			} else {
			    if (clockMode != 2) {
			        setVisibility("raceon", true);
			        setVisibility("finDeTemporada", false);
			        setVisibility("countdown", false);
			        setVisibility("sessionNameHolder", false);
			        setVisibility("sleeping", false);
			        setVisibility("weather", false);
			        setVisibility("clockSubItem", false);
			        clockMode = 2;
			    }
			    if (clockMode != 1) {
			        setVisibility("raceon", false);
			        setVisibility("finDeTemporada", false);
			        setVisibility("countdown", true);
			        setVisibility("sessionNameHolder", true);
			        setVisibility("sleeping", false);
			        setVisibility("weather", true);
			        setVisibility("clockSubItem", true);
			    }
			    clockMode = 1;
			}

	} else {
	    if (clockMode != 3) {
	        setVisibility("raceon", false);
	        setVisibility("finDeTemporada", true);
	        setVisibility("countdown", false);
	        setVisibility("sessionNameHolder", false);
	        setVisibility("sleeping", false);
	        setVisibility("weather", false);
	        setVisibility("clockSubItem", false);
	    }
	    clockMode = 3;
	}

    if (gp.session == "Race" && (gp.sessionID == Session_ID) && sessionFinished) {
        setVisibility("raceon", false);
        setVisibility("finDeTemporada", false);
        setVisibility("countdown", false);
        setVisibility("sessionNameHolder", false);
        setVisibility("sleeping", true);
        setVisibility("weather", false);
        setVisibility("clockSubItem", false);
    }
	
	if((gp.sessionID == Session_ID) && !sessionFinished) {
	    setVisibility("raceon", true);
	    setVisibility("finDeTemporada", false);
	    setVisibility("countdown", false);
	    setVisibility("sessionNameHolder", false);
	    setVisibility("sleeping", false);
	    setVisibility("weather", false);
	    setVisibility("clockSubItem", false);
	}	
	}
	setTimeout("updateCountdown()", 1001 - (new Date()).getMilliseconds());
}

function setVisibility(o,b) {
	v = getElement(o);
	if (v == null) return;
	v = v.style;
	var s = (b)?"":"none";
	if (v.display != s)
		v.display = s;
}

function getElement(o) {
	return MM_findObj(o);
	if (document.getElementById)
		return document.getElementById(o);
	else if (document.all)
		return document.all[o];
}

function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}


function Time_ParseISO8601 (sISO, bWithTimeZone) {
    var sYear = sISO.substr(0, 4);
    var sMonth = sISO.substr(5, 2);
    var sDay = sISO.substr(8, 2);
    var sHour = sISO.substr(11, 2);
    var sMinute = sISO.substr(14, 2);
    var sZone;

    if (bWithTimeZone)
        sZone = sISO.substr(16, 3).toString() + sISO.substr(20, 2); 
    else
        sZone = "+0000";

    var sFormat = sYear + "/" + sMonth + "/" + sDay + " " + sHour + ":" + sMinute + ":00 UTC" + sZone;

    return new Date(sFormat);
}

function Time_GetMonthName(iMonth) {
    switch (iMonth) {
        case 0: return "January"; break;
        case 1: return "February"; break;
        case 2: return "March"; break;
        case 3: return "April"; break;
        case 4: return "May"; break;
        case 5: return "June"; break;
        case 6: return "July"; break;
        case 7: return "August"; break;
        case 8: return "September"; break;
        case 9: return "October"; break;
        case 10: return "November"; break;
        case 11: return "December"; break;
        default: return ""; 
    }
}

function Time_GetWeekdayName(iDay) {
    switch (iDay) {
        case 0: return "Sunday"; break;
        case 1: return "Monday"; break;
        case 2: return "Tuesday"; break;
        case 3: return "Wednesday"; break;
        case 4: return "Thursday"; break;
        case 5: return "Friday"; break;
        case 6: return "Saturday"; break;
        default: return ""; 
    }
}

function cambiarHora(obj) {
    var dTime;
    var sZone = obj.id.toString().toLowerCase();
    var bLocal = false;
	
	switch (sZone) {
        case "race":
                bLocal = false;
                obj.childNodes[0].nodeValue = "Convertir a mi horario local";
				document.getElementById("spanHorario").innerHTML = "Horario de la carrera";
                obj.id = "local";
                break;
        default:
                bLocal = true;
                obj.childNodes[0].nodeValue = "Convertir a horario de la carrera";
				document.getElementById("spanHorario").innerHTML = "Horario de mi país";
                obj.id = "race";
    }
	
	jQuery('span[id^="timeSession_"]').each(function(){
    	var partes=jQuery(this).attr("id").split("_");
    	dTime = Time_ParseISO8601(partes[1], bLocal);
    	
    	if (bLocal)
            this.innerHtml = Time_GetWeekdayName(dTime.getDay()) + "&nbsp;" + dTime.getDate() + "&nbsp;" + Time_GetMonthName(dTime.getMonth());
        else
        	this.innerHtml = Time_GetWeekdayName(dTime.getUTCDay()) + "&nbsp;" + dTime.getUTCDate() + "&nbsp;" + Time_GetMonthName(dTime.getUTCMonth());
    	
    	 this.childNodes[0].nodeValue = "";
         
         if (bLocal) {
             this.childNodes[0].nodeValue = Time_GetWeekdayName(dTime.getDay()).substr(0, 3) + " " + ZeroPadInteger(dTime.getHours()) + ":" + ZeroPadInteger(dTime.getMinutes());
         } else {
             this.childNodes[0].nodeValue = Time_GetWeekdayName(dTime.getUTCDay()).substr(0, 3) + " " + ZeroPadInteger(dTime.getUTCHours()) + ":" + ZeroPadInteger(dTime.getUTCMinutes());
         }
    	 
    });

    

    return false;
}

function ZeroPadInteger(integer) {
    string = "";
    
    try {
        integer = parseInt(integer, 10);
        if (integer < 0)
            string = integer;
        else if (integer < 10)
            string = "0" + integer.toString();
        else 
            string = integer.toString();
    } catch (error) {
    }

    return string;
}

jQuery(document).ready(function(){
    var sessionsGP = new Array();
    SEASON_ENDED = false;    
	var race = "";
	jQuery.post('script/clock.php', function(responseJson) {
		 race = responseJson;
    	 grand_prix[0] = new GrandPrix(race.id[0],120,race.circuit.name[0],race.circuit.country.name[0]);
    	 for(var x=0;x<race.sessions.length;x++){
    		 var sessionGP = race.sessions[x];
    		 var duration = sessionGP.duration[0];
    		 var tipoSesion = sessionGP.type[0];
    		 if(duration == '')
    			 duration = 60;
    		 if(tipoSesion == '1')
    			 tipoSesion = true;
    		 else
    			 tipoSesion = false;
    		 sessionsGP.push(new ListaGrandPrix(sessionGP.name[0],sessionGP.utc[0],duration,tipoSesion, sessionGP.complete[0], sessionGP.id[0]));
			 
			 var fila = "";
			 fila += "<tr><td>" + sessionGP.name[0] + "</td><td style='text-align: right !important;'><span id='timeSession_" + sessionGP.local[0] + "'/>" + sessionGP.date[0].substring(0,3) + " " + sessionGP.startTime[0] + "</span></td></tr>" ;
			 document.getElementById('bodyTablaSesiones').innerHTML += fila;

    	 }
		var boton = "<tr><td colspan='2' align='center' valign='bottom' style='text-align: center !important; vertical-align: bottom !important;'><a id='local' href='javascript:void(0);' onclick='return cambiarHora(this)'>Convertir a mi horario local</a></td></tr>";
		document.getElementById('bodyTablaSesiones').innerHTML += boton;
		grand_prix[0].sessions = sessionsGP;
		 
		 /** Armado de circuito etc **/
		 
		 $("#datesRace").html(race.dates[0]);
		 $("#raceCountry").html(race.circuit.country.name[0]);
		 $("#raceCircuit").html(race.name[0]);
		 $("#imgCircuito").attr("src","img/circuitos/"+race.circuit.name[0].toLowerCase()+".jpg");
		 $("#imgCircuito").attr("title",race.name[0]);
		 $("#imgCircuito").attr("alt",race.name[0]);
		 
    	 updateCountdown();
     });
});
	

<%@ page import="java.util.Date"%>
<%@ page import="com.pochoF1.enums.Equipos"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body class="page">
	<div class="wrap">
		<header id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		<div id="main">
			<div class="breadcrumb-wrapper">
				<div class="container" style="margin-left: 0px !important; margin-right: 0px !important; width: 100%;">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-xs-12 col-sm-2">
							<h2 class="title"><spring:message code="showraces.main.title" text="Simulación" /></h2>
						</div>
						<div class="col-lg-9 col-md-9 col-xs-12 col-sm-10">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><spring:message code="showraces.main.title" text="Simulación" /></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="container" style="margin-left: 0px !important; margin-right: 0px !important; width: 100%;">
				<div class="row">
					<%-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h3>
							<spring:message code="showraces.title.tests" text="Tests 2015" />
						</h3>
					</div>
					<c:choose>
						<c:when test="${ERRORPRACTICA}">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="noPracticas">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<h3 style="text-align: center;"><spring:message code="showraces.no.hay.sesion" text="No Live Sessions" /></h3>
								</div>
								<br/><br/>
								<hr/>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="practicas" style="display:none;">
								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
									<h3 style="text-align: center;" id="tituloPractica">${practica.title}</h3>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
									<h4>
										<spring:message code="showraces.estado.pista" text="Track Status: " />&nbsp;&nbsp;
										<span id="trackStatus">
											<c:choose>
												<c:when test="${fn:toUpperCase(practica.status) eq 'GREEN'}"><img title="<spring:message code="showraces.pista.verde" text="Green Track" />" alt="<spring:message code="showraces.pista.verde" text="Green Track" />" src="static/img/estado_verde.png" width="24px"/></c:when>
												<c:when test="${fn:toUpperCase(practica.status) eq 'FINISH'}"><img title="<spring:message code="showraces.pista.termino" text="Finish" />" alt="<spring:message code="showraces.pista.termino" text="Finish" />" src="static/img/estado_finish.png" width="24px"/></c:when>
												<c:otherwise><img title="<spring:message code="showraces.pista.roja" text="Red Track" />" alt="<spring:message code="showraces.pista.roja" text="Red Track" />" src="static/img/estado_rojo.png" width="24px"/></c:otherwise>
											</c:choose>
										</span>
									</h4>
									<h4>
										<spring:message code="showraces.clima" text="Track Weather: " />&nbsp;&nbsp;
										<span id="trackWeather">
											${practica.weather_text}
										</span>
									</h4>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
									<table class="table table-condensed table-striped" style="text-align: center;" id="tablaTest">
										<thead id="theadTest">
											<tr>
												<th style="text-align: center;"><spring:message code="showraces.nombre.piloto" text="Driver" /></th>
												<th style="text-align: center;"><spring:message code="showraces.equipo" text="Team" /></th>
												<th style="text-align: center;"><spring:message code="showraces.tiempo" text="Time" /></th>
												<th style="text-align: center;"><spring:message code="showraces.diferencia" text="Difference" /></th>
												<th style="text-align: center;"><spring:message code="showraces.vueltas" text="Laps" /></th>
												<th style="text-align: center;"><spring:message code="showraces.ultima.goma" text="Last tyre used" /></th>
												<th style="text-align: center;"><spring:message code="showraces.box" text="Box" /></th>
											</tr>
										</thead>
										<tbody id="tbodyTest">
											<c:forEach var="piloto" items="${practica.results}">
												<tr id="${piloto.coureurID}">
													<td>${piloto.naam}</td>
													<td>${piloto.teamnaam} - ${piloto.chassis}</td>
													<td>${piloto.laptime}</td>
													<td>${piloto.difference}</td>
													<td>${piloto.ronden}</td>
													<td>${piloto.banden}</td>
													<td>${piloto.pit}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<br /> <br />
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="noPracticas" style="display:none;">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<h3 style="text-align: center;"><spring:message code="showraces.no.hay.sesion" text="No Live Sessions" /></h3>
								</div>
								<br/><br/>
								<hr/>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="practicas">
								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
									<h3 style="text-align: center;" id="tituloPractica">${practica.title}</h3>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
									<h4>
										<spring:message code="showraces.estado.pista" text="Track Status: " />&nbsp;&nbsp;
										<span id="trackStatus">
											<c:choose>
												<c:when test="${fn:toUpperCase(practica.status) eq 'GREEN'}"><img title="<spring:message code="showraces.pista.verde" text="Green Track" />" alt="<spring:message code="showraces.pista.verde" text="Green Track" />" src="static/img/estado_verde.png" width="24px"/></c:when>
												<c:when test="${fn:toUpperCase(practica.status) eq 'FINISH'}"><img title="<spring:message code="showraces.pista.termino" text="Finish" />" alt="<spring:message code="showraces.pista.termino" text="Finish" />" src="static/img/estado_finish.png" width="24px"/></c:when>
												<c:otherwise><img title="<spring:message code="showraces.pista.roja" text="Red Track" />" alt="<spring:message code="showraces.pista.roja" text="Red Track" />" src="static/img/estado_rojo.png" width="24px"/></c:otherwise>
											</c:choose>
										</span>
									</h4>
									<h4>
										<spring:message code="showraces.clima" text="Track Weather: " />&nbsp;&nbsp;
										<span id="trackWeather">
											${practica.weather_text}
										</span>
									</h4>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
									<table class="table table-condensed table-striped" style="text-align: center;" id="tablaTest">
										<thead id="theadTest">
											<tr>
												<th style="text-align: center;"><spring:message code="showraces.nombre.piloto" text="Driver" /></th>
												<th style="text-align: center;"><spring:message code="showraces.equipo" text="Team" /></th>
												<th style="text-align: center;"><spring:message code="showraces.tiempo" text="Time" /></th>
												<th style="text-align: center;"><spring:message code="showraces.diferencia" text="Difference" /></th>
												<th style="text-align: center;"><spring:message code="showraces.vueltas" text="Laps" /></th>
												<th style="text-align: center;"><spring:message code="showraces.ultima.goma" text="Last tyre used" /></th>
												<th style="text-align: center;"><spring:message code="showraces.box" text="Box" /></th>
											</tr>
										</thead>
										<tbody id="tbodyTest">
											<c:forEach var="piloto" items="${practica.results}">
												<tr id="${piloto.coureurID}">
													<td>${piloto.naam}</td>
													<td>${piloto.teamnaam} - ${piloto.chassis}</td>
													<td>${piloto.laptime}</td>
													<td>${piloto.difference}</td>
													<td>${piloto.ronden}</td>
													<td>${piloto.banden}</td>
													<td>${piloto.pit}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<br /> <br />
							</div>
						</c:otherwise>
					</c:choose> --%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-7 col-md-7 col-sm-8 col-xs-10 col-lg-offset-5 col-md-offset-5 col-sm-offset-4 col-xs-offset-2">
							<div class="input-group date" style="width: 220px;">
								<input type="text" class="form-control" readonly="readonly" value="${raceYear}" id="fecha" name="fecha"><span class="input-group-addon" style="cursor: pointer;"><i class="icon-th"></i>&nbsp;<spring:message code="showraces.choose.year" text="Choose year" /></span>
							</div>
						</div>
					</div>
					<br /> <br />
					<div class="col-lg-12  col-md-12 col-sm-12 col-xs-12">
						<h3>
							<spring:message code="showraces.title.races" text="Races - Year :" />
							<span id="spanYear"></span>
						</h3>
						<div class="portfolio">
							<c:set var="cantidad" value="${fn:length(season.races)}" />
							<form action="showRaces" method="POST" id="raceForm">
								<input type="hidden" name="raceRound" id="raceRound" /> 
								<input type="hidden" name="raceYear" id="raceYear" /> 
								<input type="hidden" name="raceName" id="raceName" /> 
								<input type="hidden" name="raceId" id="raceId" />
								<div id="divRaces">
									<c:forEach var="race" items="${season.races}">
										<div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 item">
											<div class="portfolio-item" style="cursor: pointer;" onclick="selectRace('<fmt:formatNumber type="number" pattern="00" value="${race.round}" />','${race.circuit.country.name}','${race.id}')">
												<img src="static/img/Races/flag-<c:out value='${fn:toLowerCase(race.circuit.country.name)}'/>.png" alt="${race.name}" title="${race.name}" />
												<div class="portfolio-item-title">
													<p>${fn:toUpperCase(race.circuit.country.name)} - ${fn:toUpperCase(race.dates)}</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">
						<spring:message code="modal.loading" text="Loading" />
					</h3>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<h2>
							<spring:message code="modal.waiting" text="Please wait" />
						</h2>
					</div>
					<div class="text-center">
						<img alt="Loading" src="static/img/ajax-loader.gif" width="45px" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- scripts -->
	<script src="static/js/bootstrap.js"></script>
	<script src="static/js/jquery.parallax.js"></script>
	<script src="static/js/modernizr-2.6.2.min.js"></script>
	<script src="static/js/revolution-slider/js/jquery.themepunch.revolution.min.js"></script>
	<script src="static/js/jquery.prettyPhoto.js"></script>
	<script src="static/js/superfish.js"></script>
	<script src="static/js/jquery.sticky.js"></script>
	<script src="static/js/imagesloaded.pkgd.min.js"></script>
	<script src="static/js/waypoints.min.js"></script>
	<script src="static/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script src="static/datepicker/js/bootstrap-datepicker.js"></script>
	<script src="static/js/functions.js"></script>
	<script src="static/js/index.js"></script>
	<script>
		$("#spanYear").html($("#fecha").val());
		$(document).ready(function() {
			if ($("#fecha").val() < new Date().getFullYear()) {
				cargarCarrerasPorAnio($("#fecha").val());
			}
			//setInterval(actualizarTablaTests,10000);
		});
	</script>
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
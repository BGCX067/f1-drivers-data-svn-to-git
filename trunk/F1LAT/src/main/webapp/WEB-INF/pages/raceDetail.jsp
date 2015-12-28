<%@ page import="java.util.Date"%>
<%@ page import="com.pochoF1.enums.Equipos"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<h2 class="title">
								<c:choose>
									<c:when test="${session == 'RESULTS'}">
										${raceName} - ${raceYear} ${fn:toUpperCase(session)}
									</c:when>
									<c:when test="${session == 'FAST'}">
										${raceName} - ${raceYear}
									</c:when>
									<c:otherwise>
										${raceName} - ${raceYear} ${fn:toUpperCase(session)}
									</c:otherwise>
								</c:choose>
							</h2>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><a href="showRaces"><spring:message code="showraces.main.title" text="Simulación" /></a></li>
									<li><c:choose>
											<c:when test="${session == 'RESULTS'}">
										${raceName} - ${raceYear} ${fn:toUpperCase(session)}
									</c:when>
											<c:when test="${session == 'FAST'}">
										${raceName} - ${raceYear}
									</c:when>
											<c:otherwise>
										${raceName} - ${raceYear} ${fn:toUpperCase(session)}
									</c:otherwise>
										</c:choose></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="content">
			<div class="container" style="margin-left: 0px !important; margin-right: 0px !important; width: 100%;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<c:choose>
						<c:when test="${OK}">
							<c:choose>
								<c:when test="${session == 'RESULTS'}">
									<div class="row">
										<div class="col-md-7">
											<h4 class="clearfix pull-left">
												<spring:message code="race.detail.title" text="Results" />
											</h4>
										</div>
										<div class="col-md-4 col-md-offset-1">
											<h4 class="clearfix pull-left">
												<spring:message code="race.detail.podium" text="Podium" />
											</h4>
										</div>
									</div>
									<div class="row">
										<div class="col-md-7 table-responsive well well-sm">
											<table class="table table-condensed table-striped">
												<thead>
													<tr>
														<th><spring:message code="common.table.pos" text="Pos" /></th>
														<th><spring:message code="common.table.nro" text="No." /></th>
														<th><spring:message code="common.table.driver" text="Driver" /></th>
														<th><spring:message code="common.table.team" text="Team" /></th>
														<th><spring:message code="common.table.laps" text="Laps" /></th>
														<th><spring:message code="common.table.time" text="Time" /></th>
														<th><spring:message code="common.table.grid" text="Grid" /></th>
														<th><spring:message code="common.table.points" text="Points" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="resultado" items="${RESULTADOCARRERAS}">
														<tr>
															<td>${resultado.posicion}</td>
															<td>${resultado.numeroPiloto}</td>
															<td>${resultado.piloto}</td>
															<td>${resultado.equipoString}</td>
															<td>${resultado.vueltas}</td>
															<td>${resultado.tiempoTotal}</td>
															<td>${resultado.grid}</td>
															<td>${resultado.puntos}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="col-md-4 col-md-offset-1 well">
											<div style="text-align: center;">
												<img src="static/img/Drivers/portrait/${fn:replace(fn:replace(RESULTADOCARRERAS[0].piloto,'ä','a'),'ö','o')}.jpg" alt="${RESULTADOCARRERAS[0].piloto}" title="${RESULTADOCARRERAS[0].piloto}" height="130px" />
												<h3>
													<spring:message code="race.detail.podium.winner" text="Winner" />
												</h3>
											</div>
											<div style="float: left; text-align: center;">
												<img src="static/img/Drivers/portrait/${fn:replace(fn:replace(RESULTADOCARRERAS[1].piloto,'ä','a'),'ö','o')}.jpg" alt="${RESULTADOCARRERAS[1].piloto}" title="${RESULTADOCARRERAS[1].piloto}" height="130px" />
												<h4>
													<spring:message code="race.detail.podium.second" text="Second Place" />
												</h4>
											</div>
											<div style="float: right; text-align: center;">
												<img src="static/img/Drivers/portrait/${fn:replace(fn:replace(RESULTADOCARRERAS[2].piloto,'ä','a'),'ö','o')}.jpg" alt="${RESULTADOCARRERAS[2].piloto}" title="${RESULTADOCARRERAS[2].piloto}" height="130px" />
												<h4>
													<spring:message code="race.detail.podium.third" text="Third Place" />
												</h4>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<c:set var="empezarPagina" value="4" />
									<c:set var="nroFila" value="0" />
									<c:set var="cerrarFila" value="1" />
									<c:set var="cantidadPilotos" value="${fn:length(LISTAPILOTOS)}" />
									<c:forEach var="piloto" items="${LISTAPILOTOS}">
										<c:if test="${empezarPagina % 4 == 0}">
											<c:set var="cerrarFila" value="1" />
											<c:set var="nroFila" value="${nroFila + 1}" />
											<div class="row" id="fila_${nroFila}">
										</c:if>
										<div class="col-md-3">
											<h5>
												${fn:trim(piloto.nombre)} <span id="contendor" style="float: right;"> <a href="#" style="text-decoration: none;" class="btn-select-all-nonPits_${piloto.numero}" title="<spring:message code="common.checkbox.all.laps"  text="Selects all valid laps (No pit entrance/exit)"/>"><i class="fa fa-check"></i></a> <input type="checkbox" data-toggle="tooltip" data-placement="top"
													title="<spring:message code="common.checkbox.select.driver"  text="Select Driver to compare"/>" id="checkBox_${piloto.numero}" name="checkBox_${piloto.numero}" value="${piloto.numero}" />
												</span>
											</h5>
											<div class="table-responsive" style="max-height: 500px; width: 100%; overflow-y: auto;">
												<table class="table table-condensed table-hover" style="border-left: 1px solid #ddd; border-bottom: 1px solid #ddd; border-right: 1px solid #ddd; border-top: 1px solid #ddd;">
													<thead>
														<tr>
															<th style="text-align: center; font-size: 82%"><spring:message code="common.table.nro" text="No." /></th>
															<th style="text-align: center; font-size: 82%"><spring:message code="common.table.time" text="Time" /></th>
															<th style="text-align: center; font-size: 82%"><spring:message code="common.table.pits" text="Pits" /></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="vuelta" items="${piloto.vueltasActuales}">
															<c:choose>
																<c:when test="${vuelta.pits or vuelta.vueltaSalida}">
																	<tr onclick="seleccionarVuelta(this,'${piloto.numero}','${vuelta.nroVuelta}');" style="cursor: pointer;">
																		<td style="text-align: center; font-size: 96%">${vuelta.nroVuelta}</td>
																		<td style="text-align: center; font-size: 96%">${vuelta.tiempoVuelta}</td>
																		<td style="text-align: center; font-size: 96%"><c:if test="${vuelta.pits}">P</c:if></td>
																	</tr>
																</c:when>
																<c:otherwise>
																	<tr onclick="seleccionarVuelta(this,'${piloto.numero}','${vuelta.nroVuelta}');" id="TR_${piloto.numero};${vuelta.nroVuelta}" style="cursor: pointer;">
																		<td style="text-align: center; font-size: 96%">${vuelta.nroVuelta}</td>
																		<td style="text-align: center; font-size: 96%">${vuelta.tiempoVuelta}</td>
																		<td style="text-align: center; font-size: 96%"><c:if test="${vuelta.pits}">P</c:if></td>
																	</tr>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<c:if test="${cantidadPilotos > 0 }">
												<div style="width: 100%; padding-top: 10px;">
													<table style="width: 100%;">
														<tr>
															<th style="text-align: center; font-size: 0.8em;"><span id="avg_${piloto.numero}"><spring:message code="common.table.footer.avg" text="Average LapTime :" /></span>&nbsp;<span id="${piloto.numero}">0:00.000</span></th>
														</tr>
													</table>
												</div>
											</c:if>
										</div>
										<c:if test="${cerrarFila % 4 == 0 or empezarPagina-3 == cantidadPilotos}">
											</div>
											<br/>
										</c:if>
										<c:set var="empezarPagina" value="${empezarPagina + 1}" />
										<c:set var="cerrarFila" value="${cerrarFila + 1}" />
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<div class="row">
								<h4>${ERROR}</h4>
							</div>
							<br/>
						</c:otherwise>
					</c:choose>
				</div>
				<br />
				<div class="row">
					<div class="col-lg-2 col-md-2 col-xs-6 col-lg-offset-5 col-md-offset-5 col-xs-offset-3" title="<spring:message code="common.buttons.back.text"  text="Back to previous page"/>" onclick="history.back();">
						<a class="quick-button"> <i class="fa fa-chevron-left"></i>
							<p>
								<spring:message code="common.buttons.back" text="Back" />
							</p>
						</a>
					</div>
				</div>
				<br/>
			</div>
		</div>
	</div>

	<c:if test="${OK and session != 'RESULTS' and session != 'FAST'}">
		<div id="floatdiv" style="position: absolute; padding: 6px; z-index: 100; text-align: center; bottom: 30px; left: 25px;">
			<form action="compareDrivers" method="POST" id="formCompare">
				<input type="hidden" name="driversSel" id="driversSel" />
			</form>
			<form action="lapChart" method="POST" id="formLapChart">
				<input type="hidden" name="driversSelChart" id="driversSelChart" />
			</form>
			<div style="padding-bottom: 5px;">
				<button data-toggle="tooltip" data-placement="top" title="<spring:message code="common.buttons.compare.drivers"  text="Click to compare selected drivers"/>" class="btn btn-danger btn-xs" onclick="compareSelectedDrivers();">
					<b><spring:message code="common.buttons.compare.drivers.text" text="Compare\nselected drivers" /></b>
				</button>
			</div>
			<div>
				<button data-toggle="tooltip" data-placement="top" title="<spring:message code="common.buttons.lapchart.drivers"  text="Click to see lapcharts from selected drivers"/>" class="btn btn-success btn-xs hidden-xs hidden-sm" onclick="watchLapCharts();">
					<b><spring:message code="common.buttons.lapchart.drivers.text" text="See LapChart" /></b>
				</button>
			</div>
		</div>
	</c:if>

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
	<script src="static/js/floating-1.12.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			$('#floatdiv').addFloating({
				targetLeft : 30,
				centerY : true,
				snap : true
			});
		});
	</script>
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
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
								<spring:message code="compare.title" text="Drivers Compare" />
							</h2>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><a href=""><spring:message code="showraces.main.title" text="Simulación" /></a></li>
									<li><spring:message code="compare.title" text="Drivers Compare" /></li>
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
						<c:when test="${OKCOMPARE}">
							<div class="row">
								<c:set var="empezarPagina" value="4" />
								<c:set var="nroFila" value="0" />
								<c:set var="cerrarFila" value="1" />
								<c:set var="cantidadPilotos" value="${fn:length(LISTAPILOTOSFILTRADA)}" />
								<c:forEach var="piloto" items="${LISTAPILOTOSFILTRADA}">
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
										<div class="table-responsive" style="max-height: 500px; width: 100%; overflow-y: auto">
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
												<table  style="width: 100%;">
													<table style="width: 100%;">
														<th style="text-align: center; font-size: 0.8em;"><span id="avg_${piloto.numero}"><spring:message code="common.table.footer.avg" text="Average LapTime :" /></span>&nbsp;<span id="${piloto.numero}">0:00.000</span></th>
													</tr>
												</table>
											</div>
										</c:if>
									</div>
									<c:if test="${cerrarFila % 4 == 0 or empezarPagina-3 == cantidadPilotos}">
										</div>
										</br>
									</c:if>
									<c:set var="empezarPagina" value="${empezarPagina + 1}" />
									<c:set var="cerrarFila" value="${cerrarFila + 1}" />
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row">
								<h4>${ERROR}</h4>
							</div>
						</c:otherwise>
					</c:choose>
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
					<br />
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
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
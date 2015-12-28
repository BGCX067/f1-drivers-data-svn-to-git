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
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<h2 class="title">
								<spring:message code="driver.info.title" text="Driver Info" />
							</h2>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><a href="showRaces"><spring:message code="showraces.main.title" text="Simulación" /></a></li>
									<li><spring:message code="driver.info.title" text="Driver Info" /></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="container" style="margin-left: 0px !important; margin-right: 0px !important; width: 99%;">
				<div class="row">
					<div class="col-lg-2 col-md-3 col-sm-3 col-xs-4 col-lg-offset-2 col-md-offset-1 col-sm-offset-3 col-xs-offset-4 item">
						<div class="portfolio-item">
							<img class="img-responsive" src="static/img/Drivers/info/<c:out value="${driverId}" />.jpg" alt="<c:out value="${nombrePiloto}" />" title="<c:out value="${nombrePiloto}" />" />
						</div>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<h3>
								<c:out value="${nombrePiloto}" />
							</h3>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<table class="table">
								<tr>
									<th class="info"><spring:message code="driver.info.nationality" text="Nationality" /></th>
									<td class="" style="text-align: center;">${datosPiloto.nationality}</td>
								</tr>
								<tr>
									<th class="info"><spring:message code="driver.info.number" text="Number" /></th>
									<td class="" style="text-align: center;">${not empty datosPiloto.number ? datosPiloto.number : '---'}</td>
								</tr>
								<tr>
									<th class="info"><spring:message code="driver.info.nationality" text="Birthday" /></th>
									<td class="" style="text-align: center;"><fmt:formatDate pattern="dd-MM-yyyy" value="${datosPiloto.dob}" /></td>
								</tr>
								<tr>
									<th class="info"><spring:message code="driver.info.standing" text="Standing" /></th>
									<td class="" style="text-align: center;">---</td>
								</tr>
								<tr>
									<th class="info"><spring:message code="driver.info.standing" text="Points" /></th>
									<td class="" style="text-align: center;">---</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div role="tabpanel">
							<ul class="nav nav-pills" role="tablist">
								<li role="presentation" class="active"><a href="#carreer" aria-controls="home" role="tab" data-toggle="tab"><spring:message code="driver.info.results.title" text="Driver's carreer results" /></a></li>
								<li role="presentation"><a href="#standings" aria-controls="profile" role="tab" data-toggle="tab"><spring:message code="driver.info.results.title" text="Standings" /></a></li>
								<!-- <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
								<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li> -->
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="carreer">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
										<c:choose>
											<c:when test="${not errorResultadosPilotos}">
												<div id="driverResults"></div>
											</c:when>
											<c:otherwise>
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
													<hr>
													<div class="alert alert-danger" role="alert">
														<c:out value="${ERROR}"></c:out>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="standings">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
										<c:choose>
											<c:when test="${not errorPuntajePilotos}">
												<div id="driverStandings"></div>
											</c:when>
											<c:otherwise>
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table-responsive">
													<hr>
													<div class="alert alert-danger" role="alert">
														<c:out value="${ERRORPUNTAJE}"></c:out>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<!--   <div role="tabpanel" class="tab-pane" id="messages">...</div>
								<div role="tabpanel" class="tab-pane" id="settings">...</div> -->
							</div>
						</div>
					</div>
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
			</div>
		</div>
		<br />
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
	<script src="static/js/highcharts.js"></script>
	<script src="static/js/exporting.js"></script>
	<jsp:include page="analytics.jsp"></jsp:include>

	<script>
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			var tabId = $(e.target).attr('href');
			switch (tabId) {
			case '#carreer':
				if (typeof $('#driverResults').val() != 'undefined') {
					$('#driverResults').highcharts().reflow();
				}
				break;
			case '#standings':
				if (typeof $('#driverStandings').val() != 'undefined') {
					$('#driverStandings').highcharts().reflow();
				}
				break;
			default:
				break;
			}
		})
		$(function() {
			$('#driverResults')
					.highcharts(
							{
								chart : {
									type : 'column',
								},
								title : {
									text : '<spring:message code="drivers.info.results.text"  text="Drivers Results"/>',
									x : -20
								//center
								},
								tooltip : {
									enabled : true,
									pointFormat : '<spring:message code="info.tooltip.drivers.carreer.result"  text="Results"/>: <b>{point.y}</b><br/>',
									shared : true
								},
								subtitle : {
									text : '<spring:message code="drivers.carreer.chart.mensaje.subtitulo"  text="You can export clicking on the button on the right"/>',
									x : -20
								},
								credits : {
									enabled : false
								},
								xAxis : {
									title : {
										text : '<spring:message code="driver.results.eje.x"  text="Position"/>'
									},
									categories : [ <c:out value="${categoriesDriversResultChart}" escapeXml="false" /> ]
								},
								yAxis : {
									title : {
										text : '<spring:message code="driver.results.eje.y"  text="Result"/>'
									}
								},
								legend : {
									enabled : false
								},
								series : [ <c:out value="${dataDriversResultChart}" escapeXml="false" /> ],
								exporting : {
									filename : 'driversResults'
								}
							});
			$('#driverStandings')
					.highcharts(
							{
								chart : {
									type : 'column',
								},
								title : {
									text : '<spring:message code="drivers.info.standings.text"  text="Drivers Standings"/>',
									x : -20
									//center
								},
								tooltip : {
									enabled : true,
									pointFormat : '{series.name}: <b>{point.y}</b><br/>',
									shared : true
								},
								subtitle : {
									text : '<spring:message code="drivers.carreer.chart.mensaje.subtitulo"  text="You can export clicking on the button on the right"/>',
									x : -20
								},
								credits : {
									enabled : false
								},
								xAxis : {
									title : {
										text : '<spring:message code="driver.standings.eje.x"  text="Season"/>'
									}
								},
								yAxis : [
										{
											title : {
												text : '<spring:message code="driver.standings.eje.y1"  text="Points"/>'
											}
										},
										{
											title : {
												text : '<spring:message code="driver.standings.eje.y2"  text="Position"/>'
											},
											opposite : true
										} 
								],
								series : [ <c:out value="${dataStandingsChart}" escapeXml="false" /> ],
								exporting : {
									filename : 'driverStanding'
								}
							});
		});
	</script>
</body>
</html>
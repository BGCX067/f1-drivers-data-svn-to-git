<%@page import="com.pochoF1.enums.PilotosEnum"%>
<%@ page import="java.util.Date"%>
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
							<h2 class="title"><spring:message code="drivers.main.title" text="Pilotos" /></h2>
						</div>
						<div class="col-lg-9 col-md-9 col-xs-12 col-sm-10">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><spring:message code="drivers.main.title" text="Pilotos" /></li>
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
						<div class="col-lg-7 col-md-7 col-sm-8 col-xs-10 col-lg-offset-5 col-md-offset-5 col-sm-offset-4 col-xs-offset-2">
							<div class="input-group date" style="width: 220px;">
								<input type="text" class="form-control" readonly="readonly" value="${raceYear}" id="fecha" name="fecha"><span class="input-group-addon" style="cursor: pointer;"><i class="icon-th"></i>&nbsp;<spring:message code="showraces.choose.year" text="Choose year" /></span>
							</div>
						</div>
					</div>
					<br /> <br /> --%>
					<div class="col-lg-12  col-md-12 col-sm-12 col-xs-12">
						<%-- <h3>
							<spring:message code="showraces.title.races" text="Races - Year :" />
							<span id="spanYear"></span>
						</h3> --%>
						<div class="portfolio">
							<%-- <c:set var="cantidad" value="${fn:length(season.races)}" /> --%>
							<form action="driverInfo" method="POST" id="driverForm">
								<input type="hidden" name="driverId" id="driverId" /> 
								<div id="divDrivers">
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.hamilton%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.hamilton%>.jpg" alt="<%=PilotosEnum.hamilton.getPilotoString()%>" title="<%=PilotosEnum.hamilton.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.hamilton.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.rosberg%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.rosberg%>.jpg" alt="<%=PilotosEnum.rosberg.getPilotoString()%>" title="<%=PilotosEnum.rosberg.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.rosberg.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.massa%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.massa%>.jpg" alt="<%=PilotosEnum.massa.getPilotoString()%>" title="<%=PilotosEnum.massa.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.massa.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.bottas%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.bottas%>.jpg" alt="<%=PilotosEnum.bottas.getPilotoString()%>" title="<%=PilotosEnum.bottas.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.bottas.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.ricciardo%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.ricciardo%>.jpg" alt="<%=PilotosEnum.ricciardo.getPilotoString()%>" title="<%=PilotosEnum.ricciardo.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.ricciardo.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.kvyat%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.kvyat%>.jpg" alt="<%=PilotosEnum.kvyat.getPilotoString()%>" title="<%=PilotosEnum.kvyat.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.kvyat.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.vettel%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.vettel%>.jpg" alt="<%=PilotosEnum.vettel.getPilotoString()%>" title="<%=PilotosEnum.vettel.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.vettel.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.raikkonen%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.raikkonen%>.jpg" alt="<%=PilotosEnum.raikkonen.getPilotoString()%>" title="<%=PilotosEnum.raikkonen.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.raikkonen.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.alonso%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.alonso%>.jpg" alt="<%=PilotosEnum.alonso.getPilotoString()%>" title="<%=PilotosEnum.alonso.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.alonso.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.button%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.button%>.jpg" alt="<%=PilotosEnum.button.getPilotoString()%>" title="<%=PilotosEnum.button.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.button.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.hulkenberg%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.hulkenberg%>.jpg" alt="<%=PilotosEnum.hulkenberg.getPilotoString()%>" title="<%=PilotosEnum.hulkenberg.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.hulkenberg.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.perez%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.perez%>.jpg" alt="<%=PilotosEnum.perez.getPilotoString()%>" title="<%=PilotosEnum.perez.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.perez.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.max_verstappen%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.max_verstappen%>.jpg" alt="<%=PilotosEnum.max_verstappen.getPilotoString()%>" title="<%=PilotosEnum.max_verstappen.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.max_verstappen.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.sainz%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.sainz%>.jpg" alt="<%=PilotosEnum.sainz.getPilotoString()%>" title="<%=PilotosEnum.sainz.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.sainz.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.ericsson%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.ericsson%>.jpg" alt="<%=PilotosEnum.ericsson.getPilotoString()%>" title="<%=PilotosEnum.ericsson.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.ericsson.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 item">
										<div class="portfolio-item" style="cursor: pointer;" onclick="selectDriver('<%=PilotosEnum.nasr%>')">
											<img src="static/img/Drivers/info/<%=PilotosEnum.nasr%>.jpg" alt="<%=PilotosEnum.nasr.getPilotoString()%>" title="<%=PilotosEnum.nasr.getPilotoString()%>" />
											<div class="portfolio-item-title">
												<p><%=PilotosEnum.nasr.getPilotoString()%></p>
											</div>
										</div>
									</div>
									<%-- <c:forEach var="race" items="${season.races}">
										<div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 item">
											<div class="portfolio-item" style="cursor: pointer;" onclick="selectRace('<fmt:formatNumber type="number" pattern="00" value="${race.round}" />','${race.circuit.country.name}','${race.id}')">
												<img src="static/img/Races/flag-<c:out value='${fn:toLowerCase(race.circuit.country.name)}'/>.png" alt="${race.name}" title="${race.name}" />
												<div class="portfolio-item-title">
													<p>${fn:toUpperCase(race.circuit.country.name)} - ${fn:toUpperCase(race.dates)}</p>
												</div>
											</div>
										</div>
									</c:forEach> --%>
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
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
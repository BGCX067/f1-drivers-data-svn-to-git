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
								<spring:message code="raceinfo.title" text="Sessions Details" />
								- ${raceName} ${raceYear} GP
							</h2>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><a href="showRaces"><spring:message code="showraces.main.title" text="Simulación" /></a></li>
									<li><spring:message code="raceinfo.title" text="Sessions Details" /></li>
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
				<div class="row">
					<form action="raceInfo" method="POST" id="raceInfoForm">
						<input type="hidden" name="session" id="session" />
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<c:choose>
								<c:when test="${raceYear ge 2013}">

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.fp1"  text="Click to see laps details - Practice 1"/>" onclick="selectSession('FP1');">
										<a class="quick-button"> <i class="fa fa-info-circle"></i>
											<p>
												<spring:message code="raceinfo.fp1" text="Practice 1" />
											</p> <span class="notification blue"><spring:message code="raceinfo.friday" text="Friday" /></span>
										</a>
									</div>

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.fp2"  text="Click to see laps details - Practice 2"/>" onclick="selectSession('FP2');">
										<a class="quick-button"> <i class="fa fa-info-circle"></i>
											<p>
												<spring:message code="raceinfo.fp2" text="Practice 2" />
											</p> <span class="notification blue"><spring:message code="raceinfo.friday" text="Friday" /></span>
										</a>
									</div>

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.fp3"  text="Click to see laps details - Practice 3"/>" onclick="selectSession('FP3');">
										<a class="quick-button"> <i class="fa fa-info-circle"></i>
											<p>
												<spring:message code="raceinfo.fp3" text="Practice 3" />
											</p> <span class="notification blue"><spring:message code="raceinfo.saturday" text="Saturday" /></span>
										</a>
									</div>

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.qualy"  text="Click to see laps details - Qualy"/>" onclick="selectSession('QUALY');">
										<a class="quick-button"> <i class="fa fa-clock-o"></i>
											<p>
												<spring:message code="raceinfo.qualy" text="Qualy" />
											</p> <span class="notification green"><spring:message code="raceinfo.saturday" text="Saturday" /></span>
										</a>
									</div>

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.race"  text="Click to see laps details - Race"/>" onclick="selectSession('RACE');">
										<a class="quick-button"> <i class="fa fa-road"></i>
											<p>
												<spring:message code="raceinfo.race" text="Race" />
											</p> <span class="notification red"><spring:message code="raceinfo.sunday" text="Sunday" /></span>
										</a>
									</div>

									<div class="col-sm-2" title="<spring:message code="raceinfo.button.results"  text="Click to see results of the race"/>" onclick="selectSession('RESULTS');">
										<a class="quick-button"> <i class="fa fa-trophy"></i>
											<p>
												<spring:message code="raceinfo.results" text="Results" />
											</p> <span class="notification red"><spring:message code="raceinfo.sunday" text="Sunday" /></span>
										</a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-2" title="<spring:message code="raceinfo.button.results"  text="Click to see results of the race"/>" onclick="selectSession('RESULTS');">
										<a class="quick-button"> <i class="fa fa-trophy"></i>
											<p>
												<spring:message code="raceinfo.results" text="Results" />
											</p> <span class="notification red"><spring:message code="raceinfo.sunday" text="Sunday" /></span>
										</a>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</form>
				</div>
				<br />
				<c:choose>
					<c:when test="${raceYear ge 2013}">
						<br />
						<div class="row">
							<div class="col-lg-10 col-md-8 col-sm-12 col-xs-12 col-lg-offset-1 col-md-offset-2">
								<img class="img-responsive" src="static/img/Races/<c:out value='${fn:toLowerCase(raceName)}'/>.jpg" alt="${raceName}" title="${raceName}" />
							</div>
						</div>
					</c:when>
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
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
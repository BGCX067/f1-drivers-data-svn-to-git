<%@ page import="java.util.Date"%>
<%@ page import="com.pochoF1.enums.Equipos"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="assets/css/compiled/index.css" type="text/css" media="screen" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div> <a href="home"> <i class="icon-home"></i> <span><spring:message code="navbar.home" text="Home" /></span>
			</a>
			</li>
			<li>
				<a href="showRaces"> <i class="icon-calendar"></i> <span><spring:message code="navbar.race.details"  text="Race Details"/></span></a>
			</li>
			<li>
				<a href="about"> <i class="icon-envelope"></i> <span><spring:message code="navbar.about"  text="About me"/></span></a>
			</li>
			<!-- Keeps to have an example of dropdown <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-group"></i>
                    <span>Users</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="user-list.html">User list</a></li>
                    <li><a href="new-user.html">New user form</a></li>
                    <li><a href="user-profile.html">User profile</a></li>
                </ul>
            </li>-->
		</ul>
	</div>
	<!-- end sidebar -->
	<!-- main container -->
	<div class="content">
		<div id="pad-wrapper">
			<div class="row">
				<div class="col-md-12">
					<h4 class="clearfix pull-left">
						<spring:message code="title.driver.championship" text="Driver Championship" />
					</h4>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12 well well-sm">
					<div class="table-responsive" id="divDriverChampionship">
						<div class="progress progress-striped active" id="pilotoLoading">
						  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
						  </div>
						</div>
					</div>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-7">
					<h4 class="clearfix pull-left">
						<spring:message code="title.constructor.championship" text="Constructor Championship"/>
					</h4>
				</div>
				<div class="col-md-4 col-md-offset-1">
					<h4 class="clearfix pull-left"><spring:message code="title.current.race" text="Current Race" /></h4>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-7 well well-sm">
					<div class="table-responsive" id="divTeamChampionship">
						<div class="progress progress-striped active" id="teamLoading">
						  <div class="progress-bar progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
						  </div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-md-offset-1 well well-sm">
					<c:choose>
						<c:when test="${not errorCurrentRace}">
							<div id="currentRace">
								<h4>
									<c:out value="${race.dates}" />
								</h4>
								<div style="" id="currentRaceDiv">
									<h4>
										<b><c:out value="${fn:toUpperCase(race.circuit.country.name)}" /> </b>
										<c:out value="${race.circuit.name}" />
									</h4>
								</div>
								<div id="flag" style="text-align: center;">
									<img alt="<c:out value="${fn:toUpperCase(race.circuit.country.name)}"/>" title="<c:out value="${fn:toUpperCase(race.circuit.country.name)}"/>" src="assets/img/Races/flag-<c:out value="${fn:replace(fn:toLowerCase(race.circuit.country.name),' ','-')}"/>.png">
								</div>
							</div>
							<div>
								<div id="raceon">
									<div>
										<h3>
											<a href="http://www.formula1.com/live_timing/" target="_blank"><span id="sessionNow"></span>&nbsp;<spring:message code="session.now.on" text="SESSION NOW ON"/></a>
										</h3>
									</div>
								</div>
							</div>
							<div id="sleeping" style="display: none">
								<h3 class="sessionComplete"><spring:message code="race.complete"  text="RACE COMPLETE"/></h3>
							</div>
							<c:set var="today" value="<%=new Date()%>" />
							<div id="finDeTemporada" style="display: none">
								<h2>
									<fmt:formatDate type="date" value="${today}" pattern="yyyy" />
									FIA FORMULA ONE<br> WORLD CHAMPIONSHIP
								</h2>
							</div>
							<div id="clockSubItem" style="">
								<h2 id="sessionNameHolder" style="">
									<b><span id="session_name"></span> </b>
								</h2>
								<div id="countdown" style="">
									<span id="dias" style="color: #4093FD; font-size: 1.7em;"></span>&nbsp;<span id="diasSpan" style="color: #2D6FC2; font-size: 0.9em;"><spring:message code="clock.days" text="Days" /></span>&nbsp; <span id="horas" style="color: #4093FD; font-size: 1.6em;"></span>&nbsp;<span style="color: #2D6FC2; font-size: 0.8em;"><spring:message code="clock.hours" text="Hours" /></span>&nbsp; <span id="min" style="color: #4093FD; font-size: 1.6em;"></span>&nbsp;<span style="color: #2D6FC2; font-size: 0.8em;"><spring:message code="clock.minutes" text="Minutes" /></span>&nbsp; <span
										id="seg" style="color: #4093FD; font-size: 1.6em;"></span>&nbsp;<span style="color: #2D6FC2; font-size: 0.8em;"><spring:message code="clock.seconds" text="Seconds" /></span>
								</div>
							</div>
							<br>
							<div id="divSesiones" class="table-responsive">
								<table id="tablaSesiones" class="table table-condensed table-striped">
									<c:forEach var="s" items="${race.sessions}">
										<tr>
											<td><c:out value="${s.name}" /></td>
											<td style="text-align: right !important;"><span id="timeSession_<c:out value="${s.local}"/>"><c:out value="${fn:substring(s.date,0,3)}" />&nbsp;<c:out value="${s.startTime}" /> </span></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="center" valign="bottom" style="text-align: center !important; vertical-align: bottom !important;"><a id="local" href="javascript:void(0);" onclick="return cambiarHora(this)">Convert to my local time</a></td>
									</tr>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							<h4><spring:message code="error.loading.current.race" text="Error loading current Race" /></h4>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- scripts -->
	<script src="assets/js/jquery-1.11.0.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script src="assets/js/theme.js"></script>
	<script type="text/javascript" src="assets/js/clock/customCountdown.js"></script>
	<script src="assets/datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/js/functions.js"></script>
	<script src="assets/js/indexJS.js"></script>
	<script src="assets/js/widgets.js"></script>
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
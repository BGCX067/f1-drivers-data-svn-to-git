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
								<spring:message code="common.buttons.lapchart.drivers.text" text="Drivers Lapchart" />
							</h2>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="breadcrumbs pull-right">
								<ul>
									<li><spring:message code="breadcrum.here" text="Estas acá" />:</li>
									<li><a href="http://www.f1lat.com/"><spring:message code="navbar.home" text="Inicio" /></a></li>
									<li><a href="showRaces"><spring:message code="showraces.main.title" text="Simulación" /></a></li>
									<li><spring:message code="common.buttons.lapchart.drivers.text" text="Drivers Lapchart" /></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="container" style="margin-left: 0px !important; margin-right: 0px !important; width: 99%;">
				<div class="row table-responsive">
					<div id="container" style="min-width: 1000px !important; min-height: 600px !important"></div>
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
		$(function() {
			$('#container')
					.highcharts(
							{
								title : {
									text : '<spring:message code="common.buttons.lapchart.drivers.text"  text="Drivers Lapchart"/>',
									x : -20
								//center
								},
								/*colors : [ '#FF0000', '#666666', '#2f7ed8',
										'#0d233a', '#8bbc21', '#8c198c',
										'#910000', '#1aadce', '#492970',
										'#be68be', '#f28f43', '#77a1e5',
										'#c42525', '#a6c96a' ],*/
								tooltip: {
									enabled : true,
						            pointFormat: '{series.name}: <b>{point.y}</b><br/>',
						            valueSuffix: ' s',
						            shared: true
						        },
								subtitle : {
									text : '<spring:message code="lapchart.mensaje.subtitulo"  text="You can export clicking on the button on the right<br/>You can disable any driver by clicking on his name on the right label"/>',
									x : -20
								},
								credits : {
									enabled : false
								},
								xAxis : {
									title : {
										text : '<spring:message code="lapchart.eje.x"  text="Laps"/>'
									},
									tickInterval : 1
								},
								yAxis : {
									title : {
										text : '<spring:message code="lapchart.eje.y"  text="Time (Seconds)"/>'
									}
								},
								legend : {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'middle',
									borderWidth : 0
								},
								plotOptions : {
									series : {
										marker : {
											enabled : true
										}
									}
								},
								series : [ <c:out value="${dataChart}" escapeXml="false" /> ]
							});
		});
	</script>
</body>
</html>
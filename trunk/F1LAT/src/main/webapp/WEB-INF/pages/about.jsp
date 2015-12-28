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
<link rel="stylesheet" href="assets/css/compiled/form-showcase.css" type="text/css" media="screen" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li>
				<a href="home"> <i class="icon-home"></i> <span><spring:message code="navbar.home" text="Home" /></span></a>
			</li>
			<li>
				<a href="showRaces"> <i class="icon-calendar"></i> <span><spring:message code="navbar.race.details"  text="Race Details"/></span></a>
			</li>
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div> <a href="about"> <i class="icon-envelope"></i> <span><spring:message code="navbar.about"  text="About me"/></span></a>
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
		<div id="pad-wrapper" class="form-page">
			<div class="row">
				<div class="row">
					<div class="col-md-12">
						<h4 class="clearfix pull-left">
							<spring:message code="title.contact.me" text="Contact me" />
						</h4>
					</div>
				</div>
				<br/>
				<div class="row form-wrapper">
					<div class="col-md-12">
						<form action="enviarMail" method="POST" id="formContact">
							<div class="field-box">
	                            <label><spring:message code="contact.me.name" text="Name:" /></label>
	                            <div class="col-md-7">
	                                <input class="form-control" type="text" id="name" name="name" required="required">
	                            </div>                            
	                        </div>
	                        <div class="field-box">
	                            <label><spring:message code="contact.me.email" text="Email:" /></label>
	                            <div class="col-md-7">
	                                <input class="form-control" type="email" id="email" name="email" required="required">
	                            </div>                            
	                        </div>
	                        <div class="field-box">
	                            <label><spring:message code="contact.me.message" text="Message:" /></label>
	                            <div class="col-md-7">
	                                <textarea class="form-control" rows="4" cols="10" id="message" name="message"></textarea>
	                            </div>
	                        </div>
	                        <div style="text-align: center;">
	                        	<input type="submit" class="btn btn-primary"/>
	                        </div>
						</form> 
						<c:if test="${MOSTRAR}">
							<c:choose>
								<c:when test="${OK}">
									<div class="col-md-12">
										<h4><spring:message code="contact.me.ok" text="Your message has been sent. Thank you for your support" /></h4>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-md-12">
										<h4>${ERROR}</h4>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="row">
					<div class="col-md-12">
						<h4 class="clearfix pull-left">
							<spring:message code="title.make.contribution" text="Make a contribution" />
						</h4>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<h5><spring:message code="title.make.contribution.text" text="If you like my webpage and want to help me to maintain this site you can make it through Paypal" /></h5>
						<br/>
						<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_blank">
							<input type="hidden" name="cmd" value="_s-xclick"> 
							<input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----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-----END PKCS7-----">
							<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!"> 
							<img alt="" border="0" src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif" width="1" height="1">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- scripts -->
	<script src="assets/js/jquery-1.11.0.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script src="assets/js/theme.js"></script>
	<script src="assets/datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/js/widgets.js"></script>
	<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>
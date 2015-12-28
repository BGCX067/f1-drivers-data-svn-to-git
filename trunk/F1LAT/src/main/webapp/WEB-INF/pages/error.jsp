<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>POCHO_F1 | ERROR</title>
<meta name="description" content="POCHO_F1">
<meta name="author" content="pochoF1">
<meta name="keyword"
	content="pochoF1, pocho_f1, ariel pocho_f1,F1, F1 DATA, F1 DATA ANALYSIS, F1 laptimes analysis, f1 laptimes, f1 average laptimes, f1 average, f1 media vuelta, f1 media, f1 vueltas medias, f1 vuelta media"
>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="assets/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<link href="assets/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
<link href="assets/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="assets/datepicker/css/datepicker3.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/compiled/layout.css" />
<link rel="stylesheet" type="text/css" href="assets/css/compiled/elements.css" />
<link rel="stylesheet" type="text/css" href="assets/css/compiled/icons.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fonts/lato.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fonts/openSans.css" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap/bootstrap-social.css" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap/font-awesome.min.css" />
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="stylesheet" href="assets/css/compiled/index.css" type="text/css" media="screen" />
</head>
<body>
	<header class="navbar navbar-inverse" role="banner">
		<div class="navbar-header col-md-12">
			<button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home"> <img src="assets/img/logo.png" alt="logo" /> </a>
			<ul class="pull-right">
				<!--  <li>
				<a href="https://twitter.com/intent/tweet?text=Check%20out%20this%20site.%20Average%20laptimes%20and%20data%20of%20F1&url=http%3A%2F%2Fpochof1.com.ar&via=pocho_f1" class="btn btn-block btn-social btn-twitter"><i class="fa fa-twitter"></i> Share&nbsp;</a>
			</li>-->
				<li><a href="https://twitter.com/intent/follow?screen_name=pocho_F1" class="btn btn-block btn-social btn-twitter"><i class="fa fa-twitter"></i> Follow me
						(@pocho_f1)&nbsp;</a></li>
			</ul>
		</div>
	</header>
	<div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div> <a href="home"> <i class="icon-home"></i> <span>Home</span> </a></li>
			<li><a href="showRaces"> <i class="icon-calendar"></i> <span>Race Details</span> </a>
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
					<h4 class="clearfix pull-left">Error</h4>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-12 well well-sm">
					<h1>The page that you are looking does not exists</h1>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-7">
					<h4 class="clearfix pull-left"><spring:message code="title.constructor.championship" /></h4>
				</div>
				<div class="col-md-4 col-md-offset-1">
					<h4 class="clearfix pull-left">Current Race</h4>
				</div>
			</div>
			<br />
		</div>
	</div>
	<div class="clearfix"></div>
	<footer>
		<p>
			<span style="text-align: left; float: left">Copyright: &copy; 2014 PochoF1</span> <br /> <span>All data showed here belongs to: <a href="http://www.formula1.com">&copy;
					2003-2014 Formula One World Championship Limited</a> &amp; <a href="http://www.fia.com">&copy; 2014 FIA</a> </span>
		</p>
	</footer>
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
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-42352982-2' ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;

			ga.src = 'assets/js/dc.js';

			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>
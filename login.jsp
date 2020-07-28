<!DOCTYPE html>
<%@page import="com.emailclassification.util.Constants"%>
<html lang="en">

<!-- Basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Site Metas -->
<title><%=Constants.PROJECT_NAME%> by <%=Constants.COMPANY_SHORT_NAME%></title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">

<!-- Site Icons -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Site CSS -->
<link rel="stylesheet" href="style.css">
<!-- Responsive CSS -->
<link rel="stylesheet" href="css/responsive.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/custom.css">

<!-- Modernizer for Portfolio -->
<script src="js/modernizer.js"></script>

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

	<!-- LOADER -->
	<div id="preloader">
		<div class="loader">
			<div class="loader__bar"></div>
			<div class="loader__bar"></div>
			<div class="loader__bar"></div>
			<div class="loader__bar"></div>
			<div class="loader__bar"></div>
			<div class="loader__ball"></div>
		</div>
	</div>
	<!-- end loader -->
	<!-- END LOADER -->

	<div class="top-bar">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="right-top">
						<div class="social-box">
							<ul>
								<li><a href="#"><i class="fa fa-facebook-square"
										aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram"
										aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin-square"
										aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter-square"
										aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="left-top">
						<div class="email-box">
							<a style='color: white;'><i class="fa fa-building-o"
								aria-hidden="true"></i> <%=Constants.COMPANY_NAME%> (<%=Constants.COMPANY_SHORT_NAME%>)</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<header class="header header_style_01">
		<nav class="megamenu navbar navbar-expand-lg navbar-light bg-light">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand"><b><%=Constants.PROJECT_NAME%></b></a>
					<button class="navbar-toggler collapsed" type="button"
						data-toggle="collapse" data-target="#navbar"
						aria-controls="navbar" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="navbar-nav ml-auto">
						<li><a href="index.jsp">Home</a></li>
						<li><a href="register.jsp">Register</a></li>
						<li><a class="active" href="login.jsp">Login</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>


	<div id="features" class="section wb">
		<div class="container">
			<div class="section-title text-center">
				<h3>Login</h3>
				<p class="lead">Access your account by entering your login credentials</p>
			</div>
			<!-- end title -->

			<hr class="hr1">
			<div class="row" style='min-height:400px;'>
			<div class='col-md-2'></div>
			<div class='col-md-8'>
							<%
							   String msg = request.getParameter("msg");
							%>
							<%
							   if (msg != null)
							   {
							%>
							<div class="alert alert-success alert-dismissable">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Message!</strong>
								<%=msg%>.
							</div>
							<%
							   }
							%>

				<form method="post" action="account" >
						<input type="hidden" name='request_type' value='login' /> <label>Email:</label>
						<input type="text" name="email" class="form-control" /> <br /> <label>Password:</label>
						<input type="password" name="password"
							class="validate-email form-control" /> <br/>
						<button class="btn btn-success" type="submit">Login</button>
				</form>
			</div>
			<div class='col-md-2'></div>
			</div>



		</div>
		<!-- end container -->
	</div>
	<!-- end section -->




	<div class="copyrights">
		<div class="container">
			<div class="footer-distributed">
				<div class="footer-left">
					<p class="footer-company-name">
						All Rights Reserved. &copy; 2019
						<%=Constants.PROJECT_NAME%>
						by
						<%=Constants.COMPANY_NAME%>
						(<%=Constants.COMPANY_SHORT_NAME%>)
				</div>


			</div>
		</div>
		<!-- end container -->
	</div>
	<!-- end copyrights -->

	<a href="#" id="scroll-to-top" class="dmtop global-radius"><i
		class="fa fa-angle-up"></i></a>

	<!-- ALL JS FILES -->
	<script src="js/all.js"></script>
	<!-- ALL PLUGINS -->
	<script src="js/custom.js"></script>
	<script src="js/portfolio.js"></script>
	<script src="js/hoverdir.js"></script>

</body>
</html>
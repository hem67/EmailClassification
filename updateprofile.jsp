<!DOCTYPE html>
<%@page import="com.emailclassification.pojo.User"%>
<%
	User u1 = (User) session.getAttribute("user");
	if (u1 == null) {
		response.sendRedirect("login.jsp?msg=Session expired. Login again");
	} else {
%>

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
						<li><a href="welcome.jsp">Welcome</a></li>
						<li><a href="#" class="dropdown-toggle active" data-toggle="dropdown"><%=u1.getFname() %> <%=u1.getLname() %> <span class="caret"></span></a>
							          <ul class="dropdown-menu">
							            <li><a href="updateprofile.jsp">Update Profile</a></li>
							            <li><a href="changepassword.jsp">Change Password</a></li>
							            <li><a href="account?request_type=deleteprofile">Delete Profile</a></li>
							            <li role="separator" class="divider"></li>
							            <li><a href="account?request_type=logout">Logout</a></li>
							          </ul>
							
						
						
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>


	<div id="features" class="section wb">
		<div class="container">
			<div class="section-title text-center">
				<h3>Update Profile</h3>
				<p class="lead">Review your profile information and update them if necessary</p>
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

				


							<form action='account'  method='post'>
								<input type=hidden name='request_type' value='updateprofile' />
								<label> Enter your Email ID </label> <input type=text
									readonly="readonly" class='form-control' name='email'
									placeholder='Email ID' value='<%=u1.getEmail()%>' /> <br /> <label>
									Enter your First name </label> <input type=text class='form-control'
									name='fname' placeholder='First name'
									value='<%=u1.getFname()%>' /> <br /> <label> Enter
									your Last name </label> <input type=text class='form-control'
									name='lname' placeholder='Last name' value='<%=u1.getLname()%>' />
								<br /> <label> Select your Gender </label><br /> <input
									type="radio" <%if (u1.getGender().equals("Male"))
            {%>
									checked="checked" <%}%> name='gender' value='Male' /> Male
								&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
									<%if (u1.getGender().equals("Female"))
            {%>
									checked="checked" <%}%> name='gender' value='Female' /> Female
								<br /> <br /> <label> Enter your Mobile number </label> <input
									type=text class='form-control' name='mobile'
									placeholder='Mobile number' value='<%=u1.getMobile()%>' /> <br />
								<label> Enter your Address </label>
								<textarea class='form-control' name='addr' placeholder='Address'><%=u1.getAddr()%></textarea>
								<br /> <br />
								<button class="btn btn-warning" type="submit">Update
									Profile</button>
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
<% } %>
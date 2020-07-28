<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.emailclassification.pojo.Mail"%>
<%@page import="com.emailclassification.daoimpl.MailBoxDAOImpl"%>
<%@page import="com.emailclassification.dao.MailBoxDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.emailclassification.daoimpl.UserDAOImpl"%>
<%@page import="com.emailclassification.dao.UserDAO"%>
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
						<li><a class="active" href="mailbox_inbox.jsp">Mailbox</a></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">Datasets  <span class="caret"></span></a>
							          <ul class="dropdown-menu">
							            <li><a href="dataset_add.jsp">Add Dataset</a></li>
							            <li><a href="dataset?requestType=get">View Datasets</a></li>
							          </ul>
						</li>
						<li><a href="welcome.jsp" class="dropdown-toggle" data-toggle="dropdown">Obscure</a>
							          <ul class="dropdown-menu">
							            <li><a href="obscure_run.jsp">Start Obscure process</a></li>
							            <li><a href="obscure?requestType=get">View Obscured Datasets</a></li>
							          </ul>						
						</li>
						<li><a href="run.jsp">Run</a></li>
						<li><a href="results.jsp">Results</a></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=u1.getFname() %> <%=u1.getLname() %> <span class="caret"></span></a>
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
				<h3>Mailbox</h3>
				
			</div>
			<!-- end title -->

			<hr class="hr1">
			<div class="row" style='min-height:400px;'>
			<div class='col-md-12'>
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


				<%
					UserDAO uDao = new UserDAOImpl();
					List<User> users = uDao.getAllUsers();
				%>
				
				<a href='' style='width:170px; text-align: center;'  data-toggle="modal" data-target="#compose" class='btn btn-warning'><i class='fa fa-envelope-o'></i> Compose</a>
														<!-- Modal -->
														<div class="modal fade bs-example-modal-lg" id="compose" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
														  <div class="modal-dialog modal-lg" role="document">
														    <div class="modal-content">
														      <div class="modal-header">
														        <h4 class="modal-title" id="myModalLabel" style='color: gray;' >Compose Mail</h4>
														      </div>
														      <form action='mailbox' method=post>
															      <div class="modal-body">
															        
															        <input type=hidden name='request_type' value='compose' />
															        <select name='rec' required="required" class='form-control' >
															        	<option> </option>
															        	<%
															        		for (User u : users)
															        		{
															        			if (!u.getEmail().equals(u1.getEmail())) {
															        			%>
															        				<option value='<%=u.getEmail()%>'><%=u.getFname() %> <%=u.getLname() %> (<%=u.getEmail() %>)</option>
															        			<%
															        			}
															        		}
															        	%>
															        </select>
															        <br/>
															        <input type=text name='subject' placeholder="Subject" required="required" class='form-control' />
															        <br/>
															        <textarea style='height:200px;' required="required" name='body' placeholder="Body" class='form-control'></textarea>
															        <br/>
															        														        
															      </div>
															      <div class="modal-footer">
															        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
															        <button type="submit" class="btn btn-primary"><i class='fa fa-send'></i> Send Mail</button>
															      </div>
														      </form>
														    </div>
														  </div>
														</div>
				
				<br/><br/>
				<table class='table'>
					<tr>
						<td style='width:15%;'>
						<a href='mailbox_inbox.jsp' style='width:100%; text-align: left;' class='btn btn-primary'><i class='fa fa-inbox'></i> Inbox</a>
						<br/>
						<a href='mailbox_sent.jsp' style='width:100%; text-align: left;' class='btn btn-default'><i class='fa fa-send'></i> Sent</a>
						<br/>
						<a href='mailbox_trash.jsp' style='width:100%; text-align: left;' class='btn btn-default'><i class='fa fa-trash'></i> Trash</a>
						<br/>
						<a href='mailbox_spam.jsp' style='width:100%; text-align: left;' class='btn btn-default'><i class='fa fa-remove'></i> Spam</a>
						</td>
						<td style='width:85%;'>
						
						
						<%
							MailBoxDAO mDao = new MailBoxDAOImpl();
							List<Mail> inboxMails = mDao.getInbox(u1.getEmail());
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy kk:mm");
							SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy | kk:mm");
							if (inboxMails == null || inboxMails.size() == 0)
							{
								%>
									<h5>No Mails in your inbox yet</h5>
								<%
							}
							else
							{
								%>
									<table class='table table-hover'>
									<%
										int i = 0;
										for (Mail m: inboxMails)
										{
											i++;
											User us = uDao.getUserDetails(m.getSender());
											
											if (m.getNb().equals("spam") && m.getHmm().equals("spam"))
											{
												continue;
											}
											
											if (m.getNb().equals("spam") || m.getHmm().equals("spam"))
											{
												%>
												<tr style='background-color: lightgray;' title='HMM:<%=m.getHmm()%>, NB: <%=m.getNb()%> '>
												
												<%	
											}
											else
											{
												%>
												<tr style=''>
													
												<%
												
											}
									%>
												<td style='width:20%;'><a href='#' data-toggle="modal" data-target="#inbox<%=i%>"><%=us.getFname() %> <%=us.getLname() %></a></td>
												<td style='width:60%;'><a href='#' data-toggle="modal" data-target="#inbox<%=i%>"><%=m.getSubject() %></a></td>
												<td style='width:20%;'><a href='#' data-toggle="modal" data-target="#inbox<%=i%>"><%=sdf.format(m.getEntry_time()) %></a>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href='mailbox?request_type=movetotrash&id=<%=m.getId()%>'><i class='fa fa-trash'></i></a>
												
												
														<!-- Modal -->
														<div class="modal fade bs-example-modal-lg" id="inbox<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
														  <div class="modal-dialog modal-lg" role="document">
														    <div class="modal-content">
														      <div class="modal-header">
														        <h4 class="modal-title" id="myModalLabel" style='color: gray;' >Read Mail</h4>
														      </div>
															      <div class="modal-body">
															        Sender:<br/>
															        <b><%=us.getFname() %> <%=us.getLname() %></b>
															        <br/><b>(<%=us.getEmail() %>)</b>
															        <hr/>
															        Receiver:<br/>
															        <b><%=u1.getFname() %> <%=u1.getLname() %></b>
															        <br/><b>(<%=u1.getEmail() %>)</b>
															        <hr/>
															        Sent Time:<br/>
															        <b><%=sdf2.format(m.getEntry_time()) %></b>
															        <hr/>
															        <label>Subject</label>
															        
															        <input readonly="readonly" type=text name='subject' value='<%=m.getSubject() %>' placeholder="Subject" required="required" class='form-control' />
															        <br/>
															        <label>Body</label>
															        <textarea readonly="readonly" style='height:200px;' required="required" name='body' placeholder="Body" class='form-control'><%=m.getBody() %></textarea>
															        <br/>
															        														        
															      </div>
															      <div class="modal-footer">
															        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	<a class='btn btn-danger' href='mailbox?request_type=movetotrash&id=<%=m.getId()%>'><i class='fa fa-trash'></i> Move To Trash</a>
															        
															      </div>
														    </div>
														  </div>
														</div>
												
												
												</td>
											</tr>						
									<% } %>									
									</table>
								<%
							}
						%>
						
						
						
						</td>
					</tr>	
					
				</table>
				
				
			</div>
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
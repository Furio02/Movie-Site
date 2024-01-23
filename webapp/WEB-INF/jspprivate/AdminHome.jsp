<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/CSS/AdminHome.css">
<link
	href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css"
	rel="stylesheet">
<title>Admin Home</title>
</head>
<body>

	<header class="testata">

		<ul>
			<li><form action="<%=request.getContextPath()%>/HomeServlet" method="post"><input type="submit" value="HOME"></form></li>
			<li><form action="<%=request.getContextPath()%>/MoviePageServlet" method="post"><input type="submit" value="MOVIE LIST"></form></li>
			<li><form action="<%=request.getContextPath()%>/ProjectionPageServlet" method="post"><input type="submit" value="PROJECTION LIST"></form></li>
		</ul>
		
		<a href="<%=request.getContextPath()%>/jspubbliche/Index.jsp"><input type="button" value="Log Out" id="logOut"></a>

	</header>

	<div class="container">

		<div class="container-list">
	
			<div class="user-list">
				<h4>
					Lista Utenti <i class="ri-user-fill"></i>
				</h4>
				<div class="user">
					<%
					ArrayList<User> users = (ArrayList) request.getAttribute("listaUtenti");
					%>
					<%
					if (users != null) {
					%>
					<%
					for (User u : users) {
					%>
					<p><%=u.getEmail()%></p>
					<%
					}
					%>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
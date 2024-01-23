<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/index.css">
    <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/REF/pontors.png">
<title>Banana Cinema</title>
</head>
<body>

<div class="background-container-left">
    <div class="sliding-background-left"></div>
</div>

<div class="background-container-right">
    <div class="sliding-background-right"></div>
</div>

<div class="container">
    <img src="<%=request.getContextPath()%>/REF/logo-3.png" alt="banana's logo" id="banana-logo">
    <div class="card-container">
        <div class="card">
            <h1>Login</h1>
            <%-- <form action="<%=request.getContextPath()%>/LoginServlet" method="post"> --%>
            <form action="/ProgettoCinema/LoginServlet" method="post">
                <input type='email' placeholder='mariorossi@gmail.com'name='emailFormInput'>
                <input type='password' placeholder='password' name='passwordFormInput'>
                <div class="swiper">
                    <input type="checkbox" name='restaCollegato' id="resta-collegato">Resta Colleagato
                    <label for="resta-collegato" class="button"></label>
                </div>
                <input type='submit' id="btn" value="LOGIN">
            </form>    
        </div>
        <div class="line"></div>

        <%
        String errorUmsg = (String) request.getAttribute("errorUmsg");
        %>
        <%
        if (errorUmsg != null) {
        %>
        <p><%=errorUmsg%></p>
        <%
        }
        %>
    
        <div class="card-2">    
            <h1>Registrati</h1>
            <form action="<%=request.getContextPath()%>/RegisterServlet" method="post">
                <input type='email' placeholder='mariorossi@gmail.com' name='emailFormInput' required>
                <input type='password' placeholder='password' name='passwordFormInput' required>
                <input type='password' placeholder='password' name='confermaPasswordFormInput' required>
                <input type='submit' id="btn" value="REGISTER">
            </form>
        </div>
    </div>
</div>

	<%
	String errormsg = (String) request.getAttribute("errormsg");
	%>
	<%
	if (errormsg != null) {
	%>
	<p><%=errormsg%></p>
	<%
	}
	%>
</body>
</html>
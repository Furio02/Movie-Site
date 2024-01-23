<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/CSS/ProjectionList.css">
<link
	href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css"
	rel="stylesheet">
<title>Projection List</title>
</head>
<body>

	<header class="testata">

		<ul>
			<li><form action="<%=request.getContextPath()%>/HomeServlet" method="post"><input type="submit" value="HOME"></form></li>
			<li><form action="<%=request.getContextPath()%>/MoviePageServlet" method="post"><input type="submit" value="MOVIE LIST"></form></li>
			<li><form action="<%=request.getContextPath()%>/ProjectionPageServlet" method="post"><input type="submit" value="PROJECTION LIST"></form></li>
		</ul>

	</header>

	<div class="container">
	
		<div class="add_card">

			<div class="add-show">
				<h4>
					Aggiungi Spettacolo <i class="ri-film-line"></i>
				</h4>

				<form action="<%=request.getContextPath()%>/AddShowServlet"
					method="post">
					<select name='titleInput'>
						<%
						List<Film> listaFilm = (List<Film>) request.getAttribute("listaFilm");
						if (listaFilm != null) {
							for (Film f : listaFilm) {
						%>
						<option value='<%=f.getTitolo()%>'><%=f.getTitolo()%></option>
						<%
						}
						%>
						<%
						}
						%>
					</select> 
					<input type="datetime-local" name='data'> <select
						name='salaInput'>
						<%
						List<Sala> sale = (List<Sala>) request.getAttribute("listaSale");
						%>
						<%
						if (sale != null) {
						%>
						<%
						for (Sala sa : sale) {
						%>
						<option value="<%=sa.getNomeSala()%>"><%=sa.getNomeSala()%></option>
						<%
						}
						%>
						<%
						}
						%>
						<input type='submit' value='Aggiungi Spettacolo' id="send">
					</select>
				</form>
			</div>
		</div>
	
		<div class="list-show">
			<h4>
				Lista Spettacoli <i class="ri-film-line"></i>
			</h4>
			<div class="show">
				<%
				ArrayList<Spettacolo> spettacolo = (ArrayList<Spettacolo>) request.getAttribute("listaSpettacoli");
				%>
				<%
				if (spettacolo != null) {
					for (Spettacolo s : spettacolo) {
				%>
				<div class="film-s">
					<p>
						Titolo:
						<%=s.getF().getTitolo()%></p>
					<p>
						Durata:
						<%=s.getF().getDurata()%></p>
					<p>
						Descrizione:
						<%=s.getF().getTrama()%></p>
					<p>
						Genere:
						<%=s.getF().getDescription().getDescription()%></p>
					<p>
						Attori:

						<%
					String spettacoloAttoriToString = "";
					if (s.getF().getAttori() != null) {
						for (Actor a : s.getF().getAttori()) {
							spettacoloAttoriToString += a.getNome() + " " + a.getCognome() + ", ";
					%>
						<%
						}
						%>
						<%=spettacoloAttoriToString%></p>
					<%
					}
					%>

					<p>
						Sala:
						<%=s.getS().getNomeSala()%></p>

					<%
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
					%>
					<p>
						Orario Programmazione:
						<%=s.getDataSpettacolo().format(dtf)%>
					</p>
					<form action="<%=request.getContextPath()%>/DeleteSpettacolo"
						method="post">
						<input type="hidden" name="spettacolo" value="<%=s.getId()%>">
						<input type="submit" value="ARCHIVE">
					</form>
					
					<button id="btn-modifica">MODIFY</button>
					
					<form id="modificaSpettacolo" action="<%=request.getContextPath()%>/ModifyProjection" method="post" class="nascosto">
						<input type="datetime-local" name='data' placeholder="<%=s.getDataSpettacolo()%>"> 
						<select name='salaInput'>
							<%if (sale != null) {%>
								<%for (Sala sa : sale) {%>
									<option value="<%=sa.getNomeSala()%>"><%=s.getS().getNomeSala()%></option>
										<%}%>
									<%}%>
						<input type="hidden" name="projectionId" value="<%=s.getId()%>">
						<input type="hidden" name="titleInput" value="<%=s.getF().getTitolo()%>">
						<input type="submit" id="submit-btn" value="Submit Changes">
					</select>
					</form>
				</div>
				<%
				}
				%>
				<%
				}
				%>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/ProjectionList.js"></script>
	
</body>
</html>
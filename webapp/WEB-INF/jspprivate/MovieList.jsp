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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/MovieList.css">
<link href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css" rel="stylesheet">
<title>Movie List</title>
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

			<div class="genere">
				<h4>
					Aggiungi Genere <i class="ri-pantone-fill"></i>
				</h4>
				<form action="<%=request.getContextPath()%>/AddGenreServlet"
					method='post'>
					<input type='text' name='genereDaInserire' placeholder='Horror...'>
					<input type='submit' value='Aggiungi Genere' id="send">
				</form>
			</div>

			<div class="actor">
				<h4>
					Aggiungi Attore <i class="ri-user-star-line"></i>
				</h4>
				<form action="<%=request.getContextPath()%>/AddActorServlet"
					method='post'>
					<input type='text' name='attoreDaInserire'
						placeholder='Topino Irrilevante'> <input type='submit'
						value='Aggiungi Attore' id="send">
				</form>
			</div>

			<div class="sala">
				<h4>
					Aggiungi Sala <i class="ri-home-gear-line"></i>
				</h4>
				<form action="<%=request.getContextPath()%>/AddSalaServlet"
					method="post">
					<input type='text' name='salaInput' placeholder='Sala 1...'>
					<input type='submit' value="Aggiungi Sala" id="send">
				</form>
			</div>
		</div>

		<div class="add-movie">
			<h4>
				Inserisci un Film <i class="ri-movie-2-line"></i>
			</h4>
			<form action="<%=request.getContextPath()%>/AddFilmServlet"
				method="post">
				<input type='text' name='titleInput' placeholder='Titolo'> <input
					type='text' name='durataInput' placeholder='Durata'> <input
					type='text' name='plotInput' placeholder='Trama'> <select
					name='genreInput'>
					<%
					List<Genre> listaGenere = (List<Genre>) request.getAttribute("listaGenere");
					if (listaGenere != null) {
						for (Genre g : listaGenere) {
					%>
					<option value='<%=g.getDescription()%>'><%=g.getDescription()%></option>
					<%
					}
					%>
					<%
					}
					%>
				</select>
				<div class="attore">
					<%
					List<Actor> actorList = (List<Actor>) request.getAttribute("listaAttori");
					if (actorList != null) {
						for (Actor a : actorList) {
					%>

					<input type="checkbox" name='attoreInput'
						value='<%=a.getNome() + " " + a.getCognome()%>'><%=a.getNome() + " " + a.getCognome()%>
					<%
					}
					%>
					<%
					}
					%>

				</div>
				<input type='submit' value="Aggiungi Film" id="send">
				<%
				String filmPresente = (String) request.getAttribute("errormsg");
				%>
				<%
				if (filmPresente != null) {
				%>
				<p><%=filmPresente%></p>
				<%
				}
				%>
			</form>
		</div>
		<div class="movie-list">
			<h4>
				Lista Film <i class="ri-movie-2-line"></i>
			</h4>
			<form action="OrderMovie" method="post">
				<select name="order">
					<option value="alphabetical">A-Z</option>
					<option value="genre">Genre</option>
				</select> <input type="submit" value="Ordina">
			</form>
			<div class="movie">
				<%
				List<Film> movies = (List<Film>) request.getAttribute("listaFilm");
				%>
				<%
				if (movies != null) {
				%>
	
				<%
				for (Film f : movies) {
				%>
				<div class="film">
	
	
					<p>
						Titolo:
						<%=f.getTitolo()%>
					</p>
					<p>
						Durata:
						<%=f.getDurata()%>
					</p>
					<p>
						Descrizione:
						<%=f.getTrama()%>
					</p>
					<p>
						Genere:
						<%=f.getDescription().getDescription()%>
					</p>
					<p>
						Attori:
	
						<%
					String attoriToString = "";
					if (f.getAttori() != null) {
						for (Actor a : f.getAttori()) {
							attoriToString += a.getNome() + " " + a.getCognome() + ",  ";
					%>
						<%
						}
						%>
						<%=attoriToString%>
					</p>
	
					<form action="<%=request.getContextPath()%>/DeleteMovieServlet"
						method="post">
						<input type="hidden" name="film" value="<%=f.getId()%>"> <input
							type="submit" value="ARCHIVE">
					</form>
	
					<button id="btn-modifica">MODIFY</button>
	
					<form id="modificaFilm"
						action="<%=request.getContextPath()%>/ModifyMovie" method="post"
						class="nascosto">
						Titolo : <input type="text" name="titleInput"
							value="<%=f.getTitolo()%>"> Durata : <input type="text"
							name="durataInput" value="<%=f.getDurata()%>"> Descrizione
						: <input type="text" name="plotInput" value="<%=f.getTrama()%>">
						Genere : <select name='genreInput'>
							<%
							if (listaGenere != null) {
								for (Genre g : listaGenere) {
							%>
							<option value='<%=g.getDescription()%>'><%=g.getDescription()%></option>
							<%
							}
							%>
							<%
							}
							%>
						</select> Attori :
						<%
						if (actorList != null) {
							for (Actor a : actorList) {
						%>
						<%
						if (f.getAttori().contains(a)) {
						%>
						<label>
						<input type="checkbox" name='attoreInput' checked="checked" value='<%=a.getNome() + " " + a.getCognome()%>'><%=a.getNome() + " " + a.getCognome()%></label>
						<%
						} else {
						%>
						<label>
						<input type="checkbox" name='attoreInput'
							value='<%=a.getNome() + " " + a.getCognome()%>'><%=a.getNome() + " " + a.getCognome()%>
						</label>
						<%
						}
						%>
						<%
						}
						%>
						<%
						}
						%>
						<input type="hidden" name="filmId" value="<%=f.getId()%>">
						<input type="submit" id="submit-btn" value="Submit Changes">
					</form>
	
	
	
	
	
				</div>
				<%
				}
				%>
	
				<%
				}
				%>
	
	
				<%
				}
				%>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/MovieList.js"></script>
</body>
</html>
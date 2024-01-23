<%@page import="model.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/UserHome.css">
<link href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css" rel="stylesheet">
<title>Profilo Utente</title>
</head>
<body>
	
	<%
	String utente = (String) request.getAttribute("nomeUtente");
	%>
	<%
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	%>

    <header class="welcome">
        <h1>
            Benvenuto
            <%=utente%></h1>
        <a href="<%=request.getContextPath()%>/LogOut"><input
            type="button" value="Log Out"></a>
    </header>

    <div class="container">

        
        <div class="lista-biglietti">
            <h4>Biglietti Acquistati</h4>
            
            <%
            ArrayList<Biglietto> biglietti = (ArrayList<Biglietto>) request.getAttribute("listaBiglietti");
            %>
            <%
            if (biglietti != null) {
            %>
            <%
            for (Biglietto b : biglietti) {
            %>
            <p><%=b.getShow().getF().getTitolo() + " " + b.getShow().getDataSpettacolo().format(dtf) + " "
                + b.getShow().getS().getNomeSala()%></p>
            <%
            }
            %>
            <%
            }
            %>
        </div>
        
        <div class="lista-spettacoli">

            <h4>Spettacoli</h4>
            <div class="showS">
            
            <%
            ArrayList<Spettacolo> spettacolo = (ArrayList<Spettacolo>) request.getAttribute("listaSpettacoli");
            %>
            <%
            if (spettacolo != null) {
                for (Spettacolo s : spettacolo) {%>
            <div class="spettacolo">

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
                <p>
                    Orario Programmazione:
                    <%=s.getDataSpettacolo().format(dtf)%>
                </p>
            
                <form action="<%=request.getContextPath()%>/BuyTicketServlet"
                    method="post">
                    <input type="hidden" name='titolo' value='<%=s.getF().getTitolo()%>'>
                    <input type="submit" name='acquista' value="Acquista biglietto">
                    
                </form>
				 </div>            
                <%
                }
                %>
                <%
                }
                %>
                

            </div>
             <%
                String effettuato = (String) request.getAttribute("acquistoEffettuato");
                %>
                <%
                if (effettuato != null) {
                %>
                <p><%=effettuato%></p>
                <%
                }
                %>
        </div>
    </div>
</body>
</html>
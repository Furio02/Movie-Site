package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Film;
import model.Sala;
import model.Spettacolo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bl.BusinessLogic;

public class AddShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddShowServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getParameter("data");
		String titolo = request.getParameter("titleInput");
		String sala = request.getParameter("salaInput");
		

		try {
			if(data.isEmpty()) {
				String errormsg = "Data non valida!";
				request.setAttribute("errorSpettacolo", errormsg);
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaSale", BusinessLogic.getAllSale());
				request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
				request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
				return;
			}
			
			LocalDateTime orarioSpettacoloISO = LocalDateTime.parse(data);
			Spettacolo s = new Spettacolo();
			
			s.setDataSpettacolo(orarioSpettacoloISO);
			Film f = BusinessLogic.selectByTitolo(titolo);
			f.getId();
			s.setF(f);

			Sala sa = BusinessLogic.selectByNomeSala(sala);
			sa.getId();
			s.setS(sa);

			if (BusinessLogic.getAllShows().contains(s)) {
				String errormsg = "Spettacolo già inserito";
				request.setAttribute("errorSpettacolo", errormsg);
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaSale", BusinessLogic.getAllSale());
				request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
				request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
			} else {
				
				if(!titolo.isEmpty() && !data.isEmpty() && !sala.isEmpty()) {
				
				BusinessLogic.insertShow(s);
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaSale", BusinessLogic.getAllSale());
				request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
				request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
				} else {
					request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
					request.setAttribute("listaSale", BusinessLogic.getAllSale());
					request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
					request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

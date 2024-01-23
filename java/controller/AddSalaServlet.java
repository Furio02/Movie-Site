package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sala;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

public class AddSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddSalaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeSala;

		nomeSala = request.getParameter("salaInput");

		Sala s = new Sala();

		s.setNomeSala(nomeSala);

		try {
			if (BusinessLogic.getAllSale().contains(s)) {
				String salaPresente = "Sala già Presente";

				request.setAttribute("errormsg", salaPresente);
				request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
			} else {
				
				if(!nomeSala.isEmpty()) {					
					BusinessLogic.insertSala(s);
					request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
					request.setAttribute("listaAttori", BusinessLogic.getAllActors());
					request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
					request.setAttribute("listaSale", BusinessLogic.getAllSale());
					request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
					request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
				} else {
					request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
					request.setAttribute("listaAttori", BusinessLogic.getAllActors());
					request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
					request.setAttribute("listaSale", BusinessLogic.getAllSale());
					request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
					request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

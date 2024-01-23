package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Actor;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

public class AddActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddActorServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attore;

		attore = request.getParameter("attoreDaInserire");

		String[] nomeCognome = attore.split(" ");
		

		try {
			
			Actor a = new Actor();
			
			if(nomeCognome[0] == "") {
				
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaAttori", BusinessLogic.getAllActors());
				request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
				
				request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
				return;
			} else {
			
				a.setNome(nomeCognome[0]);
				a.setCognome(nomeCognome[1]);
			}
			if (BusinessLogic.getAllActors().contains(a)) {
				String attorePresente = "Attore già Presente";
				request.setAttribute("errormsg", attorePresente);
				request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
			} else {
				
				BusinessLogic.insertActor(a);
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaAttori", BusinessLogic.getAllActors());
				request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
				
				request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

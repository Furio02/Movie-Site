package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genre;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

public class AddGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddGenreServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String description;

		description = request.getParameter("genereDaInserire");

		Genre g = new Genre();

		g.setDescription(description);

		try {
			if (BusinessLogic.getAllGenres().contains(g)) {
				String generePresente = "Genere già presente!";
				request.setAttribute("errormsg", generePresente);
				request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
			} else {
				
				if(!description.isEmpty()) {
				
				BusinessLogic.insertGenre(g);
				
				request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
				request.setAttribute("listaAttori", BusinessLogic.getAllActors());
				request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
				
				request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
				} else {
					
					request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
					request.setAttribute("listaAttori", BusinessLogic.getAllActors());
					request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
					
					request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
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

package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

/**
 * Servlet implementation class MoviePageServlet
 */
public class MoviePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MoviePageServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

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
 * Servlet implementation class DeleteMovieServlet
 */
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteMovieServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Long idFilm = Long.parseLong(request.getParameter("film"));
			
			BusinessLogic.deleteByFilmId(idFilm);
			
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

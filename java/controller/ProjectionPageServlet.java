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
 * Servlet implementation class ProjectionPageServlet
 */
public class ProjectionPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProjectionPageServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.setAttribute("listaSale", BusinessLogic.getAllSale());
			request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
			request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

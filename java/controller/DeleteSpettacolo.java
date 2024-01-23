package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Film;
import model.Spettacolo;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

public class DeleteSpettacolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteSpettacolo() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			String idSpettacolo = request.getParameter("spettacolo"); 
				
			long id = Long.parseLong(idSpettacolo); 
			
			BusinessLogic.deleteShowById(id);
						
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.setAttribute("listaSale", BusinessLogic.getAllSale());
			request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
			request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

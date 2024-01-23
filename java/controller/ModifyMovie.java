package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Actor;
import model.Film;
import model.Genre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bl.BusinessLogic;

public class ModifyMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyMovie() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idFilm = Long.parseLong(request.getParameter("filmId"));
		
		String title,durata,plot,description;
		
		title = request.getParameter("titleInput");
		durata = request.getParameter("durataInput");
		plot = request.getParameter("plotInput");
		description = request.getParameter("genreInput");

		String[] nomiAttori;
		
		nomiAttori = request.getParameterValues("attoreInput");
		
		List<Actor> app = new ArrayList<>();
		
		try {
			
			Genre g = BusinessLogic.selectGenreByName(description);
			if (nomiAttori != null) {
				for (String s : nomiAttori) {
					String[] appoggio = s.split(" ");
					String nome = appoggio[0];
					String cognome = appoggio[1];
					Actor a = BusinessLogic.selectByNomeCompleto(nome, cognome);

					app.add(a);

				}
			}
			Film f = new Film();
			f.setId(idFilm);
			f.setTitolo(title);
			f.setDurata(durata);
			f.setTrama(plot);
			f.setDescription(g);
			f.setAttori(app);
			
			BusinessLogic.updateMovie(f);
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			
			request.getRequestDispatcher("WEB-INF/jspprivate/MovieList.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Film;
import model.Genre;
import utility.GenreMovieComparator;
import utility.TitleMovieComparator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import bl.BusinessLogic;

/**
 * Servlet implementation class OrderMovie
 */
public class OrderMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public OrderMovie() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String order = request.getParameter("order");
		
		try {
			
			List<Film> film = BusinessLogic.getAllMovies();
			
			film.forEach(f1 -> System.out.println(f1));
			
//			List<Genre> genre = film.stream().peek(f1 -> System.out.println(f1.getTitolo())).sorted((f1, f2)-> f1.getTitolo().compareTo(f2.getTitolo())).peek(f1 -> System.out.println(f1.getTitolo())).map(f1 -> f1.getDescription()).toList();
			
			List<Genre> genre = BusinessLogic.getAllGenres();
			
			genre.stream().allMatch(g1 -> g1.getDescription().length() > 5);
			
			//film.removeIf(f1 -> f1.getAttori().stream().map(a1 -> a1.getNome() + " " + a1.getCognome()).toList().contains("Topino Irrilevante"));
			
			Comparator<Film> titleComparator = new Comparator<Film>() {

				@Override
				public int compare(Film o1, Film o2) {
					return o1.getTitolo().compareTo(o2.getTitolo());
				}
				
			};
			
			switch(order) {
			case "alphabetical":
//				film.sort(new TitleMovieComparator());
//				film.sort(titleComparator);
				film.sort((Film f1, Film f2) -> f1.getTitolo().compareTo(f2.getTitolo()));
				break;
			case "genre":
//				film.sort(new GenreMovieComparator());
				genre.sort((Genre g1, Genre g2) -> g1.getDescription().compareTo(g2.getDescription()));
			}
			
			request.setAttribute("listaUtenti", BusinessLogic.getAllUsers());
			request.setAttribute("listaFilm", film);
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.setAttribute("listaSale", BusinessLogic.getAllSale());
			request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
			request.getRequestDispatcher("WEB-INF/jspprivate/AdminHome.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

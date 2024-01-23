package controller;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/PrimaServlet")
public class PrimaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrimaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String email = null, password = null;
			
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("email")) {
						email = c.getValue();
					}
					if(c.getName().equals("password")) {
						password = c.getValue();
					}
				}
				
				if(email != null && password != null) {
					if(!email.isEmpty() && !password.isEmpty()) {
						
						User u = BusinessLogic.login(email, password);
						
						if(u != null) {
							request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
							request.setAttribute("listaAttori", BusinessLogic.getAllActors());
							request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
							request.setAttribute("listaSale", BusinessLogic.getAllSale());
							request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
	
							request.setAttribute("listaBiglietti", BusinessLogic.getAllBigliettiByUserId(u.getId()));
							
							request.setAttribute("nomeUtente", email);
							request.getRequestDispatcher("WEB-INF/jspprivate/UserHome.jsp").forward(request, response);
						}
					}
				}
			}
			
			request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Biglietto;
import model.Spettacolo;
import model.User;
import utility.Utility;

import java.io.IOException;
import java.sql.SQLException;

import bl.BusinessLogic;

public class BuyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuyTicketServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titolo = request.getParameter("titolo");

		try {
		
			Biglietto b = new Biglietto();
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loggedUser");
			
			for (Spettacolo s : BusinessLogic.getAllShows()) {
				if (titolo.equalsIgnoreCase(s.getF().getTitolo())) {
					
					b.setShow(s);
					b.setUtente(user);
					
					BusinessLogic.insertBiglietto(b);
					
					String effettuato = "L'utente " + user.getEmail() + " ha preso il biglietto per il film "
							+ titolo;
					request.setAttribute("acquistoEffettuato", effettuato);
				}
			}
			
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaAttori", BusinessLogic.getAllActors());
			request.setAttribute("listaGenere", BusinessLogic.getAllGenres());
			request.setAttribute("listaSale", BusinessLogic.getAllSale());
			request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
			request.setAttribute("listaBiglietti", BusinessLogic.getAllBigliettiByUserId(user.getId()));
			request.setAttribute("nomeUtente", user.getEmail());
			request.getRequestDispatcher("WEB-INF/jspprivate/UserHome.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

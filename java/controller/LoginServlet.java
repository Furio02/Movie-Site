package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Sala;
import model.User;
import utility.Utility;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bl.BusinessLogic;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email, password;

		email = request.getParameter("emailFormInput");
		password = request.getParameter("passwordFormInput");
		

		try {
			
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			HttpSession session = request.getSession();
			
			if (u.getEmail().equals("admin@admin.com") && u.getPassword().equals("admin")) {

				request.setAttribute("listaUtenti", BusinessLogic.getAllUsers());
				request.getSession().setAttribute("loggedUser", u);
				request.getRequestDispatcher("WEB-INF/jspprivate/AdminHome.jsp").forward(request, response);
				return;
			}
			if (BusinessLogic.login(email, password, session)) {
				
				User user = (User) session.getAttribute("loggedUser");
				
				String[] checkbox = request.getParameterValues("restaCollegato");	
				
				if(checkbox != null) {
					response.addCookie(new Cookie("email",email));
					response.addCookie(new Cookie("password", password));
				}
				
				request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());

				request.setAttribute("listaBiglietti", BusinessLogic.getAllBigliettiByUserId(user.getId()));
				
				request.setAttribute("nomeUtente", user.getEmail());
				request.getRequestDispatcher("WEB-INF/jspprivate/UserHome.jsp").forward(request, response);
			} else {
				String errorUmsg = "Devi prima Registrarti";
				request.setAttribute("errorUmsg", errorUmsg);
				request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

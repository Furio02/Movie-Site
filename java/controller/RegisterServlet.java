package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bl.BusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utility.Utility;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email, password, confermaPassword;

		email = request.getParameter("emailFormInput");
		password = request.getParameter("passwordFormInput");
		confermaPassword = request.getParameter("confermaPasswordFormInput");

		try {
			if (password.equals(confermaPassword)) {

				User u = new User();
				u.setEmail(email);
				u.setPassword(password);

				List<User> users = BusinessLogic.getAllUsers();

				boolean userExists = false;

				for (User existingUsers : users) {
					if (existingUsers.getEmail().equals(email)) {
						userExists = true;
						break;
					}
				}

				if (userExists) {
					String utenteRegistrato = "Sei già registrato, effettua il Login";
					request.setAttribute("errormsg", utenteRegistrato);
					request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
				} else {
					
					if(!email.isEmpty() && !password.isEmpty()) {
					
					BusinessLogic.insertUser(u);
					
					request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
					}
				}

			} else {
				String msg = "Le password non coincidono";
				request.setAttribute("errormsg", msg);
				request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
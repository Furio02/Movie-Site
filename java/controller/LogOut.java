package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogOut() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.addCookie(new Cookie("email", ""));
		response.addCookie(new Cookie("password", ""));
		
		request.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
		
//		METODO ALTERNATIVO
		
//		Cookie[] cookies = request.getCookies();
//		
//		for(Cookie c : cookies) {
//			if(c.getName().equals("email") || c.getName().equals("password")) {
//				c.setMaxAge(0);
//			}
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

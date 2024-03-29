package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({"/AddActorServlet","/AddFilmServlet","/AddGenreServlet","/AddSalaServlet","/AddShowServlet","/DeleteMovieServlet","/DeleteSpettacoloServlet","/OrderMovie"})
public class AdminFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession s = req.getSession();
		
		User u = (User) s.getAttribute("loggedUser");
		
		if(u.getEmail().equals("admin@admin.com") && u.getPassword().equals("admin")) {
			
			chain.doFilter(request, response);
		
		}else {
			
			req.setAttribute("errormsg", "Non hai le credenziali per accedere a questa risorsa");
			
			req.getRequestDispatcher("jspubbliche/Index.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

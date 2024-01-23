package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Film;
import model.Sala;
import model.Spettacolo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import bl.BusinessLogic;

public class ModifyProjection extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyProjection() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("projectionId"));
		
		String data = request.getParameter("data");
		String sala = request.getParameter("salaInput");
		String titolo = request.getParameter("titleInput");
		
		
		try {

			LocalDateTime orarioSpettacoloISO = LocalDateTime.parse(data);
			Spettacolo s = new Spettacolo();
			
			s.setDataSpettacolo(orarioSpettacoloISO);
			Film f = BusinessLogic.selectByTitolo(titolo);
			f.getId();
			s.setF(f);

			Sala sa = BusinessLogic.selectByNomeSala(sala);
			sa.getId();
			s.setS(sa);
			s.setId(id);
			
			
			BusinessLogic.updateSpettacolo(s);
			request.setAttribute("listaFilm", BusinessLogic.getAllMovies());
			request.setAttribute("listaSale", BusinessLogic.getAllSale());
			request.setAttribute("listaSpettacoli", BusinessLogic.getAllShows());
			request.getRequestDispatcher("WEB-INF/jspprivate/ProjectionList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

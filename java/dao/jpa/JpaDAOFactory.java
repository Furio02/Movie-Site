package dao.jpa;

import dao.ActorDAO;
import dao.BigliettoDAO;
import dao.DAOFactory;
import dao.FilmDAO;
import dao.GenreDAO;
import dao.SalaDAO;
import dao.SpettacoloDAO;
import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaDAOFactory extends DAOFactory{

	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("my_pu").createEntityManager();
	}
	
	@Override
	public UserDAO getUserDAO() {
		return JpaUserDAO.getInstance();
	}

	@Override
	public FilmDAO getFilmDAO() {
		return JpaFilmDAO.getInstance();
	}

	@Override
	public GenreDAO getGenreDAO() {
		return JpaGenreDAO.getInstance();
	}

	@Override
	public ActorDAO getActorDAO() {
		return JpaActorDAO.getInstance();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return JpaHallDAO.getInstance();
	}

	@Override
	public SpettacoloDAO getShowDAO() {
		return JpaProjectionDAO.getInstance();
	}

	@Override
	public BigliettoDAO getBigliettoDAO() {
		return JpaTicketDAO.getInstance();
	}

}

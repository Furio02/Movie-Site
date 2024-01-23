package dao.inmemory;

import dao.ActorDAO;
import dao.BigliettoDAO;
import dao.DAOFactory;
import dao.FilmDAO;
import dao.GenreDAO;
import dao.SalaDAO;
import dao.SpettacoloDAO;
import dao.UserDAO;

public class InMemoryDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return InMemoryUserDAO.getInstance();
	}

	@Override
	public FilmDAO getFilmDAO() {
		return InMemoryFilmDAO.getInstance();
	}

	@Override
	public GenreDAO getGenreDAO() {
		return InMemoryGenreDAO.getInstance();
	}

	@Override
	public ActorDAO getActorDAO() {

		return InMemoryActorDAO.getInstance();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return InMemorySalaDAO.getInstance();
	}

	@Override
	public SpettacoloDAO getShowDAO() {
		return InMemoryShowDAO.getInstance();
	}

	@Override
	public BigliettoDAO getBigliettoDAO() {
		return InMemoryBigliettoDAO.getInstance();
	}

}

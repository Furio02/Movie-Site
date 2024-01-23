package dao;

import dao.inmemory.InMemoryDAOFactory;
import dao.jdbc.JdbcDAOFactory;
import dao.jpa.JpaDAOFactory;

public abstract class DAOFactory {

	private static DAOFactory instance;

	public static DAOFactory getDAOFactory() {

		return new JpaDAOFactory();
	}

	public abstract UserDAO getUserDAO();

	public abstract FilmDAO getFilmDAO();

	public abstract GenreDAO getGenreDAO();

	public abstract ActorDAO getActorDAO();

	public abstract SalaDAO getSalaDAO();

	public abstract SpettacoloDAO getShowDAO();

	public abstract BigliettoDAO getBigliettoDAO();

}

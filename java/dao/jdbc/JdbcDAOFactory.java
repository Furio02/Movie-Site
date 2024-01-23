package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ActorDAO;
import dao.BigliettoDAO;
import dao.DAOFactory;
import dao.FilmDAO;
import dao.GenreDAO;
import dao.SalaDAO;
import dao.SpettacoloDAO;
import dao.UserDAO;

public class JdbcDAOFactory extends DAOFactory {

	protected static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");
	}

	@Override
	public UserDAO getUserDAO() {
		return JdbcUserDao.getInstance();
	}

	@Override
	public FilmDAO getFilmDAO() {
		return JdbcFilmDao.getInstance();
	}

	@Override
	public GenreDAO getGenreDAO() {
		return JdbcGenreDao.getInstance();
	}

	@Override
	public ActorDAO getActorDAO() {
		return JdbcAttoriDao.getInstance();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return JdbcSalaDao.getInstance();
	}

	@Override
	public SpettacoloDAO getShowDAO() {
		return JdbcSpettacoloDao.getInstance();
	}

	@Override
	public BigliettoDAO getBigliettoDAO() {
		return JdbcBigliettoDao.getInstance();
	}

}
package dao.inmemory;

import java.util.List;

import dao.FilmDAO;
import database.FakeDB;
import model.Film;

public class InMemoryFilmDAO implements FilmDAO {

	private InMemoryFilmDAO() {
	}

	private static InMemoryFilmDAO instance;

	public static InMemoryFilmDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryFilmDAO();
		}
		return instance;
	}

	@Override
	public void insert(Film entity) {
		FakeDB.getInstance().getFilms().add(entity);
	}

	@Override
	public void update(Film entity) {

	}

	@Override
	public List<Film> selectAll() {
		return FakeDB.getInstance().getFilms();
	}

	@Override
	public Film selectById(long id) {
		return null;
	}

	@Override
	public void delete(Film entity) {

	}

	@Override
	public Film selectByTitolo(String titolo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByFilmId(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

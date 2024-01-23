package dao.inmemory;

import java.util.List;

import dao.GenreDAO;
import database.FakeDB;
import model.Genre;

public class InMemoryGenreDAO implements GenreDAO {

	private InMemoryGenreDAO() {
	}

	private static InMemoryGenreDAO instance;

	public static InMemoryGenreDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryGenreDAO();
		}
		return instance;
	}

	@Override
	public void insert(Genre entity) {
		FakeDB.getInstance().getGenres().add(entity);

	}

	@Override
	public void update(Genre entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Genre> selectAll() {
		return FakeDB.getInstance().getGenres();
	}

	@Override
	public Genre selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Genre entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Genre selectByName(String name) {
		// TODO Auto-generated method stub
		for (Genre g : FakeDB.getInstance().getGenres()) {
			if (g.getDescription().equals(name)) {
				return g;
			}
		}
		return null;
	}

}

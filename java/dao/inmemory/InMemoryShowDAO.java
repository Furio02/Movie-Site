package dao.inmemory;

import java.util.List;

import dao.SpettacoloDAO;
import database.FakeDB;
import model.Spettacolo;

public class InMemoryShowDAO implements SpettacoloDAO {

	private InMemoryShowDAO() {
	}

	private static InMemoryShowDAO instance;

	public static InMemoryShowDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryShowDAO();
		}
		return instance;
	}

	@Override
	public void insert(Spettacolo entity) {
		FakeDB.getInstance().getShows().add(entity);

	}

	@Override
	public void update(Spettacolo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Spettacolo> selectAll() {
		return FakeDB.getInstance().getShows();
	}

	@Override
	public Spettacolo selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Spettacolo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByShowId(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

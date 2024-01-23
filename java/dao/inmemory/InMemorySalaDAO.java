package dao.inmemory;

import java.util.List;

import dao.SalaDAO;
import database.FakeDB;
import model.Sala;

public class InMemorySalaDAO implements SalaDAO {

	private InMemorySalaDAO() {
	}

	private static InMemorySalaDAO instance;

	public static InMemorySalaDAO getInstance() {
		if (instance == null) {
			instance = new InMemorySalaDAO();
		}
		return instance;
	}

	@Override
	public void insert(Sala entity) {
		FakeDB.getInstance().getSale().add(entity);
	}

	@Override
	public void update(Sala entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sala> selectAll() {
		return FakeDB.getInstance().getSale();
	}

	@Override
	public Sala selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sala entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sala selectSalaByNome(String nomeSala) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

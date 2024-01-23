package dao.inmemory;

import java.util.List;

import dao.BigliettoDAO;
import database.FakeDB;
import model.Biglietto;

public class InMemoryBigliettoDAO implements BigliettoDAO {

	private InMemoryBigliettoDAO() {
	}

	private static InMemoryBigliettoDAO instance;

	public static InMemoryBigliettoDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryBigliettoDAO();
		}
		return instance;
	}

	@Override
	public void insert(Biglietto entity) {
		FakeDB.getInstance().getBiglietti().add(entity);

	}

	@Override
	public void update(Biglietto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Biglietto> selectAll() {
		return FakeDB.getInstance().getBiglietti();
	}

	@Override
	public Biglietto selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Biglietto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Biglietto> getAllBigliettiByUserId(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

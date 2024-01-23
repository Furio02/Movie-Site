package dao.inmemory;

import java.util.List;

import dao.ActorDAO;
import database.FakeDB;
import model.Actor;

public class InMemoryActorDAO implements ActorDAO {

	private InMemoryActorDAO() {
	}

	private static InMemoryActorDAO instance;

	public static InMemoryActorDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryActorDAO();
		}
		return instance;
	}

	@Override
	public void insert(Actor entity) {
		FakeDB.getInstance().getActors().add(entity);

	}

	@Override
	public void update(Actor entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Actor> selectAll() {
		// TODO Auto-generated method stub
		return FakeDB.getInstance().getActors();
	}

	@Override
	public Actor selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Actor entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Actor selectByNomeCompleto(String nome, String cognome) {

		for (Actor a : FakeDB.getInstance().getActors()) {
			if (a.getNome().equalsIgnoreCase(nome) && a.getCognome().equalsIgnoreCase(cognome)) {
				return a;
			}
		}

		return null;
	}

}

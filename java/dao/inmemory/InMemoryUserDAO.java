package dao.inmemory;

import java.util.List;

import dao.UserDAO;
import database.FakeDB;
import model.User;

public class InMemoryUserDAO implements UserDAO {

	private InMemoryUserDAO() {
	}

	private static InMemoryUserDAO instance;

	public static InMemoryUserDAO getInstance() {
		if (instance == null) {
			instance = new InMemoryUserDAO();
		}
		return instance;
	}

	@Override
	public void insert(User entity) {
		FakeDB.getInstance().getUsers().add(entity);
	}

	@Override
	public void update(User entity) {

	}

	@Override
	public List<User> selectAll() {
		return FakeDB.getInstance().getUsers();
	}

	@Override
	public User selectById(long id) {
		return null;
	}

	@Override
	public void delete(User entity) {

	}

	@Override
	public User selectByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

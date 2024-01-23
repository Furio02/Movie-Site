package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import dao.UserDAO;
import model.User;

public class JdbcUserDao implements UserDAO {

	private JdbcUserDao() {
	}

	private static JdbcUserDao instance;

	public static JdbcUserDao getInstance() {
		if (instance == null) {
			instance = new JdbcUserDao();
		}
		return instance;
	}

	@Override
	public void insert(User entity) throws SQLException {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into utente values(null,?,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> selectAll() throws SQLException {
		List<User> users = new ArrayList<User>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from utente";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				User u = new User();

				u.setId(rs.getLong("id"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));

				users.add(u);

			}
		}

		return users;
	}

	@Override
	public User selectById(long id) throws SQLException {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from utente where id = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();

				u.setId(rs.getLong("id"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				return u;
			}
		}
		return null;
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public User selectByEmail(String email) throws Exception {
		try(Connection c = JdbcDAOFactory.getConnection()){
			String query = "select * from utente where email = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User u = new User();
				
				u.setId(rs.getLong("id"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				
				return u;
			}
		}
		return null;
	}

	@Override
	public User login(String email, String password) throws Exception {
		try(Connection c = JdbcDAOFactory.getConnection()){
			String query = "Select * from utente where email = ? and password = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				
				return u;
			}
			
		}
		return null;
	}
}

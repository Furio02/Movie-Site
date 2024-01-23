package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ActorDAO;
import model.Actor;
import model.User;

public class JdbcAttoriDao implements ActorDAO {

	private JdbcAttoriDao() {
	}

	private static JdbcAttoriDao instance;

	public static JdbcAttoriDao getInstance() {
		if (instance == null) {
			instance = new JdbcAttoriDao();
		}
		return instance;
	}

	@Override
	public void insert(Actor entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into attore values(null,?,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getCognome());
			ps.executeUpdate();
		}

	}

	@Override
	public void update(Actor entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Actor> selectAll() throws Exception {
		List<Actor> actors = new ArrayList<Actor>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from attore";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				Actor a = new Actor();

				a.setId(rs.getLong("id"));
				a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));

				actors.add(a);

			}
		}

		return actors;
	}

	@Override
	public Actor selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Actor entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Actor selectByNomeCompleto(String nome, String cognome) throws Exception {

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from attore where nome = ? and cognome = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Actor a = new Actor();

				a.setId(rs.getLong("id"));
				a.setNome(rs.getString("nome"));
				a.setCognome(rs.getString("cognome"));
				return a;
			}

		}

		return null;
	}

}

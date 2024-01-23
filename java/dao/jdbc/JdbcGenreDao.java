package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import dao.GenreDAO;
import model.Genre;
import model.User;

public class JdbcGenreDao implements GenreDAO {

	private JdbcGenreDao() {
	}

	private static JdbcGenreDao instance;

	public static JdbcGenreDao getInstance() {
		if (instance == null) {
			instance = new JdbcGenreDao();
		}
		return instance;
	}

	@Override
	public void insert(Genre entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into genere values(null,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, entity.getDescription());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(Genre entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Genre> selectAll() throws Exception {
		List<Genre> genres = new ArrayList<Genre>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from genere";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				Genre g = new Genre();

				g.setId(rs.getLong("id"));
				g.setDescription(rs.getString("nome"));

				genres.add(g);

			}
		}

		return genres;
	}

	@Override
	public Genre selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Genre entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Genre selectByName(String name) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from genere where nome = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Genre g = new Genre();

				g.setId(rs.getLong("id"));
				g.setDescription(rs.getString("nome"));

				return g;
			}
		}
		return null;
	}

}

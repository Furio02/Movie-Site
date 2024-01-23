package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.SalaDAO;
import model.Actor;
import model.Sala;

public class JdbcSalaDao implements SalaDAO {

	private JdbcSalaDao() {
	}

	private static JdbcSalaDao instance;

	public static JdbcSalaDao getInstance() {
		if (instance == null) {
			instance = new JdbcSalaDao();
		}
		return instance;
	}

	@Override
	public void insert(Sala entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into sala values(null,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, entity.getNomeSala());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(Sala entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sala> selectAll() throws Exception {
		List<Sala> sale = new ArrayList<Sala>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from sala";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				Sala sa = new Sala();

				sa.setId(rs.getLong("id"));
				sa.setNomeSala(rs.getString("nome_sala"));

				sale.add(sa);

			}
		}

		return sale;
	}

	@Override
	public Sala selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sala entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Sala selectSalaByNome(String nomeSala) throws Exception {

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from sala where nome_sala = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, nomeSala);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Sala s = new Sala();

				s.setId(rs.getLong("id"));
				s.setNomeSala(rs.getString("nome_sala"));

				return s;
			}

		}

		return null;
	}

}

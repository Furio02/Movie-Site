package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.BigliettoDAO;
import model.Biglietto;
import model.Film;
import model.Sala;
import model.Spettacolo;
import model.User;

public class JdbcBigliettoDao implements BigliettoDAO {

	private JdbcBigliettoDao() {
	}

	private static JdbcBigliettoDao instance;

	public static JdbcBigliettoDao getInstance() {
		if (instance == null) {
			instance = new JdbcBigliettoDao();
		}
		return instance;
	}

	@Override
	public void insert(Biglietto entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into biglietto values(null,?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, entity.getUtente().getId());
			ps.setLong(2, entity.getShow().getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(Biglietto entity) throws Exception {

	}

	@Override
	public List<Biglietto> selectAll() throws Exception {
		List<Biglietto> biglietti = new ArrayList<Biglietto>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "SELECT * FROM biglietto " + "JOIN spettacolo ON spettacolo.id = biglietto.id_spettacolo "
					+ "JOIN film ON spettacolo.id_film = film.id " + "JOIN sala ON spettacolo.id_sala = sala.id";

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {

				Biglietto b = new Biglietto();
				b.setId(rs.getLong("biglietto.id"));

				Film f = new Film();
				f.setId(rs.getLong("id_film"));
				f.setTitolo(rs.getString("titolo"));

				Sala sa = new Sala();
				sa.setId(rs.getLong("id_sala"));
				sa.setNomeSala(rs.getString("nome_sala"));

				User u = new User();
				String query2 = "select id_utente from biglietto join utente on biglietto.id_utente = utente.id where biglietto.id = ?";
				PreparedStatement ps = c.prepareStatement(query2);
				ps.setLong(1, b.getId());
				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {
					u.setId(rs.getLong("id_utente")); // Otteniamo l'ID dell'utente direttamente dal ResultSet
				}

				u.setBiglietti(biglietti);

				Spettacolo sp = new Spettacolo();
				sp.setId(rs.getLong("spettacolo.id"));
				sp.setDataSpettacolo((LocalDateTime) rs.getObject("data_spettacolo"));
				sp.setF(f);
				sp.setS(sa);

				b.setShow(sp);
				b.setUtente(u);

				biglietti.add(b); // Aggiungi il biglietto alla lista dei biglietti
			}
		}
		return biglietti;
	}

	@Override
	public Biglietto selectById(long id) throws Exception {
		return null;
	}

	@Override
	public void delete(Biglietto entity) throws Exception {

	}

	@Override
	public List<Biglietto> getAllBigliettiByUserId(long id) throws Exception {
		List<Biglietto> biglietti = new ArrayList<Biglietto>();
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "SELECT * FROM biglietto JOIN spettacolo ON spettacolo.id = biglietto.id_spettacolo JOIN film ON spettacolo.id_film = film.id JOIN sala ON spettacolo.id_sala = sala.id WHERE id_utente = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Biglietto b = new Biglietto();
				b.setId(rs.getLong("biglietto.id"));

				Film f = new Film();
				f.setId(rs.getLong("id_film"));
				f.setTitolo(rs.getString("titolo"));

				Sala sa = new Sala();
				sa.setId(rs.getLong("id_sala"));
				sa.setNomeSala(rs.getString("nome_sala"));

				Spettacolo sp = new Spettacolo();
				sp.setId(rs.getLong("spettacolo.id"));
				sp.setDataSpettacolo((LocalDateTime) rs.getObject("data_spettacolo"));
				sp.setF(f);
				sp.setS(sa);

				b.setShow(sp);
				biglietti.add(b);

			}

		}
		return biglietti;
	}

}

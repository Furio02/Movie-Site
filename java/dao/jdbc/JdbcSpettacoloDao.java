package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.SpettacoloDAO;
import model.Actor;
import model.Film;
import model.Genre;
import model.Sala;
import model.Spettacolo;

public class JdbcSpettacoloDao implements SpettacoloDAO {

	private JdbcSpettacoloDao() {
	}

	private static JdbcSpettacoloDao instance;

	public static JdbcSpettacoloDao getInstance() {
		if (instance == null) {
			instance = new JdbcSpettacoloDao();
		}
		return instance;
	}

	@Override
	public void insert(Spettacolo entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into spettacolo values(null,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, entity.getF().getId());
			ps.setLong(2, entity.getS().getId());
			ps.setObject(3, entity.getDataSpettacolo());
			ps.setBoolean(4, false);
			ps.executeUpdate();
		}

	}

	@Override
	public void update(Spettacolo entity) throws Exception {
		
	}

	@Override
	public List<Spettacolo> selectAll() throws Exception {
		List<Spettacolo> spettacoli = new ArrayList<Spettacolo>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select hidden, spettacolo.id as spettacolo_id,id_sala,nome_sala,data_spettacolo,film.id as film_id,titolo,trama,durata,id_genere,genere.nome as genere from spettacolo join sala on spettacolo.id_sala = sala.id join film on spettacolo.id_film = film.id join genere on film.id_genere = genere.id";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				
				
				if(!rs.getBoolean("hidden")) {
					Spettacolo sp = new Spettacolo();

					Sala sa = new Sala();

					Film f = new Film();

					Genre g = new Genre();

					sa.setId(rs.getLong("id_sala"));
					sa.setNomeSala(rs.getString("nome_sala"));

					sp.setId(rs.getLong("spettacolo_id"));

					g.setId(rs.getLong("id_genere"));
					g.setDescription(rs.getString("genere"));

					f.setId(rs.getLong("film_id"));
					f.setTitolo(rs.getString("titolo"));
					f.setDurata(rs.getString("durata"));
					f.setTrama(rs.getString("trama"));
					f.setDescription(g);

					List<Actor> app = new ArrayList<>();
					String query2 = "select attore.id as attore_id,nome,cognome from attore join attore_film on attore.id = attore_film.id_attore join film on film.id = attore_film.id_film join spettacolo on spettacolo.id_film = film.id where film.id = ?";
					PreparedStatement ps = c.prepareStatement(query2);
					ps.setLong(1, f.getId());
					ResultSet rs2 = ps.executeQuery();

					while (rs2.next()) {
						Actor a = new Actor();

						a.setId(rs2.getLong("attore_id"));
						a.setNome(rs2.getString("nome"));
						a.setCognome(rs2.getString("cognome"));

						app.add(a);
					}

					f.setAttori(app);

					sp.setF(f);
					sp.setS(sa);
					sp.setDataSpettacolo((LocalDateTime) rs.getObject("data_spettacolo"));
					sp.setHidden(rs.getBoolean("hidden"));
					spettacoli.add(sp);
				}
			}
		}

		return spettacoli;
	}

	@Override
	public Spettacolo selectById(long id) throws Exception {
		try(Connection c = JdbcDAOFactory.getConnection()){
			String query = "select * from spettacolo where id = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Spettacolo s = new Spettacolo();
				
				s.setId(rs.getLong("id"));
				
				Film f = new Film();
				
				f.setId(rs.getLong("id_film"));
				
				s.setF(f);
				
				Sala sa = new Sala();
				
				sa.setId(rs.getLong("id_sala"));
				
				s.setS(sa);
				
				s.setDataSpettacolo((LocalDateTime)rs.getObject("data_spettacolo"));
				
				s.setHidden(rs.getBoolean("hidden"));
				
				return s;
			}
		}
		return null;
	}

	@Override
	public void delete(Spettacolo entity) throws Exception {

	}

	@Override
	public void deleteByShowId(long id) throws Exception {
		try(Connection c = JdbcDAOFactory.getConnection()){
			String query = "Update spettacolo set hidden = true where id = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setLong(1, id);
			ps.executeUpdate();
			
		}
		
	}

}
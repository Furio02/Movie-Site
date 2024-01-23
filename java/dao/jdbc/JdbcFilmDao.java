package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bl.BusinessLogic;
import dao.FilmDAO;
import model.Actor;
import model.Film;
import model.Genre;
import model.User;

public class JdbcFilmDao implements FilmDAO {

	private JdbcFilmDao() {
	}

	private static JdbcFilmDao instance;

	public static JdbcFilmDao getInstance() {
		if (instance == null) {
			instance = new JdbcFilmDao();
		}
		return instance;
	}

	@Override
	public void insert(Film entity) throws Exception {
		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "insert into film values(null,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, entity.getDescription().getId());
			ps.setString(2, entity.getTitolo());
			ps.setString(3, entity.getDurata());
			ps.setString(4, entity.getTrama());
			ps.executeUpdate();

			// Recupera l'ID del film appena inserito
			ResultSet generatedKeys = ps.getGeneratedKeys();
			long filmId = -1; // Valore iniziale predefinito
			if (generatedKeys.next()) {
				filmId = generatedKeys.getLong(1);
			}

			// Inserisci attori associati al film nella tabella attore_film
			String query2 = "insert into attore_film values(null,?,?)";
			PreparedStatement ps2 = c.prepareStatement(query2);

			for (Actor a : entity.getAttori()) {
				ps2.setLong(1, filmId); // Utilizza l'ID del film
				ps2.setLong(2, a.getId());
				ps2.executeUpdate();
			}
		}
	}

	@Override
	public void update(Film entity) throws Exception {
		
	}

	@Override
	public List<Film> selectAll() throws Exception {
		List<Film> movies = new ArrayList<Film>();

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select film.id as film_id,titolo,durata,trama,id_genere,genere.nome as genere 			from film join genere on genere.id = film.id_genere";

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				Film f = new Film();

				Genre g = new Genre();

				g.setId(rs.getLong("id_genere"));
				g.setDescription(rs.getString("genere"));

				f.setId(rs.getLong("film_id"));
				f.setDescription(g);
				f.setTitolo(rs.getString("titolo"));
				f.setDurata(rs.getString("durata"));
				f.setTrama(rs.getString("trama"));

				List<Actor> app = new ArrayList<>();

				String query2 = "select id_attore,nome,cognome from attore join attore_film on attore.id = attore_film.id_attore join film on film.id = attore_film.id_film where film.id = ?";
				PreparedStatement s2 = c.prepareStatement(query2);
				s2.setLong(1, f.getId());
				ResultSet rs2 = s2.executeQuery();

				while (rs2.next()) {
					Actor a = new Actor();

					a.setId(rs2.getLong("id_attore"));
					a.setNome(rs2.getString("nome"));
					a.setCognome(rs2.getString("cognome"));

					app.add(a);
				}

				f.setAttori(app);

				movies.add(f);

			}
		}

		return movies;
	}

	@Override
	public Film selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Film entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Film selectByTitolo(String titolo) throws Exception {

		try (Connection c = JdbcDAOFactory.getConnection()) {
			String query = "select * from film where titolo = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, titolo);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Film f = new Film();

				f.setId(rs.getLong("id"));
				f.setTitolo(rs.getString("titolo"));

				return f;
			}

		}

		return null;
	}

	@Override
	public void deleteByFilmId(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

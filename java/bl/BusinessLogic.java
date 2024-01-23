package bl;

import java.util.List;

import dao.DAOFactory;
import jakarta.servlet.http.HttpSession;
import model.Actor;
import model.Biglietto;
import model.Film;
import model.Genre;
import model.Sala;
import model.Spettacolo;
import model.User;

public class BusinessLogic {

	public static void updateMovie(Film f) throws Exception{
		DAOFactory.getDAOFactory().getFilmDAO().update(f);
	}
	
	public static void updateSpettacolo(Spettacolo s) throws Exception{
		DAOFactory.getDAOFactory().getShowDAO().update(s);
	}
	
	public static List<User> getAllUsers() throws Exception {
		return DAOFactory.getDAOFactory().getUserDAO().selectAll();
	}

	public static List<Film> getAllMovies() throws Exception {
		return DAOFactory.getDAOFactory().getFilmDAO().selectAll();
	}

	public static List<Genre> getAllGenres() throws Exception {
		return DAOFactory.getDAOFactory().getGenreDAO().selectAll();
	}

	public static List<Actor> getAllActors() throws Exception {
		return DAOFactory.getDAOFactory().getActorDAO().selectAll();
	}

	public static List<Sala> getAllSale() throws Exception {
		return DAOFactory.getDAOFactory().getSalaDAO().selectAll();
	}

	public static List<Spettacolo> getAllShows() throws Exception {
		return DAOFactory.getDAOFactory().getShowDAO().selectAll();
	}

	public static List<Biglietto> getAllBiglietti() throws Exception {
		return DAOFactory.getDAOFactory().getBigliettoDAO().selectAll();
	}
	
	public static List<Biglietto> getAllBigliettiByUserId(long id) throws Exception{
		return DAOFactory.getDAOFactory().getBigliettoDAO().getAllBigliettiByUserId(id);
	}

	public static Actor selectByNomeCompleto(String nome, String cognome) throws Exception {
		return DAOFactory.getDAOFactory().getActorDAO().selectByNomeCompleto(nome, cognome);
	}

	public static void insertUser(User u) throws Exception {
		DAOFactory.getDAOFactory().getUserDAO().insert(u);
	}

	public static void insertMovie(Film f) throws Exception {
		DAOFactory.getDAOFactory().getFilmDAO().insert(f);
	}

	public static void insertGenre(Genre g) throws Exception {
		DAOFactory.getDAOFactory().getGenreDAO().insert(g);
	}

	public static void insertActor(Actor a) throws Exception {
		DAOFactory.getDAOFactory().getActorDAO().insert(a);
	}

	public static void insertSala(Sala s) throws Exception {
		DAOFactory.getDAOFactory().getSalaDAO().insert(s);
	}

	public static void insertShow(Spettacolo sh) throws Exception {
		DAOFactory.getDAOFactory().getShowDAO().insert(sh);
	}

	public static void insertBiglietto(Biglietto b) throws Exception {
		DAOFactory.getDAOFactory().getBigliettoDAO().insert(b);
	}

	public static Genre selectGenreByName(String name) throws Exception {
		return DAOFactory.getDAOFactory().getGenreDAO().selectByName(name);
	}

	public static Film selectByTitolo(String titolo) throws Exception {
		return DAOFactory.getDAOFactory().getFilmDAO().selectByTitolo(titolo);
	}

	public static Sala selectByNomeSala(String nomeSala) throws Exception {
		return DAOFactory.getDAOFactory().getSalaDAO().selectSalaByNome(nomeSala);
	}

	public static User selectUserById(long id) throws Exception{
		return DAOFactory.getDAOFactory().getUserDAO().selectById(id);
	}
	
	public static Spettacolo selectShowById(long id) throws Exception{
		return DAOFactory.getDAOFactory().getShowDAO().selectById(id);
	}
	
	public static void deleteShowById(long id) throws Exception{
		DAOFactory.getDAOFactory().getShowDAO().deleteByShowId(id);
	}
	
	public static void deleteByFilmId(long id) throws Exception{
		DAOFactory.getDAOFactory().getFilmDAO().deleteByFilmId(id);
	}
	
	public static User selectUserByEmail(String email) throws Exception{
		return DAOFactory.getDAOFactory().getUserDAO().selectByEmail(email);
	}
	
	public static boolean login(String email, String password, HttpSession httpSession) throws Exception{
		User temp = DAOFactory.getDAOFactory().getUserDAO().login(email, password);
		if(temp!= null) {
			httpSession.setAttribute("loggedUser", temp);
			return true;
		}
		return false;
	}
	
	public static User login(String email, String password) throws Exception{
		
		return DAOFactory.getDAOFactory().getUserDAO().login(email, password);
	}
	
}

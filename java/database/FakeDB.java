package database;

import java.util.ArrayList;
import java.util.List;

import model.Actor;
import model.Biglietto;
import model.Film;
import model.Genre;
import model.Sala;
import model.Spettacolo;
import model.User;

public class FakeDB {

	private FakeDB() {
	}

	private static FakeDB instance;

	public static FakeDB getInstance() {

		if (instance == null) {
			instance = new FakeDB();
		}

		return instance;

	}

	public List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		return users;
	}

	public List<Film> films = new ArrayList<>();

	public List<Film> getFilms() {
		return films;
	}

	public List<Actor> actors = new ArrayList<>();

	public List<Actor> getActors() {
		return actors;
	}

	public List<Genre> genres = new ArrayList<>();

	public List<Genre> getGenres() {
		return genres;
	}

	public List<Sala> sale = new ArrayList<>();

	public List<Sala> getSale() {
		return sale;
	}

	public List<Spettacolo> spettacolos = new ArrayList<>();

	public List<Spettacolo> getShows() {
		return spettacolos;
	}

	public List<Biglietto> biglietti = new ArrayList<>();

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

}

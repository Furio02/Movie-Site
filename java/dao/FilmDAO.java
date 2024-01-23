package dao;

import model.Film;

public interface FilmDAO extends GeneralDAO<Film> {

	Film selectByTitolo(String titolo) throws Exception;

	void deleteByFilmId(long id) throws Exception;

}

package dao;

import model.Genre;

public interface GenreDAO extends GeneralDAO<Genre> {
	Genre selectByName(String name) throws Exception;
}

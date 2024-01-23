package dao;

import model.Spettacolo;

public interface SpettacoloDAO extends GeneralDAO<Spettacolo> {
	
	void deleteByShowId(long id) throws Exception;
}

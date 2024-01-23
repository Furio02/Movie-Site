package dao;

import java.util.List;

import model.Biglietto;

public interface BigliettoDAO extends GeneralDAO<Biglietto> {
	
	List<Biglietto> getAllBigliettiByUserId(long id) throws Exception;
	
	
}

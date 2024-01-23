package dao;

import model.Sala;

public interface SalaDAO extends GeneralDAO<Sala> {

	Sala selectSalaByNome(String nomeSala) throws Exception;

}

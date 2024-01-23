package dao;

import model.Actor;

public interface ActorDAO extends GeneralDAO<Actor> {

	Actor selectByNomeCompleto(String nome, String cognome) throws Exception;

}

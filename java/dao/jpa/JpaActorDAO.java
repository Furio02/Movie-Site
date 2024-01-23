package dao.jpa;

import java.util.List;

import dao.ActorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Actor;

public class JpaActorDAO implements ActorDAO {

	private JpaActorDAO() {}

	private static JpaActorDAO instance;
	
	public static JpaActorDAO getInstance() {
		if(instance == null) {
			instance = new JpaActorDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(Actor entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(Actor entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Actor> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Actor a ORDER BY a.nome ASC");
		return q.getResultList();
	}

	@Override
	public Actor selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Actor entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Actor selectByNomeCompleto(String nome, String cognome) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select a from Actor a where a.nome = :x and a.cognome = :y");
		q.setParameter("x", nome);
		q.setParameter("y", cognome);
		return (Actor) q.getSingleResult();
	}

}

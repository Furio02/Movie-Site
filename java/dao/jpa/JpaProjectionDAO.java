package dao.jpa;

import java.util.List;

import dao.SpettacoloDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Spettacolo;

public class JpaProjectionDAO implements SpettacoloDAO {
	
	private JpaProjectionDAO() {}

	private static JpaProjectionDAO instance;
	
	public static JpaProjectionDAO getInstance() {
		if(instance == null) {
			instance = new JpaProjectionDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(Spettacolo entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(Spettacolo entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.merge(entity);
		t.commit();

	}

	@Override
	public List<Spettacolo> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select s from projection s where hidden = false");
		return q.getResultList();
	}

	@Override
	public Spettacolo selectById(long id) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		return em.find(Spettacolo.class, id);
	}

	@Override
	public void delete(Spettacolo entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByShowId(long id) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		Spettacolo sToDelete = em.find(Spettacolo.class, id);
		
		sToDelete.setHidden(true);
		
		em.merge(sToDelete);
		
		t.commit();
	}

}

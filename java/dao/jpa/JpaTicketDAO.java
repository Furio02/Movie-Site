package dao.jpa;

import java.util.List;

import dao.BigliettoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Biglietto;

public class JpaTicketDAO implements BigliettoDAO {

	private JpaTicketDAO() {}

	private static JpaTicketDAO instance;
	
	public static JpaTicketDAO getInstance() {
		if(instance == null) {
			instance = new JpaTicketDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(Biglietto entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(Biglietto entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Biglietto> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select b from ticket b where");
		return q.getResultList();
	}

	@Override
	public Biglietto selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Biglietto entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Biglietto> getAllBigliettiByUserId(long id) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select b from ticket b where b.utente.id = :x AND hidden = false");
		q.setParameter("x", id);
		return q.getResultList();
	}

}

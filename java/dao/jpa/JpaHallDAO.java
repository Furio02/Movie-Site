package dao.jpa;

import java.util.List;

import dao.SalaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Sala;

public class JpaHallDAO implements SalaDAO {
	
	private JpaHallDAO() {}

	private static JpaHallDAO instance;
	
	public static JpaHallDAO getInstance() {
		if(instance == null) {
			instance = new JpaHallDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(Sala entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(Sala entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sala> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select s from hall s");
		return q.getResultList();
	}

	@Override
	public Sala selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sala entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Sala selectSalaByNome(String nomeSala) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q  = em.createQuery("select s from hall s where s.nomeSala = :x");
		q.setParameter("x", nomeSala);
		return (Sala) q.getSingleResult();
	}

}

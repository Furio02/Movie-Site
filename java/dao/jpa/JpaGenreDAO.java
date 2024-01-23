package dao.jpa;

import java.util.List;

import dao.GenreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Genre;

public class JpaGenreDAO implements GenreDAO {
	
	private JpaGenreDAO() {}

	private static JpaGenreDAO instance;
	
	public static JpaGenreDAO getInstance() {
		if(instance == null) {
			instance = new JpaGenreDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(Genre entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(Genre entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Genre> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select g from genre g");
		return q.getResultList();
	}

	@Override
	public Genre selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Genre entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Genre selectByName(String name) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select g from genre g where g.description = :x");
		q.setParameter("x", name);
		return (Genre) q.getSingleResult();
	}

}

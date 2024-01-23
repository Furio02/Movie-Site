package dao.jpa;

import java.util.List;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.User;

public class JpaUserDAO implements UserDAO {

	private JpaUserDAO() {}

	private static JpaUserDAO instance;
	
	public static JpaUserDAO getInstance() {
		if(instance == null) {
			instance = new JpaUserDAO();
		}
		return instance;
	}
	
	@Override
	public void insert(User entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}

	@Override
	public void update(User entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select u from User u");
		return q.getResultList();
	}

	@Override
	public User selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public User selectByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String email, String password) {
	    EntityManager em = JpaDAOFactory.getEntityManager();
	    Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :x AND u.password = :y");
	    q.setParameter("x", email);
	    q.setParameter("y", password);
	    
	    List<User> users = q.getResultList();
	    if (!users.isEmpty()) {
	        return users.get(0); // Assuming there should be only one matching user
	    } else {
	        // Handle the case where no user was found, such as returning null or throwing a custom exception.
	        return null;
	    }
	}


}

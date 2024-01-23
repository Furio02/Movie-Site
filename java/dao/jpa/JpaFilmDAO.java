package dao.jpa;

import java.util.List;

import dao.FilmDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Biglietto;
import model.Film;
import model.Spettacolo;

public class JpaFilmDAO implements FilmDAO {

	private JpaFilmDAO() {
	}

	private static JpaFilmDAO instance;

	public static JpaFilmDAO getInstance() {
		if (instance == null) {
			instance = new JpaFilmDAO();
		}
		return instance;
	}

	@Override
	public void insert(Film entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entity);
		t.commit();
	}
	
	@Override
	public void update(Film entity) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.merge(entity);
		t.commit();

	}

	@Override
	public List<Film> selectAll() throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select m from Film m where hidden = false");
		return q.getResultList();
	}

	@Override
	public Film selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Film entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Film selectByTitolo(String titolo) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		Query q = em.createQuery("select m from Film m where m.titolo = :x and hidden = false");
		q.setParameter("x", titolo);
		return (Film) q.getSingleResult();
	}

	public void deleteByFilmId(long id) throws Exception {
		EntityManager em = JpaDAOFactory.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Film fToDelete = em.find(Film.class, id);
		if (fToDelete != null) {
			List<Spettacolo> spettacoliAssociati = fToDelete.getSpettacoli();
			fToDelete.setHidden(true);
			for (Spettacolo spettacolo : spettacoliAssociati) {
				spettacolo.setHidden(true);
				em.merge(spettacolo);
				List<Biglietto> biglietti = spettacolo.getBiglietti();
				for(Biglietto biglietto : biglietti) {
					biglietto.setHidden(true);
					em.merge(biglietto);
				}
			}
			t.commit();
		}
	}

}

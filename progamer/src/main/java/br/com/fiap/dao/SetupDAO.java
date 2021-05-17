package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFactorySingleton;

public class SetupDAO {

	private EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Setup setup) {
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
	}

	public List<Setup> getAllByUser(User user) {
		String jpql = "SELECT s FROM Setup s WHERE s.user = :user";
		TypedQuery<Setup> createQuery = manager.createQuery(jpql, Setup.class);
		createQuery.setParameter("user", user);
		return createQuery.getResultList();
	}

	public List<Setup> getAll() {
		String jpql = "SELECT s FROM Setup s";
		TypedQuery<Setup> createQuery = manager.createQuery(jpql, Setup.class);
		return createQuery.getResultList();
	}

	public Setup findById(Long id) {
		return manager.find(Setup.class, id);
	}

	public void update(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
	}

	public void remove(Setup setup) {
		manager.getTransaction().begin();
		manager.remove(setup);
		manager.getTransaction().commit();
	}
}

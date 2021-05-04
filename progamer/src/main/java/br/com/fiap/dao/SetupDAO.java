package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import br.com.fiap.model.Setup;
import br.com.fiap.util.EntityManagerFactorySingleton;

public class SetupDAO {

	private EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Setup setup) {
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
		manager.close();
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
}

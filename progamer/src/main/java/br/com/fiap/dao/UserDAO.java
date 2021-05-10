package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFactorySingleton;

public class UserDAO {

	private EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(User user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<User> getAll() {
		String jpql = "SELECT u FROM User u";
		TypedQuery<User> createQuery = manager.createQuery(jpql, User.class);
		return createQuery.getResultList();
	}

	public User findById(Long id) {
		return manager.find(User.class, id);
	}

	public void update(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
	}

	public void remove(User user) {
		manager.getTransaction().begin();
		manager.remove(user);
		manager.getTransaction().commit();
	}

}

package com.mgnyniuk.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.User;

@Stateless
public class UserService {

	public User findUserByName(String username) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = User.findUserByName(em, username);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return user;
	}

	public void add(String username, String password, String email) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = new User(username, password, email);
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}

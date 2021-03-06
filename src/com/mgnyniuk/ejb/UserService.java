package com.mgnyniuk.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.Group;
import com.mgnyniuk.jpa.Log;
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
		/*User user2 = User.findUserByName(em, "vov");
		em.remove(user2);*/
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
		Group group = new Group("users", user);
		em.persist(user);
		em.persist(group);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	public List<User> findAllUsers() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<User> userList = User.findAllUsers(em);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return userList;
	}
}

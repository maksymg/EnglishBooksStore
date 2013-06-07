package com.mgnyniuk.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.Order;

@Stateless
public class OrderService {
	
	public void add(Order order) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();	
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}

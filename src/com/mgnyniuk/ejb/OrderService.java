package com.mgnyniuk.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.OrderItem;


@Stateless
public class OrderService {
	
	private List<OrderItem> orderList;
	
	public void add(OrderItem order) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();	
		
		em.merge(order);
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	public List<OrderItem> findOrdersByUser(String username) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		orderList = OrderItem.findOrdersByUser(em, username);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return orderList;
	}

}

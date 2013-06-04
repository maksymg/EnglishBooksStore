package com.mgnyniuk.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.User;

@Stateless
public class BookService {
	
	public void add(String title, String author, String description, Double price, byte[] cover) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Book book = new Book(title, author, description, price, cover);
		em.persist(book);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}

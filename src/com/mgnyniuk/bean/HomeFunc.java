package com.mgnyniuk.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.Book;

@Stateless
public class HomeFunc {

	private String initString;
	private List<Book> bookList;

	@PostConstruct
	void init() {

		initString = "init String!";

	}

	public List<Book> findAllBooks() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		bookList = Book.findAll(em);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return bookList;
	}

	public Book findBook(int id) {
		if (bookList != null) {

			for (Book book : bookList) {
				if (book.getId() == id) {
					return book;
				}
			}
		}
		return null;

	}

	public String getMessage() {
		Collections.emptyList();
		return "Message from EJB!" + initString;
	}

}

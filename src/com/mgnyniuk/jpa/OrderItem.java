package com.mgnyniuk.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "ordertable")
@NamedQueries(value = {@NamedQuery(name="Order.findOrdersByUser", query = "select ord from OrderItem ord where ord.user.username = :username")})
public class OrderItem implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@JoinTable(name = "ordertable_book", joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "book_id", referencedColumnName = "id") })
	@ManyToMany
	private Collection<Book> bookList;

	public OrderItem() {

	}
	
	public static List<OrderItem> findOrdersByUser(EntityManager em, String username) {

		Query query = em.createNamedQuery("Order.findOrdersByUser");
		query.setParameter("username", username);
		return query.getResultList();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Book> getBookList() {
		return bookList;
	}

	public void setBookList(Collection<Book> bookList) {
		this.bookList = bookList;
	}

}

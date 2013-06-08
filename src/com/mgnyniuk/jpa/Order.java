package com.mgnyniuk.jpa;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordertable")
public class Order implements Serializable {

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

	public Order() {

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

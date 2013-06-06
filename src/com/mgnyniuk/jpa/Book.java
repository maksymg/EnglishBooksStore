package com.mgnyniuk.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Entity
@NamedQueries(value = { @NamedQuery(name = "Book.findAll", query = "select bk from Book bk") })
public class Book implements Serializable {

	@Id
	private int id;

	@Column
	private String title;

	@Column
	private String author;

	@Column
	private String description;

	@Column
	private Double price;

	@Column
	private byte[] cover;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_order", joinColumns = @JoinColumn(name = "bookid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "orderid", referencedColumnName = "id"))
	private List<Order> orderList = new ArrayList<Order>();

	public Book() {

	}

	public Book(String title, String author, String description, Double price,
			byte[] cover) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.price = price;
		this.cover = cover;
	}

	public static List<Book> findAll(EntityManager em) {

		Query query = em.createNamedQuery("Book.findAll");

		return query.getResultList();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}

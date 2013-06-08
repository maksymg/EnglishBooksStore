package com.mgnyniuk.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries(value = { @NamedQuery(name = "User.findUserByName", query = "select user from User User where user.username = :username") })
public class User {

	@Id
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@OneToMany(mappedBy = "user")
	private List<OrderItem> orderList = new ArrayList<OrderItem>();

	public User() {

	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public static User findUserByName(EntityManager em, String username) {
		Query query = em.createNamedQuery("User.findUserByName");
		query.setParameter("username", username);
		List<User> userList = query.getResultList();
		User user = null;
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
		}
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrderItem> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderItem> orderList) {
		this.orderList = orderList;
	}
}

package com.mgnyniuk.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order implements Serializable {
	
	@Id
	private int id;
	
	@Column
	private String description;
	
	@Column
	private int userid;
	
	@Column
	private int bookid;
	
	public Order() {
		
	}
	
	public Order(int id, String description, int userid, int bookid) {
		this.id = id;
		this.description = description;
		this.userid = userid;
		this.bookid = bookid;
	}
}

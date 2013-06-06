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
	
	public Order() {
		
	}
	
	public Order(int id, String description, int userid, int bookid) {
		this.id = id;
		this.description = description;
		this.userid = userid;
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}

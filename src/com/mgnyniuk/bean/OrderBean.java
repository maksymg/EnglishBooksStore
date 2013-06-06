package com.mgnyniuk.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Order;

@ManagedBean
@SessionScoped
public class OrderBean {
	
	private Order order;
	
	@PostConstruct
	private void init() {
		
	}
	
	public void createOrder(List<Book> bookList) {
		order = new Order();
		order.setBookList(bookList);
		order.setDescription("Order");
		//user)
	}

}

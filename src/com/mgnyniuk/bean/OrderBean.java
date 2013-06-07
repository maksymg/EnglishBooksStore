package com.mgnyniuk.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Order;
import com.mgnyniuk.jpa.User;

@ManagedBean
@SessionScoped
public class OrderBean {

	private Order order;

	@PostConstruct
	private void init() {
		System.out.println(FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user"));
	}

	public void createOrder(List<Book> bookList) {
		User currentUser = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		order = new Order();
		order.setBookList(bookList);
		order.setDescription("Order");
		order.setUser(currentUser);
	}

}

package com.mgnyniuk.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.ejb.OrderService;
import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Order;
import com.mgnyniuk.jpa.User;

@ManagedBean
@RequestScoped
public class OrderBean {

	private Order order;
	private OrderService orderService;
	private List<Order> ordersByUser;

	@PostConstruct
	private void init() {
		try {
			orderService = (OrderService) InitialContext
					.doLookup("java:module/OrderService");

			User currentUser = (User) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("user");

			ordersByUser = orderService.findOrdersByUser(currentUser
					.getUsername());

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createOrder(List<Book> bookList) {
		User currentUser = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		order = new Order();
		order.setBookList(bookList);
		order.setDescription("Order");
		order.setUser(currentUser);
		orderService.add(order);
	}

	public void findOrdersByUser(String username) {
		User currentUser = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");

		ordersByUser = orderService.findOrdersByUser(currentUser.getUsername());

	}

	public List<Order> getOrdersByUser() {
		return ordersByUser;
	}

	public void setOrdersByUser(List<Order> ordersByUser) {
		this.ordersByUser = ordersByUser;
	}

}

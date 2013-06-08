package com.mgnyniuk.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.ejb.LogService;
import com.mgnyniuk.ejb.OrderService;
import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.OrderItem;
import com.mgnyniuk.jpa.User;

@ManagedBean
@RequestScoped
public class OrderBean {

	private OrderItem order;
	private OrderService orderService;
	private List<OrderItem> ordersByUser;
	private LogService logService;

	@PostConstruct
	private void init() {
		try {
			orderService = (OrderService) InitialContext
					.doLookup("java:module/OrderService");

			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");

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
		order = new OrderItem();
		order.setBookList(bookList);
		order.setDescription("Order");
		order.setUser(currentUser);
		orderService.add(order);
		logService.add(new Log("User: " + currentUser.getUsername()
				+ " make new order.", new Timestamp((new Date().getTime()))));
	}

	public void findOrdersByUser(String username) {
		User currentUser = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");

		ordersByUser = orderService.findOrdersByUser(currentUser.getUsername());

	}

	public List<OrderItem> getOrdersByUser() {
		return ordersByUser;
	}

	public void setOrdersByUser(List<OrderItem> ordersByUser) {
		this.ordersByUser = ordersByUser;
	}

}

package com.mgnyniuk.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.ejb.LogService;
import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Cart;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.User;

@ManagedBean
@SessionScoped
public class CartBean {

	private Cart cart;
	private Book selectedBook;
	private LogService logService;

	@PostConstruct
	public void init() {
		try {
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cart = new Cart();
	}

	public void addToCart(Book selectedBook) {
		cart.getBookList().add(selectedBook);
		logService.add(new Log("User: "
				+ ((User) FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap().get("user"))
						.getUsername() + " added to cart " + "\""
				+ selectedBook.getTitle() + "\"" + " book.", new Timestamp(
				(new Date().getTime()))));
	}

	public void deleteBookFromCart(Book selectedBook) {
		cart.getBookList().remove(selectedBook);
		logService.add(new Log("User: "
				+ ((User) FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap().get("user"))
						.getUsername() + " deleted from cart " + "\""
				+ selectedBook.getTitle() + "\"" + " book.", new Timestamp(
				(new Date().getTime()))));
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public int getSize() {
		return cart.getBookList().size();
	}

}

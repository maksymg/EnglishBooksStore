package com.mgnyniuk.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Cart;
import com.mgnyniuk.jpa.User;

@ManagedBean
@SessionScoped
public class CartBean {
	
	private Cart cart;
	private Book selectedBook;
	
	@PostConstruct
	public void init() {
		System.out.println(((User)FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user")).getUsername());
		cart = new Cart();
	}
	
	public void addToCart(Book selectedBook) {
		cart.getBookList().add(selectedBook);
	}
	
	public void deleteBookFromCart(Book selectedBook) {
		cart.getBookList().remove(selectedBook);
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

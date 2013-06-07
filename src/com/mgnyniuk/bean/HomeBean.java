package com.mgnyniuk.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.jpa.Book;

@ManagedBean
@RequestScoped
public class HomeBean implements Serializable {

	private String ejbMessage;
	private HomeFunc homeFunc;

	private List<String> booksImages;
	private List<Book> books;

	public List<String> getBooksImages() {
		return booksImages;
	}

	@PostConstruct
	public void init() throws NamingException {
		homeFunc = (HomeFunc) InitialContext.doLookup("java:module/HomeFunc");
		ejbMessage = homeFunc.getMessage();
		
		books = homeFunc.findAllBooks();
		
		System.out.println(books.size());
		booksImages = new ArrayList<String>();

		for (int i = 1; i <= 3; i++) {
			booksImages.add("booksImages" + i + ".jpg");
		}
	}

	public String getEjbMessage() {
		return ejbMessage;
	}

	public void setEjbMessage(String ejbMessage) {
		this.ejbMessage = ejbMessage;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}

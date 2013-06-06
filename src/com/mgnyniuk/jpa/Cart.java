package com.mgnyniuk.jpa;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Book> bookList;
	
	public Cart() {
		bookList = new ArrayList<Book>();
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}

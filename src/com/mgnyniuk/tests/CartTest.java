package com.mgnyniuk.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mgnyniuk.bean.CartBean;
import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Cart;
import com.mgnyniuk.jpa.User;

@RunWith(JUnit4.class)
public class CartTest {
	
	CartBean cartBean;
	
	@Before
	public void init() {
		cartBean = new CartBean();
		cartBean.setCart(new Cart());
		cartBean.setUser(new User("fake", "fake", "fake"));
	}

	@Test
	public void cartAddTest() {
		
		cartBean.addToCart(new Book("fake", "fake", null, null, null));
		cartBean.addToCart(new Book("fake", "fake", null, null, null));
		
		assertTrue(cartBean.getCart().getBookList().size() == 2);
	}
	
	/*@Test
	public void cartDeleteTest() {
		Book book1 = new Book("fake", "fake", null, null, null);
		cartBean.addToCart(book1);
		cartBean.addToCart(new Book("fake", "fake", null, null, null));
		cartBean.deleteBookFromCart(book1);
		
		// delete one element and must be 1
		assertTrue(cartBean.getCart().getBookList().size() == 1);
	}*/
}

package com.mgnyniuk.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mgnyniuk.bean.CartBean;
import com.mgnyniuk.jpa.Book;
import com.mgnyniuk.jpa.Cart;
import com.mgnyniuk.jpa.User;

@RunWith(JUnit4.class)
public class CartTest {

	@Test
	public void cartAddTest() {
		CartBean cartBean = new CartBean();
		cartBean.setCart(new Cart());
		cartBean.setUser(new User("fake", "fake", "fake"));
		cartBean.addToCart(new Book("fake", "fake", null, null, null));
		cartBean.addToCart(new Book("fake", "fake", null, null, null));
		

		assertTrue(cartBean.getCart().getBookList().size() == 2);
	}
}

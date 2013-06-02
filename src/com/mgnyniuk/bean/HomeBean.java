package com.mgnyniuk.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {

	private String ejbMessage;
	private HomeFunc homeFunc;

	private List<String> booksImages;

	public List<String> getBooksImages() {
		return booksImages;
	}

	@PostConstruct
	public void init() throws NamingException {
		homeFunc = (HomeFunc) InitialContext.doLookup("java:module/HomeFunc");
		ejbMessage = homeFunc.getMessage();
		
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
}

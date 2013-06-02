package com.mgnyniuk.bean;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class HomeFunc {

	private String initString;

	@PostConstruct
	void init() {

		initString = "init String!";

	}

	public String getMessage() {
		Collections.emptyList();
		return "Message from EJB!" + initString;
	}

}

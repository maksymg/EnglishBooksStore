package com.mgnyniuk.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.ejb.UserService;

@ManagedBean
@SessionScoped
public class UserBean {
	
	private String username;
	private String password;
	private String email;
	private UserService userService;
	

	@PostConstruct
	private void init() {
		try {
			userService = (UserService) InitialContext.doLookup("java:module/UserService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String register() {
		userService.add(username, password, email);
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

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
import com.mgnyniuk.ejb.UserService;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private String username;
	private String password;
	private String email;
	private UserService userService;
	private LogService logService;

	@PostConstruct
	private void init() {
		try {
			userService = (UserService) InitialContext
					.doLookup("java:module/UserService");
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String register() {
		userService.add(username, password, email);
		logService.add(new Log("User: " + username
				+ " registered in ProgBooks ", new Timestamp((new Date()
				.getTime()))));
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

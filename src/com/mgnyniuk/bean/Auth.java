package com.mgnyniuk.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.mgnyniuk.ejb.UserService;
import com.mgnyniuk.jpa.User;

@ManagedBean
@ViewScoped
public class Auth {

	private String email;
	private String password;
	private String originalURL;
	private UserService userService;

	@PostConstruct
	public void init() {
		try {
			userService = (UserService) InitialContext
					.doLookup("java:module/UserService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		originalURL = (String) externalContext.getRequestMap().get(
				RequestDispatcher.FORWARD_REQUEST_URI);

		if (originalURL == null) {
			originalURL = externalContext.getRequestContextPath()
					+ "/home.xhtml";
		} else {
			String originalQuery = (String) externalContext.getRequestMap()
					.get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}
	}

	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {
			request.login("Max", "12345");
			User user = userService.find(email, password);
			externalContext.getSessionMap().put("user", user);
			externalContext.redirect(originalURL);
		} catch (ServletException e) {
			// Handle unknown username/password in request.login().
			context.addMessage(null, new FacesMessage("Unknown login"));
		}
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath()
				+ "/login.xhtml");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}


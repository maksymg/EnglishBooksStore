package com.mgnyniuk.bean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
import javax.servlet.http.HttpSession;

import com.mgnyniuk.ejb.LogService;
import com.mgnyniuk.ejb.UserService;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.User;

@ManagedBean
@ViewScoped
public class Auth {

	private String username;
	private String password;
	private String originalURL;
	private UserService userService;
	private LogService logService;

	@PostConstruct
	public void init() {
		try {
			userService = (UserService) InitialContext
					.doLookup("java:module/UserService");
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");
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
					+ "/faces/users/index.xhtml";
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
			request.login(username, password);
			User user = userService.findUserByName(username);
			externalContext.getSessionMap().put("user", user);
			externalContext.redirect(originalURL);
			logService.add(new Log("User: " + user.getUsername() + " login.",
					new Timestamp((new Date().getTime()))));
		} catch (ServletException e) {
			// Handle unknown username/password in request.login().
			context.addMessage(null, new FacesMessage("Unknown login"));
		}
	}

	public String logout() {
		String result = "/index?faces-redirect=true";

		User user = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			request.logout();
			logService.add(new Log(
					"User: " + (user.getUsername()) + " logout.",
					new Timestamp((new Date().getTime()))));
		} catch (ServletException e) {
			result = "/loginerror?faces-redirect=true";
		}

		HttpSession httpSession = (HttpSession) context.getExternalContext()
				.getSession(false);
		httpSession.invalidate();

		return result;
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

}

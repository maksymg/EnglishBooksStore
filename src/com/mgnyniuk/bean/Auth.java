package com.mgnyniuk.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class Auth {

	public String logout() {
		String result = "/index?faces-redirect=true";

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			request.logout();
		} catch (ServletException e) {
			result = "/loginError?faces-redirect=true";
		}

		return result;
	}
}

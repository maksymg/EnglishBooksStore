package com.mgnyniuk.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.FileUploadEvent;

import com.mgnyniuk.ejb.BookService;
import com.mgnyniuk.ejb.LogService;
import com.mgnyniuk.jpa.Log;

@ManagedBean
@SessionScoped
public class AdminBean {

	private String title;
	private String author;
	private String description;
	private Double price;
	private byte[] cover;
	private BookService bookService;
	private LogService logService;

	@PostConstruct
	private void init() {
		try {
			bookService = (BookService) InitialContext
					.doLookup("java:module/BookService");
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		cover = event.getFile().getContents();
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + "is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String add() {
		bookService.add(title, author, description, price, cover);
		logService.add(new Log("Book: " + title + " added", new java.sql.Date(
				(new Date().getTime()))));
		return "index";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

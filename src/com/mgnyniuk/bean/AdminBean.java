package com.mgnyniuk.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import com.mgnyniuk.ejb.UserService;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.User;

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
	private UserService userService;
	private List<Log> logs;
	private Date logsFromDate;
	private Date logsToDate;
	private List<User> users;

	@PostConstruct
	private void init() {
		try {
			bookService = (BookService) InitialContext
					.doLookup("java:module/BookService");
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");
			userService = (UserService) InitialContext
					.doLookup("java:module/UserService");

			logs = logService.findAllLogs();
			users = userService.findAllUsers();
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
		logService.add(new Log("Book: " + title + " added", new Timestamp(
				(new Date().getTime()))));
		return "index";
	}

	public void findLogsByPeriod() {
		logs = logService.findLogsByPeriod(
				new Timestamp(logsFromDate.getTime()),
				new Timestamp(logsToDate.getTime()));
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

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public Date getLogsFromDate() {
		return logsFromDate;
	}

	public void setLogsFromDate(Date logsFromDate) {
		this.logsFromDate = logsFromDate;
	}

	public Date getLogsToDate() {
		return logsToDate;
	}

	public void setLogsToDate(Date logsToDate) {
		this.logsToDate = logsToDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}

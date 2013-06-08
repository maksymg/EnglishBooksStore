package com.mgnyniuk.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
@NamedQueries(value = {
		@NamedQuery(name = "Log.findAll", query = "select log from Log log"),
		@NamedQuery(name = "Log.findLogsByPeriod", query = "select log from Log log where log.date between :fromDate and :toDate") })
public class Log implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String body;

	@Column
	private Timestamp date;

	public Log() {

	}

	public Log(String body, Timestamp date) {
		this.body = body;
		this.date = date;
	}

	public static List<Log> findAll(EntityManager em) {

		Query query = em.createNamedQuery("Log.findAll");

		return query.getResultList();
	}

	public static List<Log> findLogsByPeriod(EntityManager em,
			Timestamp fromDate, Timestamp toDate) {
		Query query = em.createNamedQuery("Log.findLogsByPeriod");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		List<Log> logList = query.getResultList();

		return logList;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}

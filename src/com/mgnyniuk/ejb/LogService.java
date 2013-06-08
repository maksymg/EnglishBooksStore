package com.mgnyniuk.ejb;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mgnyniuk.jpa.Log;

@Stateless
public class LogService {
	
	public void add(Log log) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(log);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	public List<Log> findAllLogs() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Log> logs = Log.findAll(em);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return logs;
	}
	
	public List<Log> findLogsByPeriod(Timestamp fromDate, Timestamp toDate) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(
				"em", map);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Log> logs = Log.findLogsByPeriod(em, fromDate, toDate);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return logs;
	}

}

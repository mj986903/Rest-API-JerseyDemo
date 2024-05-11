package com.mohit.other;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class AlienRepository {
	private static Session getSession() {
		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry registery = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).configure().build();
		SessionFactory sf = con.buildSessionFactory(registery);
		Session s = sf.openSession();
		return s;
	}
	public List<Alien> getAliens(){
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from Alien");
		List<Alien> list = q.list();
		tx.commit();
		return list;
	}
	public Alien getAlien(int id){
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		return s.get(Alien.class, id);
	}
	public void create(Alien a) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		s.persist(a);
		tx.commit();
	}
	public Alien deleteAlien(int id) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		Alien a = s.get(Alien.class, id);
		s.delete(a);
		tx.commit();
		return a;
	}
}

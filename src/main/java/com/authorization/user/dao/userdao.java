package com.authorization.user.dao;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.authorization.user.entity.user;

@Repository("userdao")
public class userdao {

	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public Object saveOrUpdate(Object obj) {
		
		Session session =sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			session.flush();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return obj;
		
	}
	
	
	
public String deleteRecord(user obj) {
		
		Session session =sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.delete(obj);
			session.flush();
			tx.commit();
		}catch(Error e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return "deleted";
	}


public List<Object> getUserList() {
	
	Session session =sessionFactory.openSession();
	List<Object> list1=null;
	try {
		Transaction tx = session.beginTransaction();
		 list1 = session.createCriteria(user.class).list();
		session.flush();
		tx.commit();
	}catch(Error e) {
		e.printStackTrace();
		
	}finally {
		session.close();
	}
	return list1;
}


public List<Object> getUserListByParam(DetachedCriteria dc) {
	
	Session session =sessionFactory.openSession();
	List<Object> list=null;
	try {
		Transaction tx = session.beginTransaction();
		 list = dc.getExecutableCriteria(session).list();
		session.flush();
		tx.commit();
	}catch(Error e) {
		e.printStackTrace();
		
	}finally {
		session.close();
	}
	return list;
}

public Object getUserListByusername(String username) {
	
	Session session =sessionFactory.openSession();
	Object obj = null;
	
	DetachedCriteria dc =  DetachedCriteria.forClass(user.class);
	dc.add(Restrictions.eq("username", username));
	try {
		Transaction tx = session.beginTransaction();
		 obj = dc.getExecutableCriteria(session);
		session.flush();
		tx.commit();
	}catch(Error e) {
		e.printStackTrace();
		
	}finally {
		session.close();
	}
	return obj;
}

	
}

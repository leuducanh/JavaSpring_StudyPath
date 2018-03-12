package edu.hanoi.service.springservice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hanoi.service.springservice.dao.UserDAO;
import edu.hanoi.service.springservice.dao.model.User;


@Component("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory  sessionFactory;
	
	@Override
	public List<User> list() {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from User order by age desc");
			
			return (List<User>)query.getResultList();
		}finally {
			session.close();
		}
	}

	@Override
	public User get(String username) {
	
		Session session = sessionFactory.openSession();
		try{
			
			return session.get(User.class, username);
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(String name) {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			User user = session.get(User.class, name);
			if(user!=null) session.delete(user);
			session.flush();
			session.getTransaction().commit();
		}finally {
			session.close();
		}
	}

	@Override
	public void update(User user) {

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			user = (User) session.merge(user);
			session.update(user);
			session.getTransaction().commit();
			
		}finally {
			session.close();
		}
	}
	
	
}

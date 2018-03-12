package edu.hanoi.service.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;

@Component("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
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

}

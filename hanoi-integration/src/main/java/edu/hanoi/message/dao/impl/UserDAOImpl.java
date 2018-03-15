package edu.hanoi.message.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanoi.message.dao.GroupDAO;
import edu.hanoi.message.model.Group;
import edu.hanoi.message.model.User;
import edu.hanoi.message.service.UserService;
@Service("userDAOImpl")
public class UserDAOImpl {

	
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	public GroupDAO groupDAO;

	
	public String insert(User user) {
		Session session = null;
		try{
			session =  sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
		}finally {
			session.close();
		}
		
		return user.getUsername();
	}
	
	
	public List<User> query(String username) {
		Session session = null;
		try{
			session =  sessionFactory.openSession();
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.like("username", username,MatchMode.START));
			
			return Collections.checkedList(cr.list(), User.class);
		}finally {
			session.close();
		}
	}

	

	
}

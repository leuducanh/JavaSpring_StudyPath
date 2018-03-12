package edu.hanoi.service.springservice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hanoi.service.springservice.dao.GroupDAO;
import edu.hanoi.service.springservice.dao.model.Group;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Group> list() {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Group");
			
			return (List<Group>)query.getResultList();
		}finally {
			session.close();
		}
	}

	
}

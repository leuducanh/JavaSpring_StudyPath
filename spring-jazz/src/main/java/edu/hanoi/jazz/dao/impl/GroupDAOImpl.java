package edu.hanoi.jazz.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;

public class GroupDAOImpl implements GroupDAO{

	Logger Log = Logger.getLogger(GroupDAOImpl.class);
	private CriteriaBuilder builder;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	
	@Override
	public void insert(Group group) {
		
		Session session = sessionFactory.getObject().openSession();
		session.beginTransaction();
		session.save(group);
		session.flush();
		session.getTransaction().commit();
		
		
		session.close();
	}

	public LocalSessionFactoryBean getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Group> list(String groupName) {
		Session session = sessionFactory.getObject().openSession();
		if(groupName == null || groupName.length() < 1){
			javax.persistence.Query query = session.createQuery("from Group");
			try{
				
				List<Group> l =  query.getResultList();
				return l;
			}finally {
				session.close();
			}
		}else{
//			builder = session.getCriteriaBuilder();
//			CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
//			Root<Group> root = criteriaQuery.from(Group.class);
//			criteriaQuery.select(root).where(builder.like(root.get("name"),"%" + groupName + "%"));
//			javax.persistence.Query query = session.createQuery(criteriaQuery);
//			List<Group> list = query.getResultList();
//			session.close();
//			return list;
			
			javax.persistence.Query query = session.createQuery("from Group where name like :name");
			query.setParameter("name", "%"+groupName+"%");
			List<Group> list = query.getResultList();
			session.close();
			return list;
		}
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getObject().openSession();
		session.beginTransaction();
		Group group = (Group)session.get(Group.class, id);
		session.flush();
		session.getTransaction().commit();
		if(group == null) return;
		session.beginTransaction();
		session.delete(group);
		session.flush();
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public void update(Group group) {
		Session session = sessionFactory.getObject().openSession();
		session.beginTransaction();
		Log.info("dang chay group mot: " + group.getId());
		group = (Group) session.merge(group);
		Log.info("dang chay group" + group.getId());
		session.save(group);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Group get(Integer id) {
		Group g = null;
		Session session = sessionFactory.getObject().openSession();
		session.beginTransaction();
		g = session.get(Group.class, id);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
		return g;
	}
}

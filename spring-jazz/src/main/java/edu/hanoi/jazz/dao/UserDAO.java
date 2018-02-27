package edu.hanoi.jazz.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hanoi.jazz.dao.impl.UserFactory;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;

@Component("userDAO")
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private final static int SIZE_OF_PAGE = 2;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<User> page(int page){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(User.class);
		int start = (page - 1)*SIZE_OF_PAGE;
		cr.setFirstResult(start);
		cr.setMaxResults(SIZE_OF_PAGE);
		return (List<User>) cr.list();
	}

	public void insert(User user){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public List<User> listUserByNativeSQL(int age){
		Session session = sessionFactory.openSession();
		String sql = "select * from hn_user where age > :value";
		session.beginTransaction();
		NativeQuery<User> q = session.createSQLQuery(sql);
		q.addEntity(User.class);
		q.setParameter("value", age);
		session.getTransaction().commit();
		return q.list();
	}
	
	public List<User> list(){
		Session session  = sessionFactory.openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		Query q = session.createQuery(criteriaQuery);
		return q.getResultList();
	}
	
	public User getUser(String username){
		Session session  = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.list().get(0);
	}
	
	public List<User> list(Integer group){
		Session session  = sessionFactory.openSession();
		Query query;
		try{
			if(group == null || group < 0){
				query = session.createQuery("from User");
				return (List<User>)query.getResultList();
			}
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			Criteria criteria =session.createCriteria(User.class);
			criteria.add(Restrictions.eq("groupid", group));
			return (List<User>)criteria.list();
		}finally {
			session.close();
		}
//		session.beginTransaction();
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		javax.persistence.criteria.CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
//		Root<User> root = criteriaQuery.from(User.class);
//		criteriaQuery.select(root);
//		Query q = session.createQuery(criteriaQuery);
//		return q.getResultList();
	}
	
	public List<User> listAll(){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from User order by age desc");
			return (List<User>)query.getResultList();
		
		}finally {
			session.close();
		}
	}
	
	public List<User> listOlder(int age){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.gt("age", age));
			return cr.list();
		}finally {
			session.close();
		}
	}
	
	public int averageAge(){
		Session session = sessionFactory.openSession();
		try{
			Criteria cr = session.createCriteria(User.class);
			cr.setProjection(Projections.avg("age"));
			return ((Double)cr.list().get(0)).intValue();
		}finally {
			
		}
		 
	}
	
	public void delete(String name){
		Session session = sessionFactory.openSession();
		try{
			String hql = "delete from User where username like :name";
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.executeUpdate();
			session.getTransaction().commit();
		}finally {
			
		}
	}
	
	public void addBatch(){
		Transaction trans = null;
		Session session = sessionFactory.openSession();
		
		try{
			trans = session.beginTransaction();
			for(int i = 0;i < 50;i++){
				session.save(UserFactory.generate(i+1));
			}
			session.flush();
			trans.commit();
		}catch (Exception e) {
			if(trans !=null)trans.rollback();
		}
		finally {
			session.close();
		}
	}
}


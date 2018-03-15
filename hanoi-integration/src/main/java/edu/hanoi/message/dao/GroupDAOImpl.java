package edu.hanoi.message.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.cj.api.xdevapi.Collection;

import edu.hanoi.message.model.Group;


@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO{

	Logger log = Logger.getLogger(GroupDAOImpl.class);
	@Autowired
	SessionFactory sessionFatory;
	
	@Override
	public Integer add(Group group) {
		Session session = (Session) sessionFatory.openSession();

		log.info(Thread.currentThread().getName() + "--addGroup " + group.getName());
		
		try{
			session.beginTransaction();
			int i = Integer.parseInt(session.save(group).toString());
			session.getTransaction().commit();
			return new Integer(i);	
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Group> load(String name) {
		Session session = (Session) sessionFatory.openSession();

		try{
			session.beginTransaction();
			Criteria cr = session.createCriteria(Group.class);
			cr.add(Restrictions.ilike("name", name,MatchMode.START));
List<Group> g = 			new ArrayList();
g.add(new Group(1, "abc"));
			return g;
		}finally {
			session.close();
		}
	}
}

package edu.java.data.dao.impl;

import edu.hanoi.data.model.Group;
import edu.java.data.dao.GroupDAO;

public class GroupDAOImpl implements GroupDAO{

	@Override
	public String insert(Group group) {
		
		return "DAO add group" + group.getName();
	}

	
}

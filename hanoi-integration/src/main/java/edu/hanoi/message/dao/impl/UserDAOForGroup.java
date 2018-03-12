package edu.hanoi.message.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanoi.message.dao.GroupDAO;
import edu.hanoi.message.model.Group;
@Service("userDAOForGroup")
public class UserDAOForGroup {

	@Autowired
	public GroupDAO groupDAO;

	
	public List<Group> loadGroup(String name) {
		// TODO Auto-generated method stub
		return groupDAO.load(name);
	}
}

package edu.hanoi.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.hanoi.message.model.Group;
import edu.hanoi.message.model.User;


public interface UserService {
	public String insert(User user);
	public void insertGroup(Group group);
	public List<User> query(String username);
	
	public List<Group> loadGroup(String name);
}

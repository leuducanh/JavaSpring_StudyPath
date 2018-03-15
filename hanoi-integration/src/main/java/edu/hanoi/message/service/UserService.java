package edu.hanoi.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.hanoi.message.model.Group;
import edu.hanoi.message.model.User;


public interface UserService {
	public String insert(User user);
	public Integer insertGroup(Group group);
	public List<User> query(String username);
	public String addGroup(String name);
	public List<Group> loadGroup(String name);
	public String addUser(String data);
}

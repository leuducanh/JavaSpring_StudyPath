package edu.hanoi.service.springservice.dao;

import java.util.List;

import edu.hanoi.service.springservice.dao.model.User;


public interface UserDAO {
	public List<User> list();
	public User get(String username);
	public void delete(String name);
	public void update(User user);
}

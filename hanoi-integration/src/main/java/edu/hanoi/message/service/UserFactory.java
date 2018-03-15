package edu.hanoi.message.service;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.hanoi.message.model.User;

@Component
public class UserFactory {

	public User create(List<String> values){
		User user = new User();
		user.setUsername(values.get(0));
		user.setAge(1);
		user.setEmail("a");
		user.setPassword("a");
		user.setGroupid(2);
		return user;
	}
}

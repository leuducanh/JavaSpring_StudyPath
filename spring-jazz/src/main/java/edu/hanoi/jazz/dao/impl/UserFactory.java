package edu.hanoi.jazz.dao.impl;

import java.util.Random;

import edu.hanoi.jazz.dao.model.User;

public class UserFactory {
	public static User generate(int index){
		User user = new User();
		Random r = new Random();
		user.setUsername("username-random" + index);
		user.setPassword("password" + r.nextInt(10) + 1);
		user.setEmail("ten" + r.nextInt(10) + 1 + "@gmail.com");
		user.setGroupid(102);
		return user;
	}
}

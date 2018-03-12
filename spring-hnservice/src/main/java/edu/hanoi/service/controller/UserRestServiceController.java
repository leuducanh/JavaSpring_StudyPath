package edu.hanoi.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;
@RestController
public class UserRestServiceController {

	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/list/users")
	public List<User> listUser(){
		
		return userDAO.list();
	}
}

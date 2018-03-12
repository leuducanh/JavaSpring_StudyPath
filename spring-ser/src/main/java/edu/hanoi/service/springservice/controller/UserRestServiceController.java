package edu.hanoi.service.springservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.service.springservice.dao.GroupDAO;
import edu.hanoi.service.springservice.dao.UserDAO;
import edu.hanoi.service.springservice.dao.model.Group;
import edu.hanoi.service.springservice.dao.model.User;


@RestController
@RequestMapping("/")
public class UserRestServiceController {
	
	Logger log = Logger.getLogger(UserRestServiceController.class);
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private GroupDAO groupDAO;
	
	@GetMapping("/list/users")
//	@PostFilter("filterObject.username =='a'")
	@PostFilter("hasPermission(filterObject,'read')")
	public  List<User> listUser(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("abc");
		return userDAO.list();
	}
	
	@GetMapping("/list/groups")
	public Group[] listGroups(){
		return groupDAO.list().toArray(new Group[0]);
	}
	@GetMapping("/get/user/{name}")
	public User getUser(@PathVariable(name="name")String name){
		return userDAO.get(name);
	}
	@DeleteMapping("/delete/user/{name}")
	public void delUser(@PathVariable(name="name")String name){
		userDAO.delete(name);
	}
	
	@PutMapping("/update/user")
	public void updateUser(@RequestBody User user){
		userDAO.update(user);
	}
}

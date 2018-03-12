package edu.hanoi.message.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.message.model.Group;
import edu.hanoi.message.model.User;
import edu.hanoi.message.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
	
	@GetMapping(value="/user/list")
	public List<User> list(@RequestParam(value="name",required=false)String username){
		
		return userService.query(username);
	}
	
	@PostMapping(value="/user/add")
	public String add(@RequestBody User user){
		return userService.insert(user);
	}
	
	@GetMapping(value="/user/group/{name}")
	public List<Group> loadGroup(@PathVariable String name){
		return userService.loadGroup(name);
	}
	
	@PostMapping(value="/user/add/group/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insertGroup(@RequestBody Group group){
		userService.insertGroup(group);
	}
}

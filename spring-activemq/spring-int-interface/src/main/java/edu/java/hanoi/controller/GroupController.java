package edu.java.hanoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.data.model.Group;
import edu.java.hanoi.service.GroupService;



@RestController
public class GroupController {

	@Autowired
	public GroupService groupService;
	
	@PostMapping(value="/group/add")
	public String add(@RequestBody Group group){
		System.out.println("Nhan dc " + group.getName());
		return String.valueOf(groupService.create(group));
	}
}

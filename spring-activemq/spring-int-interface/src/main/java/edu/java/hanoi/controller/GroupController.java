package edu.java.hanoi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.data.model.Group;



@RestController
public class GroupController {

	@PostMapping(value="/group/add")
	public String add(@RequestBody Group group){
		System.out.println("Nhan dc " + group.getName());
		return "add group" + group.getName();
	}
}

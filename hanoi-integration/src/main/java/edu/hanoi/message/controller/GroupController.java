package edu.hanoi.message.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.message.model.Group;
import edu.hanoi.message.service.UserService;

@RestController
public class GroupController {

	
	@Autowired
	private MessageChannel groupChannel;
	
	@Autowired
	private MessageChannel executorChannel;
	
	@Autowired
	private MessageChannel publishGroupChannel;
	
	@Autowired
	private MessageChannel groupNameInputChannel;

	@Autowired
	private MessageChannel validateGroupNameInputChannel;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/group/add/{name}",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public String add(@PathVariable String name){
		return userService.addGroup(name);
	}
	
	@RequestMapping(value="/group/add",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Integer insert(@RequestBody Group group){
		return userService.insertGroup(group);
	}
	
	@RequestMapping(value="/group/say",method=RequestMethod.POST)
	public boolean say(@RequestBody Group group){
		return publishGroupChannel.send(MessageBuilder.withPayload(group).build());
	}
}

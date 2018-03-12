package edu.hanoi.message.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.message.model.Group;

@RestController
public class GroupController {

	
	@Autowired
	private MessageChannel groupChannel;
	
	@Autowired
	private MessageChannel executorChannel;
	
	@Autowired
	private MessageChannel publishGroupChannel;
	

	
	@RequestMapping(value="/group/add",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean add(@RequestBody Group group){
		return groupChannel.send(MessageBuilder.withPayload(group).build());
	}
	
	@RequestMapping(value="/group/say",method=RequestMethod.POST)
	public boolean say(@RequestBody Group group){
		return publishGroupChannel.send(MessageBuilder.withPayload(group).build());
	}
}

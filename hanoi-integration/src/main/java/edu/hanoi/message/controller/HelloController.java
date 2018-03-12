package edu.hanoi.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Channel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hanoi.message.service.HelloService;

@RestController
public class HelloController {

	@Autowired
	private MessageChannel helloChannel;
	
	@RequestMapping(value="/say")
	public boolean say(@RequestParam(value="name") String name){
		return helloChannel.send(MessageBuilder.withPayload(name).build());
	}
}

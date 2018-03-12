package edu.hanoi.message.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String say(String name){
		return "Hello " + name;
	}
}

package edu.hanoi.message.service;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.hanoi.message.model.Group;

@Service
public class GroupToStringService {

	private final Logger log = Logger.getLogger(GroupToStringService.class);
	
	public String takeString(Group group){
		log.info(Thread.currentThread().getName() + " thread local-> " + group.getName());
		return  " " +group.getName();
	}
}

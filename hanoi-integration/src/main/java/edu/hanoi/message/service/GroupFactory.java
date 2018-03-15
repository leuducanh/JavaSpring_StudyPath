package edu.hanoi.message.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.hanoi.message.model.Group;

@Component("groupFactory")
public class GroupFactory {
	Logger log = Logger.getLogger(GroupFactory.class);
	public Group create(String name){
		Group group = new Group();
		group.setName(name);
		return group;
	}
	
	public List<String> toList(String name){
		log.info("split--->" + name);
		List<String> values = new ArrayList<String>();
		Collections.addAll(values, name.split("\\,"));
		return values;
	}
	
	public boolean validate(String name){
		log.info("-->" + name);
		return name.length() > 5 && Character.isLetter(name.charAt(0));
		
	}
	
	public String routeGroup(String name){
		if(name.length() > 5){
			return "dataChannel";
		}else if(name.length() > 1){
			return "validateGroupNameInputChannel";
		}
		return "stdoutChannel";
	}
}

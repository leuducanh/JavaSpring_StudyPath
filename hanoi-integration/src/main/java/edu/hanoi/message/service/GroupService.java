package edu.hanoi.message.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import edu.hanoi.message.dao.GroupDAO;
import edu.hanoi.message.model.Group;

@Service
public class GroupService {
	
	Logger log = Logger.getLogger(GroupService.class);
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private MessageChannel dataChannel;
	
	private List<Group> groups;
	
	public Group add(Group group){
		log.info("add group " + group.getName());
		return group;
	}
	
	public void validate(Group group){
		log.info("validate " + group.getName());
		dataChannel.send(MessageBuilder.withPayload(group).build());	
	}
	
	public void handleMessage(List<Group> groups){
		this.groups = groups;
		groups.forEach(group->{
			System.out.println(" " + group.getName());
		});
	}
	
	public String groupToStringService(Group group){
		return group.getName() + group.getId();
	}
	
	public List<Group> loadGroup(String username){
		return groupDAO.load(username);
	}
}

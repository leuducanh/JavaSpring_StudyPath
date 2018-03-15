package edu.java.hanoi.service.iplm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import edu.hanoi.data.model.Group;
import edu.java.hanoi.service.GroupService;

public class GroupServiceImpl implements GroupService{

	@Autowired
	MessageChannel groupChannel;
	
	@Override
	public boolean create(Group group) {
		
		return groupChannel.send(MessageBuilder.withPayload(group).build());
	}

}

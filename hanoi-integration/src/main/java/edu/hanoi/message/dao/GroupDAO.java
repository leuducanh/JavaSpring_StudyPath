package edu.hanoi.message.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.hanoi.message.model.Group;

public interface GroupDAO {
	public int add(Group group);
	public List<Group> load(String name);
}
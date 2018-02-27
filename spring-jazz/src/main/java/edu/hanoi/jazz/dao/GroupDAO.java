package edu.hanoi.jazz.dao;

import java.util.List;

import edu.hanoi.jazz.dao.model.Group;

public interface GroupDAO {

	public Group get(Integer id);
	public void insert(Group group);
	public List<Group> list(String groupName);
	public void delete(int id);
	public void update(Group group);
}

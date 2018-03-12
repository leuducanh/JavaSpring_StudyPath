package edu.hanoi.data.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.hanoi.data.model.Group;

public interface GroupRepository extends CrudRepository<Group,Long>{

	public List<Group> findByName(String name);
}

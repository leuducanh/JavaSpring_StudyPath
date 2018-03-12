package edu.hanoi.data.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.hanoi.data.model.Group;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class GroupRepositoryTest {

	@Autowired GroupRepository repository;
	
	@Test
	public void findByName(){
		Group group = new Group(1,"Group-12345");
		group = repository.save(group);
		List<Group> groups = repository.findByName("123");
		
		Assert.assertTrue(groups!=null);
		Assert.assertEquals(groups.size(), 1);
	}
}

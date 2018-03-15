package edu.java.hanoi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import edu.hanoi.data.model.Group;

public class GroupClientTest {

	public static void main(String[] args) {
		String address = "http://localhost:8080/group/add";
		
		RestTemplate restTemplate = new RestTemplate();
		Group group = new Group();
		group.setName("Group-Test");
		ResponseEntity<String> res =  restTemplate.postForEntity(address, group, String.class);
		System.out.println("From server" + res.getBody());
	}
}

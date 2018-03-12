package edu.hanoi.service.springservice.test;

import java.util.List;
public class SpringServiceClientTests{
	
}
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import edu.hanoi.service.springservice.dao.model.User;
//
//@RunWith(JUnit4.class)
//
//public class SpringServiceClientTests {
//
//	private RestTemplate restTemplate;
//	
//	@Before
//	public void setUp(){
//		HttpClientBuilder builder = HttpClientBuilder.create();
//		HttpClient httpClient = builder.build();
//		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
//	}
//	
//	@Test
//	public void contextLoads(){
////		String address = "http://localhost:8080/list/users/";
////		ResponseEntity<List> entite = restTemplate.getForEntity(address, List.class);
////		List<User> users = entite.getBody();
////		Assert.assertEquals(true, users.size() > 0);
////		String address = "http://localhost:8080/list/groups/";
////		ResponseEntity<Group[]> groupsen = restTemplate.getForEntity(address, Group[].class);
////		Group[] groups = groupsen.getBody();
////		
////		Assert.assertTrue(groups.length > 0);
////		
////		for(int i = 0;i <groups.length;i++){
////			System.out.println(groups[i].getId());
//		
////		}
////		String address = "http://localhost:8080/get/user/test3";
////		ResponseEntity<User> user = restTemplate.getForEntity(address, User.class);
////		Assert.assertEquals("123456", user.getBody().getPassword());
//		
////		String address = "http://localhost:8080/delete/user/test3";
////		restTemplate.delete(address);
////		address = "http://localhost:8080/get/user/test3";
////		ResponseEntity<User> getUser = restTemplate.getForEntity(address, User.class);
////		Assert.assertEquals(null, getUser.getBody());
////		
//		String address = "http://localhost:8080/get/user/test2";
//		ResponseEntity<User> getEn = restTemplate.getForEntity(address, User.class);
//		User user = (User)getEn.getBody();
//		user.setPassword("111");
//		address = "http://localhost:8080/update/user/";
//		HttpEntity<User> request = new HttpEntity<>(user);
//		restTemplate.exchange(address	, HttpMethod.PUT,request,Void.class);
//		
//		address = "http://localhost:8080/get/user/test2/";
//		ResponseEntity<User> getEn2 = restTemplate.getForEntity(address, User.class);
//		Assert.assertEquals(user.getPassword(), getEn2.getBody().getPassword());
//		
//		
//	}
//}

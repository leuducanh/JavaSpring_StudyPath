package edu.hanoi.message.test;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import edu.hanoi.message.model.Group;
import edu.hanoi.message.model.User;

public class ClassClientTest {

	public static void main(String[] args) {
		String address = "http://localhost:10526/group/add/aaaa987,bbbb877";
		
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = builder.build();
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
		
//		for(int i = 0;i < 20;i++){
//			Group group = new Group(900,"hhh" + i);
//			ResponseEntity<String> insertEntity = restTemplate.postForEntity(address, group, String.class);
//			System.out.println("From server " + insertEntity.getBody());
//
//		}
//		User user = new User("sweff", "a", "sd", 12, 2);
//		for(int i= 0;i<20;i++){
//			Group g = new Group(1,"uoa" + i);
			ResponseEntity<String> insertEntity = restTemplate.getForEntity(address, null, String.class);
//			System.out.println("From server " + insertEntity.getBody());
//		}
		
	}
}

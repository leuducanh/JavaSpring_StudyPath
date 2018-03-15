package edu.java.data.springintdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config-data.xml")
public class SpringIntDataApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringIntDataApplication.class, args);
	}
}

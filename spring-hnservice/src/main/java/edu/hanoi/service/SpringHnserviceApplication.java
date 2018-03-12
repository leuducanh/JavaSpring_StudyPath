package edu.hanoi.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.hanoi.service")
public class SpringHnserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHnserviceApplication.class, args);
	}
}

package edu.hanoi.springclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("edu.hanoi")
public class SpringClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClassApplication.class, args);
	}
}

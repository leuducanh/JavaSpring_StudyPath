package edu.java.hanoi.springintinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config-interface.xml")
public class SpringIntInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntInterfaceApplication.class, args);
	}
}

package edu.hanoi.service.springservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@SpringBootApplication
@ComponentScan(basePackages = {"edu.hanoi.service.springservice"} )
public class SpringServiceApplication {

	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		 ConfigurableApplicationContext ctx = SpringApplication.run(SpringServiceApplication.class, args);
		 ctx.start();
	}
}

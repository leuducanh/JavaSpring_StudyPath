package edu.hanoi.service.springservice;

import java.sql.DriverManager;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages = {"edu.hanoi.service.springservice"} )
public class AppConfig extends WebMvcConfigurerAdapter{
	@Resource
	public Environment env;
	

	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Bean
	public DataSource dataSource(){
		String url = env.getProperty("jdbc.url");
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
		dataSource.setUrl("jdbc:mysql://localhost:3306/sampledb");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("Leu1234567");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("edu.hanoi.service.springservice.dao.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	
	public Properties hibernateProperties(){
		Properties p = new Properties();
		p.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		p.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		return p;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}

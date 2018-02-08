package thaythuan.springjdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
		StudentJdbcDAO jdbc = (StudentJdbcDAO) cxt.getBean("studentJdbcDAO");
		
		jdbc.save("Q", 1);
	}
}

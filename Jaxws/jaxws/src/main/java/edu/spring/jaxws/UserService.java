package edu.spring.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

@WebService
@Component
public class UserService {

	@WebMethod(operationName="say")
	public String sayHello(String name){
		return "Hello java" + name;
	}
}

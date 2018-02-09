package edu.java.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;

@Controller
@RequestMapping
public class ClazzController {
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@RequestMapping(value="/clazz/xml",produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody JavaClazz viewInXml(){
		return new JavaClazz(studentDAO.list("a"));
	}
}

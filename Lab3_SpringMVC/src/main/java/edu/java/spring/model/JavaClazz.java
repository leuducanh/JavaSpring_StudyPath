package edu.java.spring.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="clazz")
public class JavaClazz {

	private List<Student> students;
	
	public JavaClazz() {
		super();
	}

	public JavaClazz(List<Student> students) {
		super();
		this.students = students;
	}

	@XmlElements(@XmlElement(name="student",type=Student.class))
	public List<Student> getStudents(){
		return students;
	}
}

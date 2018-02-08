package edu.java.spring.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.java.spring.model.Student;

@Controller
public class StudentController {

	@RequestMapping(value = "student/add",method = RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("student.form","command",new Student());
	}
	
	@RequestMapping(value = "student/save",method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") Student student,BindingResult result){
		ModelAndView mv = new ModelAndView("student.form","command",student);
		if(result.hasErrors()){
			mv.addObject("errors",result);
			return mv;
		}
		
		return mv;
	}
}

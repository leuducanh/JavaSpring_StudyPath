package edu.hanoi.jazz.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

	
	
	@GetMapping(value = "")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	
	@GetMapping(value = "/dang-nhap")
	public ModelAndView login(@RequestParam(value="error",required=false)String error){
		ModelAndView mv = new ModelAndView("login");
		if(error!=null) mv.addObject("error","sai ten dang nhap mk!");
		return mv;
	}
	
	
	@GetMapping(value = "/nguoi-dung")
	public ModelAndView forUser(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message","Protected Page");
		
		return mv;
	}
}

package edu.hanoi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {
	private final static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message","hello");
		logger.info("Da truy cap");
		return mv;
	}
	@GetMapping(value = "/login")
	public ModelAndView login(@RequestParam(value="error",required=false)String error){
		
		ModelAndView mv = new ModelAndView("login");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(error!=null) mv.addObject("error","quen ml hoac tendnhap");
		return mv;
	}
	@GetMapping(value = "/log")
	public ModelAndView login1(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("log");
		return mv;
	}
	
	@RequestMapping(value = "/403",method = RequestMethod.GET)
	public ModelAndView page403(){
		ModelAndView mv = new ModelAndView("403");
		return mv;
	}
	
	@RequestMapping(value = "/nguoi-dung",method=RequestMethod.GET)
	public ModelAndView forUser(){
		ModelAndView mv = new ModelAndView("index");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		mv.addObject("message", "hello user" + auth.getName());
		return mv;
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		logger.info("logout activate");
		return "redirect:/";
	}
}

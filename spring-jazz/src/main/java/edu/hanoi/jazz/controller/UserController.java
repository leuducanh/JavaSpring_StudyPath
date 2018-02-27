package edu.hanoi.jazz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;

@Controller
public class UserController {

	Logger LOGGER =Logger.getLogger(UserController.class);
	@Autowired
	private GroupDAO groupDAO;
	@Autowired
	private UserDAO userDAO;
	
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	@PostMapping(value="/aluu")
	public ModelAndView addNew(@Valid @ModelAttribute(value="command")User user,BindingResult result){
		ModelAndView mv = null;
		LOGGER.info("uesrname day" + user.getUsername());
		if(result.hasErrors()){
			mv = new ModelAndView();
			mv.addObject("groups",toGroupMap(groupDAO.list(null)));
			mv.addObject("errors",result);
		}else{
			userDAO.insert(user);
		}
		
		mv = new ModelAndView("redirect:/athem");
		return mv;
	}

	@GetMapping(value="/athem")
	public ModelAndView addForm(){
		ModelAndView mv = new ModelAndView("user.form");
		mv.addObject("command",new User("","","",0,1));
		mv.addObject("groups",toGroupMap(groupDAO.list(null)));
		return mv;
	}
	
	@GetMapping(value = "/adanh-sach/page")
	public ModelAndView list(@RequestParam(value="age",required=false)Integer age ){
		if(age == null)age = 0;
		ModelAndView mv =  new ModelAndView("user.list","users",userDAO.page(1));
		mv.addObject("avg",userDAO.averageAge());
		return mv;
	}
	
	@GetMapping(value = "/adanh-sach/native")
	public ModelAndView listNative(@RequestParam(value="age",required=false)Integer age){
		if(age == null) age = 0;
		ModelAndView mv =  new ModelAndView("user.list","users",userDAO.listUserByNativeSQL(age));
		mv.addObject("avg",userDAO.averageAge());
		return mv;
	}
	
	@GetMapping(value = "/chitiet/{name}")
	public ModelAndView userdetail(@PathVariable String name){
		return new ModelAndView("user.detail","user",userDAO.getUser(name));
	}
	
	@GetMapping(value = "/axoa/{name}")
	public ModelAndView delete(@PathVariable(name="name")String name){
		userDAO.delete(name);
		return new ModelAndView("redirect:/adanh-sach/native");
	}
	
	@GetMapping(value = "/them-nhieu")
	public String addManyUsers(){
		 userDAO.addBatch();
		 return "redirect:/adanh-sach/native";
	}
	
	
	private Map toGroupMap(List<Group> list) {
		Map<Integer,String> map = new HashMap<>();
		list.forEach(group->{
			map.put(group.getId(), group.getName());
		});
		return map;
	}
}


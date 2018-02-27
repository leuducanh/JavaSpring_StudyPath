package edu.hanoi.jazz.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller

public class GroupController {

	private static final Logger logger = Logger.getLogger(GroupController.class);
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(value = "/them")
	public ModelAndView home(){
		return new ModelAndView("group.form","command",new Group());
	}
	
	@GetMapping(value = "/danh-sach")
	public ModelAndView list(@RequestParam(value="q",required=false)String groupName){
		ModelAndView mv = new ModelAndView("group.list");
		mv.addObject("groups",groupDAO.list(groupName));
		
		return mv;
	}
	
	@PostMapping(value = "/luu")
	public ModelAndView addNew(@Valid @ModelAttribute("command")Group group,BindingResult result){
		ModelAndView model = new ModelAndView("redirect:/danh-sach");
		
		if(result.hasErrors()){
			model.addObject("errors",result);
			return model;
		}
		logger.info("dang vao" + group.getId() + " " + group.getName());
		if(group.getId() > 0){
			groupDAO.update(group);
		}else{
			groupDAO.insert(group);			
		}
		
		logger.info("add new group --> " + group);
		return model;
	}
	
	
	
	@GetMapping(value = "/xoa/{id}")
	public ModelAndView delete(@PathVariable(value = "id")Integer id){
		if(id != null)groupDAO.delete(id);
		return new ModelAndView("redirect:/danh-sach");
	}
	
	@GetMapping(value = "/sua")
	public ModelAndView updateForm(@RequestParam(value = "id",required = true)int id){
		Group g = groupDAO.get(id);
		if(g==null){
			return new ModelAndView("redirect:/danh-sach");
		}
		return new ModelAndView("group.form","command",g);
	}

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
}

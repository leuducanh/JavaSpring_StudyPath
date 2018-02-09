package edu.java.spring.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

@Controller
public class StudentController {

	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping(value = "student/add",method = RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("student/student.form","command",new Student());
	}
	
	@RequestMapping(value = "student/save",method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") Student student,BindingResult result){
		ModelAndView mv = new ModelAndView("student/student.view","command",student);
		if(result.hasErrors()){
			mv.addObject("errors",result);
			return mv;
		}
		
		if(student.getId()>0){
			studentDAO.update(student);
		}else{
			studentDAO.insert(student);
		}
		return mv;
	}
	
	@RequestMapping(value = "student/list",method=RequestMethod.GET)
	public ModelAndView listStudents(@RequestParam(value = "q",required = false)String query){
		return new ModelAndView("student/student.list","students",studentDAO.list(query));
	}
	
	@RequestMapping(value = "student/delete/{id}")
	public String delete(@PathVariable(name = "id")int id){
		studentDAO.delete(id);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "student/edit/{id}")
	public ModelAndView edit(@PathVariable(name="id")int id){
		Student student = studentDAO.get(id);
		ModelAndView mv = new ModelAndView("student/student.form","command",student);
		return mv;
	}
	
	@RequestMapping(value = "student/edit/save",method = RequestMethod.POST)
	public ModelAndView saveEdit(@ModelAttribute("command") Student student){
		ModelAndView mv = new ModelAndView("forward:/student/save","command",student);
		return mv;
	}
	
	@RequestMapping(value="/student/json/{id}",method = RequestMethod.GET)
	public @ResponseBody Student model(@PathVariable(name="id")int id){
		return studentDAO.get(id);
	}
	
	@RequestMapping(value="/student/avatar/save",method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file,@RequestParam(value="id")int id,HttpServletRequest request){
		if(file.isEmpty()) return  "/student.error";
		
		ServletContext ctx = request.getSession().getServletContext();
		Path avatarPath = getImagePath(request, id);
		try {
			Files.write(avatarPath, file.getBytes(),StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/student/avatar/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable(value = "id")Integer id,HttpServletRequest request){
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		if(id != null){
			Path avatarPath = getImagePath(request, id);
			if(Files.exists(avatarPath))
				try {
					byteOutput.write(Files.readAllBytes(avatarPath));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(byteOutput.toByteArray(),headers,HttpStatus.CREATED);
	}
	
	private Path getImagePath(HttpServletRequest request,int id){
		ServletContext ctx = request.getSession().getServletContext();
		String diskPath=ctx.getRealPath("/");
		File folder = new File(diskPath + File.separator + "avatar" + File.separator);
		folder.mkdirs();
		return new File(folder,String.valueOf(id)+".jpg").toPath();
	}
}


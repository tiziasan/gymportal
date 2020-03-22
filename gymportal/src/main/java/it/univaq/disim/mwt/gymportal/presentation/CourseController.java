package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("course")
public class CourseController {
	
	
	@GetMapping("/create")
    public String createStart() {
		return "/course/form";
   	 
    }
	@GetMapping("/update")
    public String updateStart() {
		return "/course/form";
   	 
    }
	@GetMapping("/list")
    public String list() {
		return "/course/list";
   	 
    }

}

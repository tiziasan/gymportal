package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseBO serviceCourse;
	
	@Autowired
	private GymBO serviceGym;
	
	
	@GetMapping("/create")
    public String createStart(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);

		return "/course/form";
   	 
    }
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("course") Course course, Errors errors) throws BusinessException {
		
		
		serviceCourse.createCourse(course);
		return "redirect:/course/list";
	}
	
	@GetMapping("/update")
    public String updateStart() {
		return "/course/form";
   	 
    }
	@GetMapping("/list")
    public String list() {
		return "/course/list";
   	 
    }
	
	@ModelAttribute
	public void addAllAree(Model model) throws BusinessException {
		List<Gym> gyms = serviceGym.findAllGym();
		model.addAttribute("gyms", gyms);
	}

}

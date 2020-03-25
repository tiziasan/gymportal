package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String create(@Valid @ModelAttribute("course") Course course, Errors errors, Model model) throws BusinessException {
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);

			return "/course/form";
		}
		serviceCourse.createCourse(course);
		String message = "Operazione andata a buon fine, aggiungi un altro corso!";
		model.addAttribute("message", message);
		return "/course/form";
	}
	
	@GetMapping("/update")
    public String updateStart() {
		return "/course/form";
   	 
    }
	
	@GetMapping("/gym")
	public String listCo(@RequestParam long id, Model model) throws BusinessException {
		List<Course> courseList = serviceCourse.findCourseByGymId(id);
		Gym gym = serviceGym.findByID(id);
        model.addAttribute("courseList", courseList);
        model.addAttribute("gym", gym);

		return "/course/list";

	}
	
	@ModelAttribute
	public void addAllAree(Model model) throws BusinessException {
		List<Gym> gyms = serviceGym.findAllGym();
		model.addAttribute("gyms", gyms);
	}

}

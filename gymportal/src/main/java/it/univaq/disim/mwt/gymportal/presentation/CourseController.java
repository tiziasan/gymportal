package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import it.univaq.disim.mwt.gymportal.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private FavoriteCourseBO serviceFavoriteCourse;
	@Autowired
	private FeedbackCourseBO serviceFeedbackCourse;
	
	@Autowired
	private GymBO serviceGym;
	
	@Autowired
	private FeedbackGymBO serviseFeedbackGym;
	
	@GetMapping("/create")
    public String createStart(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);

		return "/course/form"; 
    }
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("course") Course course, Errors errors, Model model, RedirectAttributes ra)  {
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);

			return "/course/form";
		}
		serviceCourse.createCourse(course);
		String message = "Operazione andata a buon fine, aggiungi un altro corso!";
		ra.addFlashAttribute("message", message);
		return "redirect:/course/create";
	}
	
	
	@GetMapping(value= {"/gym/{id}", "/gym/{id}?search={search}"})
	public String listCo(@PathVariable long id, @RequestParam(required = false) String search, Model model)  {
		List<Course> courseList;
		if(search != null) {
			courseList=serviceCourse.searchByIdAndName(id, search);
			model.addAttribute("search", search);
		}else {
			courseList=serviceCourse.findCourseByGymId(id);
		}
		List<FeedbackGym> feedbackList = serviseFeedbackGym.findAllFeedbackByGym(id);
		Gym gym = serviceGym.findByID(id);
        model.addAttribute("courseList", courseList);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("gym", gym);

		return "/course/list";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model)  {
		Course course = serviceCourse.findByID(id);
		model.addAttribute("course", course);
		return "/course/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("course") Course course, Errors errors)  {
		Course courseComplete = serviceCourse.findByID(course.getId());

		if (errors.hasErrors()) {
			return "/common/error";
		}
		long id = courseComplete.getGym().getId();
		String redirect = "redirect:/course/gym?id=" + id;
		serviceFavoriteCourse.deleteAllByCourse(course);
		serviceFeedbackCourse.deleteAllByCourse(course);
		serviceCourse.deleteCourse(course);
		return redirect;
	}
	
	@GetMapping("/update/{id}")
	public String updateStart(@PathVariable long id, Model model)  {
		Course course = serviceCourse.findByID(id);
		model.addAttribute("course", course);
		return "/course/form";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("course") Course course , Errors errors)  {
		Course courseComplete = serviceCourse.findByID(course.getId());

		if (errors.hasErrors()) {
			return "/common/error";
		}
		long id = courseComplete.getGym().getId();
		String redirect = "redirect:/course/gym/" + id;
		serviceCourse.updateCourse(course);
		return redirect;
	}
	
	@ModelAttribute
	public void addAll(Model model)  {
		List<Gym> gyms = serviceGym.findAllGym();
		model.addAttribute("gyms", gyms);
	}
	
	

}
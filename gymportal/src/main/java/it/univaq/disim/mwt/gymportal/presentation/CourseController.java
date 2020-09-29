package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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

	@Autowired
	private  UserBO userService;
	
	@GetMapping("/create")
    public String createStart(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);

		return "/course/form"; 
    }
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("course") Course course, Errors errors, Model model, RedirectAttributes ra) throws BusinessException {
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
	public String listCo(@PathVariable long id, @RequestParam(required = false) String search, Model model) throws BusinessException {
		Set<Course> courseList;
		if(search != null) {
			courseList=serviceCourse.searchByIdAndName(id, search);
			model.addAttribute("search", search);
		}else {
			courseList=serviceCourse.findCourseByGymId(id);
		}
		Set<FeedbackGym> feedbackList = serviseFeedbackGym.findAllFeedbackByGym(id);
		Gym gym = serviceGym.findByID(id);
        model.addAttribute("courseList", courseList);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("gym", gym);

		return "/course/list";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
		Course course = serviceCourse.findByID(id);
		model.addAttribute("course", course);
		return "/course/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("course") Course course, Errors errors, Model model) throws BusinessException {
		Course courseComplete = serviceCourse.findByID(course.getId());

		if (errors.hasErrors()) {
			return "/common/error";
		}

		long id = courseComplete.getGym().getId();
		String redirect = "redirect:/course/gym?id=" + id;
		serviceFavoriteCourse.deleteAllByCourse(course);
		serviceFeedbackCourse.deleteAllByCourse(course);
		serviceCourse.deleteCourse(course);
		model.addAttribute("success", "Eliminazione del corso andata a buon fine");
		return "/index";	}
	
	@GetMapping("/update/{id}")
	public String updateStart(@PathVariable long id, Model model) throws BusinessException {
		Course course = serviceCourse.findByID(id);
		model.addAttribute("course", course);
		return "/course/form";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("course") Course course , Errors errors) throws BusinessException {
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
	public void addAll(Model model) throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
			Manager user = userService.findUserByUsername(auth.getName());
			Set<Gym> gyms = user.getGym();
			model.addAttribute("gyms", gyms);
		}
	}
	
	

}
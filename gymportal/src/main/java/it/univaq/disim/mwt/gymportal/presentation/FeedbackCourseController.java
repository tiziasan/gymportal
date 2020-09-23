
package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import javax.validation.Valid;

import it.univaq.disim.mwt.gymportal.business.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("feedbackCourse")
public class FeedbackCourseController {

	@Autowired
	private FeedbackCourseBO serviceFeedbackCourse;

	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private UserBO userService;


	@GetMapping("/{id}")
	public String listCo(@PathVariable long id, Model model){
		
		List<FeedbackCourse> feedbackList = serviceFeedbackCourse.findAllFeedbackByCourse(id);
		model.addAttribute("feedbackList", feedbackList);
		return "/feedback/list";

	}

	@GetMapping("/create/{id}")
	public String createStart(Model model,@PathVariable long id){
		Course course = serviceCourse.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("course", course);
		model.addAttribute("user", user);
		
		FeedbackCourse feedbackCourse = new FeedbackCourse();

		model.addAttribute("feedbackCourse", feedbackCourse);

		return "/feedbackCourse/form";
	} 

	@PostMapping("/create/{id}")
	public String create(@Valid @ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, Errors errors, Model model){
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedbackCourse/form";
		}
		serviceFeedbackCourse.createFeedbackCourse(feedbackCourse);
		
		long id = feedbackCourse.getCourse().getId();

		String redirect = "redirect:/feedbackCourse/" + id;

		return redirect;
	}


	@GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model){

		return "/feedback/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, Errors errors){

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFeedbackCourse.deleteFeedbackCourse(feedbackCourse);
        return "redirect:/profile";
	}


	@GetMapping("/update/{id}")
	public String updateStart(@PathVariable long id, Model model){
		FeedbackCourse feedback = serviceFeedbackCourse.findByID(id);
		model.addAttribute("feedbackCourse", feedback);
		return "/feedbackCourse/update";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse , Errors errors){

		if (errors.hasErrors()) {
			return "/common/error";
		}
		
		serviceFeedbackCourse.updateFeedbackCourse(feedbackCourse);
		return "redirect:/profile";
	}
	

}

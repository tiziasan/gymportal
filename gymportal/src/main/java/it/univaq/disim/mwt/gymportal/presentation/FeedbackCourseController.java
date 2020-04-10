
package it.univaq.disim.mwt.gymportal.presentation;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("feedbackCourse")
public class FeedbackCourseController {

	@Autowired
	private FeedbackCourseBO serviceFeedbackCourse;

	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private UserService userService;


	
	
	
	@GetMapping("/create")
	public String createStart(Model model,@RequestParam long id) throws BusinessException {
		Course course = serviceCourse.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("course", course);
		model.addAttribute("user", user);
		
		FeedbackGym feedbackCourse = new FeedbackGym();

		model.addAttribute("feedbackCourse", feedbackCourse);

		return "/feedbackCourse/form";
	} 
	

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("feedback") FeedbackCourse feedbackCourse, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedbackCourse/form";
		}
		serviceFeedbackCourse.createFeedbackCourse(feedbackCourse);
		
		long id = feedbackCourse.getCourse().getId();

		String redirect = "redirect:/feedback?id=" + id;

		return redirect;
	}
	

}


package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

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


	@GetMapping("")
	public String listCo(@RequestParam long id, Model model) throws BusinessException {
		
		List<FeedbackCourse> feedbackList = serviceFeedbackCourse.findAllFeedbackByCourse(id);
		model.addAttribute("feedbackList", feedbackList);
		return "/feedback/list";

	}
	
	
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

		String redirect = "redirect:/feedbackCourse?id=" + id;

		return redirect;
	}
	
	@GetMapping("/delete")
    public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		return "/feedback/delete";
    }
	
	@PostMapping("/delete")
	public String delete(@ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFeedbackCourse.deleteFeedbackCourse(feedbackCourse);;
		return "redirect:/profile";
	}
	
	@GetMapping("/update")
	public String updateStart(@RequestParam Long id, Model model) throws BusinessException {
		FeedbackCourse feedback = serviceFeedbackCourse.findByID(id);
		model.addAttribute("feedbackCourse", feedback);
		return "/feedbackCourse/update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse , Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		
		serviceFeedbackCourse.updateFeedbackCourse(feedbackCourse);
		return "redirect:/profile";
	}
	

}

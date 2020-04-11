
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
import it.univaq.disim.mwt.gymportal.business.FeedbackGymBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("feedback")
public class FeedbackGymController {

	@Autowired
	private FeedbackGymBO serviceFeedbackGym;

	@Autowired
	private GymBO serviceGym;

	@Autowired
	private UserService userService;


	
	
	
	@GetMapping("/create")
	public String createStart(Model model,@RequestParam long id) throws BusinessException {
		Gym gym = serviceGym.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("gym", gym);
		model.addAttribute("user", user);
		
		FeedbackGym feedback = new FeedbackGym();

		model.addAttribute("feedback", feedback);

		return "/feedback/form";
	}
	

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("feedback") FeedbackGym feedback, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedback/form";
		}
		serviceFeedbackGym.createFeedbackGym(feedback);
		
		long id = feedback.getGym().getId();

		String redirect = "redirect:/course/gym?id=" + id;

		return redirect;
	}
	
	@GetMapping("/delete")
    public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		return "/feedback/delete";
    }
	
	@PostMapping("/delete")
	public String delete(@ModelAttribute("feedback") FeedbackGym feedback, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFeedbackGym.deleteFeedbackGym(feedback);;
		return "redirect:/profile";
	}
	
	@GetMapping("/update")
	public String updateStart(@RequestParam Long id, Model model) throws BusinessException {
		FeedbackGym feedback = serviceFeedbackGym.findByID(id);
		model.addAttribute("feedback", feedback);
		return "/feedback/update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("feedback") FeedbackGym feedback , Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		
		serviceFeedbackGym.updateFeedbackGym(feedback);
		return "redirect:/profile";
	}
	

}

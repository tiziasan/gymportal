
package it.univaq.disim.mwt.gymportal.presentation;

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

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackGymBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
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
	private UserBO userService;


	@GetMapping("/create/{id}")
	public String createStart(Model model,@PathVariable long id) throws BusinessException {
		Gym gym = serviceGym.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		model.addAttribute("gym", gym);
		model.addAttribute("user", user);
		
		FeedbackGym feedback = new FeedbackGym();

		model.addAttribute("feedback", feedback);

		return "/feedback/form";
	}

	@PostMapping("/create/{id}")
	public String create(@Valid @ModelAttribute("feedback") FeedbackGym feedback, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedback/form";
		}
		serviceFeedbackGym.createFeedbackGym(feedback);
		
		long id = feedback.getGym().getId();

		String redirect = "redirect:/course/gym/" + id;

		return redirect;
	}


	@GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
		return "/feedback/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("feedback") FeedbackGym feedback, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFeedbackGym.deleteFeedbackGym(feedback);
        return "redirect:/profile";
	}


	@GetMapping("/update/{id}")
	public String updateStart(@PathVariable long id, Model model) throws BusinessException {
		FeedbackGym feedback = serviceFeedbackGym.findByID(id);
		model.addAttribute("feedback", feedback);
		return "/feedback/update";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("feedback") FeedbackGym feedback , Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		
		serviceFeedbackGym.updateFeedbackGym(feedback);
		return "redirect:/profile";
	}
	

}

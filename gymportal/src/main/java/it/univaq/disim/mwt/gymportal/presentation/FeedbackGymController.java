
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
import it.univaq.disim.mwt.gymportal.business.FeedbackGymBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
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
	public String createStart(Model model, @RequestParam long id) throws BusinessException {
		Gym gym = serviceGym.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		FeedbackGym feedback = new FeedbackGym();
		System.out.println("porcodio"+feedback);
		model.addAttribute("gym", gym);
		model.addAttribute("user", user);

		model.addAttribute("feedback", feedback);

		return "/review/form";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("feedback") FeedbackGym feedback, Errors errors, Model model)
			throws BusinessException {
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);


			return "/review/form";
		}
		serviceFeedbackGym.createFeedbackGym(feedback);
		String message = "Operazione andata a buon fine, aggiungi un altro corso!";
		return "/";
	}

}

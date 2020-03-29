package it.univaq.disim.mwt.gymportal.presentation;

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
import it.univaq.disim.mwt.gymportal.business.UserBO;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller
@RequestMapping("/registration")

public class RegistrationController {
	@Autowired
	private UserBO serviceUser;
	
	@GetMapping("")
    public String registrationStart(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "/registration/index";
   	 
    }
	
	@PostMapping("")
    public String registration(@Valid @ModelAttribute("user") User user, Errors errors, Model model) throws BusinessException {
		if (errors.hasErrors()) {
			String message = "Errore nella registrazione";
			model.addAttribute("message", message);
			return "/registration/index";
		}
		serviceUser.createUser(user);;
		return "/profile/index";
   	 
    }

}

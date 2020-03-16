package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")

public class RegistrationController {
	
	@GetMapping("")
    public String registration() {
		return "/registration/index";
   	 
    }

}

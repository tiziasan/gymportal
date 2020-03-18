package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gym")

public class GymController {
	
	@GetMapping("/create")
    public String createStart() {
		return "/gym/form";
   	 
    }

}

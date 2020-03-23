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
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Controller
@RequestMapping("gym")

public class GymController {
	
	@Autowired
	private GymBO service;
	
	@GetMapping("/create")
    public String createStart(Model model) {
		Gym gym = new Gym();
		model.addAttribute("gym", gym);
		return "/gym/form";
    }
	
	

	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("gym") Gym gym, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/gym/form";
		}
		
		service.createGym(gym);
		return "redirect:/gym/list";
	}
	
	
	@GetMapping("/update")
    public String createUpdate() {
		return "/gym/form";
   	 
    }
	@GetMapping("/list")
    public String list() {
		return "/region/index";
   	 
    }

}

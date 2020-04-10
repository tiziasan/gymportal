package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;

@Controller
@RequestMapping("region")

public class RegionController {

	@Autowired
	private GymBO serviceGym;

	@GetMapping("/{region}")
	public String listG(@PathVariable String region, Model model) throws BusinessException {
		List<Gym> gymList = serviceGym.findByRegion(region);
		model.addAttribute("region", region);
		model.addAttribute("gymList", gymList);
		return "/region/index";
	}

}
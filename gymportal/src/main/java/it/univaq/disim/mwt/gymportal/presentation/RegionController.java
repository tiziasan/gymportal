package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("region")
@Slf4j
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
	
	@PostMapping("/{region}")
	public String listSearch(@PathVariable String region, @RequestParam String search, Errors errors,  Model model) throws BusinessException{
		List<Gym> gymList = serviceGym.searchByRegionAndName(region, search);
		model.addAttribute("region", region);
		if(search != "") {
		model.addAttribute("search", search);
		}
		model.addAttribute("gymList", gymList);
		if(errors.hasErrors()) {
			return "/region/index";
		}
		return "/region/index";
	}
	

}
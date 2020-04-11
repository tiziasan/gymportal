package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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


	//https://stackoverflow.com/questions/60528613/rest-api-with-mix-of-path-param-and-requestparam
	@GetMapping(value = {"/{region}","/{region}?search={search}"})
	public String listGym(@PathVariable String region, @RequestParam(required = false) String search, Model model) throws BusinessException{
		List<Gym> gymList;
		if (search != null){
			gymList = serviceGym.searchByRegionAndName(region,search);
			model.addAttribute("search", search);
		} else {
			gymList = serviceGym.findByRegion(region);
		}
		model.addAttribute("region", region);
		model.addAttribute("gymList", gymList);
		return "/region/index";
	}

}
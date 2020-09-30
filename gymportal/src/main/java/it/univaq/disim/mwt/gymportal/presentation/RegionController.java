package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.Manager;
import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("region")
public class RegionController {

	@Autowired
	private GymService serviceGym;

	@Autowired
	private UserService userService;

	// https://stackoverflow.com/questions/60528613/rest-api-with-mix-of-path-param-and-requestparam
	@GetMapping(value = { "/{region}", "/{region}?search={search}" })
	public String listGym(@PathVariable String region, @RequestParam(required = false) String search, Model model)
			throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Set<Gym> gymList = null;

		if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
			Manager user = userService.findUserByUsername(auth.getName());
			gymList = serviceGym.searchByRegionAndUser(region,user);
		}

		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")) ||
				auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))) {
			gymList = serviceGym.findByRegion(region);
		}

		if (search != null &&
				( auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER)) ||
				auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) ) {
			gymList = serviceGym.searchByRegionAndName(region, search);
			model.addAttribute("search", search);
		}

		if (search != null && auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
			User user = userService.findUserByUsername(auth.getName());
			long id = user.getId();
			gymList = serviceGym.searchByRegionAndNameAndUser(region, search, user);
			model.addAttribute("search", search);
		}

		model.addAttribute("region", region);
		model.addAttribute("gymList", gymList);
		return "/region/index";
	}

}
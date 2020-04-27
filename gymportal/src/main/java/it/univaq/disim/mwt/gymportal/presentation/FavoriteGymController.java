
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
import it.univaq.disim.mwt.gymportal.business.FavoriteGymBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("favoriteGym")
public class FavoriteGymController {

	@Autowired
	private FavoriteGymBO serviceFavoriteGym;

	@Autowired
	private GymBO serviceGym;

	@Autowired
	private UserService userService;


	
	
	
	@GetMapping("/create")
	public String createStart(Model model,@RequestParam long id) throws BusinessException {
		Gym gym = serviceGym.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("gym", gym);
		model.addAttribute("user", user);
		
		FavoriteGym favoriteGym = new FavoriteGym();

		model.addAttribute("favoriteGym", favoriteGym);

		return "/favoriteGym/create";
	}
	

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("favoriteGym") FavoriteGym favoriteGym, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedback/form";
		}
		serviceFavoriteGym.createFavoriteGym(favoriteGym);
		

		String redirect = "redirect:/profile";

		return redirect;
	}
	
	@GetMapping("/delete")
    public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		return "/favoriteGym/delete";
    }
	
	@PostMapping("/delete")
	public String delete(@ModelAttribute("favoriteGym") FavoriteGym favoriteGym, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFavoriteGym.deleteFavoriteGym(favoriteGym);
		return "redirect:/profile";
	}
	
	

}

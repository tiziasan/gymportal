
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
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.FavoriteCourseBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("favoriteCourse")
public class FavoriteCourseController {

	@Autowired
	private FavoriteCourseBO serviceFavoriteCourse;

	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private UserService userService;


	
	
	
	@GetMapping("/create")
	public String createStart(Model model,@RequestParam long id) throws BusinessException {
		Course course = serviceCourse.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("course", course);
		model.addAttribute("user", user);
		
		FavoriteCourse favoriteCourse = new FavoriteCourse();

		model.addAttribute("favoriteCourse", favoriteCourse);

		return "/favoriteCourse/create";
	}
	

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedback/form";
		}
		serviceFavoriteCourse.createFavoriteCourse(favoriteCourse);
		

		String redirect = "redirect:/profile";

		return redirect;
	}
	
	@GetMapping("/delete")
    public String deleteStart(@RequestParam Long id, Model model) throws BusinessException {
		return "/feedback/delete";
    }
	
	@PostMapping("/delete")
	public String delete(@ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFavoriteCourse.deleteFavoriteCourse(favoriteCourse);
		return "redirect:/profile";
	}
	
	

}

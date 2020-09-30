
package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.FavoriteBO;
import it.univaq.disim.mwt.gymportal.business.UserBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

@RequestMapping("favoriteCourse")
public class FavoriteCourseController {

	@Autowired
	private FavoriteBO serviceFavoriteCourse;

	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private UserBO userService;

	@GetMapping("/create/{id}")
	public String createStart(@PathVariable long id, Model model) throws BusinessException {
		Course course = serviceCourse.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer user = userService.findUserByUsername(auth.getName());
		model.addAttribute("course", course);
		model.addAttribute("user", user);
		
		FavoriteCourse favoriteCourse = new FavoriteCourse();

		model.addAttribute("favoriteCourse", favoriteCourse);
		return "/favoriteCourse/create";
	}

	@PostMapping("/create/{id}")
	public String create(@Valid @ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors, Model model)
			throws BusinessException {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "redirect:/profile";
		}
		System.out.println(favoriteCourse);

		serviceFavoriteCourse.createFavoriteCourse(favoriteCourse);
		

		String redirect = "redirect:/profile";

		return redirect;
	}

	@GetMapping("/delete/{id}")
	public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
		return "/favoriteCourse/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors) throws BusinessException {

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFavoriteCourse.deleteFavoriteCourse(favoriteCourse);
		return "redirect:/profile";
	}
	
	

}

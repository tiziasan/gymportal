
package it.univaq.disim.mwt.gymportal.presentation;

import javax.validation.Valid;

import it.univaq.disim.mwt.gymportal.business.UserBO;
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
import org.springframework.web.bind.annotation.PathVariable;

import it.univaq.disim.mwt.gymportal.business.CourseBO;
import it.univaq.disim.mwt.gymportal.business.FavoriteCourseBO;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.User;

@Controller

@RequestMapping("favoriteCourse")
public class FavoriteCourseController {

	@Autowired
	private FavoriteCourseBO serviceFavoriteCourse;

	@Autowired
	private CourseBO serviceCourse;

	@Autowired
	private UserBO userService;

	@GetMapping("/create/{id}")
	public String createStart(@PathVariable long id, Model model){
		Course course = serviceCourse.findByID(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("course", course);
		model.addAttribute("user", user);
		
		FavoriteCourse favoriteCourse = new FavoriteCourse();

		model.addAttribute("favoriteCourse", favoriteCourse);

		return "/favoriteCourse/create";
	}

	@PostMapping("/create/{id}")
	public String create(@Valid @ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			String message = "Errore nell'inserimento";
			model.addAttribute("message", message);
			return "/feedback/form";
		}
		serviceFavoriteCourse.createFavoriteCourse(favoriteCourse);
		

		String redirect = "redirect:/profile";

		return redirect;
	}

	@GetMapping("/delete/{id}")
	public String deleteStart(@PathVariable long id, Model model){
		return "/favoriteCourse/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors){

		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceFavoriteCourse.deleteFavoriteCourse(favoriteCourse);
		return "redirect:/profile";
	}
	
	

}

package it.univaq.disim.mwt.gymportal.presentation;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import it.univaq.disim.mwt.gymportal.business.*;
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
import org.springframework.web.servlet.ModelAndView;

import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.domain.Chat;


@RequestMapping("/profile")

@Controller
public class ProfileController {
	
	@Autowired
	private UserBO userService;
	
	@Autowired
	private FeedbackCourseBO serviceFeedbackCourse;
	
	@Autowired
	private FeedbackGymBO serviceFeedbackGym;
	
	@Autowired
	private FavoriteGymBO serviceFavoriteGym;
	
	@Autowired
	private FavoriteCourseBO serviceFavoriteCourse;

	@Autowired
	private ChatBO serviceChat;
	
	
	@GetMapping("")
	public ModelAndView home(Model model) throws BusinessException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Set<FeedbackCourse> feedbackCourseList = serviceFeedbackCourse.findAllFeedbackByUserId(user.getId());
		Set<FeedbackGym> feedbackGymList = serviceFeedbackGym.findAllFeedbackByUserId(user.getId());
		Set<FavoriteGym> favoriteGymList = serviceFavoriteGym.findAllFavoriteByUserId(user.getId());
		Set<FavoriteCourse> favoriteCourseList = serviceFavoriteCourse.findAllFavoriteByUserId(user.getId());

		model.addAttribute("user",user);
		model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
		model.addAttribute("feedbackCourseList",feedbackCourseList);
		model.addAttribute("feedbackGymList",feedbackGymList);
		model.addAttribute("favoriteGymList",favoriteGymList);
		model.addAttribute("favoriteCourseList",favoriteCourseList);

		modelAndView.setViewName("/profile/index");
		return modelAndView;
	}
	
	@GetMapping("/update")
	public String updateStart(Model model) throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("user", user);
		return "/profile/update";
		
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("user") User user , Errors errors) throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (errors.hasErrors()) {
			return "/common/error";
		}

		if(auth.toString().contains("utente")) {
			userService.updateUser(user);
		}
		if(auth.toString().contains("gestore")) {
			userService.updateGestore(user);
		}

		List<Chat> chatList = serviceChat.findByUserId(user.getId());
		for ( Chat c: chatList ) {
			c.setUserName(user.getName() + " " + user.getLastName());
		}
		serviceChat.saveAllChats(chatList);

		return "redirect:/login";

	}
	
}

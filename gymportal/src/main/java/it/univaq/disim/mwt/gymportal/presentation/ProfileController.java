package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

		User user;

		Set<FeedbackCourse> feedbackCourseList = new HashSet<>();
		Set<FeedbackGym> feedbackGymList = new HashSet<>();
		Set<FavoriteGym> favoriteGymList = new HashSet<>();
		Set<FavoriteCourse> favoriteCourseList = new HashSet<>();

		if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))){
			Customer customer = userService.findUserByUsername(auth.getName());
			user = customer;
			feedbackCourseList = customer.getFeedbackCourse();
			feedbackGymList = customer.getFeedbackGym();
			favoriteGymList = customer.getFavoriteGym();
			favoriteCourseList = customer.getFavoriteCourse();
//			feedbackCourseList = serviceFeedbackCourse.findAllFeedbackByUserId(user.getId());
//			feedbackGymList = serviceFeedbackGym.findAllFeedbackByUserId(user.getId());
//			favoriteGymList = serviceFavoriteGym.findAllFavoriteByUserId(user.getId());
//			favoriteCourseList = serviceFavoriteCourse.findAllFavoriteByUserId(user.getId());
		} else {
			user = userService.findUserByUsername(auth.getName());
		}

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
		User user = userService.findUserByUsername(auth.getName());
		model.addAttribute("user", user);
		return "/profile/update";
		
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("user") User user , Errors errors) throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (errors.hasErrors()) {
			return "/common/error";
		}

		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))) {
			userService.updateCustomer(user);
		}
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
			userService.updateManager(user);
		}

		Set<Chat> chatList = serviceChat.findByUserId(user);
		for ( Chat c: chatList ) {
			c.setUserName(user.getName() + " " + user.getLastname());
		}
		serviceChat.saveAllChats(chatList);

		return "redirect:/login";

	}
	
}

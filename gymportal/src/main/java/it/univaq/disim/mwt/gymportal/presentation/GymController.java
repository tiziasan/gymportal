package it.univaq.disim.mwt.gymportal.presentation;

import javax.validation.Valid;

import it.univaq.disim.mwt.gymportal.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.domain.Chat;

import java.util.List;

@Controller
@RequestMapping("gym")

public class GymController {
	
	@Autowired
	private GymBO serviceGym;
	
	@Autowired
	private CourseBO serviceCourse;
	
	@Autowired
	private UserBO userService;

	@Autowired
	private ChatBO serviceChat;

	@Autowired
	private MessageBO serviceMessage;
	
	@GetMapping("/create")
    public String createStart(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("user", user);
		Gym gym = new Gym();
		model.addAttribute("gym", gym);
		return "/gym/form";
    }
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("gym") Gym gym, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/gym/form";
		}
		serviceGym.createGym(gym);
		return "redirect:/course/create";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
		Gym gym = serviceGym.findByID(id);
		model.addAttribute("gym", gym);
		return "/gym/delete";
    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("gym") Gym gym, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/common/error";
		}
		serviceCourse.deleteAllCourseByGymId(gym.getId());
		serviceGym.deleteGym(gym);

		List<Chat> chats = serviceChat.findByGymId(gym.getId());
		for ( Chat c: chats ) {
			serviceMessage.deleteMessagesByChat(c);
		}
		serviceChat.deleteChatsByGymId(gym.getId());

		return "redirect:/";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateStart(@PathVariable long id, Model model) throws BusinessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addAttribute("user", user);
		Gym gym = serviceGym.findByID(id);
		gym.setRegion("");
		model.addAttribute("gym", gym);
		return "/gym/form";
	}

	@PostMapping("/update/{id}")
	public String update(@ModelAttribute("gym") Gym gym, Errors errors) throws BusinessException {
		if (errors.hasErrors()) {
			return "/gym/form";
		}
		serviceGym.updateGym(gym);

		List<Chat> chatList = serviceChat.findByGymId(gym.getId());
		for ( Chat c: chatList ) {
			c.setGymName(gym.getName());
		}
		serviceChat.saveAllChats(chatList);

		return "redirect:/";
	}
	
	
	@GetMapping("/list")
    public String list() {
		return "/region/index"; 
    }
	
	@GetMapping("/gym")
    public String listByRegion() {
		return "/region/index"; 
    }
	
}
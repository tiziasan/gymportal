package it.univaq.disim.mwt.gymportal.presentation;


import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import javax.validation.Valid;

@Controller
@RequestMapping("gym/{idGym}/chat")
public class MessageController {

    @Autowired
    private MessageBO serviceMessage;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView createStart(@PathVariable Long idGym) {
		ModelAndView modelAndView = new ModelAndView();
		Message message = new Message();
		message.setGym_id(idGym);
		modelAndView.addObject("message", message);
		modelAndView.setViewName("chat/index");
		return modelAndView;
	}

    @PostMapping("")
    public String create(@Valid @ModelAttribute("message") Message message, Errors errors) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if (errors.hasErrors()) {
            return "/chat/index";
        }
        message.setUser_id(user.getId());
        message.setDate(LocalDateTime.now());
        serviceMessage.createMessage(message);
        
        return "/chat/index";
    }

}

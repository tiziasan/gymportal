package it.univaq.disim.mwt.gymportal.presentation;


import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.domain.User;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
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

import java.time.LocalDateTime;

import javax.validation.Valid;

@Controller
@RequestMapping("chat")
public class MessageController {

    @Autowired
    private MessageBO serviceMessage;

    @GetMapping("")
    
    public ModelAndView chat() {
		ModelAndView modelAndView = new ModelAndView();
		Message message = new Message();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("chat/index");
		return modelAndView;
	}

    @PostMapping("/create")

    public String create(@Valid @ModelAttribute("message") Message message, Errors errors) throws BusinessException {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    	System.out.println(authentication.getPrincipal());
    	
        if (errors.hasErrors()) {
            return "/chat/index";
        }
        message.setUser_id(1L);
        message.setDate(LocalDateTime.now());
        serviceMessage.createMessage(message);
        
        return "redirect:/gymportal";
    }

}

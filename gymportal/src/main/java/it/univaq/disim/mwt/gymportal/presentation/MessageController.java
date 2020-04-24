package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.domain.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import javax.validation.Valid;
@Slf4j
@Controller
@RequestMapping("chat")
public class MessageController {

    @Autowired
    private MessageBO serviceMessage;

    @Autowired
    private ChatBO serviceChat;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView createStart(@RequestParam(required = false) Long idGym) {
/*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //se gestore restituisco lista delle palestre e la lista delle chat per ogni palestra che gli appartiene

        if (user.isGestore()){

        }

        //se utente restituisco lista chat dell'utente
            //se id gym restituisci la chat che fa match con idGym e idUtente erestituisci la lista dei messaggi di quella specifica chatù
                //se chat non esiste -> creala
        if(idGym != null){

        } else {

        }
*/
		ModelAndView modelAndView = new ModelAndView();
		Message message = new Message();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("chat/index");
		return modelAndView;
	}

    @PostMapping("")
    public String create(@Valid @ModelAttribute("message") Message message, Errors errors) throws BusinessException {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUserName(auth.getName());
        if (errors.hasErrors()) {
            return "/chat/index";
        }

        message.setDate(LocalDateTime.now());
        message.setSender("utente1");
        message = serviceMessage.createMessage(message);

        System.out.println(message);

        Chat chat = new Chat();
        chat.setGym_id(1L);
        //chat.setUser_id(user.getId());
        chat.setUser_id(1L);
        chat.addMessage(message);
        

        return "/chat/index";
    }

}

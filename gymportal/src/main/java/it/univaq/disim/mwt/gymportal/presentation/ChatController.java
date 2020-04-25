package it.univaq.disim.mwt.gymportal.presentation;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private MessageBO serviceMessage;

    @Autowired
    private GymBO serviceGym;
    
    @Autowired
    private ChatBO serviceChat;

    @Autowired
    private UserService userService;
    
    //se utente restituisco lista chat dell'utente
    //se id gym restituisci la chat che fa match con idGym e idUtente erestituisci la lista dei messaggi di quella specifica chatù
        //se chat non esiste -> creala


    @GetMapping("")
    public String list(Model model) throws BusinessException {
    	
    	List<Chat> chatList;
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //se gestore restituisco lista delle palestre e la lista delle chat per ogni palestra che gli appartiene

        if (user.isGestore()){
        	Gym gym = serviceGym.findByUser(user);
        	chatList = serviceChat.findByGymId(gym.getId());
        	model.addAttribute("chatList", chatList);
    		return "/chat/index";
        }else {
        	chatList = serviceChat.findByUserId(user.getId());
        	model.addAttribute("chatList", chatList);
    		return "/chat/index";
        }
	}
    
    @GetMapping("?idGym={gymId}")
    public String singleChat(@RequestParam Long gymId,  Model model) throws BusinessException{
    	
    	Chat chat;
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        
        
    	
    	
    	return null;
    	
    }

//    @PostMapping("")
//    public String create(@Valid @ModelAttribute("message") Message message, Errors errors) throws BusinessException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        
//        if (errors.hasErrors()) {
//            return "/chat/index";
//        }
//
//        System.out.println(message);
//
//        Chat chat = new Chat();
//        chat = serviceChat.createChat(chat);
//        
//        System.out.println("<--------------CHAT");
//        System.out.println(chat);
//        
//        message.setDate(LocalDateTime.now());
//        message.setSender("utente1");
//        message.setChat(chat);
//        message = serviceMessage.createMessage(message);
//
//        System.out.println("<--------------MESSAGE");
//        System.out.println(message);
//        
//
//        return "/chat/index";
//    }

}
	


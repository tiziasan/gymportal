package it.univaq.disim.mwt.gymportal.presentation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatBO;
import it.univaq.disim.mwt.gymportal.business.GymBO;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.Message;
import it.univaq.disim.mwt.gymportal.domain.User;

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
    public String createStart(@RequestParam(required = false) Long idGym, Model model) throws BusinessException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        if (auth.toString().contains("gestore")){ //se gestore restituisco lista delle palestre e la lista delle chat per ogni palestra che gli appartiene

            List<Gym> gyms = serviceGym.searchByUser(user.getId());
            Map<String, List<Chat>> chatMap = new HashMap<>();
            for (Gym g: gyms ) {
                chatMap.put(g.getName(), serviceChat.findByGymId(g.getId()));
            }
            model.addAttribute("chatMap", chatMap);

        } else { //se utente restituisco lista chat dell'utente con le palestre

            List<Chat> chatList = serviceChat.findByUserId(user.getId());
            model.addAttribute("chatList", chatList);

            if(idGym != null){ //se id gym settato restituisci la chat che fa match con idGym e username e restituisci la lista dei messaggi di quella specifica chat
                //se chat non esiste non bisogna crearla qui ma nel metodo che crea i messaggi

                List<Message> messageList = serviceMessage.findByChat(serviceChat.findByUserIdAndGymId(user.getId(), idGym));
                model.addAttribute("messageList", messageList);

            }

        }

        Message message = new Message();
        model.addAttribute("message", message);
        return "chat/index";
    }

    @GetMapping("/{idChat}")
    public String showChat(@PathVariable String idChat, Model model) throws BusinessException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        if (auth.toString().contains("gestore")){ //se gestore restituisco lista delle palestre e la lista delle chat per ogni palestra che gli appartiene

            List<Gym> gyms = serviceGym.searchByUser(user.getId());
            Map<String, List<Chat>> chatMap = new HashMap<>();
            for (Gym g: gyms ) {
                chatMap.put(g.getName(), serviceChat.findByGymId(g.getId()));
            }
            model.addAttribute("chatMap", chatMap);

        } else { //se utente restituisco lista chat dell'utente con le palestre

            List<Chat> chatList = serviceChat.findByUserId(user.getId());
            model.addAttribute("chatList", chatList);

        }

        List<Message> messageList = serviceMessage.findByChat(serviceChat.findChatById(idChat));
        model.addAttribute("messageList", messageList);

        Message message = new Message();
        model.addAttribute("message", message);
        return "chat/index";
    }

    @PostMapping("")
    public String create(@RequestParam(required = false) Long idGym, @Valid @ModelAttribute("message") Message message, Errors errors) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        //Gym gym = serviceGym.findByID(idGym);
        if (errors.hasErrors()) {
            return "/chat/index";
        }

        Chat chat = new Chat();
        chat.setUserId(user.getId());
        chat.setUserName(user.getLastName() + " " + user.getName());
        //chat.setGymId(gym.getId());
        chat.setGymId(2L);
        //chat.setGymName(gym.getName());
        chat.setGymName("palestra da cancellare");

        chat = serviceChat.createChat(chat);

        System.out.println("<--------------CHAT");
        System.out.println(chat);

        message.setDate(LocalDateTime.now());
        message.setSender("palestra");
        message.setChat(chat);
        message.setGym(true);
        message = serviceMessage.createMessage(message);

        System.out.println("<--------------MESSAGE");
        System.out.println(message);


        return "/chat/index";
    }

}
	


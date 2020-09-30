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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private UserBO userService;

    @GetMapping(value = {"", "/{idChat}", "?idGym={idGym}"})
    public String createStart(@PathVariable(required = false) String idChat, @RequestParam(required = false) Long idGym, Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))){ //se gestore restituisco mappa delle palestre e la lista delle chat per ognuna di esse

            User user = userService.findUserByUsername(auth.getName());
            Set<Gym> gyms = serviceGym.searchByUser(user);
            Map<String, Set<Chat>> chatMap = new HashMap<>();
            for (Gym g: gyms ) {
                chatMap.put(g.getName(), serviceChat.findByGymId(g));
            }
            model.addAttribute("chatMap", chatMap);

            if(idChat != null && idGym == null){
                Chat chat = serviceChat.findChatById(idChat);
                model.addAttribute("chat", chat);
                Set<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);
            }

        } else { //se utente restituisco lista chat dell'utente con le palestre

            User user = userService.findUserByUsername(auth.getName());
            Set<Chat> chatList = serviceChat.findByUserId(user);
            model.addAttribute("chatList", chatList);

            if(idChat != null && idGym == null){    //se idchat settata allora già esiste
                Chat chat = serviceChat.findChatById(idChat);
                model.addAttribute("chat", chat);
                Set<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);
            }
            if(idChat == null && idGym != null){    //se id gym settato restituisci la chat che fa match con idGym e username e restituisci la lista dei messaggi di quella specifica chat
                Chat chat = serviceChat.findByUserIdAndGymId(user, idGym);     //se chat non esiste non bisogna crearla qui ma nel metodo che fa inserimento dei messaggi
                model.addAttribute("chat", chat);
                Set<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);
            }
        }

        Message message = new Message();
        model.addAttribute("message", message);
        return "chat/index";
    }

    @PostMapping(value = {"", "/{idChat}", "?idGym={idGym}"})
    public String create(@PathVariable(required = false) String idChat, @RequestParam(required = false) Long idGym, @Valid Message message, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            return "/chat/index";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Chat chat = new Chat();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))){
            if(idChat != null && idGym == null){    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                chat = serviceChat.findChatById(idChat);
                message.setGym(true);
            }
        } else {
            if(idChat != null && idGym == null){    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                chat = serviceChat.findChatById(idChat);
                message.setGym(false);
            }
            if(idChat == null && idGym != null){    //se ho idGYm prendo la chat che fa match con userId
                chat = serviceChat.findByUserIdAndGymId(user, idGym);
                if (chat == null){  //se non esiste la creo
                    chat = new Chat();
                    chat.setUserId(user.getId());
                    chat.setUserName(user.getLastname() + " " + user.getName());
                    Gym gym = serviceGym.findByID(idGym);
                    chat.setGymId(gym.getId());
                    chat.setGymName(gym.getName());
                    chat = serviceChat.saveChat(chat);
                }
                message.setGym(false);
            }
        }
        message.setChat(chat);
        message.setDate(LocalDateTime.now());
        serviceMessage.createMessage(message);

        return "redirect:/chat/" + chat.getId();
    }

}
	


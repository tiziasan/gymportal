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

    @GetMapping(value = {"", "/{idChat}", "?idGym={idGym}"})
    public String createStart(@PathVariable(required = false) String idChat, @RequestParam(required = false) Long idGym, Model model) throws BusinessException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        String chatTitle = "Seleziona una Chat";

        if (auth.toString().contains("gestore")){ //se gestore restituisco mappa delle palestre e la lista delle chat per ognuna di esse

            List<Gym> gyms = serviceGym.searchByUser(user.getId());
            Map<String, List<Chat>> chatMap = new HashMap<>();
            for (Gym g: gyms ) {
                chatMap.put(g.getName(), serviceChat.findByGymId(g.getId()));
            }
            model.addAttribute("chatMap", chatMap);

            if(idChat != null && idGym == null){
                Chat chat = serviceChat.findChatById(idChat);
                List<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);

                chatTitle = chat.getGymName() + " - " + chat.getUserName();
            }

        } else { //se utente restituisco lista chat dell'utente con le palestre

            List<Chat> chatList = serviceChat.findByUserId(user.getId());
            model.addAttribute("chatList", chatList);

            if(idChat != null && idGym == null){    //se idchat settata allora già esiste
                Chat chat = serviceChat.findChatById(idChat);
                List<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);

                chatTitle = chat.getGymName();
            }
            if(idChat == null && idGym != null){    //se id gym settato restituisci la chat che fa match con idGym e username e restituisci la lista dei messaggi di quella specifica chat
                Chat chat = serviceChat.findByUserIdAndGymId(user.getId(), idGym);     //se chat non esiste non bisogna crearla qui ma nel metodo che fa inserimento dei messaggi
                List<Message> messageList = serviceMessage.findByChat(chat);
                model.addAttribute("messageList", messageList);

                chatTitle = chat.getGymName();
            }
        }
        model.addAttribute("chatTitle", chatTitle);

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
        User user = userService.findUserByUserName(auth.getName());

        Chat chat = new Chat();
        if (auth.toString().contains("gestore")){
            if(idChat != null && idGym == null){    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                chat = serviceChat.findChatById(idChat);
                Gym gym = serviceGym.findByID(chat.getGymId());
                message.setSender(gym.getName());
                message.setGym(true);
            }
        } else {
            if(idChat != null && idGym == null){    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                chat = serviceChat.findChatById(idChat);
                message.setSender(user.getLastName() + " " + user.getName());
                message.setGym(false);
            }
            if(idChat == null && idGym != null){    //se ho idGYm prendo la chat che fa match con userId
                chat = serviceChat.findByUserIdAndGymId(user.getId(), idGym);
                if (chat == null){  //se non esiste la creo
                    chat = new Chat();
                    chat.setUserId(user.getId());
                    chat.setUserName(user.getUserName());
                    Gym gym = serviceGym.findByID(idGym);
                    chat.setGymId(gym.getId());
                    chat.setGymName(gym.getName());
                    chat = serviceChat.createChat(chat);
                }
                message.setSender(user.getLastName() + " " + user.getName());
                message.setGym(false);
            }
        }
        message.setChat(chat);
        message.setDate(LocalDateTime.now());
        serviceMessage.createMessage(message);

        return "redirect:/chat/" + chat.getId();
    }

}
	


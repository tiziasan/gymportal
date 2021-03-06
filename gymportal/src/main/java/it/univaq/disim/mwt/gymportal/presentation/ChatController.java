package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private GymService gymService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/{idChat}", "?idGym={idGym}"})
    public String createStart(@PathVariable(required = false) String idChat, @RequestParam(required = false) Long idGym, Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) { //se gestore restituisco mappa delle palestre e la lista delle chat per ognuna di esse

            User user = userService.findUserByUsername(auth.getName());
            Set<Gym> gyms = gymService.searchByUser(user);
            Map<String, Set<Chat>> chatMap = new HashMap<>();
            for (Gym g : gyms) {
                chatMap.put(g.getName(), chatService.findByGymId(g));
            }
            model.addAttribute("chatMap", chatMap);

            if (idChat != null && idGym == null) {
                Chat chat = chatService.findChatById(idChat);
                model.addAttribute("chat", chat);
                Set<Message> messageList = messageService.findByChat(chat);
                model.addAttribute("messageList", messageList);
            }

        } else { //se utente restituisco lista chat dell'utente con le palestre

            User user = userService.findUserByUsername(auth.getName());
            Set<Chat> chatList = chatService.findByUserId(user);
            model.addAttribute("chatList", chatList);

            if (idChat != null && idGym == null) {    //se idchat settata allora già esiste
                Chat chat = chatService.findChatById(idChat);
                model.addAttribute("chat", chat);
                Set<Message> messageList = messageService.findByChat(chat);
                model.addAttribute("messageList", messageList);
            } else if (idChat == null && idGym != null) {    //se id gym settato restituisci la chat che fa match con idGym e username e restituisci la lista dei messaggi di quella specifica chat
                Chat chat = chatService.findByUserIdAndGymId(user, idGym);     //se chat non esiste non bisogna crearla qui ma nel metodo che fa inserimento dei messaggi
                model.addAttribute("chat", chat);
                Set<Message> messageList = messageService.findByChat(chat);
                model.addAttribute("messageList", messageList);
            }
        }

        Message message = new Message();
        model.addAttribute("message", message);
        return "chat/index";
    }

    @PostMapping(value = {"", "/{idChat}", "?idGym={idGym}"})
    public String create(@PathVariable(required = false) String idChat, @RequestParam(required = false) Long idGym, @ModelAttribute("message") @Valid Message message, Errors errors, RedirectAttributes ra, Model model) throws BusinessException {
        if (errors.hasErrors()) {
            model.addAttribute("message", message);
            if (idChat != null) {
                return "redirect:/chat/" + idChat;
            }
            return "redirect:/chat";
        }

        Chat chat = new Chat();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUsername(auth.getName());
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
                if (idChat != null && idGym == null) {    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                    chat.setId(idChat);
                    message.setGym(true);
                }
            } else {
                if (idChat != null && idGym == null) {    //se ho idChat prendo la chat, faccio inserimento del messaggio e aggiorno solo la lista dei messaggi e ritorno a /chat/idchat
                    chat.setId(idChat);
                }
                if (idChat == null && idGym != null) {    //se ho idGym prendo la chat che fa match con userId
                    chat = chatService.findByUserIdAndGymId(user, idGym);
                    if (chat == null) {     //se non esiste la creo
                        String userName = user.getLastname() + " " + user.getName();
                        Gym gym = gymService.findByID(idGym);
                        chat = new Chat(user.getId(), userName, gym.getId(), gym.getName());
                        chat = chatService.saveChat(chat);
                    }
                }
                message.setGym(false);
            }
            message.setChat(chat);
            messageService.createMessage(message);
        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/chat/" + chat.getId();
    }

}
	


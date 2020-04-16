package it.univaq.disim.mwt.gymportal.presentation;


import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.MessageBO;
import it.univaq.disim.mwt.gymportal.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("chat")
public class MessageController {

    @Autowired
    private MessageBO serviceMessage;

    @GetMapping("")
    public String createStart(Model model) {
        Message message = new Message();
        model.addAttribute("message", message);
        return "/chat/index";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("message") Message msg, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            return "/chat/index";
        }
        serviceMessage.createMessage(msg);
        return "redirect:/gymportal";
    }

}

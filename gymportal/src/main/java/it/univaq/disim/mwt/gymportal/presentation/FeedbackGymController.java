package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FeedbackGymService;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FeedbackGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller

@RequestMapping("feedback")
public class FeedbackGymController {

    @Autowired
    private FeedbackGymService feedbackGymService;

    @Autowired
    private GymService gymService;

    @Autowired
    private UserService userService;


    @GetMapping("/create/{id}")
    public String createStart(Model model, @PathVariable long id) throws BusinessException {
        Gym gym = gymService.findByID(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer user = userService.findUserByUsername(auth.getName());
        model.addAttribute("gym", gym);
        model.addAttribute("user", user);

        FeedbackGym feedback = new FeedbackGym();

        model.addAttribute("feedback", feedback);

        return "/feedback/form";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid @ModelAttribute("feedback") FeedbackGym feedback, RedirectAttributes ra, Model model, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            String message = "Errore nell'inserimento";
            model.addAttribute("message", message);
            return "/feedback/form";
        }
        try {
            feedbackGymService.createFeedbackGym(feedback);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("UK2ucpf4y44fsmt5cj767ygmyu2")){
                ra.addFlashAttribute("error", "Hai già scritto una recensione per questa palestra!");
                return "redirect:/profile";
            }
        }

        return "redirect:/course/gym/" + feedback.getGym().getId();
    }


    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id) throws BusinessException {
        return "/feedback/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("feedback") FeedbackGym feedback, RedirectAttributes ra) throws BusinessException {
        feedbackGymService.deleteFeedbackGym(feedback);

        return "redirect:/profile";
    }


    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        FeedbackGym feedback = feedbackGymService.findByID(id);

        model.addAttribute("feedback", feedback);
        return "/feedback/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("feedback") FeedbackGym feedback, RedirectAttributes ra, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            return "/common/error";
        }

        feedbackGymService.updateFeedbackGym(feedback);
        return "redirect:/profile";
    }


}

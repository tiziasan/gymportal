package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteService;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller

@RequestMapping("favoriteGym")
public class FavoriteGymController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private GymService gymService;

    @Autowired
    private UserService userService;


    @GetMapping("/create/{id}")
    public String createStart(@PathVariable long id, Model model) throws BusinessException {
        Gym gym = gymService.findByID(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer user = userService.findUserByUsername(auth.getName());
        model.addAttribute("gym", gym);
        model.addAttribute("user", user);

        FavoriteGym favoriteGym = new FavoriteGym();

        model.addAttribute("favoriteGym", favoriteGym);

        return "/favoriteGym/create";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid @ModelAttribute("favoriteGym") FavoriteGym favoriteGym, Errors errors, RedirectAttributes ra, Model model)
            throws BusinessException {
        if (errors.hasErrors()) {
            model.addAttribute("course", favoriteGym.getGym());
            model.addAttribute("user", favoriteGym.getUser());
            return "/favoriteGym/create";
        }
        try {
            favoriteService.createFavoriteGym(favoriteGym);
            ra.addFlashAttribute("success", "palestra aggiunta ai preferiti");
        } catch (DataAccessException e) {
            if (e instanceof DataIntegrityViolationException) {
                ra.addFlashAttribute("warning", "Hai già inserito la palestra ai preferiti");
                return "redirect:/profile";
            }
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }
        return "redirect:/profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id) throws BusinessException {
        return "/favoriteGym/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("favoriteGym") FavoriteGym favoriteGym, RedirectAttributes ra) throws BusinessException {
        try {
            favoriteService.deleteFavoriteGym(favoriteGym);
            ra.addFlashAttribute("success", "Eliminazione avvenuta con successo");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }
        return "redirect:/profile";
    }


}

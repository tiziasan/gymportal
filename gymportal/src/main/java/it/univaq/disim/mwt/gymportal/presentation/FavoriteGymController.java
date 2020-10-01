package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.FavoriteService;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FavoriteGym;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String create(@Valid @ModelAttribute("favoriteGym") FavoriteGym favoriteGym, RedirectAttributes redir, Errors errors, Model model)
            throws BusinessException {

        if (errors.hasErrors()) {
            String message = "Errore nell'inserimento";
            model.addAttribute("message", message);
            return "redirect:/profile";
        }
        System.out.println(favoriteGym);

        favoriteService.createFavoriteGym(favoriteGym);
        redir.addFlashAttribute("message", "palestra aggiunta ai preferiti");


        String redirect = "redirect:/profile";

        return redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        return "/favoriteGym/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("favoriteGym") FavoriteGym favoriteGym, Errors errors) throws BusinessException {
        favoriteService.deleteFavoriteGym(favoriteGym);
        return "redirect:/profile";
    }


}

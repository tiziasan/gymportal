package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseService;
import it.univaq.disim.mwt.gymportal.business.FavoriteService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
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

@RequestMapping("favoriteCourse")
public class FavoriteCourseController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("/create/{id}")
    public String createStart(@PathVariable long id, Model model) throws BusinessException {
        Course course = courseService.findByID(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer user = userService.findUserByUsername(auth.getName());
        model.addAttribute("course", course);
        model.addAttribute("user", user);

        FavoriteCourse favoriteCourse = new FavoriteCourse();

        model.addAttribute("favoriteCourse", favoriteCourse);
        return "/favoriteCourse/create";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid @ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors, RedirectAttributes ra, Model model)
            throws BusinessException {
        if (errors.hasErrors()) {
            model.addAttribute("course", favoriteCourse.getCourse());
            model.addAttribute("user", favoriteCourse.getUser());
            return "/favoriteCourse/create";
        }
        try {
            favoriteService.createFavoriteCourse(favoriteCourse);
            ra.addFlashAttribute("success", "corso aggiunto ai preferiti");
        } catch (DataAccessException e) {
            if (e instanceof DataIntegrityViolationException) {
                ra.addFlashAttribute("warning", "Hai già inserito il corso ai preferiti");
                return "redirect:/profile";
            }
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id) throws BusinessException {
        return "/favoriteCourse/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, RedirectAttributes ra) throws BusinessException {
        try {
            favoriteService.deleteFavoriteCourse(favoriteCourse);
            ra.addFlashAttribute("success", "Eliminazione avvenuta con successo");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/profile";
    }


}

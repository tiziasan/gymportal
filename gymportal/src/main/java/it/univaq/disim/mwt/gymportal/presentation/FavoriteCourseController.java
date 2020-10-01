package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseService;
import it.univaq.disim.mwt.gymportal.business.FavoriteService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FavoriteCourse;
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
    public String create(@Valid @ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, RedirectAttributes redir, Errors errors, Model model)
            throws BusinessException {

        if (errors.hasErrors()) {
            String message = "Errore nell'inserimento";
            model.addAttribute("message", message);
            return "redirect:/profile";
        }
        System.out.println(favoriteCourse);

        favoriteService.createFavoriteCourse(favoriteCourse);
        redir.addFlashAttribute("message", "corso aggiunto ai preferiti");


        String redirect = "redirect:/profile";

        return redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        return "/favoriteCourse/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("favoriteCourse") FavoriteCourse favoriteCourse, Errors errors) throws BusinessException {
        favoriteService.deleteFavoriteCourse(favoriteCourse);
        return "redirect:/profile";
    }


}

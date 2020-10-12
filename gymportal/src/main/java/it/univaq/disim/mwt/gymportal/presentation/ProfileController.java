package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.ChatService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.configuration.FileUploadUtil;
import it.univaq.disim.mwt.gymportal.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@RequestMapping("/profile")

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView home(Model model) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user;

        Set<FeedbackCourse> feedbackCourseList = new HashSet<>();
        Set<FeedbackGym> feedbackGymList = new HashSet<>();
        Set<FavoriteGym> favoriteGymList = new HashSet<>();
        Set<FavoriteCourse> favoriteCourseList = new HashSet<>();

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))) {
            Customer customer = userService.findUserByUsername(auth.getName());
            user = customer;
            feedbackCourseList = customer.getFeedbackCourses();
            feedbackGymList = customer.getFeedbackGyms();
            favoriteGymList = customer.getFavoriteGyms();
            favoriteCourseList = customer.getFavoriteCourses();

        } else {
            user = userService.findUserByUsername(auth.getName());
        }

        model.addAttribute("user", user);
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        model.addAttribute("feedbackCourseList", feedbackCourseList);
        model.addAttribute("feedbackGymList", feedbackGymList);
        model.addAttribute("favoriteGymList", favoriteGymList);
        model.addAttribute("favoriteCourseList", favoriteCourseList);

        modelAndView.setViewName("/profile/index");
        return modelAndView;
    }

    @GetMapping("/update")
    public String updateStart(Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        model.addAttribute("user", user);

        return "/profile/update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") User user, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes ra, Errors errors) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/common/error";
        }
        String uploadDir = "src/main/upload/user/" + user.getId();
        FileUploadUtil.saveFile(uploadDir, user.getId() + ".jpeg", multipartFile);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))) {
            userService.updateUser(user, Role.CUSTOMER);
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
            userService.updateUser(user, Role.MANAGER);
        }

        return "redirect:/login";
    }

}

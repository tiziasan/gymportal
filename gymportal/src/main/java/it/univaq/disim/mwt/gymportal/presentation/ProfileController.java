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

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@RequestMapping("/profile")

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


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
    public String update(@Valid @ModelAttribute("user") User user, Errors errors, @RequestParam("image") MultipartFile multipartFile) throws BusinessException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (errors.hasErrors()) {
            return "/common/error";
        }

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.CUSTOMER))) {
            userService.updateCustomer(user);
            User newUser = userService.saveUser(new Customer(user));
            String uploadDir = "src/main/upload/user/" + newUser.getId();
            FileUploadUtil.saveFile(uploadDir, newUser.getId() + ".jpeg", multipartFile);
        }
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
            userService.updateManager(user);
            User newUser = userService.saveUser(new Customer(user));
            String uploadDir = "src/main/upload/user/" + newUser.getId();
            FileUploadUtil.saveFile(uploadDir, newUser.getId()+ ".jpeg", multipartFile);
        }

        Set<Chat> chatList = chatService.findByUserId(user);
        for (Chat c : chatList) {
            c.setUserName(user.getName() + " " + user.getLastname());
        }
        chatService.saveAllChats(chatList);

        return "redirect:/login";

    }

}

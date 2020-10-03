package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.configuration.FileUploadUtil;
import it.univaq.disim.mwt.gymportal.domain.Chat;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("gym")

public class GymController {

    @Autowired
    private GymService gymService;


    @Autowired
    private FeedbackGymService feedbackGymService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FeedbackCourseService feedbackCourseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/create")
    public String createStart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Manager user = userService.findUserByUsername(auth.getName());
        model.addAttribute("user", user);
        Gym gym = new Gym();
        model.addAttribute("gym", gym);
        return "/gym/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("gym") Gym gym, Errors errors, @RequestParam("image") MultipartFile multipartFile) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/gym/form";
        }
        gymService.createGym(gym);
        String uploadDir = "src/main/upload/gym/" + gym.getId();
        FileUploadUtil.saveFile(uploadDir, gym.getId() + ".jpeg", multipartFile);

        return "redirect:/course/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        Gym gym = gymService.findByID(id);
        model.addAttribute("gym", gym);
        return "/gym/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("gym") Gym gym, Errors errors, Model model) throws BusinessException {

        Set<Course> courses = courseService.findCourseByGymId(gym);
        for (Course c : courses) {
            favoriteService.deleteAllByCourse(c);
            feedbackCourseService.deleteAllByCourse(c);
        }
        courseService.deleteAllCourseByGymId(gym);

        favoriteService.deleteAllByGym(gym);
        feedbackGymService.deleteAllByGym(gym);
        gymService.deleteGym(gym);

        Set<Chat> chats = chatService.findByGymId(gym);
        for (Chat c : chats) {
            messageService.deleteMessagesByChat(c);
        }
        chatService.deleteChatsByGymId(gym);
        model.addAttribute("success", "Eliminazione della palestra andata a buon fine");

        return "/index";
    }

    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Manager user = userService.findUserByUsername(auth.getName());
        model.addAttribute("user", user);
        Gym gym = gymService.findByID(id);
        gym.setRegion("");
        model.addAttribute("gym", gym);
        return "/gym/form";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("gym") Gym gym, Errors errors,@RequestParam("image") MultipartFile multipartFile) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/gym/form";
        }
        gymService.updateGym(gym);
        String uploadDir = "src/main/upload/gym/" + gym.getId();
        FileUploadUtil.saveFile(uploadDir, gym.getId() + ".jpeg", multipartFile);

        Set<Chat> chatList = chatService.findByGymId(gym);
        for (Chat c : chatList) {
            c.setGymName(gym.getName());
        }
        chatService.saveAllChats(chatList);

        return "redirect:/";
    }


    @GetMapping("/list")
    public String list() {
        return "/region/index";
    }

    @GetMapping("/gym")
    public String listByRegion() {
        return "/region/index";
    }

}
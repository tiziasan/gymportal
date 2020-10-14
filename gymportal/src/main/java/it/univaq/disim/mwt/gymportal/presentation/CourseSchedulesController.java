package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseSchedulesService;
import it.univaq.disim.mwt.gymportal.business.GymService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.configuration.FileUploadUtil;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("courseschedules")
public class CourseSchedulesController {

    @Autowired
    CourseSchedulesService courseSchedulesService;

    @Autowired
    UserService userService;

    @Autowired
    GymService gymService;

    @GetMapping("/create")
    public String createStart(Model model) {
        CourseSchedules courseSchedules = new CourseSchedules();
        model.addAttribute("courseSchedules", courseSchedules);

        return "/courseSchedules/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("courseSchedules") CourseSchedules courseSchedules, Errors errors, RedirectAttributes ra, Model model) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/courseschedules/form";
        }
        try {
            courseSchedulesService.addCourseSchedules(courseSchedules);
            ra.addFlashAttribute("success", "Operazione andata a buon fine, aggiungi un altro orario per il corso!");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/courseschedules/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        CourseSchedules courseSchedules = courseSchedulesService.findCourseSchedulesById(id);
        model.addAttribute("courseSchedules", courseSchedules);
        return "/courseschedules/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("courseSchedules") CourseSchedules courseSchedules, RedirectAttributes ra, Model model) throws BusinessException {
        try {
            courseSchedulesService.deleteCourseSchedules(courseSchedules);
            model.addAttribute("success", "Eliminazione dell'orario del corso andata a buon fine");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/course/gym/" + courseSchedules.getCourse().getGym().getId();
    }

    @ModelAttribute
    public void addAll(Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
            Manager user = userService.findUserByUsername(auth.getName());
            Set<Course> courses = new HashSet<>();
            Set<Gym> gyms = new HashSet<>(user.getGyms());
            for (Gym gym : gyms) {
                courses.addAll(gym.getCourses());
            }
            model.addAttribute("courses", courses);
        }

    }
}

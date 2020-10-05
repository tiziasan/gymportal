package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseSchedulesService;
import it.univaq.disim.mwt.gymportal.business.GymService;
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
    public String create(@Valid @ModelAttribute("courseSchedules") CourseSchedules courseSchedules, Errors errors, Model model, RedirectAttributes ra) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            String message = "Errore nell'inserimento";
            model.addAttribute("message", message);
            return "/courseSchedules/form";
        }

        courseSchedulesService.addCourseSchedules(courseSchedules);

        String message = "Operazione andata a buon fine, aggiungi un altro orario per il corso!";
        ra.addFlashAttribute("message", message);
        return "redirect:/courseSchedules/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        CourseSchedules courseSchedules = courseSchedulesService.findCourseSchedulesById(id);
        model.addAttribute("courseSchedules", courseSchedules);
        return "/courseSchedules/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("courseSchedules") CourseSchedules courseSchedules, Model model) throws BusinessException {
        courseSchedulesService.deleteCourseSchedules(courseSchedules);

        model.addAttribute("success", "Eliminazione dell'orario del corso andata a buon fine");
        return "/index";
    }

    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        CourseSchedules courseSchedules = courseSchedulesService.findCourseSchedulesById(id);

        model.addAttribute("courseSchedules", courseSchedules);
        return "/courseSchedules/form";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("courseSchedules") CourseSchedules courseSchedules, Errors errors) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/CourseSchedules/form";
        }

        courseSchedulesService.updateCourseschedules(courseSchedules);
        return "redirect:/";
    }

    @ModelAttribute
    public void addAll(Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
            Manager user = userService.findUserByUsername(auth.getName());
            Set<Gym> gyms = new HashSet<Gym>();
            Set<Course> courses = new HashSet<Course>();
            gyms.addAll(user.getGyms());
            System.out.println("funziona");
            System.out.println(gyms.iterator().next());
//            for (Gym gym : gyms) {
//              Set<Course> courseOfGym;
//                courseOfGym = gym.getCourses();
//                System.out.println(courseOfGym);
//                for (Course c : courseOfGym) {
//                    System.out.println("sono qui prima");
//                    courses.add(c);
//                    System.out.println("sono qui dopo");
//                }
//            }
//
//                System.out.println(courses);
//
               model.addAttribute("courses", courses);
        }

    }
}

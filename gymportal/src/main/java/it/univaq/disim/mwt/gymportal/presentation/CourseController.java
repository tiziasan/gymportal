package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
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
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private GymService gymService;

    @Autowired
    private FeedbackGymService feedbackGymService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createStart(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);

        return "/course/form";
    }

    @PostMapping("/create")

    public String create(@Valid @ModelAttribute("course") Course course, Errors errors, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes ra, Model model) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/course/form";
        }
        try {
            Course newCourse = courseService.createCourse(course);

            String uploadDir = "src/main/upload/course/" + newCourse.getId();
            FileUploadUtil.saveFile(uploadDir, newCourse.getId() + ".jpeg", multipartFile);

            ra.addFlashAttribute("success", "Operazione andata a buon fine, ora aggiungi gli orari!");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/courseschedules/create";
    }


    @GetMapping(value = {"/gym/{id}", "/gym/{id}?search={search}"})
    public String listCo(@PathVariable long id, @RequestParam(required = false) String search, Model model) throws BusinessException {
        Set<Course> courseList;
        Gym gym = gymService.findByID(id);

        if (search != null) {
            courseList = courseService.searchByIdAndName(id, search);
            model.addAttribute("search", search);
        } else {
            courseList = courseService.findCourseByGymId(gym);
        }
        Set<FeedbackGym> feedbackList = feedbackGymService.findAllFeedbackByGym(gym);
        model.addAttribute("courseList", courseList);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("gym", gym);

        return "/course/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        Course course = courseService.findByID(id);
        model.addAttribute("course", course);
        return "/course/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("course") Course course, RedirectAttributes ra, Model model) throws BusinessException {
        try{
            courseService.deleteCourse(course);
            File photo = new File("src/main/upload/gym/"+course.getId()+"/"+course.getId()+".jpeg");
            photo.delete();
            model.addAttribute("success", "Eliminazione del corso andata a buon fine");
        }
        catch (DataAccessException e) {
            ra.addAttribute("error", "Errore!!! Eliminazione del corso non è andata a buon fine");
            return "redirect:/";
        }
        return "redirect:/course/gym/" + course.getGym().getId();
    }

    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        Course course = courseService.findByID(id);
        model.addAttribute("course", course);
        return "/course/form";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("course") Course course, Errors errors, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes ra) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            return "/course/form";
        }
        try {
            courseService.updateCourse(course);

            String uploadDir = "src/main/upload/course/" + course.getId();
            FileUploadUtil.saveFile(uploadDir, course.getId() + ".jpeg", multipartFile);

            ra.addAttribute("success", "Aggiornamento avvenuto con successo!");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "redirect:/course/gym/" + course.getGym().getId();
    }

    @ModelAttribute
    public void addAll(Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.Values.MANAGER))) {
            Manager user = userService.findUserByUsername(auth.getName());
            Set<Gym> gyms = user.getGyms();
            model.addAttribute("gyms", gyms);
        }
    }


}
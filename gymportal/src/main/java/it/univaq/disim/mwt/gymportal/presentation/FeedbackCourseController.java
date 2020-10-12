package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.CourseService;
import it.univaq.disim.mwt.gymportal.business.FeedbackCourseService;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.domain.Course;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.FeedbackCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller

@RequestMapping("feedbackCourse")
public class FeedbackCourseController {

    @Autowired
    private FeedbackCourseService feedbackCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public String listCo(@PathVariable long id, Model model) throws BusinessException {
        Set<FeedbackCourse> feedbackList = feedbackCourseService.findAllFeedbackByCourse(id);
        model.addAttribute("feedbackList", feedbackList);
        return "/feedback/list";

    }

    @GetMapping("/create/{id}")
    public String createStart(Model model, @PathVariable long id) throws BusinessException {
        Course course = courseService.findByID(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer user = userService.findUserByUsername(auth.getName());
        model.addAttribute("course", course);
        model.addAttribute("user", user);

        FeedbackCourse feedbackCourse = new FeedbackCourse();

        model.addAttribute("feedbackCourse", feedbackCourse);

        return "/feedbackCourse/form";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid @ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, RedirectAttributes ra, Model model, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            String message = "Errore nell'inserimento";
            model.addAttribute("message", message);
            return "/feedbackCourse/form";
        }
        try {
            feedbackCourseService.createFeedbackCourse(feedbackCourse);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("UKmlqk03e4td4n6ph6susmvfoh")) {
                ra.addFlashAttribute("error", "Hai già scritto una recensione per questo corso!");
                return "/index";
            }
        }

        return "redirect:/feedbackCourse/" + feedbackCourse.getCourse().getId();
    }


    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {

        return "/feedback/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, RedirectAttributes ra, Errors errors) throws BusinessException {
        feedbackCourseService.deleteFeedbackCourse(feedbackCourse);
        return "redirect:/profile";
    }


    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        FeedbackCourse feedback = feedbackCourseService.findByID(id);
        model.addAttribute("feedbackCourse", feedback);
        return "/feedbackCourse/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("feedbackCourse") FeedbackCourse feedbackCourse, RedirectAttributes ra, Errors errors) throws BusinessException {
        if (errors.hasErrors()) {
            return "/common/error";
        }

        feedbackCourseService.updateFeedbackCourse(feedbackCourse);
        return "redirect:/profile";
    }


}

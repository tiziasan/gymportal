package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.*;
import it.univaq.disim.mwt.gymportal.configuration.FileUploadUtil;
import it.univaq.disim.mwt.gymportal.domain.Gym;
import it.univaq.disim.mwt.gymportal.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
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

@Controller
@RequestMapping("gym")

public class GymController {

    @Autowired
    private GymService gymService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createStart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Manager user = userService.findUserByUsername(auth.getName());
        Gym gym = new Gym();

        model.addAttribute("user", user);
        model.addAttribute("gym", gym);
        return "/gym/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("gym") Gym gym, Errors errors, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes ra, Model model) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            model.addAttribute("user", gym.getUser());
            return "/gym/form";
        }
        try {
            Gym newGym = gymService.createGym(gym);

            String uploadDir = "src/main/upload/gym/" + newGym.getId();
            FileUploadUtil.saveFile(uploadDir, newGym.getId() + ".jpeg", multipartFile);

            ra.addFlashAttribute("success", "Creazione avvenuta con successo");

        } catch (DataAccessException e) {
            if (e instanceof DataIntegrityViolationException) {
                ra.addFlashAttribute("warning", "Hai già inserito questa palestra");
                return "redirect:/gym/create";
            }
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }
        return "redirect:/course/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteStart(@PathVariable long id, Model model) throws BusinessException {
        Gym gym = gymService.findByID(id);
        model.addAttribute("gym", gym);
        return "/gym/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@ModelAttribute("gym") Gym gym, RedirectAttributes ra, Model model) throws BusinessException {
        try {
            gymService.deleteGym(gym);

            File photo = new File("src/main/upload/gym/"+gym.getId()+"/"+gym.getId()+".jpeg");
            photo.delete();

            model.addAttribute("success", "Eliminazione della palestra andata a buon fine");

        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

        return "/index";
    }

    @GetMapping("/update/{id}")
    public String updateStart(@PathVariable long id, Model model) throws BusinessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Manager user = userService.findUserByUsername(auth.getName());
        Gym gym = gymService.findByID(id);
        gym.setRegion("");

        model.addAttribute("user", user);
        model.addAttribute("gym", gym);
        return "/gym/form";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("gym") Gym gym, Errors errors, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes ra, Model model) throws BusinessException, IOException {
        if (errors.hasErrors()) {
            model.addAttribute("user", gym.getUser());
            return "/gym/form";
        }
        try {
            gymService.updateGym(gym);

            String uploadDir = "src/main/upload/gym/" + gym.getId();
            FileUploadUtil.saveFile(uploadDir, gym.getId() + ".jpeg", multipartFile);

            ra.addFlashAttribute("success", "Aggiornamento eseguito con successo");
        } catch (DataAccessException e) {
            ra.addFlashAttribute("error", "Errore!!! Riprova o contatta l'assistenza");
            return "redirect:/";
        }

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
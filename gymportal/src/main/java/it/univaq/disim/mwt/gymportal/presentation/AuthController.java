package it.univaq.disim.mwt.gymportal.presentation;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.UserService;
import it.univaq.disim.mwt.gymportal.configuration.FileUploadUtil;
import it.univaq.disim.mwt.gymportal.domain.Customer;
import it.univaq.disim.mwt.gymportal.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/index");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/registration/index");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, @RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult) throws BusinessException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        try {
            User userExists = userService.findUserByUsername(user.getUsername());

            if (userExists != null) {
                bindingResult.rejectValue("username", "error.user",
                        "There is already a user registered with the user name provided");
            }
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("/registration/index");
            } else {
                User newUser = userService.saveUser(new Customer(user));
                String uploadDir = "src/main/upload/user/" + newUser.getId();
                FileUploadUtil.saveFile(uploadDir, newUser.getId() + ".jpeg", multipartFile);

                modelAndView.addObject("successMessage", "Utente registrato correttamente, fai il login per entrare!");
                modelAndView.addObject("user", new User());
                modelAndView.setViewName("/registration/index");
            }
        } catch (DataAccessException e) {
            modelAndView.setViewName("/index");
            modelAndView.addObject("error", "Errore!!! Riprova o contatta l'assistenza");
            return modelAndView;
        }

        return modelAndView;
    }

}
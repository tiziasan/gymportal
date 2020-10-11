package it.univaq.disim.mwt.gymportal.presentation;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception ex, Model model) {
        log.info("Exception Occured:: URL=" + request.getRequestURL() + ", method=" + request.getMethod());
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        ex.printStackTrace(printWriter);
//        printWriter.flush();
//        model.addAttribute("errorMessage", stringWriter.toString());
        model.addAttribute("errorMessage", "Errore!!! Contatta l'assistenza di GymPortal");
        return "/common/error";
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public ModelAndView handleJDBCConnectionException(JDBCConnectionException e) {
        log.error("GlobalExceptionHandler - JDBCConnectionException catched:", e);

        ModelAndView mav = new ModelAndView("common/genericError");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());

        return mav;
    }

    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(HttpServletRequest request, DataAccessException e, Model model) {
        log.info("Exception Occured:: URL=" + request.getRequestURL() + ", method=" + request.getMethod());

        model.addAttribute("name", e.getClass().getSimpleName());
        model.addAttribute("message", e.getMessage());
        return "/common/genericError";
    }

}

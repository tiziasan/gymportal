package it.univaq.disim.mwt.gymportal.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception ex, Model model) {
		log.info("Exception Occured:: URL=" + request.getRequestURL() + ", method=" + request.getMethod());
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		printWriter.flush();
		if(stringWriter.toString().contains("UK3720qfodb5fi73gktwatyprks")){
			model.addAttribute("success", "Hai già inserito la palestra ai preferiti");
			return "/index";

		} else if (stringWriter.toString().contains("UKmttnhvruuluxke3r6mkni90ht")){
			model.addAttribute("success", "Hai già inserito il corso ai preferiti");
			return "/index";
		}
		else if (stringWriter.toString().contains("UK2ucpf4y44fsmt5cj767ygmyu2")){
			model.addAttribute("success", "Hai già scritto una recensione per questa palestra!");
			return "/index";
		}
		else if (stringWriter.toString().contains("UKmlqk03e4td4n6ph6susmvfoh")){
			model.addAttribute("success", "Hai già scritto una recensione per questo corso!");
			return "/index";
		}

		else {
			model.addAttribute("errorMessage", stringWriter.toString());
			return "/common/error";

		}
	}

}

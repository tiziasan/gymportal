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

		String message = (ex.getCause() == null) ? "" : ex.getCause().getMessage();
		model.addAttribute("errorCause", message);
		model.addAttribute("errorMessage", stringWriter.toString());
		return "/common/error";
	}

}

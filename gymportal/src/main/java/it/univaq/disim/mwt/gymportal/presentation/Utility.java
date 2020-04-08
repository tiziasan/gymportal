package it.univaq.disim.mwt.gymportal.presentation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import it.univaq.disim.mwt.gymportal.domain.User;
import it.univaq.disim.mwt.gymportal.security.UserDetailsImpl;

public class Utility {

	public static User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
			return ((UserDetailsImpl) usernamePasswordAuthenticationToken.getPrincipal()).getUser();
		} else {
			throw new RuntimeException();
		}
	}
}

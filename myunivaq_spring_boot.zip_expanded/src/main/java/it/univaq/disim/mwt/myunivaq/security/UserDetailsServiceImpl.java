package it.univaq.disim.mwt.myunivaq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import it.univaq.disim.mwt.myunivaq.business.BusinessException;
import it.univaq.disim.mwt.myunivaq.business.UtenteService;
import it.univaq.disim.mwt.myunivaq.domain.Utente;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UtenteService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente user;
		try {
			user = service.findUtenteByUsername(username);
		} catch (BusinessException e) {
			throw new UsernameNotFoundException("utente non trovato");
		}

		if (user == null) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		return new UserDetailsImpl(user);

	}

}

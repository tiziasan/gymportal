package it.univaq.disim.mwt.gymportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.univaq.disim.mwt.gymportal.business.BusinessException;
import it.univaq.disim.mwt.gymportal.business.UserBO;
import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.domain.User;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserBO serviceUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		Role role;
		try {
			user = serviceUser.findByUsername(username);
			System.out.println("PorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodio"+user);	
			role = serviceUser.findUserRole(user.getId());
			user.setRole(role.getName());
		} catch (BusinessException e) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		

		return new UserDetailsImpl(user);
	}

}

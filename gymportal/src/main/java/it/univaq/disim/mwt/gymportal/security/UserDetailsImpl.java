package it.univaq.disim.mwt.gymportal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.univaq.disim.mwt.gymportal.domain.User;


public class UserDetailsImpl implements UserDetails {
	
	private static final String ROLE_PREFIX = "ROLE_";

	private static final SimpleGrantedAuthority ROLE_GESTORE = new SimpleGrantedAuthority(ROLE_PREFIX + "gestore");
	private static final SimpleGrantedAuthority ROLE_UTENTE = new SimpleGrantedAuthority(ROLE_PREFIX + "utente");
	
	private User user;
	
	public UserDetailsImpl(User user) {
		super();
		this.user = user;
		System.out.println("PorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodio"+user);	

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("PorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodioPorcodio"+user);	

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		
		result.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));

		if (user.getRole().equals("gestore")) {
			result.add(ROLE_GESTORE);
		}
		
		if (user.getRole().equals("utente")) {
			result.add(ROLE_UTENTE);
		}
		
		return result;
	}

	

	
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "UserDetailsImpl [username=" + user.getUsername() + "]";
	}
	public User getUser() {
		return user;
	}
	

}

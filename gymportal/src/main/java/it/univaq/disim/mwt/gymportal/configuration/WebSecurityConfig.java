package it.univaq.disim.mwt.gymportal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);

	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.headers().disable().csrf().disable().formLogin().loginPage("/login").loginProcessingUrl("/login")
				.failureUrl("/?error=invalidlogin").defaultSuccessUrl("/", false).and().logout()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/common/accessdenied").and()
				.authorizeRequests()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/gym/**","/course/create","/course/delete", "/course/update").hasAnyRole("gestore");

	}
}

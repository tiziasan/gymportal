package it.univaq.disim.mwt.gymportal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.univaq.disim.mwt.gymportal.business.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().disable().csrf().disable().formLogin().loginPage("/login")
				.failureUrl("/login?error=true").defaultSuccessUrl("/profile", false).usernameParameter("user_name")
                .passwordParameter("password").and().logout()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/login").and()
				.authorizeRequests() // Specificare le url che
				.antMatchers("/", "/static/**", "/favicon.ico","/login").permitAll().antMatchers("/common/**").authenticated()
				.antMatchers("/gym/**","/course/create","/course/update","/course/delete","/gym/delete","/gym/delete","/gym/delete").hasAuthority("gestore")
		        .antMatchers("/gym/region","/feedback/**","/feedbackCourse/**").hasAuthority("utente");


	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
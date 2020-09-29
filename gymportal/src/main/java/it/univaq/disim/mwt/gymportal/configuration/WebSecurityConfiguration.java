package it.univaq.disim.mwt.gymportal.configuration;

import it.univaq.disim.mwt.gymportal.domain.Role;
import it.univaq.disim.mwt.gymportal.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
				.antMatchers("/", "/static/**", "/favicon.ico","/login","/chat/**").permitAll().antMatchers("/common/**").authenticated()
				.antMatchers("/gym/**","/course/create","/course/update","/course/delete","/gym/delete","/gym/delete","/gym/delete","/feedbackCourse/{id}","/feedback/{id}").hasAuthority(Role.Values.MANAGER)
		        .antMatchers("/gym/region","/feedback/**","/feedbackCourse/**","/feedback/{id}","/feedbackCourse/{id}").hasAuthority(Role.Values.CUSTOMER);


	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
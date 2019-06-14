package com.heno.fullback.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()
				.mvcMatchers("/book/*").hasAnyRole("USER", "ADMIN")
				.mvcMatchers("/book").hasAnyRole("USER", "ADMIN");

		//FIXME
//		JsonUserPassAuthFilter jsonUserPassAuthFilter = new JsonUserPassAuthFilter(authenticationManager());
//		jsonUserPassAuthFilter.setPasswordParameter("password");
//		jsonUserPassAuthFilter.setUsernameParameter("email");
//
//		jsonUserPassAuthFilter
//				.setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
//		jsonUserPassAuthFilter
//				.setAuthenticationSuccessHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_OK));
//
//		http.addFilterAt(jsonUserPassAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//
//		http.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN));
//
//		http
//				.logout()
//				.logoutUrl("/logout")
//				.logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
//				.invalidateHttpSession(true);
	}
}

package com.heno.fullback.config;

import com.heno.fullback.common.security.JsonUserPassAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/api/member/*").permitAll()
				.mvcMatchers(HttpMethod.GET, "/api/members").permitAll()
				.mvcMatchers(HttpMethod.POST, "/api/member").hasAnyRole("PRODUCT_OWNER", "ADMIN")
				.mvcMatchers(HttpMethod.DELETE, "/api/member/*").hasAnyRole("PRODUCT_OWNER", "ADMIN")
				.mvcMatchers(HttpMethod.PUT, "/api/member").permitAll();

		JsonUserPassAuthFilter jsonUserPassAuthFilter =
				new JsonUserPassAuthFilter(authenticationManager());

		jsonUserPassAuthFilter
				.setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
		jsonUserPassAuthFilter
				.setAuthenticationSuccessHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_OK));

		http.addFilterAt(jsonUserPassAuthFilter, UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

		http.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN));

		http.logout().logoutUrl("/logout")
				.logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
				.invalidateHttpSession(true);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4, new SecureRandom());
	}
}

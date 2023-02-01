package com.ionix.testdev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.ionix.testdev.auth.AutheticationFilter;
import com.ionix.testdev.common.CommonConstants;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@Configuration
public class WebSecurityConfig  {
	
	@Value("${spring.security.user.name}")
	public String user;
	
	@Value("${spring.security.user.password}")
	public String password;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AutheticationFilter AutheticationFilter = new AutheticationFilter();
	    http
	      .csrf().disable()
	      .authorizeRequests()
	  	  .antMatchers(CommonConstants.URLS_USER_LIST,CommonConstants.URLS_USER_BY_EMAIL).permitAll()
		  .antMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
	      .anyRequest().authenticated().and()
	        .addFilter(AutheticationFilter)
     		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .httpBasic();
	    return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager managerUser = new InMemoryUserDetailsManager ();
		managerUser.createUser(User.withUsername(user)
				.password(passwordEncoder().encode(password))
				.roles()
				.build());
		return managerUser;
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService())
				.and().build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

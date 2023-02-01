package com.ionix.testdev.auth;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AutheticationFilter extends UsernamePasswordAuthenticationFilter{
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthCredentials credenciales = new ObjectMapper().readValue(request.getInputStream(), AuthCredentials.class);
			
			UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
					credenciales.getUser(), credenciales.getPassword(), Collections.emptyList());
			return getAuthenticationManager().authenticate(userAuth);
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException, java.io.IOException {

		super.successfulAuthentication(request, response, chain, auth);
		
	
	}

}

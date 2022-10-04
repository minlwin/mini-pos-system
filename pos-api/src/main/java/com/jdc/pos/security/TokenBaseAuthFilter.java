package com.jdc.pos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class TokenBaseAuthFilter extends OncePerRequestFilter{

	@Value("${app.jwt.token}")
	private String tokenName;
	
	@Autowired
	private TokenProvider provider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		provider.authenticate(request.getHeader(tokenName));
	}

}

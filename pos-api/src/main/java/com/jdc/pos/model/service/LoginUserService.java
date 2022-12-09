package com.jdc.pos.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdc.pos.model.dto.input.LoginDto;
import com.jdc.pos.model.dto.output.LoginResult;
import com.jdc.pos.model.repo.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoginUserService {

	@Autowired
	private EmployeeRepo repo;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	public LoginResult login(LoginDto dto) {
		
		var auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
		SecurityContextHolder.getContext().setAuthentication(auth);

		return repo.findOneByEmail(dto.email())
				.map(LoginResult::new)
				.orElseThrow(() -> new EntityNotFoundException());
	}
}

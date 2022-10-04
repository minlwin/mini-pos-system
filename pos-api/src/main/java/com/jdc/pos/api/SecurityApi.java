package com.jdc.pos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.pos.model.dto.input.LoginDto;
import com.jdc.pos.model.dto.output.LoginResult;
import com.jdc.pos.model.service.LoginUserService;

@RestController
public class SecurityApi {

	@Autowired
	private LoginUserService service;

	@PostMapping("/login")
	LoginResult login(@RequestBody LoginDto dto) {
		return service.login(dto);
	}
}

package com.jdc.pos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.pos.model.dto.input.ChangePassDto;
import com.jdc.pos.model.dto.output.MessageResult;
import com.jdc.pos.model.service.EmployeeService;

@RestController
@RequestMapping("member")
public class MemberApi {
	
	@Autowired
	private EmployeeService service;

	@PostMapping("password")
	MessageResult changePass(@RequestBody ChangePassDto dto) {
		
		var auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth instanceof UsernamePasswordAuthenticationToken token) {
			return service.changePass(token.getName(), dto);
		}
		
		return new MessageResult(false, "No login User.");
	}
}

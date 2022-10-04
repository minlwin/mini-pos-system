package com.jdc.pos.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.pos.model.dto.input.EmployeeDto;
import com.jdc.pos.model.dto.output.EmployeeVo;
import com.jdc.pos.model.entity.EmployeeRole.Role;
import com.jdc.pos.model.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeApi {

	@Autowired
	private EmployeeService service;
	
	@GetMapping
	List<EmployeeVo> search(Optional<Role> role, Optional<String> name) {
		return service.search(role, name);
	}
	
	@PostMapping
	EmployeeVo create(EmployeeDto dto) {
		return service.create(dto);
	}
	
	@PatchMapping("{id}")
	EmployeeVo update(@PathVariable int id, @RequestBody EmployeeDto dto) {
		return service.update(id, dto);
	}
}

package com.jdc.pos.model.dto.output;

import java.util.List;

import com.jdc.pos.model.entity.Employee;
import com.jdc.pos.model.entity.EmployeeRole.Role;

public record LoginResult(int id, String name, String email, List<Role> roles) {

	public LoginResult(Employee e) {
		this(e.getId(), e.getName(), e.getEmail(), e.getRoles().stream().map(a -> a.getRole()).toList());
	}
}

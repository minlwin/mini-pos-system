package com.jdc.pos.model.dto.output;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.pos.model.entity.Employee;
import com.jdc.pos.model.entity.EmployeeRole.Role;

public record EmployeeVo(
		int id, 
		String name, 
		String email, 
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate assign,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate retire,
		boolean pending,
		List<Role> roles) {

	public EmployeeVo(Employee emp) {
		this(
			emp.getId(), 
			emp.getName(), 
			emp.getEmail(), 
			emp.getAssign(), 
			emp.getRetire(), 
			emp.isPending(),
			emp.getRoles().stream().map(r -> r.getRole()).toList());
	}
}

package com.jdc.pos.model.dto.input;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.pos.model.entity.Employee;
import com.jdc.pos.model.entity.EmployeeRole;
import com.jdc.pos.model.entity.EmployeeRole.Role;

public record EmployeeDto(
		String name,
		String email,
		String password,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		LocalDate assign
) {
	
	public Employee employee() {
		var emp = new Employee();
		emp.setName(name);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setAssign(assign);
		
		var role = new EmployeeRole();
		role.setEmployee(emp);
		role.setRole(Role.Sale);
		
		emp.setRoles(List.of(role));
		
		return emp;
	}
}

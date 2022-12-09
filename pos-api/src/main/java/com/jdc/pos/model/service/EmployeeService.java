package com.jdc.pos.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.pos.model.dto.input.ChangePassDto;
import com.jdc.pos.model.dto.input.EmployeeDto;
import com.jdc.pos.model.dto.output.EmployeeVo;
import com.jdc.pos.model.dto.output.MessageResult;
import com.jdc.pos.model.entity.Employee;
import com.jdc.pos.model.entity.EmployeeRole.Role;
import com.jdc.pos.model.repo.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@PreAuthorize("hasAuthority('Manager')")
	@Transactional
	public EmployeeVo create(EmployeeDto dto) {
		var emp = dto.employee();
		emp.setPassword(encoder.encode(dto.password()));
		var result = repo.save(emp);
		return new EmployeeVo(result);
	}

	@PreAuthorize("hasAuthority('Manager')")
	public List<EmployeeVo> search(Optional<Role> role, Optional<String> name) {
		
		Specification<Employee> spec = Specification.where(null);
		
		var specRole = role.map(a -> {
			Specification<Employee> where = (root, query, builder) -> 
				builder.equal(root.get("roles").get("role"), a);
			return where;
		}).orElse(Specification.where(null));
		spec = spec.and(specRole);
		
		var specName = name.map(a -> {
			Specification<Employee> where = (root, query, builder) -> 
				builder.like(builder.lower(root.get("name")), a.toLowerCase().concat("%"));
			return where;
		}).orElse(Specification.where(null));
		spec = spec.and(specName);
			
		return repo.findAll(spec).stream().map(EmployeeVo::new).toList();
	}

	@Transactional
	@PreAuthorize("hasAuthority('Manager')")
	public EmployeeVo update(int id, EmployeeDto dto) {
		var emp = repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		emp.setName(dto.name());
		return new EmployeeVo(emp);
	}

	@Transactional
	@PreAuthorize("#username == authentication.principal.username")
	public MessageResult changePass(String username, ChangePassDto dto) {
		var emp = repo.findOneByEmail(username).orElseThrow();
		
		if(encoder.matches(dto.oldPass(), emp.getPassword())) {
			emp.setPassword(encoder.encode(dto.newPass()));
			return new MessageResult(true, "Password has been changed.");
		}
		
		return new MessageResult(false, "Please check old password.");
	}
}

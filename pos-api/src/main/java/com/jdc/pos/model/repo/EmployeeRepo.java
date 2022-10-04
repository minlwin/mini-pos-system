package com.jdc.pos.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{

	Optional<Employee> findOneByEmail(String email);
}

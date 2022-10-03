package com.jdc.pos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.EmployeeRole;

public interface EmployeeRoleRepo extends JpaRepository<EmployeeRole, Integer>, JpaSpecificationExecutor<EmployeeRole>{

}

package com.jdc.pos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.Sale;

public interface SaleRepo extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale>{

}

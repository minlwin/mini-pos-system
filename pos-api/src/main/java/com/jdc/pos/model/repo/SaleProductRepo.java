package com.jdc.pos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.SaleProduct;

public interface SaleProductRepo extends JpaRepository<SaleProduct, Long>, JpaSpecificationExecutor<SaleProduct>{

}

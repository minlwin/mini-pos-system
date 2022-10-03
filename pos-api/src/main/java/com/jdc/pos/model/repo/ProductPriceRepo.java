package com.jdc.pos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.ProductPrice;

public interface ProductPriceRepo extends JpaRepository<ProductPrice, Long>, JpaSpecificationExecutor<ProductPrice>{

}

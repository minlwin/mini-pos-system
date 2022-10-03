package com.jdc.pos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.pos.model.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{

}

package com.jdc.pos.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.jdc.pos.model.dto.output.CategoryVo;
import com.jdc.pos.model.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{

	@Query("""
			select new com.jdc.pos.model.dto.output.CategoryVo(c.id, c.name, c.color, count(p)) 
			from Category c join c.product p where c.id = :id
			""")
	CategoryVo findVoById(int id);
	
	@Query("""
			select new com.jdc.pos.model.dto.output.CategoryVo(c.id, c.name, c.color, count(p)) 
			from Category c join c.product p
			""")
	List<CategoryVo> findAllVo();
	
	@Query("""
			select new com.jdc.pos.model.dto.output.CategoryVo(c.id, c.name, c.color, count(p)) 
			from Category c join c.product p where lower(c.name) = lower(:name) 
			""")
	List<CategoryVo> findVoByNameStart(String name);
	

}

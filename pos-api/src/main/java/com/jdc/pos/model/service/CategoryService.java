package com.jdc.pos.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.pos.model.dto.input.CategoryDto;
import com.jdc.pos.model.dto.output.CategoryVo;
import com.jdc.pos.model.repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo repo;

	public List<CategoryVo> search(Optional<String> name) {
		return name.map(a -> repo.findVoByNameStart(a.concat("%")))
				.orElse(repo.findAllVo());
	}

	@Transactional
	public CategoryVo create(CategoryDto dto) {
		var entity = repo.save(dto.entity());
		return repo.findVoById(entity.getId());
	}

	@Transactional
	public CategoryVo update(int id, CategoryDto dto) {
		var entity = repo.findById(id).orElseThrow();
		entity.setName(dto.name());
		entity.setColor(dto.color());
		return repo.findVoById(entity.getId());
	}

}

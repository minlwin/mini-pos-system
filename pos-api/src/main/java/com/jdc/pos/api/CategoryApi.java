package com.jdc.pos.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.pos.model.PosValidationException;
import com.jdc.pos.model.dto.input.CategoryDto;
import com.jdc.pos.model.dto.output.CategoryVo;
import com.jdc.pos.model.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryApi {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	List<CategoryVo> search(@RequestParam Optional<String> name) {
		return service.search(name);
	}
	
	@PostMapping
	CategoryVo create(@RequestBody @Validated CategoryDto dto, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new PosValidationException(result);
		}
		
		return service.create(dto);
	}
	
	@PatchMapping("{id}")
	CategoryVo update(@PathVariable int id, @RequestBody @Validated CategoryDto dto, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new PosValidationException(result);
		}

		return service.update(id, dto);
	}
	
}

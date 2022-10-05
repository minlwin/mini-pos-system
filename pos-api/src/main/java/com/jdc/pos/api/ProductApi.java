package com.jdc.pos.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.pos.model.dto.input.PriceDto;
import com.jdc.pos.model.dto.input.ProductDto;
import com.jdc.pos.model.dto.input.StateDto;
import com.jdc.pos.model.dto.output.ProductDetailsVo;
import com.jdc.pos.model.dto.output.ProductListVo;
import com.jdc.pos.model.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductApi {
	
	@Autowired
	private ProductService service;

	@GetMapping
	List<ProductListVo> search(
			Optional<Integer> category,
			Optional<String> name
			) {
		return service.search(category, name);
	}
	
	@GetMapping("{id}")
	ProductDetailsVo findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	ProductDetailsVo create(@RequestBody ProductDto dto) {
		return service.create(dto);
	}
	
	@PatchMapping("{id}")
	ProductDetailsVo update(@PathVariable int id, @RequestBody ProductDto dto) {
		return service.update(id, dto);
	}
	
	@PostMapping("{id}/price")
	ProductDetailsVo updatePrice(@PathVariable int id, @RequestBody PriceDto dto) {
		return service.updatePrice(id, dto);
	}
	
	@PatchMapping("{id}/state")
	ProductDetailsVo setSoldOut(@PathVariable int id, @RequestBody StateDto<Boolean> dto) {
		return service.changeState(id, dto);
	}
	
	@PostMapping("{id}/photo")
	ProductDetailsVo uploadPhoto(@PathVariable int id, @RequestParam MultipartFile file) {
		return service.uploadPhoto(id, file);
	}
}

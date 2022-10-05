package com.jdc.pos.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.pos.model.PosBusinessException;
import com.jdc.pos.model.dto.input.PriceDto;
import com.jdc.pos.model.dto.input.ProductDto;
import com.jdc.pos.model.dto.input.StateDto;
import com.jdc.pos.model.dto.output.ProductDetailsVo;
import com.jdc.pos.model.dto.output.ProductListVo;
import com.jdc.pos.model.entity.Product;
import com.jdc.pos.model.entity.ProductPrice;
import com.jdc.pos.model.repo.CategoryRepo;
import com.jdc.pos.model.repo.ProductPriceRepo;
import com.jdc.pos.model.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ProductPriceRepo priceRepo;
	@Autowired
	private CategoryRepo catRepo;
	@Autowired
	private ImageUploadService imageService;

	public List<ProductListVo> search(Optional<Integer> category, Optional<String> name) {
		
		Specification<Product> spec = Specification.where(null);
		
		var whereCategory = category.filter(a -> a > 0).map(a -> {
			Specification<Product> where = (root, query, builder) -> 
				builder.equal(root.get("category").get("id"), a);
			return where;
		}).orElse(Specification.where(null));
		spec = spec.and(whereCategory);
		
		var whereName = name.filter(StringUtils::hasLength).map(a -> {
			Specification<Product> where = (root, query, builder) -> 
				builder.like(builder.lower(root.get("name")), a.toLowerCase().concat("%"));
			return where;
		}).orElse(Specification.where(null));
		spec = spec.and(whereName);
		
		return productRepo.findAll(spec).stream().map(ProductListVo::from).toList();
	}

	public ProductDetailsVo findById(int id) {
		return productRepo.findById(id).map(ProductDetailsVo::new).orElseThrow();
	}

	@Transactional
	public ProductDetailsVo create(ProductDto dto) {
		var product = new Product();
		product.setName(dto.name());
		product.setCategory(catRepo.getReferenceById(dto.category()));
		product = productRepo.save(product);
		
		var price = new ProductPrice();
		price.setPrice(dto.price());
		price.setProduct(product);
		price.setRefDate(LocalDate.now());
		priceRepo.save(price);
		
		return findById(product.getId());
	}

	@Transactional
	public ProductDetailsVo update(int id, ProductDto dto) {

		return productRepo.findById(id)
				.map(a -> {
					a.setCategory(catRepo.getReferenceById(dto.category()));
					a.setName(dto.name());
					return a.getId();
				})
				.map(this::findById).orElseThrow();
	}

	@Transactional
	public ProductDetailsVo updatePrice(int id, PriceDto dto) {
		if(priceRepo.countByProductIdAndRefDate(id, dto.refDate()) > 0) {
			throw new PosBusinessException("You can't create price with the same date.");
		}
		
		var price = new ProductPrice();
		price.setProduct(productRepo.getReferenceById(id));
		price.setRefDate(dto.refDate());
		price.setPrice(dto.price());
		priceRepo.save(price);
		
		return findById(id);
	}

	@Transactional
	public ProductDetailsVo changeState(int id, StateDto<Boolean> dto) {
		return productRepo.findById(id)
				.map(a -> {
					a.setSoldOut(dto.state());
					return a.getId();
				})
				.map(this::findById).orElseThrow();
	}

	@Transactional
	public ProductDetailsVo uploadPhoto(int id, MultipartFile file) {
		return productRepo.findById(id)
				.map(a -> {
					a.setImage(imageService.upload(id, file));
					return a.getId();
				})
				.map(this::findById).orElseThrow();
	}


}

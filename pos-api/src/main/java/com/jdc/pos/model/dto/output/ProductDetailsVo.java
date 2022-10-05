package com.jdc.pos.model.dto.output;

import java.util.List;

import com.jdc.pos.model.entity.Product;

public record ProductDetailsVo(
		ProductListVo product,
		List<ProductPriceVo> prices
) {

	public ProductDetailsVo(Product entity) {
		this(ProductListVo.from(entity), entity.getPrices().stream().map(ProductPriceVo::new).toList());
	}
}

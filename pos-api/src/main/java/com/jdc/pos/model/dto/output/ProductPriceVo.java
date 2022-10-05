package com.jdc.pos.model.dto.output;

import java.time.LocalDate;

import com.jdc.pos.model.entity.ProductPrice;

public record ProductPriceVo(
		long id,
		LocalDate refDate,
		int price
) {

	public ProductPriceVo(ProductPrice entity) {
		this(entity.getId(), entity.getRefDate(), entity.getPrice());
	}
}

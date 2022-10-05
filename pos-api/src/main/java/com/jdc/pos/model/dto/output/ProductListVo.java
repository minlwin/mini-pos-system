package com.jdc.pos.model.dto.output;

import java.time.LocalDate;

import com.jdc.pos.model.entity.Product;

public record ProductListVo(int id, String name, int categoryId, String categoryName, int categoryColor, int price,
		LocalDate refDate, boolean soldOut) {

	public static ProductListVo from(Product p) {
		
		var price = p.getPrices().stream().filter(a -> a.getRefDate().equals(LocalDate.now()) || a.getRefDate().isBefore(LocalDate.now()))
				.sorted((a, b) -> a.getRefDate().compareTo(b.getRefDate())).findFirst().orElseThrow();
		
		return new ProductListVo(p.getId(), p.getName(), p.getCategory().getId(), p.getCategory().getName(),
				p.getCategory().getColor(), price.getPrice(), price.getRefDate(), p.isSoldOut());
	}
}

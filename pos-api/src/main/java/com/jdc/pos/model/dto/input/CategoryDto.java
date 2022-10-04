package com.jdc.pos.model.dto.input;

import com.jdc.pos.model.entity.Category;

public record CategoryDto(
		String name,
		int color
) {

	public Category entity() {
		var entity = new Category();
		entity.setName(name);
		entity.setColor(color);
		return entity;
	}
}

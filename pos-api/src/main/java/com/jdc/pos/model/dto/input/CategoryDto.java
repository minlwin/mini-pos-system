package com.jdc.pos.model.dto.input;

import com.jdc.pos.model.entity.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
		@NotBlank(message = "Please enter category name.")
		String name,
		@Min(value = 1, message = "Please select category color.")
		int color
) {

	public Category entity() {
		var entity = new Category();
		entity.setName(name);
		entity.setColor(color);
		return entity;
	}
}

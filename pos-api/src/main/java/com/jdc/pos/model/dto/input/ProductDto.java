package com.jdc.pos.model.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
	@NotBlank(message = "Please enter product name.")
	String name,
	@NotNull(message = "Please select category.")
	@Min(value = 1, message = "Please select category.")
	Integer category,
	@NotNull(message = "Please enter price.")
	@Min(value = 1, message = "Please enter price.")
	Integer price
) {

}

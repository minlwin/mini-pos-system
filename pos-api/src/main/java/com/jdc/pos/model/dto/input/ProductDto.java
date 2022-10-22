package com.jdc.pos.model.dto.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

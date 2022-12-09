package com.jdc.pos.model.dto.input;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record PriceDto(
		@NotNull(message = "Enter Reference Date for price.")
		LocalDate refDate,
		@Min(value = 1, message = "Please enter price.")
		int price
) {

}

package com.jdc.pos.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record PriceDto(
		@NotNull(message = "Enter Reference Date for price.")
		LocalDate refDate,
		@Min(value = 1, message = "Please enter price.")
		int price
) {

}

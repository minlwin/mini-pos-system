package com.jdc.pos.model.dto.input;

import java.time.LocalDate;

public record PriceDto(
		LocalDate refDate,
		int price
) {

}

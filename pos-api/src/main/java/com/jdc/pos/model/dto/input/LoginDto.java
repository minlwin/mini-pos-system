package com.jdc.pos.model.dto.input;

import javax.validation.constraints.NotBlank;

public record LoginDto(
		@NotBlank(message = "Please enter email for login.")
		String email,
		@NotBlank(message = "Please enter password for login.")
		String password
) {

}

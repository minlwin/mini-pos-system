package com.jdc.pos.model.dto.input;

import javax.validation.constraints.NotBlank;

public record ChangePassDto(
		@NotBlank(message = "Please enter old password.")
		String oldPass,
		@NotBlank(message = "Please enter new password.")
		String newPass
) {

}

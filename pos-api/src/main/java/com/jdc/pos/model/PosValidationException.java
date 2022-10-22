package com.jdc.pos.model;

import java.util.List;

import org.springframework.validation.BindingResult;

public class PosValidationException extends RuntimeException{
	
	private List<String> messages;

	private static final long serialVersionUID = 1L;
	
	public PosValidationException(BindingResult result) {
		messages = result.getFieldErrors().stream()
				.map(a -> a.getDefaultMessage()).toList();
	}
	
	public List<String> getMessages() {
		return messages;
	}
}

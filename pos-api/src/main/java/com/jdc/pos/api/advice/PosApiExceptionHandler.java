package com.jdc.pos.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.pos.model.PosValidationException;
import com.jdc.pos.model.dto.output.ErrorResult;
import com.jdc.pos.model.dto.output.ErrorResult.Type;

@RestControllerAdvice
public class PosApiExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	ErrorResult handle(PosValidationException e) {
		return new ErrorResult(Type.Validation, e.getMessages());
	}
}

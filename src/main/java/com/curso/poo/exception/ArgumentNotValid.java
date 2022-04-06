package com.curso.poo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.poo.erros.ApiErros;

@RestControllerAdvice
public class ArgumentNotValid {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros argumentInvalid(MethodArgumentNotValidException ex) {
		BindingResult messageBinding = ex.getBindingResult();
		List<String> message = messageBinding.getAllErrors().stream()
				.map(ObjectError -> ObjectError.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiErros(message);
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<String> respMess(HttpMessageNotWritableException exce) {
		String msg = exce.getLocalizedMessage();
		
		return ResponseEntity.ok(msg);
		
	}
}

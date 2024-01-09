package com.example.demo.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleBussinessException(MethodArgumentNotValidException ex)
	{
		Map<String, String> errormap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errormap.put(error.getField(),error.getDefaultMessage());
		});
		errormap.put("status", "Failed");
		errormap.put("statusCode", "400");
		return errormap;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,WebRequest request) 
	{
		List<String> list=new ArrayList<>();
		list.add(ex.getMessage());
		return new ResponseEntity<>(list,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

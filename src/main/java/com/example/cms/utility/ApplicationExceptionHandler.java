package com.example.cms.utility;

import java.util.HashMap;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.UserAlreadyExistByEmailException;

import lombok.AllArgsConstructor;
@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {
	
	private ErrorStructure<String> structure ;
	
	


	private ResponseEntity<ErrorStructure<String>> errorResponse(
			 HttpStatus status,String message,String rootCause) {
		return new ResponseEntity<ErrorStructure<String>>(structure
				.setStatuscode(status.value())
				.setMessage(message)
				.setRootCause(rootCause),status);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(
			UserAlreadyExistByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),
				"User Already exists with the given email ID");
	}
}

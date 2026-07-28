package com.pkr.spring.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.pkr.spring.dto.HandleExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<?> handleNumberFormatException(NumberFormatException e){
		log.info("NumberFormatException due to: {}",e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HandleExceptionResponse("ERROR",-1L,"Please provide compatible numbers only."));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		log.info("HttpMessageNotReadableException due to: {}",e.getMessage());
		e.getMostSpecificCause();
		String[] errors = e.getMostSpecificCause().toString().split(":");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HandleExceptionResponse("ERROR",-1L, errors[1]));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		log.info("MethodArgumentNotValidException due to: {}",e.getMessage());
		Map<String, String> errors = new HashMap<>();
//		e.getBindingResult().getFieldErrors().stream().map(error->errors.put(error.getField(), error.getDefaultMessage()));
		e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HandleExceptionResponse("ERROR", -1L,errors.values().toString() ));
		
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
		log.info("MethodArgumentTypeMismatchException due to: {}",e.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new HandleExceptionResponse(null,-1L, "Please provide a valid argument value.")); 
	}
	
	@ExceptionHandler(HospitalException.class)
	public ResponseEntity<?> handleHospitalException(HospitalException e){
		log.info("HospitalException due to: {}",e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new HandleExceptionResponse(null,-1L, e.getMessage())); 
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e){
		log.info("NoResourceFoundException due to: {}",e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new HandleExceptionResponse(null,-1L, "Please enter a valid endpoint.")); 
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException e){
		log.info("HttpRequestMethodNotSupportedException due to: {}",e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HandleExceptionResponse("Method not allowed.",-1L,"Please enter appropriate Http Method.")); 
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		log.info("Exception due to: {}",e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HandleExceptionResponse(null,-1L, e.getMessage())); 
	}
	
}

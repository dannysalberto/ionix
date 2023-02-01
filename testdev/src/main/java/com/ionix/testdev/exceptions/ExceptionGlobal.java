package com.ionix.testdev.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ionix.testdev.common.CommonConstants;
import com.ionix.testdev.responses.GeneralResponse;


@ControllerAdvice	
public class ExceptionGlobal {	
	
	String sListError="";
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleException(Exception ex) {
	        return new ResponseEntity<GeneralResponse>(GeneralResponse.builder()
					.code(CommonConstants.ERROR_CODE)
					.message(ex.getMessage()).build(), 
					HttpStatus.INTERNAL_SERVER_ERROR);		
    }

	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
	        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	        sListError="";
	        fieldErrors.forEach(errorField->{
	        	sListError = sListError.concat(errorField.getDefaultMessage()).concat(", ");
	        });
	        return new ResponseEntity<GeneralResponse>(GeneralResponse.builder()
					.code(CommonConstants.BADREQUEST_CODE)
					.message(sListError).build(), 
					HttpStatus.BAD_REQUEST);		
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponse> supplierNotFoundException(UserNotFoundException ex) {
    	return new ResponseEntity<GeneralResponse>(GeneralResponse.builder()
					.code(CommonConstants.NOTFOUND_CODE)
					.message(ex.getLocalizedMessage()).build(), 
					HttpStatus.NOT_FOUND);		
    }
    
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralResponse> badRequestException(BadRequestException ex) {
        return new ResponseEntity<GeneralResponse>(GeneralResponse.builder()
					.code(CommonConstants.BADREQUEST_CODE)
					.message(ex.getLocalizedMessage()).build(), 
					HttpStatus.BAD_REQUEST);		
    }
    
}
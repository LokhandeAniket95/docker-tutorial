package com.example.employeeData.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeeData.model.ErrorResponse;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {EmployeeException.class})
    public ResponseEntity<Object> handleCityDoesNotExistsException(EmployeeException employeeException) {
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(employeeException.getMessage());
		error.setTimeStamp(new Date());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

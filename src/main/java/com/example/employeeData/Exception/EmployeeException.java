package com.example.employeeData.Exception;

public class EmployeeException extends RuntimeException {

	private String message;
	
	public EmployeeException(String message) {
        super(message);
        this.message = message;
    }
	public EmployeeException() {
    }
}

package com.example.employeeData.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface EmployeeDataService {
	
	public ResponseEntity<String> getEmployeeListOfEmailId(String id) throws JsonParseException, JsonMappingException, IOException;

}

package com.example.employeeData.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeData.service.EmployeeDataService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController("/Employee")
public class EmployeeDataController {
	
	@Autowired
	EmployeeDataService employeeDataService;

	@GetMapping("/getEmployeeListOfEmailId")
	public ResponseEntity<String> getEmployeeListOfEmailId(@RequestParam String id) throws JsonParseException, JsonMappingException, IOException {
		return employeeDataService.getEmployeeListOfEmailId(id);
	}
}

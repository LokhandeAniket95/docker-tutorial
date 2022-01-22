package com.example.employeeData.servieImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.employeeData.Exception.EmployeeException;
import com.example.employeeData.model.Data;
import com.example.employeeData.model.EmployeeDetails;
import com.example.employeeData.service.EmployeeDataService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeDataServiceImpl implements EmployeeDataService {
	
	@Value("${get.employee.data}")
	public String employeeDataUrl;

	@Override
	public ResponseEntity<String> getEmployeeListOfEmailId(String id)
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper objectMapper = new ObjectMapper();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = null;
		ResponseEntity<String> responseEntity = null;
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(employeeDataUrl+"/"+id);
		HttpEntity entity = new HttpEntity(headers);
		try {
			responseEntity = template.exchange(uriBuilder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				EmployeeDetails employeeDetails = objectMapper.readValue(responseEntity.getBody(),
						EmployeeDetails.class);
				Data employeeData = null;
				if(employeeDetails != null) {
					employeeData = employeeDetails.getData();
					
				}
				response = new ResponseEntity<>(employeeData.getEmail().toString(), headers, HttpStatus.OK);
			}
		} catch (HttpClientErrorException e) {
			throw new EmployeeException("Data not found");
		}
		return response;
	}
}

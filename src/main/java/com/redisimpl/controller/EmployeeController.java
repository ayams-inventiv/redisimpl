package com.redisimpl.controller;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;
import com.redisimpl.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO){
		EmpDetails employee = employeeService.createEmployee(employeeDetailsDTO);
		if(Objects.nonNull(employee)) {
			return new ResponseEntity(employee, HttpStatus.CREATED);
		}else {
			return new ResponseEntity(Collections.emptyList(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/employee")
	public ResponseEntity<Object> getAllEmployee(){
		return new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);
	}
}

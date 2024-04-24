package com.redisimpl.controller;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;
import com.redisimpl.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO) {
		EmpDetails employee = employeeService.createEmployee(employeeDetailsDTO);
		if (Objects.nonNull(employee)) {
			return new ResponseEntity(employee, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(Collections.emptyList(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/employee")
	public ResponseEntity<Object> getAllEmployee() {
		return new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@GetMapping("/getEmployeeByUserId")
	public ResponseEntity<Object> getEmployeeByUserId(@RequestParam Long userId) {
		EmpDetails allEmployeeByUserId = employeeService.getEmployeeByUserId(userId);
		if (Objects.nonNull(allEmployeeByUserId)) {
			return new ResponseEntity(allEmployeeByUserId, HttpStatus.OK);
		} else {
			return new ResponseEntity("USER_NOT_FOUND", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Object> updateEmployeeDetails(@RequestParam Long userId,
			@RequestBody EmployeeDetailsDTO employeeDetailsDTO) {
		EmpDetails allEmployeeByUserId = employeeService.updateEmployeeDetails(userId, employeeDetailsDTO);
		if (Objects.nonNull(allEmployeeByUserId)) {
			return new ResponseEntity(allEmployeeByUserId, HttpStatus.OK);
		} else {
			return new ResponseEntity("USER_NOT_FOUND", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/employee")
	public ResponseEntity<Object> updateEmployeeDetails(@RequestParam Long userId) {
		EmpDetails allEmployeeByUserId = employeeService.deleteEmployee(userId);
		if (Objects.nonNull(allEmployeeByUserId)) {
			return new ResponseEntity(allEmployeeByUserId, HttpStatus.OK);
		} else {
			return new ResponseEntity("USER_NOT_FOUND", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/flushcache")
	public ResponseEntity<Object> evictAllEmployeeCache(){
		employeeService.evictAllEmployeeCache();
		return new ResponseEntity("All Caching Removed", HttpStatus.OK);
	}
	
	@GetMapping("/flushCacheByUserId")
	public ResponseEntity<Object> flushCacheByUserId(@RequestParam Long userId){
		employeeService.evictEmployeeCache(userId);
		return new ResponseEntity("CACHING IS REMOVED FROM USER ID", HttpStatus.OK);
	}

}

package com.redisimpl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;

@Service
public interface EmployeeService {
	EmpDetails createEmployee(EmployeeDetailsDTO employeeDetailsDTO);
	List<EmpDetails> getAllEmployee();
	EmpDetails getEmployeeByUserId(Long userId);
	EmpDetails updateEmployeeDetails(Long userId, EmployeeDetailsDTO employeeDetailsDTO);
	void evictAllEmployeeCache();
	void evictEmployeeCache(Long userId);
	EmpDetails deleteEmployee(Long userId);
}

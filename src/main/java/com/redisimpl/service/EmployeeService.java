package com.redisimpl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;

@Service
public interface EmployeeService {
	EmpDetails createEmployee(EmployeeDetailsDTO employeeDetailsDTO);
	List<EmpDetails> getAllEmployee();
}

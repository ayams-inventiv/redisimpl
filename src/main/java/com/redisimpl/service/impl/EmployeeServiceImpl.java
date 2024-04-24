package com.redisimpl.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;
import com.redisimpl.repository.EmployeeDetailsRepos;
import com.redisimpl.service.EmployeeService;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired EmployeeDetailsRepos employeeDetailsRepos;

	@Override
	public EmpDetails createEmployee(EmployeeDetailsDTO empDto) {
		EmpDetails empDetails = EmpDetails.builder()
				.empAadhar(empDto.getEmpAadhar())
				.empDesignation(null).empDob(LocalDate.now())
				.empEmail(empDto.getEmpEmail()).empFirstName(empDto.getEmpFirstName())
				.empLastName(empDto.getEmpLastName()).employeeName(empDto.getEmployeeName())
				.empPan(empDto.getEmpPan())
				.build();
		return employeeDetailsRepos.save(empDetails);
	}

	@Override
	public List<EmpDetails> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeDetailsRepos.findAll();
	}
}

package com.redisimpl.service.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redisimpl.dto.EmployeeDetailsDTO;
import com.redisimpl.entity.EmpDetails;
import com.redisimpl.repository.EmployeeDetailsRepos;
import com.redisimpl.service.EmployeeService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDetailsRepos employeeDetailsRepos;

	public EmployeeServiceImpl(EmployeeDetailsRepos employeeDetailsRepos) {
		this.employeeDetailsRepos = employeeDetailsRepos;
	}

	@Override
	public EmpDetails createEmployee(EmployeeDetailsDTO empDto) {
		EmpDetails empDetails = EmpDetails.builder().empAadhar(empDto.getEmpAadhar())
				.empDesignation(empDto.getEmpDesignation()).empDob(LocalDate.now()).empEmail(empDto.getEmpEmail())
				.empFirstName(empDto.getEmpFirstName()).empLastName(empDto.getEmpLastName())
				.employeeName(empDto.getEmployeeName()).empPan(empDto.getEmpPan())
				.permanentResidentPin(empDto.getPermanentResidentPin()).build();
		return employeeDetailsRepos.save(empDetails);
	}

	@Override
	// @Cacheable("employee_chache")
	@Cacheable(value = "employee_cache", key = "'allEmployees'")
	public List<EmpDetails> getAllEmployee() {
		return employeeDetailsRepos.findAll();
	}

	@Override
	@Cacheable(value = "employee_cache", key = "#userId")
	// @Cacheable(value = "employee_cache", key = "#id")
	//@Cacheable(value = "employee_cache_UserId", unless = "#result == null", key = "#userId")
	//@CachePut("employee_cache")
	//@Cacheable(value = "employee_cache", unless = "#result == null", key = "#userId", keyGenerator = "customKeyGenerator")
	public EmpDetails getEmployeeByUserId(Long userId) {
		Optional<EmpDetails> empStream = employeeDetailsRepos.findById(userId);
		if (empStream.isPresent()) {
			return empStream.get();
		}
		return null;
	}

	@Override
	//@Cacheable(value = "employee_cache_UserId", unless = "#result == null", key = "#userId")
	@Cacheable(value = "employee_cache", key = "#userId")
	public EmpDetails updateEmployeeDetails(Long userId, EmployeeDetailsDTO empDTO) {
		Optional<EmpDetails> empStream = employeeDetailsRepos.findById(userId);
		if (empStream.isPresent()) {
			EmpDetails empDetails = empStream.get();
			empDetails.setEmpAadhar(empDTO.getEmpAadhar());
			empDetails.setEmpFirstName(empDTO.getEmpFirstName());
			empDetails.setEmpLastName(empDTO.getEmpLastName());
			empDetails.setEmpDob(empDTO.getEmpDob());
			empDetails.setEmpPan(empDTO.getEmpPan());
			empDetails.setPermanentResidentPin(empDTO.getPermanentResidentPin());
			return employeeDetailsRepos.save(empDetails);
		}
		return null;
	}

	@CacheEvict(value = "employee_cache", key = "#userId")
	public void evictEmployeeCache(Long userId) {
		log.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info("::::::::::::::::::::::::evictAllEmployeeCache for Single User:::::::::::::::::::::::::::::::");
	}

	@CacheEvict(value = "employee_cache", allEntries = true)
	public void evictAllEmployeeCache() {
		log.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info("::::::::::::::::::::::::evictAllEmployeeCache:::::::::::::::::::::::::::::::");
	}

	@Override
	@CacheEvict(value = "employee_cache", key = "#userId")
	public EmpDetails deleteEmployee(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}

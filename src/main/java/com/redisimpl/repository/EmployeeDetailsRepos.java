package com.redisimpl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redisimpl.entity.EmpDetails;

@Repository
public interface EmployeeDetailsRepos extends JpaRepository<EmpDetails, Long> {
	Optional<EmpDetails> findById(Long userId);
}

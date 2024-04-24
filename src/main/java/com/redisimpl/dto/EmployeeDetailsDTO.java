package com.redisimpl.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDTO {
	private String employeeName;
	private String empEmail;
	private LocalDate empDob;
	private String empPan;
	private String empAadhar;
	private String empDesignation;
	private String empFirstName;
	private String empLastName;
	private Integer permanentResidentPin;
}

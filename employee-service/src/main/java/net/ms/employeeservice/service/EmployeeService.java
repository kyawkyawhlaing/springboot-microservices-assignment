package net.ms.employeeservice.service;

import net.ms.employeeservice.model.dto.APIResponseDto;
import net.ms.employeeservice.model.dto.EmployeeDto;

public interface EmployeeService {
	
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    
    APIResponseDto getEmployeeById(Long id);

}

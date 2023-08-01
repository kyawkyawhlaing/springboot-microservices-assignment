package net.ms.employeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.ms.employeeservice.model.dto.EmployeeDto;
import net.ms.employeeservice.model.entity.Employee;

@Mapper
public interface EmployeeMapper {

	EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
	
	EmployeeDto mapToEmployeeDto ( Employee employee);
	
	Employee mapToEmployee ( EmployeeDto employee);
	
}

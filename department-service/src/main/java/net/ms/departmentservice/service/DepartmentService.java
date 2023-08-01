package net.ms.departmentservice.service;

import net.ms.departmentservice.model.dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto deparmentDto);
	
	DepartmentDto getDepartmentByCode(String code);
}

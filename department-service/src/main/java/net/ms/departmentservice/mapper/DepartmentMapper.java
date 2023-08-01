package net.ms.departmentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.ms.departmentservice.model.dto.DepartmentDto;
import net.ms.departmentservice.model.entity.Department;

@Mapper
public interface DepartmentMapper {
	
	DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);
	
	DepartmentDto mapToDepartmentDto(Department department);
	
	Department mapToDepartment(DepartmentDto departmentDto);

}

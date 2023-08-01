package net.ms.departmentservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.ms.departmentservice.exception.ResourceNotFoundException;
import net.ms.departmentservice.mapper.DepartmentMapper;
import net.ms.departmentservice.model.dto.DepartmentDto;
import net.ms.departmentservice.model.entity.Department;
import net.ms.departmentservice.repository.DepartmentRepository;

@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository departmentRepository;
	
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		
//		Department department = modelMapper.map(departmentDto, Department.class);
		Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);
		
//		DepartmentDto savedDepartmentDto = modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
		DepartmentDto savedDepartmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(department);
		
		return savedDepartmentDto;
		
	}

	@Override
	public DepartmentDto getDepartmentByCode(String code) {
		
		Department existingDepartment = departmentRepository.findByDepartmentCode(code).orElseThrow(
					() -> new ResourceNotFoundException("User", "Code", code)
				);
		
//		DepartmentDto departmentDto = modelMapper.map(existingDepartment, DepartmentDto.class);
		DepartmentDto departmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(existingDepartment);
		return departmentDto;
	}
	
}

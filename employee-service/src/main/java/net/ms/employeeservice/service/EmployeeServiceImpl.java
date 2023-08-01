package net.ms.employeeservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.ms.employeeservice.exception.CustomSQLException;
import net.ms.employeeservice.exception.ExceptionUtil;
import net.ms.employeeservice.exception.ResourceNotFoundException;
import net.ms.employeeservice.mapper.EmployeeMapper;
import net.ms.employeeservice.model.dto.EmployeeDto;
import net.ms.employeeservice.model.entity.Employee;
import net.ms.employeeservice.repository.EmployeeRepository;

@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//		Employee employee = modelMapper.map(employeeDto, Employee.class);
		Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);

		Employee savedEmployee = null;

		try {

			savedEmployee = employeeRepository.save(employee);

		} catch (DataIntegrityViolationException e) {
			
			String sqlerrm = ExceptionUtil.extractSQLErrorCode(e);

			throw new CustomSQLException(sqlerrm);

		}

//        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
		EmployeeDto savedEmployeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);

		return savedEmployeeDto;
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		
		Optional<Employee> exsitingEmployee = Optional.of(employeeRepository.findEmployeeById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", id)
				));
		
		return EmployeeMapper.MAPPER.mapToEmployeeDto(exsitingEmployee.get());
		
		
	}

}

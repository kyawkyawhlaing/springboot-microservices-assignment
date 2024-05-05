package net.ms.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import net.ms.employeeservice.exception.CustomSQLException;
import net.ms.employeeservice.exception.ExceptionUtil;
import net.ms.employeeservice.exception.ResourceNotFoundException;
import net.ms.employeeservice.mapper.EmployeeMapper;
import net.ms.employeeservice.model.dto.APIResponseDto;
import net.ms.employeeservice.model.dto.DepartmentDto;
import net.ms.employeeservice.model.dto.EmployeeDto;
import net.ms.employeeservice.model.entity.Employee;
import net.ms.employeeservice.repository.EmployeeRepository;

@SuppressWarnings("unused")
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;
	
	private WebClient webClient;
	
	private APIClient apiClient;

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
	public APIResponseDto getEmployeeById(Long id) {
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("User", "id", id)
				);
		
		EmployeeDto employeeDto= EmployeeMapper.MAPPER.mapToEmployeeDto(existingEmployee);
		
		/*
		 * DepartmentDto departmentDto = webClient.get()
		 * .uri("http://localhost:8081/api/departments/" +
		 * existingEmployee.getDepartmentCode()) .retrieve()
		 * .bodyToMono(DepartmentDto.class) .block();
		 */
		
		
		DepartmentDto departmentDto = apiClient.getDepartmentByCode(existingEmployee.getDepartmentCode());

		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(employeeDto);
		
		return apiResponseDto;
		
		
	}

}

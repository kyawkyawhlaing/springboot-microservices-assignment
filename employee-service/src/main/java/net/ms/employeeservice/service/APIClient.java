package net.ms.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.ms.employeeservice.model.dto.DepartmentDto;


@FeignClient( url="http://localhost:8081", value="DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("api/departments/{department-code}")
	DepartmentDto getDepartmentByCode(@PathVariable("department-code") String code);
}

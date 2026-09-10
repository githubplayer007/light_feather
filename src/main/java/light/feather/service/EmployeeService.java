package light.feather.service;

import java.util.List;

import light.feather.dto.EmployeeDto;

public interface EmployeeService {
	
	List<String> getSupervisors();
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
}

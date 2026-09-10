package light.feather.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import light.feather.dto.EmployeeDto;
import light.feather.entity.Employee;
import light.feather.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/supervisors")
	public ResponseEntity<List<String>> getSuppervisors() {
		logger.info("EmployeeController::getSuppervisors()");
		
		List<String> supervisors = employeeService.getSupervisors();
		
		return ResponseEntity.ok(supervisors);
	}

	@PostMapping("/submit")
	public ResponseEntity<EmployeeDto> postEmployee(@RequestBody EmployeeDto employeeDto) {
		logger.info("EmployeeController::postEmployee(@RequestBody Employee employee)");
		
		EmployeeDto dto = employeeService.saveEmployee(employeeDto);
		
		return ResponseEntity.ok(dto);
	}
}

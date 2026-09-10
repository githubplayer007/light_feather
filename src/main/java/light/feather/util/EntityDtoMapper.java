package light.feather.util;

import java.util.ArrayList;
import light.feather.dto.EmployeeDto;
import light.feather.dto.SupervisorDto;
import light.feather.entity.Employee;
import light.feather.entity.Supervisor;

public class EntityDtoMapper {

	public static Employee dtoToEntity(EmployeeDto dto) {
		return new Employee(null, 
				            dto.getEmail(), 
				            dto.getFirstName(), 
				            dto.getLastName(), 
				            dto.getPhone(), 
				            null);  // supervisor
	}
	
	public static EmployeeDto entityToDto(Employee entity) {
		return new EmployeeDto(entity.getId(), 
				               entity.getEmail(), 
				               entity.getFirstName(), 
				               entity.getLastName(), 
				               entity.getPhone(), 
				               entity.getSupervisor().getId());
	}
	
	
	public static Supervisor dtoToEntity(SupervisorDto dto) {
		return new Supervisor(dto.getId(), 
				              dto.getPhone(), 
				              dto.getFirstName(), 
				              dto.getLastName(),
				              dto.getJurisdiction(),
				              dto.getIdentificationNumber(),
				              new ArrayList<Employee>());
	}
	
	public static SupervisorDto dtoToEntity(Supervisor entity) {
		//List<EmployeeDto> employeeDtos = entity.getEmployees().stream().map(employee->entityToDto(employee)).toList();
		return new SupervisorDto(entity.getId(), 
				                 entity.getPhone(),
				                 entity.getFirstName(), 
				                 entity.getLastName(),
				                 entity.getJurisdiction(),
				                 entity.getIdentificationNumber());
	}
	
}

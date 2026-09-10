package light.feather.service;

import java.lang.classfile.Superclass;
import java.util.List;
import java.util.Optional;

import light.feather.repository.EmployeeRepository;
import light.feather.repository.SupervisorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import light.feather.dto.EmployeeDto;
import light.feather.dto.SupervisorDto;
import light.feather.entity.Employee;
import light.feather.entity.Supervisor;
import light.feather.util.EntityDtoMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Value("${supervisor.uri}")
	String supervisorUri;
	
	@Autowired
	SupervisorRepository supervisorRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RestClient restClient;
	// WebClient webClient;

	@Override
	public List<String> getSupervisors() {		
		List<SupervisorDto> supervisorDtos = restClient
				                            .get()
                                            .uri(supervisorUri)
                                            .retrieve()
                                            // Use ParameterizedTypeReference to preserve generic type info at runtime
                                            .body(new ParameterizedTypeReference<List<SupervisorDto>>() {}); 
		
		for (SupervisorDto dto: supervisorDtos) {
			saveSupervisor(EntityDtoMapper.dtoToEntity(dto));
		}
		
		return supervisorDtos.stream()
				             .filter(supervisorDto->"u".equalsIgnoreCase(supervisorDto.getJurisdiction()))
				             .map(Object::toString)
				             .toList();
	}
	
	@Transactional
	private Supervisor saveSupervisor(Supervisor newEntity) {
		Supervisor supervisor = supervisorRepository.findByIdentificationNumber(newEntity.getIdentificationNumber());
		if (supervisor != null) { // update existing entity
			//supervisor.setId(newEntity.getId());
			supervisor.setPhone(newEntity.getPhone());
			supervisor.setJurisdiction(newEntity.getJurisdiction());
			supervisor.setIdentificationNumber(newEntity.getIdentificationNumber());
			supervisor.setFirstName(newEntity.getFirstName());
			supervisor.setLastName(newEntity.getLastName());
		} else { // create new entity
			supervisor = newEntity;
			supervisor.setId(null);  // let our db generate its own id
		}
		return supervisorRepository.save(supervisor);
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl::saveEmployee(EmployeeDto employeeDto)");
		Employee employee = EntityDtoMapper.dtoToEntity(employeeDto);
		Optional<Supervisor> optionalSupervisor = supervisorRepository.findById(employeeDto.getSupervisorId());
		if (optionalSupervisor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't locate supervisor for id : " + employeeDto.getSupervisorId());
		}
		employee.setSupervisor(optionalSupervisor.get());
		Employee newEmployee = employeeRepository.save(employee);
		return EntityDtoMapper.entityToDto(newEmployee);
	}

}

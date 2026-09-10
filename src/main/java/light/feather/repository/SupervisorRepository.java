package light.feather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import light.feather.entity.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer>  {
	Supervisor findByIdentificationNumber(String identificationNumber);
}

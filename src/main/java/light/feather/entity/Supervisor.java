package light.feather.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supervisor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supervisor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String phone;
	String firstName;
	String lastName;
	String jurisdiction;
	String identificationNumber;

	@OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(mappedBy = "supervisor", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Employee> employees = new ArrayList<>();
}

package light.feather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto {
	Integer id;
	String phone;
	String firstName;
	String lastName;
	String jurisdiction;
	String identificationNumber;
	
	public String toString() {
		return String.format("%s - %s, %s", jurisdiction, lastName, firstName);
	}
}

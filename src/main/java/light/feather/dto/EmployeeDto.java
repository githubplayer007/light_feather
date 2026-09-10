package light.feather.dto;

import light.feather.entity.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
   Integer id;
   String email;
   String firstName;
   String lastName;
   String phone;
   Integer supervisorId;
}

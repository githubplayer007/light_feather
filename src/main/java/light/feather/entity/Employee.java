package light.feather.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String email;
	String firstName;
	String lastName;
	String phone;
    /*********************************************************************************************
     * 
     * We can not map more than one field to the same column, unless we declare as the following
     *   @Column(name = "supervisor_id", insertable=false, updatable=false)
     *   @Column(name = "supervisor_id")
     *   Integer supervisorId;
     **********************************************************************************************/
	
	public Integer getSupervisorId() {
		return supervisor == null? 0 : supervisor.getId();
	}
	
	public void setSupervisorId(Integer newId) {
		if (supervisor != null) {
			supervisor.setId(newId);
		}
	}
	
	@ManyToOne
	//@JoinColumn(name = "supervisor_id", insertable=false, updatable=false)
	//@JoinColumn(name = "supervisor_id", nullable=false)
	@JoinColumn(name = "supervisor_id")
	private Supervisor supervisor;
}

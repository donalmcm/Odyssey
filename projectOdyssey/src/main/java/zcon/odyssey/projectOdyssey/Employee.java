package zcon.odyssey.projectOdyssey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    // private @Version @JsonIgnore Long version;
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Odyssey> mentor_odyssey;

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Odyssey> mentee_odyssey;

    @OneToOne
    @JoinColumn(name= "availability_id")
    @RestResource(path = "employeeAvailability", rel = "availability")
    private Availability availability;

    @OneToOne
    @JoinColumn(name= "employeeRoles_id")
    @RestResource(path = "employeeEmployeeRoles", rel = "employeeRoles")
    private EmployeeRoles employeeRoles;

    public Employee(){}

    public Employee(String firstName, String lastName, String email, Availability availability, EmployeeRoles employeeRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.availability = availability;
        this.employeeRoles = employeeRoles;
    }

    public long getId() {
        return id;
    }
}

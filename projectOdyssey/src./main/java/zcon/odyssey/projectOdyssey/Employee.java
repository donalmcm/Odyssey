package zcon.odyssey.projectOdyssey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@Entity
public class Employee {
    private @Id
    @GeneratedValue Long id;

    private String firstName;
    private String lastName;
    private String email;

    private @Version @JsonIgnore Long version;

    private Employee(){}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

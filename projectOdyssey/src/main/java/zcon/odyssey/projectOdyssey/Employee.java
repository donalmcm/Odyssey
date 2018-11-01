package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Employee {
    private @Id
    @GeneratedValue Long id;

    private String firstName;
    private String lastName;
    private String email;

    private Employee(){}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Odyssey> mentor_odyssey;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Odyssey> mentee_odyssey;

    public Employee(){}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

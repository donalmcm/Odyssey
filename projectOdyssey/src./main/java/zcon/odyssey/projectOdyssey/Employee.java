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

    @Column(nullable = false)
    private boolean isManager = false;

    @Column(nullable = false)
    private boolean isAdmin = false;

    @Column(nullable = false)
    private boolean isMentee = false;

    @Column(nullable = false)
    private boolean mentor = false;

    private @Version @JsonIgnore Long version;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Odyssey> mentor_odyssey;

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Odyssey> mentee_odyssey;

    @OneToOne
    @JoinColumn(name= "topic_id")
    @RestResource(path = "employeeTopic", rel = "topic")
    private Topic topic;

    @OneToOne
    @JoinColumn(name= "availability_id")
    @RestResource(path = "employeeAvailability", rel = "availability")
    private Availability availability;

    public Employee(){}

    public Employee(String firstName, String lastName, String email) { // availability
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void becomeMentor(Topic topic, Availability availability) {
        this.topic = topic;
        this.availability = availability;
        mentor = true;
    }

    public void becomeMentee() {
        isMentee = true;
    }
}

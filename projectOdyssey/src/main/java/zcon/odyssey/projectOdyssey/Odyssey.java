package zcon.odyssey.projectOdyssey;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Odyssey {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Employee mentee; // perform check?

    @Column(nullable = false)
    private Employee mentor; // perform check?

    @ManyToOne
    @JoinColumn(name = "odyssey")
    private Employee employee;

    @OneToMany(mappedBy = "odyssey_meeting")
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @RestResource(path = "odysseyTopic", rel = "topic")
    private Topic topic;


    public Odyssey(Employee mentee, Employee mentor, Topic topic) { // needs to take in two employee objects and a topic
        // mentee must have isMentee = true
        // mentor must have isMentor = true
        // an odyssey must have a topic
    }
}

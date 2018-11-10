package zcon.odyssey.projectOdyssey;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Odyssey {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_odyssey")
    private Employee mentor;

    @ManyToOne
    @JoinColumn(name = "mentee_odyssey")
    private Employee mentee;

    @OneToMany(mappedBy = "odyssey")
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @RestResource(path = "odysseyTopic", rel = "topic")
    private Topic topic;

    public Odyssey(){}

    public Odyssey(Employee mentee, Employee mentor, Topic topic) { // needs to take in two employee objects and a topic
        this.mentee = mentee; // must have isMentee = true
        this.mentor = mentor;// mentor must have isMentor = true
        this.topic = topic;// an odyssey must have a topic
    }

}

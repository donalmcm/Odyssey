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
    private long id;

    @ManyToOne
    @JoinColumn(name = "mentor_odyssey")
    private Employee mentor;

    @ManyToOne
    @JoinColumn(name = "mentee_odyssey")
    private Employee mentee;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "odyssey", cascade = CascadeType.ALL)
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @RestResource(path = "odysseyTopic", rel = "topic")
    private Topic topic;

    public Odyssey(){}

    public Odyssey(Employee mentor ,Employee mentee, Topic topic, List<OdysseyMeeting> odysseyMeetings) {
        this.mentor = mentor;// mentor must have isMentor = true
        this.mentee = mentee; // must have isMentee = true
        this.topic = topic; // an odyssey must have a topic
        this.odysseyMeetings = odysseyMeetings; // an odyssey will have a list of meetings
    }

}

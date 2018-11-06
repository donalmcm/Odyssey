package zcon.odyssey.projectOdyssey;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
public class Odyssey {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String mentee;

    @Column(nullable = false)
    private String mentor;

    @ManyToOne
    @JoinColumn(name = "odyssey")
    private Employee employee;

    @OneToMany(mappedBy = "odyssey_meeting")
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @RestResource(path = "odysseyTopic", rel = "topic")
    private Topic topic;


    public Odyssey(String mentee, String mentor) {
        this.mentee = mentee;
        this.mentor = mentor;
    }
}

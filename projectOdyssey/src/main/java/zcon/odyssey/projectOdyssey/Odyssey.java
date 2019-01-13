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

    @Column
    private boolean isActive = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "odyssey", cascade = CascadeType.ALL)
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @RestResource(path = "odysseyTopic", rel = "topic")
    private Topic topic;

    public Odyssey(){}

    public Odyssey(Employee mentor ,Employee mentee, Topic topic) {
        this.mentor = mentor;// mentor must have isMentor = true
        this.mentee = mentee; // must have isMentee = true
        this.topic = topic; // an odyssey must have a topic

        isActive = true;
    }

    // To be called after the last meeting of an odyssey
    public void completedOdyssey(Odyssey odyssey) {
        odyssey.isActive = false;
        odyssey.mentor.setMentor(false);
        odyssey.mentee.setMentee(false);
    }

    public int getPercentageCompleteOfOdyssey(Odyssey odyssey) {

        int noOfMeetings = odyssey.odysseyMeetings.size();
        int noOfCompletedMeetings = 0;

        for (OdysseyMeeting meeting : odyssey.odysseyMeetings) {
            if(meeting.getIsCompleted() == true) {
                noOfCompletedMeetings ++;
            }
        }
        int percentageComplete = (noOfCompletedMeetings/noOfMeetings) * 100;

        return percentageComplete;
    }

}

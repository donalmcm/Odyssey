package com.odyssey.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NamedQueries({ @NamedQuery(name = "Odyssey.findAllOdysseys", query = "select o from Odyssey o"),
        @NamedQuery(name = "Odyssey.findByOdysseyId", query = "select o from Odyssey o where o.id=:id"), })

@XmlRootElement
@Entity
public class Odyssey {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn
    private Employee mentor;

    @ManyToOne
    @JoinColumn
    private Employee mentee;

    @Column
    private boolean isActive = false;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<OdysseyMeeting> odysseyMeetings;

    @OneToOne
    @JoinColumn
    private Topic topic;

    public Odyssey(){}

    public Odyssey(Employee mentor ,Employee mentee, Topic topic) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.topic = topic;
        mentor.getMentorDuration(); // use the create a list of meetings
        mentor.getTopic(); // use to tell the odyssey what topic the odyssey is
        isActive = true;
    }


    // To be called after the last meeting of an odyssey
    public void completedOdyssey(Odyssey odyssey) {
        odyssey.isActive = false;
        odyssey.mentor.setMentorToFalse();
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

    public long getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }
}

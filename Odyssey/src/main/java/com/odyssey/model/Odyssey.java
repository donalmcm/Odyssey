package com.odyssey.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NamedQueries({ @NamedQuery(name = "Odyssey.findAllOdysseys", query = "select o from Odyssey o"),
        @NamedQuery(name = "Odyssey.findByOdysseyId", query = "select o from Odyssey o where o.id=:id"),
        @NamedQuery(name = "Odyssey.findOdysseysByMentor", query = "select o from Odyssey o where o.mentor=:mentor"),
        @NamedQuery(name = "Odyssey.findOdysseysByMentee", query = "select o from Odyssey o where o.mentee=:mentee"),
        @NamedQuery(name = "Odyssey.findOdysseysByTopic", query = "select o from Odyssey o where o.topic=:topic")})

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
        this.mentor = mentor;// mentor must have isMentor = true
        this.mentee = mentee; // must have isMentee = true
        this.topic = topic; // an odyssey must have a topic

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

    // Generated Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Employee getMentor() {
        return mentor;
    }
    public void setMentor(Employee mentor) {
        this.mentor = mentor;
    }
    public Employee getMentee() {
        return mentee;
    }
    public void setMentee(Employee mentee) {
        this.mentee = mentee;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public List<OdysseyMeeting> getOdysseyMeetings() {
        return odysseyMeetings;
    }
    public void setOdysseyMeetings(List<OdysseyMeeting> odysseyMeetings) {
        this.odysseyMeetings = odysseyMeetings;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public Topic getTopic() {
        return topic;
    }
}

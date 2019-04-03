package com.odyssey.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NamedQueries({ @NamedQuery(name = "Odyssey.findAllOdysseys", query = "select o from Odyssey o"),
        @NamedQuery(name = "Odyssey.findByOdysseyId", query = "select o from Odyssey o where o.id=:id"),
        @NamedQuery(name = "Odyssey.findOdysseysByEmployee", query = "select o from Odyssey o where o.mentor=:id or o.mentee=:id")})

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

    public Odyssey(Employee mentor ,Employee mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.topic = mentor.getTopic();
        isActive = true;
    }

    // To be called after the last meeting of an odyssey
    public void completedOdyssey() {
        isActive = false;
        mentor.setMentorToFalse();
        mentee.setMentee(false);
    }

    public void generateOdysseyMeetings(int odysseyDuration, String dayOfMeetings,int timeOfMeeting) {
        List<OdysseyMeeting> odysseyMeetings = new ArrayList<>();
        // to be set by employees
        int dayOfWeek=0,minuteOfMeeting = 0,secondOfMeeting = 0,weekLength = 7;
        String monday = "monday",tuesday = "tuesday",wednesday = "wednesday",thursday = "thursday", friday = "friday";

        // make enum?
        if(dayOfMeetings.equalsIgnoreCase(monday)) {
            dayOfWeek = 2;
        } else if(dayOfMeetings.equalsIgnoreCase(tuesday)) {
            dayOfWeek = 3;
        } else if(dayOfMeetings.equalsIgnoreCase(wednesday)) {
            dayOfWeek = 4;
        } else if(dayOfMeetings.equalsIgnoreCase(thursday)) {
            dayOfWeek = 5;
        } else if(dayOfMeetings.equalsIgnoreCase(friday)) {
            dayOfWeek = 6;
        }

        Calendar today = Calendar.getInstance();

        for(int i=0; i<odysseyDuration;i++) {
            Calendar temp = Calendar.getInstance();
            temp.set(Calendar.DAY_OF_WEEK,dayOfWeek);
            temp.set(Calendar.HOUR_OF_DAY,timeOfMeeting);
            temp.set(Calendar.MINUTE,minuteOfMeeting);
            temp.set(Calendar.SECOND,secondOfMeeting);
            temp.add(Calendar.DATE,weekLength);
            OdysseyMeeting newMeeting = new OdysseyMeeting(temp,this);
            odysseyMeetings.add(newMeeting);
            weekLength +=7;
        }
        this.odysseyMeetings =  odysseyMeetings;
    }

    public int getPercentageCompleteOfOdyssey() {

        double noOfCompletedMeetings = 0.0,noOfMeetings = odysseyMeetings.size();

        for (OdysseyMeeting meeting : odysseyMeetings) {
            if(meeting.getIsCompleted()) {
                noOfCompletedMeetings ++;
            }
        }
        double percentageComplete = (noOfCompletedMeetings/noOfMeetings) * 100;
        return (int)percentageComplete;
    }

    public long getId() {
        return id;
    }
    public Topic getTopic() {
        return topic;
    }
    public Employee getMentor() {
        return mentor;
    }
    public Employee getMentee() {
        return mentee;
    }
    public boolean isActive() {
        return isActive;
    }
    public List<OdysseyMeeting> getOdysseyMeetings() {
        return odysseyMeetings;
    }
}

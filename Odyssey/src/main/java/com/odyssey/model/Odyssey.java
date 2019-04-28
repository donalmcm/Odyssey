package com.odyssey.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Odyssey.findAllOdysseys", query = "select o from Odyssey o"),
        @NamedQuery(name = "Odyssey.findByOdysseyId", query = "select o from Odyssey o where o.id=:id"),
        @NamedQuery(name = "Odyssey.findTopicCountByOdyssey", query = "select o.topic.name,count(*) from Odyssey o group by o.topic.name"),
        @NamedQuery(name = "Odyssey.findOdysseysByEmployee", query = "select o from Odyssey o where o.mentor.id=:id or o.mentee.id=:id"),
        @NamedQuery(name = "Odyssey.findOdysseysByMentor", query = "select o from Odyssey o where o.mentor.id=:id"),
        @NamedQuery(name = "Odyssey.findOdysseysByMentee", query = "select o from Odyssey o where o.mentee.id=:id"),
})

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
    private boolean isComplete = false;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "odyssey")
    private List<OdysseyMeeting> odysseyMeetings;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "odysseyReview")
    private List<Review> odysseyReviews;

    @OneToOne
    @JoinColumn
    private Topic topic;

    public Odyssey() {
    }

    public Odyssey(Employee mentor, Employee mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.topic = mentor.getTopic();
        this.mentee.setMentee(true);
        this.mentor.setAwaitingMentee();
    }

    // To be called after the last meeting of an odyssey
    private void completedOdyssey() {
        mentor.setMentorToFalse();
        mentee.setMentee(false);
    }

    public void generateOdysseyMeetings(int odysseyDuration, String dayOfMeetings, int timeOfMeeting) {
        List<OdysseyMeeting> odysseyMeetings = new ArrayList<>();
        // to be set by employees
        int dayOfWeek = 0, minuteOfMeeting = 0, secondOfMeeting = 0, weekLength = 7;
        String monday = "monday", tuesday = "tuesday", wednesday = "wednesday", thursday = "thursday", friday = "friday";

        // make enum?
        if (dayOfMeetings.equalsIgnoreCase(monday)) {
            dayOfWeek = 2;
        } else if (dayOfMeetings.equalsIgnoreCase(tuesday)) {
            dayOfWeek = 3;
        } else if (dayOfMeetings.equalsIgnoreCase(wednesday)) {
            dayOfWeek = 4;
        } else if (dayOfMeetings.equalsIgnoreCase(thursday)) {
            dayOfWeek = 5;
        } else if (dayOfMeetings.equalsIgnoreCase(friday)) {
            dayOfWeek = 6;
        }

        for (int i = 0; i < odysseyDuration; i++) {
            Calendar temp = Calendar.getInstance();
            temp.set(Calendar.DAY_OF_WEEK, dayOfWeek);
            temp.set(Calendar.HOUR_OF_DAY, timeOfMeeting);
            temp.set(Calendar.MINUTE, minuteOfMeeting);
            temp.set(Calendar.SECOND, secondOfMeeting);
            temp.add(Calendar.DATE, weekLength);
            OdysseyMeeting newMeeting = new OdysseyMeeting(temp, this);
            odysseyMeetings.add(newMeeting);
            weekLength += 7;
        }
        this.odysseyMeetings = odysseyMeetings;
    }

    public void generateDefaultReviews() {
        List<Review> reviews = new ArrayList<>();

        Review mentorReview = new Review(0,0,0,0,0,null,true,false,false);
        Review menteeReview = new Review(0,0,0,0,0,null,false,true,false);

        reviews.add(mentorReview);
        reviews.add(menteeReview);

        this.odysseyReviews = reviews;
    }

    public int getPercentageCompleteOfOdyssey() {

        double noOfCompletedMeetings = 0.0, noOfMeetings = odysseyMeetings.size();

        for (OdysseyMeeting meeting : odysseyMeetings) {
            if (meeting.getIsCompleted()) {
                noOfCompletedMeetings++;
            }
        }
        double percentageComplete = (noOfCompletedMeetings / noOfMeetings) * 100;
        return (int) percentageComplete;
    }

    public String getOdysseyMeetingsCompleteVsOverall() {
        int noOfCompletedMeetings = 0, noOfMeetings = odysseyMeetings.size();
        for (OdysseyMeeting meeting : odysseyMeetings) {
            if (meeting.getIsCompleted()) {
                noOfCompletedMeetings++;
            }
        }
        String completedMeetings = String.valueOf(noOfCompletedMeetings);
        String numberOfMeetings = String.valueOf(noOfMeetings);

        return completedMeetings + "/" + numberOfMeetings;
    }

    public long getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public boolean isComplete() {
        if (isComplete) {
            return isComplete;
        } else {
            if (odysseyMeetings.get(odysseyMeetings.size() - 1).getIsCompleted()) {
                completedOdyssey();
                isComplete = true;
                return isComplete;
            } else {
                isComplete = false;
                return isComplete;
            }
        }
    }

    public Employee getMentor() {
        return mentor;
    }

    public Employee getMentee() {
        return mentee;
    }

    public List<OdysseyMeeting> getOdysseyMeetings() {
        return odysseyMeetings;
    }

    public List<Review> getOdysseyReviews() {
        return odysseyReviews;
    }
}

package com.odyssey.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.GregorianCalendar;

@XmlRootElement
@Entity
public class OdysseyMeeting {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)// could this field be empty for remote calls or should skype be the location?
    private String location;

    @Column(nullable = false)
    private Calendar date = new GregorianCalendar();

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn
    private Odyssey odyssey;

    public OdysseyMeeting(){};

    public OdysseyMeeting(Calendar date,Odyssey odyssey) {
        this.date = date;
        this.odyssey = odyssey;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getIsCompleted() {
        return completed;
    }
}

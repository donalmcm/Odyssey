package com.odyssey.model;

import com.HibernateUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.GregorianCalendar;

@NamedQueries({@NamedQuery(name = "OdysseyMeeting.findAllOdysseyMeetings", query = "select om from OdysseyMeeting om"),
        @NamedQuery(name = "OdysseyMeeting.findOdysseyMeetingById", query = "select om from OdysseyMeeting om where om.id=:id")})

@XmlRootElement
@Entity
public class OdysseyMeeting {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)// could this field be empty for remote calls or should skype be the location?
    private String location;

    @Column(nullable = false) //
    private Calendar date = new GregorianCalendar();

    @Column(nullable = false)
    private boolean completed;

    @Column
    private String meetingNote;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Odyssey odyssey;

    public OdysseyMeeting() {
    }

    public OdysseyMeeting(Calendar date, Odyssey odyssey) {
        this.date = date;
        this.odyssey = odyssey;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setMeetingNote(String meetingNote) {
        this.meetingNote = meetingNote;
    }

    public int getId() {
        return id;
    }

    public boolean getIsCompleted() {
        if (completed) {
            return completed;
        }

        Calendar currentTime = Calendar.getInstance();
        if (currentTime.after(date)) {
            setCompleted(true);
        }
        return completed;
    }

    public String getDate() {
        String dayOfMonth = String.valueOf(date.get(Calendar.DATE));
        String monthOfYear = String.valueOf(date.get(Calendar.MONTH) + 1);
        String year = String.valueOf(date.get(Calendar.YEAR));

        return dayOfMonth + "/" + monthOfYear + "/" + year;
    }

    public String getDay() {
        return getDayOfWeek(date.get(Calendar.DAY_OF_WEEK));
    }

    public String getMeetingNote() {
        return meetingNote;
    }

    public String getTime() {
        int timeOfDay = date.get(Calendar.AM_PM);
        int hour = date.get(Calendar.HOUR);
        String time = String.valueOf(hour);

        if (timeOfDay == 0) {
            return time + " AM";
        } else {
            if(hour == 0) {
                return "12 PM";
            } else {
                return time + " PM";
            }
        }
    }

    public static OdysseyMeeting getOdysseyMeetingById(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<OdysseyMeeting> query = session.createNamedQuery("OdysseyMeeting.findOdysseyMeetingById", OdysseyMeeting.class);
        query.setParameter("id", id);
        OdysseyMeeting odysseyMeeting = query.getSingleResult();

        session.getTransaction().commit();
        session.close();
        return odysseyMeeting;
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }
}

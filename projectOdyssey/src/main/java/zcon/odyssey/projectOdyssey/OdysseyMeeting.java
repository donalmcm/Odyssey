package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@Entity
public class OdysseyMeeting {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)// could this field be empty for remote calls or should skype be the location?
    private String location;

    @Column(nullable = false)
    private Calendar date = new GregorianCalendar();

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "odysseyMeeting")
    private Odyssey odyssey;

    public OdysseyMeeting(){};

    public OdysseyMeeting(String location, Calendar date, Odyssey odyssey) {
        this.location = location;
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

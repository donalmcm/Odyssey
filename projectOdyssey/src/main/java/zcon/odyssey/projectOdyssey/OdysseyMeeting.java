package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class OdysseyMeeting {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = false)// could this field be empty for remote calls or should skype be the location?
    private String location;

    @Column(nullable = false, unique = false)
    private Calendar date = new GregorianCalendar();


    @ManyToOne
    @JoinColumn(name = "odysseyMeeting")
    private Odyssey odyssey;

    public OdysseyMeeting(){};

    public OdysseyMeeting(String location, Calendar date, Odyssey odyssey) {
        this.location = location;
        this.date = date;
        this.odyssey = odyssey;
    }

    public List<OdysseyMeeting> createMeetings(int duration) {
        List<OdysseyMeeting> odysseyMeetingList = new ArrayList<>();

        for(int i=0; i < duration; i++) {
            odysseyMeetingList.add(new OdysseyMeeting("Dublin" ,new GregorianCalendar(2019, Calendar.NOVEMBER, (i+1)), odyssey));
        }

        return odysseyMeetingList;
    }
}

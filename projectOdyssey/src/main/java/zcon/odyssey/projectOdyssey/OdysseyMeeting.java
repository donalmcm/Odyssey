package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class OdysseyMeeting {

    @Id
    @GeneratedValue Long id;

    @Column(unique = true)// could this field be empty for remote calls or should skype be the location?
    private String location;

    // @Column(nullable = false)
    // private Date date = new Date(); use calender

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "odyssey_id")
    private Odyssey odyssey;

    public OdysseyMeeting(){};

    public OdysseyMeeting(String location/*, Date date*/) {
        this.location = location;
        // this.date = date;
    }
}

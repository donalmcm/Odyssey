package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class OdysseyMeeting {

    @Column // could this field be empty for remote calls or should skype be the location?
    private String location;

    @Column(nullable = false)
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "odyssey_id")
    private Odyssey odyssey;

    public OdysseyMeeting(String location, Date date) {
        this.location = location;
        this.date = date;
    }
}

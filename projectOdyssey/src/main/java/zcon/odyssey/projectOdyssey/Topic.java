package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String topic;

    @OneToOne(mappedBy = "topic")
    private Odyssey odyssey;

    public Topic(){};

    public Topic(String topic) {
        this.topic = topic;
    }
}

package zcon.odyssey.projectOdyssey;

import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String topic;

    @OneToOne(mappedBy = "topic")
    private Odyssey odyssey;

    public Topic(String topic) {
        this.topic = topic;
    }
}

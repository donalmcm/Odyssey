package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String topic;

    @OneToOne(mappedBy = "topic")
    private Odyssey odyssey;

    @OneToOne(mappedBy = "topic")
    private Employee employee;

    @OneToMany(mappedBy = "topic")
    private List<SubTopic> subTopics;

    public Topic(){};

    public Topic(String topic) {
        this.topic = topic;
    }
}

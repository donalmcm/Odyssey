package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SubTopic {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String subTopic;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public SubTopic(){};

    public SubTopic(Topic parentTopic, String subTopic) {
        this.topic = parentTopic;
        this.subTopic = subTopic;
    }
}

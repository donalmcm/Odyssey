package com.odyssey.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "Topic.findAll", query = "select t from Topic t"),
        @NamedQuery(name = "Topic.findById", query = "select t from Topic t where t.id=:id") })

@XmlRootElement
@Entity
public class Topic {

    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Odyssey odyssey;

    @OneToOne
    private Employee employee;

//    @OneToMany(mappedBy = "topic")
//    private List<SubTopic> subTopics;

    public Topic(){}

    public Topic(String topic) {
        this.name = topic;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

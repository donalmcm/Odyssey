package com.odyssey.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NamedQueries({ @NamedQuery(name = "Employee.findAllEmployees", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findById", query = "select e from Employee e where e.id=:id"),
        @NamedQuery(name="Employee.findMentors", query = "select e from Employee e where e.isMentor=true"),
        @NamedQuery(name="Employee.findMentees", query = "select e from Employee e where e.isMentee=true"),
        @NamedQuery(name="Employee.findMentorsByTopic", query = "select e from Employee e where e.isMentor=true and e.topic.name=:topic"),
        @NamedQuery(name="Employee.findAvailability", query ="select e.availability from Employee e where e.id=:id" )})

@XmlRootElement
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    @Expose
    @Column(nullable = false)
    private String firstName;

    @Expose
    @Column(nullable = false)
    private String lastName;

    @Expose
    @Column(nullable = false, unique = true)
    private String email;

    @Expose
    @Column
    private boolean isManager = false;

    @Expose
    @Column
    private boolean isAdmin = false;

    @Expose
    @Column
    private boolean isMentee = false;

    @Expose
    @Column
    private boolean isMentor = false;

    @Expose
    @Column
    private int mentorDuration = 0;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
    private List<Odyssey> mentorOdyssey;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mentee")
    private List<Odyssey> menteeOdyssey;

    @Expose
    @OneToOne
    private Topic topic;

    @OneToOne
    private Availability availability;

    public Employee(){}

    public Employee(String firstName, String lastName, String email) { // availability
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(String firstName, String lastName, String email, boolean isManager, boolean isAdmin,
                    boolean isMentee, boolean isMentor, int mentorDuration, Topic topic, Availability availability) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isManager = isManager;
        this.isAdmin = isAdmin;
        this.isMentee = isMentee;
        this.isMentor = isMentor;
        this.mentorDuration = mentorDuration;
        this.topic = topic;
        this.availability = availability;
    }

    public void becomeMentor(Topic topic, Availability availability, int mentorDuration) {
        this.topic = topic;
        this.availability = availability;
        this.mentorDuration = mentorDuration;
        isMentor = true;
    }

    public void becomeManager(List<Employee> employees) {
        // create relationship between a manager and their employees
        isManager = true;
    }

    // ------------- GETTERS AND SETTERS ----------------------------

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Availability getAvailability() {
        return availability;
    }
    public void setMentorToFalse() {
        isMentor = false;
    }
    public Topic getTopic() {
        return topic;
    }
    public int getId() {
        return id;
    }
    public boolean getMentorValue() {
        return isMentor;
    }
    public void setMentee(boolean mentee) {
        isMentee = mentee;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public boolean isManager() {
        return isManager;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public boolean isMentee() {
        return isMentee;
    }
    public boolean isMentor() {
        return isMentor;
    }
    public int getMentorDuration() {
        return mentorDuration;
    }
    public List<Odyssey> getMentorOdyssey() {
        return mentorOdyssey;
    }
    public List<Odyssey> getMenteeOdyssey() {
        return menteeOdyssey;
    }

}

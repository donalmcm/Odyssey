package com.odyssey.model;

import com.HibernateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Table;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NamedQueries({ @NamedQuery(name = "Employee.findAllEmployees", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findById", query = "select e from Employee e where e.id=:id"),
        @NamedQuery(name="Employee.findMentors", query = "select e from Employee e where e.isMentor=true"),
        @NamedQuery(name="Employee.findMentees", query = "select e from Employee e where e.isMentee=true"),
        @NamedQuery(name="Employee.findMentorsByTopic", query = "select e from Employee e where e.isMentor=true and e.topic.name=:topic"),
        @NamedQuery(name="Employee.findAvailability", query ="select e.availability from Employee e where e.id=:id" ),
        @NamedQuery(name="Employee.findByEmail",query = "select e from Employee e where e.email=:email")})

@XmlRootElement
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    // change to more secure option
    @Column
    @JsonIgnore
    private String password;

    @Column
    private boolean isManager = false;

    @Column
    private boolean isAdmin = false;

    @Column
    private boolean isMentee = false;

    @Column
    private boolean isMentor = false;

    @Column
    private int mentorDuration = 0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
    @JsonManagedReference
    private List<Odyssey> mentorOdyssey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mentee")
    @JsonManagedReference
    private List<Odyssey> menteeOdyssey;

    @OneToOne
    private Topic topic;

    @OneToOne
    private Availability availability;

    public Employee(){}

    public Employee(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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


    public static Employee getEmployeeByEmail(String email) {
        if(email == null) {
            return null;
        } else {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findByEmail", Employee.class);
            query.setParameter("email", email);
            Employee employee = query.getSingleResult();

            session.getTransaction().commit();
            session.close();
            return employee;
        }
    }

    // ------------- GETTERS AND SETTERS ----------------------------

    public String getPassword() {
        return password;
    }
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

}

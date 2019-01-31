package com.odyssey.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "Availability.findAllAvailabilities", query = "select a from Availability a")})

@XmlRootElement
@Entity
public class Availability {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private boolean monday = true;

    @Column
    private boolean tuesday = true;

    @Column
    private boolean wednesday = true;

    @Column
    private boolean thursday = true;

    @Column
    private boolean friday = true;

    @OneToOne(mappedBy = "availability")
    private Employee employee;

    public Availability(){};

    public Availability(boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public int getId() {
        return id;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }
}
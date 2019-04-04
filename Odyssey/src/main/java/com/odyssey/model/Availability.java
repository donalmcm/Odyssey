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

    // Monday slots
    @Column
    private boolean monday10 = true;
    @Column
    private boolean monday11 = true;
    @Column
    private boolean monday12 = true;
    @Column
    private boolean monday14 = true;
    @Column
    private boolean monday15 = true;
    @Column
    private boolean monday16 = true;

    // Tuesday slots
    @Column
    private boolean tuesday10 = true;
    @Column
    private boolean tuesday11 = true;
    @Column
    private boolean tuesday12= true;
    @Column
    private boolean tuesday14= true;
    @Column
    private boolean tuesday15= true;
    @Column
    private boolean tuesday16= true;

    // Wednesday slots
    @Column
    private boolean wednesday10 = true;
    @Column
    private boolean wednesday11 = true;
    @Column
    private boolean wednesday12 = true;
    @Column
    private boolean wednesday14 = true;
    @Column
    private boolean wednesday15 = true;
    @Column
    private boolean wednesday16 = true;

    // Thursday slots
    @Column
    private boolean thursday10 = true;
    @Column
    private boolean thursday11 = true;
    @Column
    private boolean thursday12 = true;
    @Column
    private boolean thursday14 = true;
    @Column
    private boolean thursday15 = true;
    @Column
    private boolean thursday16 = true;

    // Friday slots
    @Column
    private boolean friday10 = true;
    @Column
    private boolean friday11 = true;
    @Column
    private boolean friday12 = true;
    @Column
    private boolean friday14 = true;
    @Column
    private boolean friday15 = true;
    @Column
    private boolean friday16 = true;


    @OneToOne(mappedBy = "availability")
    private Employee employee;

    public Availability(){}

    public Availability(boolean monday10, boolean monday11, boolean monday12, boolean monday14, boolean monday15,
                        boolean monday16, boolean tuesday10, boolean tuesday11, boolean tuesday12, boolean tuesday14,
                        boolean tuesday15, boolean tuesday16, boolean wednesday10, boolean wednesday11,
                        boolean wednesday12, boolean wednesday14, boolean wednesday15, boolean wednesday16,
                        boolean thursday10, boolean thursday11, boolean thursday12, boolean thursday14,
                        boolean thursday15, boolean thursday16, boolean friday10, boolean friday11, boolean friday12,
                        boolean friday14, boolean friday15, boolean friday16) {
        this.monday10 = monday10;
        this.monday11 = monday11;
        this.monday12 = monday12;
        this.monday14 = monday14;
        this.monday15 = monday15;
        this.monday16 = monday16;
        this.tuesday10 = tuesday10;
        this.tuesday11 = tuesday11;
        this.tuesday12 = tuesday12;
        this.tuesday14 = tuesday14;
        this.tuesday15 = tuesday15;
        this.tuesday16 = tuesday16;
        this.wednesday10 = wednesday10;
        this.wednesday11 = wednesday11;
        this.wednesday12 = wednesday12;
        this.wednesday14 = wednesday14;
        this.wednesday15 = wednesday15;
        this.wednesday16 = wednesday16;
        this.thursday10 = thursday10;
        this.thursday11 = thursday11;
        this.thursday12 = thursday12;
        this.thursday14 = thursday14;
        this.thursday15 = thursday15;
        this.thursday16 = thursday16;
        this.friday10 = friday10;
        this.friday11 = friday11;
        this.friday12 = friday12;
        this.friday14 = friday14;
        this.friday15 = friday15;
        this.friday16 = friday16;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMonday10() {
        return monday10;
    }

    public void setMonday10(boolean monday10) {
        this.monday10 = monday10;
    }

    public boolean isMonday11() {
        return monday11;
    }

    public void setMonday11(boolean monday11) {
        this.monday11 = monday11;
    }

    public boolean isMonday12() {
        return monday12;
    }

    public void setMonday12(boolean monday12) {
        this.monday12 = monday12;
    }

    public boolean isMonday14() {
        return monday14;
    }

    public void setMonday14(boolean monday14) {
        this.monday14 = monday14;
    }

    public boolean isMonday15() {
        return monday15;
    }

    public void setMonday15(boolean monday15) {
        this.monday15 = monday15;
    }

    public boolean isMonday16() {
        return monday16;
    }

    public void setMonday16(boolean monday16) {
        this.monday16 = monday16;
    }

    public boolean isTuesday10() {
        return tuesday10;
    }

    public void setTuesday10(boolean tuesday10) {
        this.tuesday10 = tuesday10;
    }

    public boolean isTuesday11() {
        return tuesday11;
    }

    public void setTuesday11(boolean tuesday11) {
        this.tuesday11 = tuesday11;
    }

    public boolean isTuesday12() {
        return tuesday12;
    }

    public void setTuesday12(boolean tuesday12) {
        this.tuesday12 = tuesday12;
    }

    public boolean isTuesday14() {
        return tuesday14;
    }

    public void setTuesday14(boolean tuesday14) {
        this.tuesday14 = tuesday14;
    }

    public boolean isTuesday15() {
        return tuesday15;
    }

    public void setTuesday15(boolean tuesday15) {
        this.tuesday15 = tuesday15;
    }

    public boolean isTuesday16() {
        return tuesday16;
    }

    public void setTuesday16(boolean tuesday16) {
        this.tuesday16 = tuesday16;
    }

    public boolean isWednesday10() {
        return wednesday10;
    }

    public void setWednesday10(boolean wednesday10) {
        this.wednesday10 = wednesday10;
    }

    public boolean isWednesday11() {
        return wednesday11;
    }

    public void setWednesday11(boolean wednesday11) {
        this.wednesday11 = wednesday11;
    }

    public boolean isWednesday12() {
        return wednesday12;
    }

    public void setWednesday12(boolean wednesday12) {
        this.wednesday12 = wednesday12;
    }

    public boolean isWednesday14() {
        return wednesday14;
    }

    public void setWednesday14(boolean wednesday14) {
        this.wednesday14 = wednesday14;
    }

    public boolean isWednesday15() {
        return wednesday15;
    }

    public void setWednesday15(boolean wednesday15) {
        this.wednesday15 = wednesday15;
    }

    public boolean isWednesday16() {
        return wednesday16;
    }

    public void setWednesday16(boolean wednesday16) {
        this.wednesday16 = wednesday16;
    }

    public boolean isThursday10() {
        return thursday10;
    }

    public void setThursday10(boolean thursday10) {
        this.thursday10 = thursday10;
    }

    public boolean isThursday11() {
        return thursday11;
    }

    public void setThursday11(boolean thursday11) {
        this.thursday11 = thursday11;
    }

    public boolean isThursday12() {
        return thursday12;
    }

    public void setThursday12(boolean thursday12) {
        this.thursday12 = thursday12;
    }

    public boolean isThursday14() {
        return thursday14;
    }

    public void setThursday14(boolean thursday14) {
        this.thursday14 = thursday14;
    }

    public boolean isThursday15() {
        return thursday15;
    }

    public void setThursday15(boolean thursday15) {
        this.thursday15 = thursday15;
    }

    public boolean isThursday16() {
        return thursday16;
    }

    public void setThursday16(boolean thursday16) {
        this.thursday16 = thursday16;
    }

    public boolean isFriday10() {
        return friday10;
    }

    public void setFriday10(boolean friday10) {
        this.friday10 = friday10;
    }

    public boolean isFriday11() {
        return friday11;
    }

    public void setFriday11(boolean friday11) {
        this.friday11 = friday11;
    }

    public boolean isFriday12() {
        return friday12;
    }

    public void setFriday12(boolean friday12) {
        this.friday12 = friday12;
    }

    public boolean isFriday14() {
        return friday14;
    }

    public void setFriday14(boolean friday14) {
        this.friday14 = friday14;
    }

    public boolean isFriday15() {
        return friday15;
    }

    public void setFriday15(boolean friday15) {
        this.friday15 = friday15;
    }

    public boolean isFriday16() {
        return friday16;
    }

    public void setFriday16(boolean friday16) {
        this.friday16 = friday16;
    }
}

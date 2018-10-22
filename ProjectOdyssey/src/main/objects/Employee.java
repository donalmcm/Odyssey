package main.objects;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String name;
    private String title;
    private String email; //regex
    private boolean isMentor;
    private boolean isMentee;
    private boolean isManager;
    private boolean isAdmin;
    private List<SubTopic> skills = new ArrayList<>();
    private List<Employee> allEmployees = new ArrayList<>();
    //availability

    public Employee() {
        name = "";
        title = "";
        email = "";
    }

    public Employee(String name, String title, String email) {
        this.name = name;
        this.title = title;
        this.email = email;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SubTopic> getSkills() {
        return skills;
    }

    public void setSkills(List<SubTopic> skills) {
        this.skills = skills;
    }

    public boolean isMentor() {
        return isMentor;
    }

    public void setMentor(boolean mentor) {
        isMentor = mentor;
    }

    public boolean isMentee() {
        return isMentee;
    }

    public void setMentee(boolean mentee) {
        isMentee = mentee;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }
}

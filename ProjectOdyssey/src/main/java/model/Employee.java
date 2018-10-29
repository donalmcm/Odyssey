package main.objects;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
    private String name;

    @Column(name = "title")
	private String title;

	@Column(name = "email")
	private String email;

	@Column(name = "isMentor")
    private boolean isMentor;

	@Column(name = "isMentee")
	private boolean isMentee;

	@Column(name = "isManager")
	private boolean isManager;

	@Column(name = "isAdmin")
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

    public long getId() {
        return id;
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

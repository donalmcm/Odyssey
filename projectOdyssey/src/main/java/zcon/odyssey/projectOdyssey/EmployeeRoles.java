package zcon.odyssey.projectOdyssey;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmployeeRoles {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private boolean isManager = false;

    @Column
    private boolean isAdmin = false;

    @OneToOne(mappedBy = "employeeRoles")
    private Employee employee;

    public EmployeeRoles(){};

    public EmployeeRoles(boolean isManager, boolean isAdmin) {
        this.isManager = isManager;
        this.isAdmin = isAdmin;
    }

}

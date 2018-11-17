package zcon.odyssey.projectOdyssey;



import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

package main.java.repo;

import org.springframework.data.repository.CrudRepository;
import main.java.model.Employee;

public interface EmployeeRepository extends CrudRespository<main.objects.Employee, Long> {
}

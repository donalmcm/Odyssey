package main.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.java.model.Employee;
import main.java.repo.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        System.out.println("Get all Employees...");

        List<Employee> list = new ArrayList<>();
        Iterable<Employee> customers = employeeRepository.findAll();
        // change from customers
        customers.forEach(list::add);
        return list;
    }

    @PostMapping("/employees/create")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        System.out.println("Create Employee: " + employee.getTitle() + "...");

        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getBook(@PathVariable("id") Long id) {
        System.out.println("Get Employee by id...");

        Optional<Employee> employeeData = employeeRepository.findById(id);
        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateBook(@PathVariable("id") Long id, @RequestBody Employee employee) {
        System.out.println("Update Employee with ID = " + id + "...");

        Optional<Employee> employeeData = employeeRepository.findById(id);
        if (employeeData.isPresent()) {
            Employee savedEmployee = employeeData.get();
            savedEmployee.setName(employee.getName());
            savedEmployee.setTitle(employee.getTitle());
            savedEmployee.setEmail(employee.getEmail());

            Employee updatedEmployee = employeeRepository.save(savedEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        System.out.println("Delete Employee with ID = " + id + "...");

        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>("Employee has been deleted!", HttpStatus.OK);
    }

}

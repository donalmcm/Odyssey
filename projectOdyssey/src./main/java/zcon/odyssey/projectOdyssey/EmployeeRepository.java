package zcon.odyssey.projectOdyssey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //@Async
    List<Employee> findByMentorIsTrueAndTopic(Topic topic); // search for mentors using topic

    //@Async
    List<Employee> findByMentorIsTrue(); // will return a list of Employees that are currently mentors

    List<Employee> findByMentorIsTrueAndTopicAndAvailability(Topic topic, Availability availability); // will search for a mentor based on the topic and their availability

}

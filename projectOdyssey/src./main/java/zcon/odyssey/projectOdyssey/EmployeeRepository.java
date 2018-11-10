package zcon.odyssey.projectOdyssey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional// (readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

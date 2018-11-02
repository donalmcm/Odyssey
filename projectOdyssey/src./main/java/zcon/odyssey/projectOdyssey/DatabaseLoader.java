package zcon.odyssey.projectOdyssey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository repository;

    @Autowired
    public DatabaseLoader(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Employee("Donal", "McManus","donal.mcm@gmail.com"));
        this.repository.save(new Employee("Ciara", "Walsh","ciara.walsh@gmail.com"));
        this.repository.save(new Employee("Ian", "Doyle","ian.doyle@gmail.com"));
        this.repository.save(new Employee("Gareth", "McGrath","gareth.mcgrath@gmail.com"));
    }
}

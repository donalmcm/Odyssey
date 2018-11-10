package zcon.odyssey.projectOdyssey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final OdysseyRepository odysseyRepository;
    private final TopicRepository topicRepository;
    private final OdysseyMeetingRepository odysseyMeetingRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository, OdysseyRepository odysseyRepository,
                          TopicRepository topicRepository, OdysseyMeetingRepository odysseyMeetingRepository) {
        this.employeeRepository = employeeRepository;
        this.odysseyRepository = odysseyRepository;
        this.topicRepository = topicRepository;
        this.odysseyMeetingRepository = odysseyMeetingRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.employeeRepository.save(new Employee("Donal", "McManus","donal.mcm@gmail.com"));
        this.employeeRepository.save(new Employee("Ciara", "Walsh","ciara.walsh@gmail.com"));
        this.employeeRepository.save(new Employee("Ian", "Doyle","ian.doyle@gmail.com"));
        this.employeeRepository.save(new Employee("Gareth", "McGrath","gareth.mcgrath@gmail.com"));

        this.topicRepository.save(new Topic("Java"));

        this.odysseyRepository.save(new Odyssey(new Employee("John", "Walker", "jwalker@gmail.com"),
                                    new Employee("John", "Walker", "john@gmail.com"),
                                    new Topic("Java")));

        this.odysseyMeetingRepository.save(new OdysseyMeeting("Hill of Tara"));


    }
}

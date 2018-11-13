package zcon.odyssey.projectOdyssey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List odyssey1Meetings = new ArrayList();
        Employee donal = new Employee("Donal", "McManus","donal@odyssey.com");
        Employee ian = new Employee("Ian", "Doyle","ian@odyssey.com");
        Topic java = new Topic("Java");
        Topic git = new Topic("Git");
        OdysseyMeeting meeting1 = new OdysseyMeeting("Hill of Tara");
        OdysseyMeeting meeting2 = new OdysseyMeeting("Lambay");
        odyssey1Meetings.add(meeting1);
        odyssey1Meetings.add(meeting2);
        Odyssey odyssey1 = new Odyssey(ian ,donal, java, odyssey1Meetings);


        this.employeeRepository.save(donal);
        this.employeeRepository.save(ian);

        this.topicRepository.save(java);

        this.odysseyMeetingRepository.save(meeting1);
        this.odysseyMeetingRepository.save(meeting2);

        this.odysseyRepository.save(odyssey1);
    }
}

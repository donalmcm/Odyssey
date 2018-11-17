package zcon.odyssey.projectOdyssey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

        List<OdysseyMeeting> odyssey1Meetings = new ArrayList<OdysseyMeeting>();

        Employee donal = new Employee("Donal", "McManus","donal@odyssey.com");
        Employee ian = new Employee("Ian", "Doyle","ian@odyssey.com");

        Topic java = new Topic("Java");
        Topic git = new Topic("Git");

        Calendar meeting1date = new GregorianCalendar(2018,Calendar.NOVEMBER, 20);
        Calendar meeting2date = new GregorianCalendar(2018, Calendar.DECEMBER, 22);

        OdysseyMeeting meeting1 = new OdysseyMeeting("Hill of Tara",meeting1date);
        OdysseyMeeting meeting2 = new OdysseyMeeting("Lambay", meeting2date);

        odyssey1Meetings.add(meeting1);
        odyssey1Meetings.add(meeting2);

        Odyssey odyssey1 = new Odyssey(ian ,donal, java, odyssey1Meetings);


        this.employeeRepository.save(donal);
        this.employeeRepository.save(ian);

        this.topicRepository.save(java);

        this.odysseyMeetingRepository.save(meeting1);
        this.odysseyMeetingRepository.save(meeting2);

        this.odysseyRepository.save(odyssey1);

        this.topicRepository.save(git);
    }
}

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
    private final SubTopicRepository subTopicRepository;
    private final OdysseyMeetingRepository odysseyMeetingRepository;
    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository, OdysseyRepository odysseyRepository,
                          TopicRepository topicRepository, OdysseyMeetingRepository odysseyMeetingRepository,
                          AvailabilityRepository availabilityRepository,
                          SubTopicRepository subTopicRepository) {
        this.employeeRepository = employeeRepository;
        this.odysseyRepository = odysseyRepository;
        this.topicRepository = topicRepository;
        this.subTopicRepository = subTopicRepository;
        this.odysseyMeetingRepository = odysseyMeetingRepository;
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<OdysseyMeeting> odyssey1Meetings = new ArrayList<OdysseyMeeting>();

        // Creating and saving availabilities
        Availability availability = new Availability();
        Availability noFridays = new Availability(true,true,true,true,false);
        this.availabilityRepository.save(availability);
        this.availabilityRepository.save(noFridays);

        // Creating and saving Employees
        Employee donal = new Employee("Donal", "McManus","donal@odyssey.com");
        Employee ian = new Employee("Ian", "Doyle","ian@odyssey.com");
        Employee joe = new Employee("Joe","Bloggs","joe@bloggs");
        Employee mary = new Employee("Mary","Bloggs","mary@bloggs");

        this.employeeRepository.save(donal);
        this.employeeRepository.save(ian);
        this.employeeRepository.save(joe);
        this.employeeRepository.save(mary);

        // Creating and saving Topic's
        Topic java = new Topic("Java");
        Topic git = new Topic("Git");
        this.topicRepository.save(java);
        this.topicRepository.save(git);

        // Creating subTopic's from Topics above
        SubTopic jpa = new SubTopic(java,"JPA");
        SubTopic branching = new SubTopic(git, "branching");
        this.subTopicRepository.save(jpa);
        this.subTopicRepository.save(branching);

        // Creating an Odyssey from previously saved objects
        Odyssey odyssey1 = new Odyssey(ian ,donal, java, odyssey1Meetings);


        // Creating Odyssey meetings with a date and location
        Calendar meeting1date = new GregorianCalendar(2018,Calendar.NOVEMBER, 20);
        Calendar meeting2date = new GregorianCalendar(2018, Calendar.DECEMBER, 22);
        OdysseyMeeting meeting1 = new OdysseyMeeting("Hill of Tara",meeting1date, odyssey1);
        OdysseyMeeting meeting2 = new OdysseyMeeting("Lambay", meeting2date, odyssey1);
        odyssey1Meetings.add(meeting1);
        odyssey1Meetings.add(meeting2);

        this.odysseyRepository.save(odyssey1);

    }
}
